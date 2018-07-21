package com.common.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/7/17.
 */
public class AnnotationUtil {

    //解析执行目标上面的注解,然后赋值
    public static void fillData(Object o) throws IllegalAccessException, InvocationTargetException {
        //先想想,这个注解是在什么地方使用的
        //获取到指定的地方
        Class<?> aClass = o.getClass();

        //解析执行变量属性的注解
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field:declaredFields) {
            Value annotation = field.getAnnotation(Value.class);
            if (annotation!=null){
                String value = annotation.value();
                field.setAccessible(true);
                field.set(o,value);
            }
        }

        //解析执行方法的注解
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method method:declaredMethods) {
            Value declaredAnnotation = method.getDeclaredAnnotation(Value.class);
            if (declaredAnnotation!=null){
                String value = declaredAnnotation.value();
                method.setAccessible(true);
                method.invoke(o,value);
            }
        }
    }
}
