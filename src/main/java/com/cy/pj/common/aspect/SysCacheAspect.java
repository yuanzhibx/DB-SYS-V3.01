package com.cy.pj.common.aspect;

import com.cy.pj.common.cache.DefaultMapCache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Yuanzhibx
 * @Date 2020-06-22
 */
@Component
@Aspect
public class SysCacheAspect {

    @Autowired
    private DefaultMapCache mapCache;

    /**
     * 定义切入点:
     * 由 RequiredCache 注解描述的方法为切入点方法
     */
    @Pointcut("@annotation(com.cy.pj.common.annotation.RequiredCache)")
    public void doCache() {

    }

    @Pointcut("@annotation(com.cy.pj.common.annotation.ClearCache)")
    public void doClear() {

    }

    @AfterReturning("doClear()")
    public void doAfterReturning() {
        mapCache.clear();
    }

    @Around("doCache()")
    public Object doAround(ProceedingJoinPoint jp) throws Throwable {
        Object object = mapCache.getObject("deptCache");
        if (object != null) {
            return object;
        }
        //目标方法的执行结果会赋予 result
        Object result = jp.proceed();
        mapCache.putObject("deptCache", result);
        return result;
    }

}
