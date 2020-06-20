package com.cy.pj.common.aspect;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 定义切面对象类型
 *
 * 特点:
 * 1. 使用 @Aspect 注解描述该类
 * 2. 切面内部包含切入点和通知的定义
 * 2.1. 通过 @Pointcut 注解定义切入点(通常对应某个类或多个类中方法的集合)
 * 2.2. 通过 @Around 等注解的方法为通知方法(方法内容要实现扩展业务的织入)
 * @author Yuanzhibx
 * @Date 2020-06-20
 */
@Aspect
@Slf4j
@Component
public class SysLogAspect {

    @Pointcut("bean(sysUserServiceImpl)")//bean(bean 名称) 为一个切入点表达式
    public void logPointCut() {

    }

    /**
     * 环绕通知方法(可以在目标方法执行之前 之后添加拓展业务逻辑)
     * @param jp 连接点(封装了切入点中某个正在执行的方法信息)
     * @return
     * @throws Throwable
     */
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        try {
            log.info("start:" + System.currentTimeMillis());
            //调用下一个切面方法或目标方法
            //调用逻辑 -> 本类中其他通知 --> 其他切面 --> 目标方法
            Object result = jp.proceed();
            log.info("end:" + System.currentTimeMillis());
            return result;
        } catch (Throwable e) {
            log.error("error" + e.getMessage());
            throw e;
        }
    }

}
