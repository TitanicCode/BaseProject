package com.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2018/7/16.
 */
//声明使用范围为变量和方法
@Target({ElementType.FIELD,ElementType.METHOD})
//生命周期,
//RUNTIME代表这个注解会一直存在到程序运行
//SOURCE代表只在源码存在,编译成class后会被jvm擦除
//ClASS代表保存带字节码中,一旦运行的时候会被jvm擦除
@Retention(RetentionPolicy.RUNTIME)
public @interface Value {
    //注解的默认属性名,我们在使用注解的时候如果想直接写值,而不需要写name,那么代表当前注解中必须有一个value()的方法
    //如果没有制定default 代表必须赋值
    String value();
}
