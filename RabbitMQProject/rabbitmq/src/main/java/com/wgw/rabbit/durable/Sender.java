package com.wgw.rabbit.durable;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.wgw.rabbit.util.ConnectionUtil;

/**
 * Created by Administrator on 2018/7/28.
 * 测试持久化：发送消息，接收端接收消息，关闭接收端，发送方发送消息，关闭发送方，重启rabbit服务器，打开接收端，发现仍然会接受到消息，这说明消息存储在服务器中了，完成了持久化
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

