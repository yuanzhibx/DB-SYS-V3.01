package com.cy.pj.common.aspect;

import com.cy.pj.common.annotation.RequiredLog;
import com.cy.pj.common.utils.IPUtils;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * AOP 编程入门
 * 定义切面对象类型
 * <p>
 * 特点:
 * 1. 使用 @Aspect 注解描述该类
 * 2. 切面内部包含切入点和通知的定义
 * 2.1. 通过 @Pointcut 注解定义切入点(通常对应某个类或多个类中方法的集合)
 * 2.2. 通过 @Around 等注解的方法为通知方法(方法内容要实现扩展业务的织入)
 *
 * @author Yuanzhibx
 * @Date 2020-06-20
 */
//@Order(1)
@Aspect
@Slf4j
@Component
public class SysLogAspect {

    //bean(bean 名称) 为一个切入点表达式
//    @Pointcut("bean(sysUserServiceImpl)")
    @Pointcut("@annotation(com.cy.pj.common.annotation.RequiredLog)")
    public void doLogPointCut() {

    }

    /**
     * 环绕通知方法(可以在目标方法执行之前 之后添加拓展业务逻辑)
     *
     * @param jp 连接点(封装了切入点中某个正在执行的方法信息)
     * @return
     * @throws Throwable
     */
    @Around("doLogPointCut()")
    public Object doAround(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("SysLogAspect.doAround");
        long start = System.currentTimeMillis();
        log.info("start {}", start);
        try {
            //调用逻辑 -> 本类中其他通知 --> 其他切面 --> 目标方法
            Object result = jp.proceed();
            long end = System.currentTimeMillis();
            log.info("end {}", end);
            //记录用户的正常行为信息, 基于此方法将用户行为信息写到数据库中
            saveLog(jp, (end - start));
            return result;
        } catch (Throwable e) {
            log.error("error {}", e.getMessage());
            throw e;
        }
    }

    @Autowired
    private SysLogService sysLogService;

    /**
     * 获取用户行为信息并进行记录
     * (谁在什么时间,执行了什么操作,访问了什么方法,传递了什么参数,..)
     * @param jp
     * @param time
     * @throws Exception
     */
    private void saveLog(ProceedingJoinPoint jp, long time) throws Exception {
        /*
            1. 获取用户行为信息
         */
        //1.1. 获取 ip 地址
        String ip = IPUtils.getIpAddr();
        //1.2. 获取登录用户名
        //TODO 做完登陆之后获取登录的用户名
        String username = "Yuanzhibx";
        //1.3. 获取目标方法上 requiredLog 注解指定的操作名
        //1.3.1 获取目标方法对象
        //1.3.1.1. 获取目标对象类型
        Class<?> targetClass = jp.getTarget().getClass();
        //1.3.1.2. 获取目标类中目标方法
        MethodSignature ms = (MethodSignature) jp.getSignature();
//        System.out.println("ms.getMethod() = " + ms.getMethod());
        Method targetMethod = targetClass.getDeclaredMethod(ms.getName(), ms.getParameterTypes());
        //1.3.2. 获取目标方法对象上的 RequiredLog 注解
        RequiredLog requiredLog = targetMethod.getAnnotation(RequiredLog.class);
        //1.3.3. 获取注解中指定的操作名
        String operation = null;
        //当切入点为注解表达式, 此语句可以不进行判断
        if (requiredLog != null) {
            operation = requiredLog.operation();
        }
        //1.4. 获取目标方法的类全名以及方法名
        String method = targetClass.getName() + "." + targetMethod.getName();
        //1.4. 获取执行方法时传入的参数
        String params = new ObjectMapper().writeValueAsString(jp.getArgs());
        /*
            2. 对用户行为进行封装
         */
        SysLog userLog = new SysLog();
        userLog.setIp(ip);
        userLog.setUsername(username);
        userLog.setOperation(operation);
        userLog.setMethod(method);
        userLog.setParams(params);
        userLog.setTime(time);
        userLog.setCreatedTime(new Date());
        //3. 将用户行为信息写入到数据库
//        new Thread() {
//            public void run() {
                sysLogService.saveObject(userLog);
//            }
//        }.start();
    }

}
