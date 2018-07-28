package com.wgw.rabbit.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.wgw.rabbit.util.ConnectionUtil;

import java.io.IOException;

/**
 * Created by Administrator on 2018/7/28.
 */
public class Receiver {
    //队列的名字
    private static final String QUEUE_NAME="testSimpleOne";
    public static void main(String[] args) throws IOException, InterruptedException {
        //建立链接
        Connection connection = ConnectionUtil.getConnection();

        //创建频道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //创建消费者
        QueueingConsumer consumer=new QueueingConsumer(channel);
        //监听队列,表示参数3会监听队列名字为参数1的消息，参数2表示接收到消息后自动确认反馈给发送者
        channel.basicConsume(QUEUE_NAME,true,consumer);
        //获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            byte[] body = delivery.getBody();
            System.out.println("收到的数据是:  "+new String(body));
            //接收到数据后也可以更新redis solr操作
        }
    }

}
