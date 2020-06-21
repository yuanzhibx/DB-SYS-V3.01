package com.cy.pj.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 通过如下切面的通知方法, 实现异常日志信息的记录
 *
 * @author Yuanzhibx
 * @Date 2020-06-20
 */
@Slf4j
@Aspect
@Component
public class SysExceptionLogAspect {

    /*
        通过如下异常方法记录异常日志, 但异常还要抛出
        所以在 afterThrowing 注解中要添加 throwing 属性,他的值为方法参数 e 的名字
     */

    @AfterThrowing(pointcut = "bean(*ServiceImpl)", throwing = "e")
    public void doHandleException(JoinPoint jp, Throwable e) {
        MethodSignature ms = (MethodSignature) jp.getSignature();
        log.error("{}'exception msg is {}", ms.getName(), e.getMessage());
    }
}
