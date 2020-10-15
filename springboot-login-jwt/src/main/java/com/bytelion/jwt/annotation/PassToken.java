package com.bytelion.jwt.annotation;

import java.lang.annotation.*;

/**
 * 用来跳过验证的PassToken
 * @author de'l'l
 */

@Inherited//说明子类可以继承父类中的该注解
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassToken {

    boolean required() default true;
}