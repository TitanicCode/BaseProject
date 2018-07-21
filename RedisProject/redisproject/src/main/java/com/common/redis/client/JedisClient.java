package com.common.redis.client;

import com.common.annotation.SkipRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/7/16.
 */
@Component
public class JedisClient implements JedisInterface{
    @Autowired
    private JedisPool jedisPool;
    @Override
    public Jedis getJedis() {
        Jedis resource = jedisPool.getResource();
        resource.auth("redis001");
        return resource;
    }


    @Override
    public void set(String key, String value,Jedis jedis) {
        jedis.set(key, value);
    }

    @Override
    public String get(String key,Jedis jedis) {

        return jedis.get(key);
    }

    @Override
    public void del(String key,Jedis jedis) {
        jedis.del(key);
    }

    @Override
    public void hSet(String key, Object o,Jedis jedis) throws  Exception {
        //根据反射获取对象的类
        Class clazz = o.getClass();
        //获取所有的变量，getDeclaredFields方法也可以获取私有变量
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            //先判断上面有没有注解,如果没有,则继续.如果有就跳过
            //注解是先定义好功能,然后使用,而不是随便写了一个东西,如何实现
            SkipRedis annotation = field.getAnnotation(SkipRedis.class);
            if (annotation != null) {//包含特定注解的变量不会被写到redis中
                continue;
            }
            String name = field.getName();//变量名
            //获取o对象中field变量的值
            //field.get(o);
            //设置可以获取私有变量的值
            //field.setAccessible(true);
            // Object o1 = field.get(o);

            PropertyDescriptor descriptor=new PropertyDescriptor(name,clazz);

            if (descriptor != null) {
                Method readMethod = descriptor.getReadMethod();//获取get方法
                if (readMethod != null) {
                    Object result = readMethod.invoke(o);
                    if (result != null) {

                        hSet(key,name,result.toString(),jedis);
                    }
                }
            }
        }
    }

    @Override
    public void hSet(String key, String filedName, String value,Jedis jedis) {
        jedis.hset(key,filedName,value);
    }

    @Override
    public Long incr(String key, Jedis jedis) {
        return jedis.incr(key);
    }

    @Override
    public String hGet(String key, String field, Jedis jedis) {
        return jedis.hget(key,field);
    }
}
