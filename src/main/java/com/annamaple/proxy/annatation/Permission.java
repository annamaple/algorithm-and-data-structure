package com.annamaple.proxy.annatation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解 用户动态代理
 *
 * @author xionglei
 * @create 2022-04-19 9:59
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Permission {

    /**
     * value 1 表示需要生成代理类执行
     */
    int value() default 1;
}
