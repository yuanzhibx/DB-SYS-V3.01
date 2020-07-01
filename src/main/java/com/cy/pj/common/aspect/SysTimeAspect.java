package com.cy.pj.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 切面代理对象
 * AOP 通知执行顺序
 *
 * @author Yuanzhibx
 * @Date 2020-06-20
 */
@Order(2)
@Aspect
@Component
public class SysTimeAspect {

    @Pointcut("bean(sysUserServiceImpl)")
    public void doTime() {

    }

    @Before("doTime()")
    public void doBefore() {
        System.out.println("SysTimeAspect.doBefore");
    }

    @After("doTime()")
    public void doAfter() {
        System.out.println("SysTimeAspect.doAfter");
    }

    @AfterReturning("doTime()")
    public void doAfterReturning() {
        System.out.println("SysTimeAspect.doAfterReturning");
    }

    @AfterThrowing("doTime()")
    public void doAfterThrowing() {
        System.out.println("SysTimeAspect.doAfterThrowing");
    }

    /**
     * 目标方法
     *
     * @param jp
     * @return
     * @throws Throwable
     */
    @Around("doTime()")
    public Object doAround(ProceedingJoinPoint jp) throws Throwable {
        try {
            System.out.println("SysTimeAspect.doAround.before");
            Object result = jp.proceed();
            System.out.println("doAround.after");
            return result;
        } catch (Throwable throwable) {
            System.out.println(throwable.getMessage());
            throw throwable;
        }
    }

}
