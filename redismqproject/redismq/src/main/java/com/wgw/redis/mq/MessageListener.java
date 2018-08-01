package com.wgw.redis.mq;



import org.springframework.stereotype.Component;

/**
 * Created by jackiechan on 18-7-18/上午9:31
 */
@Component(value = "messageListener")
public class MessageListener {

    public void onMessage(String message) {

        System.out.println(message);
        //TODO 注意更新数据
        //解析发送过来的数据

        //按照预先预定好的格式,进行数据的操作

        //比如获取到type是2的情况下就查询数据库,然后更新到缓存

        //如果获取到的type是1的情况下,我们就直接将数据更新到redis(需要提前转成对象) 更新到redis的时候注意空的数据不要更新进去

        //更新redis

        //更新数据库之类的
    }
}
