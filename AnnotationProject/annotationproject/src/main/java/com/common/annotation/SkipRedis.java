package com.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2018/7/26.
 */
//生命周期,RUNTIME代表这个注解会一直存在到程序运行 SOURCE代表只在源码存在,编译成class后会被jvm擦除//ClASS代表保存带字节码中,一旦运行的时候会被jvm擦除
@Retention(RetentionPolicy.RUNTIME)
//声明使用范围为变量
@Target(ElementType.FIELD)
public @interface SkipRedis {
}
