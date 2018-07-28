package com.wgw.rabbit.durable;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.wgw.rabbit.util.ConnectionUtil;

/**
 * Created by Administrator on 2018/7/28.
 */
public class Sender {
    private static String EXCHANGE_NAME="test_dur";

    public static void main(String[] args) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        boolean durable=true;
        //声明交换机,并开启持久化功能
        channel.exchangeDeclare(EXCHANGE_NAME, "direct", durable, false, null);
        String message="hello moto";
        //发送消息，并设置为持久化

        channel.basicPublish(EXCHANGE_NAME, "blue", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        System.out.println(" 发送消息'" + message + "'");

        channel.close();
        connection.close();
    }
}

