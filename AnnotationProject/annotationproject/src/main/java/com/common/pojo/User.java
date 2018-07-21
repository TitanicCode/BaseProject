package com.common.pojo;

import com.common.annotation.Value;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/7/17.
 */
public class User {
    @Value("xiaohua")
    private String name;
    @Value("123456")
    private String password;

    private String username;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    @Value("wangwangwang")
    public void setUsername(String username) {
        this.username = username;
    }

    public String toString(){
        Class<? extends User> aClass = this.getClass();
        //获得所有申明的字段
        Field[] declaredFields = aClass.getDeclaredFields();
        StringBuffer stringBuffer=new StringBuffer();
        for (Field field:declaredFields) {
            String name = field.getName();
            stringBuffer.append(name);
            stringBuffer.append(":=");
            try {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(name, aClass);
                Method readMethod = propertyDescriptor.getReadMethod();
                if (readMethod != null) {
                    //这里的this是一个实例化对象，所以不能填aClass，aClass是个类对象
                    Object invoke = readMethod.invoke(this);
                    String s = invoke == null ? null : invoke.toString();
                    stringBuffer.append(s);
                    stringBuffer.append("     ");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IntrospectionException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }
}
