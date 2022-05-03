package com.annamaple.annotation.action;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xionglei
 * @create 2022-04-26 11:21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Action {

    String value();
    
    String description() default "";

    ActionRequestMethod[] method() default {ActionRequestMethod.GET, ActionRequestMethod.POST};
}
