package com.bytelion.springbootlogback.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 太傅
 * @version V1.0
 * @package com.bytelion.springbootlogback.annotation
 * @description 切面日志记录注解
 * @date: Created in 2020/9/29 15:08
 * @copyright Copyright (c) 2020/9/29
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogRecord {
    String value() default "";
}
