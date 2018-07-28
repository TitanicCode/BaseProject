package com.wgw.rabbit.durable;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.wgw.rabbit.util.ConnectionUtil;

/**
 * Created by Administrator on 2018/7/28.
 */
public class Receiver {
    private static String EXCHANGE_NAME="test_dur";
    private static String queueName="test_dur_qeue";

    public static void main(String[] args) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明持久化交换机
        boolean durable=true;
        channel.exchangeDeclare(EXCHANGE_NAME, "direct", durable, false, null);
        //声明持久化队列
        channel.queueDeclare(queueName, durable, false, false, null);
        channel.queueBind(queueName, EXCHANGE_NAME, "blue");
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, consumer);
        while (true)
        {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            String routingKey = delivery.getEnvelope().getRoutingKey();
            System.out.println(" [x] Received routingKey = " + routingKey   + ",msg = " + message + ".");
        }
    }
}
