package com.blingabc.auto.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-23:10:34
 * Modify date: 2019-09-23:10:34
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Operation {
    String value() default "";
}
