package com.wgw.redis.mq;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;

/**
 * Created by jackiechan on 18-7-18/上午9:35
 */
public class TestMain {
    private static final String EXCHANGE_NAME = "wgwFanoutExchange1";
    @Test
    public void test1() throws  Exception {
        Connection connection = ConnectionUtil.getConnection();
        System.out.println(connection.toString());
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout",true);//声明一个路由类型的交换机,类型必须写direct
        String message = "啤酒饮料矿泉水,花生瓜子火腿肠,哎,腿让一下,让车过一下";
        channel.basicPublish(EXCHANGE_NAME, "",  MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        System.out.println("哦~~~~:"+message);
        channel.close();
        connection.close();
    }
}
