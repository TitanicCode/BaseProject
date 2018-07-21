package com.common.redis.client;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2018/7/16.
 */

public interface JedisInterface {
    Jedis getJedis();
    void set(String key,String value,Jedis jedis);
    String get(String key,Jedis jedis);
    void del(String key,Jedis jedis);
    void hSet(String key,Object o,Jedis jedis)  throws  Exception;
    void hSet(String key,String filedName,String value,Jedis jedis);
    Long incr(String key,Jedis jedis);
    String hGet(String key,String field,Jedis jedis);
}
