package com.cy.pj.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * AOP 通知执行顺序
 *
 * @author Yuanzhibx
 * @Date 2020-06-20
 */
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
            System.out.println("doAround.before");
            Object proceed = jp.proceed();
            System.out.println("doAround.after");
            return proceed;
        } catch (Throwable throwable) {
            System.out.println(throwable.getMessage());
            throw throwable;
        }
    }

}
