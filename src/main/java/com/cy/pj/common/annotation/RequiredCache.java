package com.cy.pj.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解, 一个特殊的类, 所有注解都默认继承 Annotation 接口(is a)
 *
 * @Target(ElementType.METHOD) 定义该注解可以描述的对象 (方法)
 * @Retention(RetentionPolicy.RUNTIME) 定义该注解何时有效 (运行时)
 *
 * @author Yuanzhibx
 * @Date 2020-06-22
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredCache {

    String value() default "";

}
