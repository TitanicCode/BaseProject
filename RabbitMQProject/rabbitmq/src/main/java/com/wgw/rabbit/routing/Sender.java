package com.wgw.rabbit.routing;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wgw.rabbit.util.ConnectionUtil;

/**
 * Created by jackiechan on 18-7-17/下午2:43
 * 注意队列在注册到交换机之前所有发送出去的消息都收不到,交换机不负责存储消息
 */
public class Sender {

    private static final String EXCHANGE_NAME = "jingyesi";

    public static void main(String[] args) throws Exception{

        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");//声明一个路由类型的交换机,类型必须写direct
        String message = "啤酒饮料矿泉水,花生瓜子火腿肠,哎,腿让一下,让车过一下";
        channel.basicPublish(EXCHANGE_NAME, "bcd", null, message.getBytes());
        System.out.println("哦~~~~:"+message);
        channel.close();
        connection.close();
    }
}
