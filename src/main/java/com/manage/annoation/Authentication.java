package com.manage.annoation;


import java.lang.annotation.*;

/**
 * 登录验证注解
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authentication {
}
