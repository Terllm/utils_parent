package com.terllm.jwt.annotatio;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname IsAuthentication
 * @Description 是否需要认证注解
 * @Date 2020/9/18 16:22
 * @Created by Terllm
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsAuthentication {


    boolean value() default false;


}
