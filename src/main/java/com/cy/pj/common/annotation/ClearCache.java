package com.cy.pj.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Yuanzhibx
 * @Date 2020-06-22
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClearCache {

    //将来可能不同模块会有不同cache, 基于key先获取cache对象
    String key() default "";

}
