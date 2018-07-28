package com.wgw.rabbit.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wgw.rabbit.util.ConnectionUtil;



/**
 * Created by jackiechan on 18-7-17/上午9:43
 * 消息的发送者
 */
public class Sender {

    //队列的名字
    private static final String QUEUE_NAME="testSimpleOne";

    public static void main(String[] args) throws Exception {
        //建立链接
        Connection connection= ConnectionUtil.getConnection();
        //获取频道,相当于我们链接数据库的时候的statement
        Channel channel = connection.createChannel();
        //设置队列名字.相当于我们发送短信时候的手机号,也就是接收者的标记
        /**
         * 声明一个队列,如果队列已经存在,则直接返回,如果不存在,则创建
         * 参数1 队列的名字
         * 参数2 durable 是否持久化,因为消息默认是在服务器的内存中,重启就丢失了,如果设置了true,则会持久化到磁盘,重启后不会丢失
         * 参数3 是否排外,两个作用 一个是当我们connection.close();关闭链接的时候是否会自动删除该队列,第二个作用私有化队列,如果不是排外的,代表有多个消费者可以同时访问该队列(只是访问),如果排外了,会给当前队列加锁,其他人就不能访问了,强制访问会排除异常
         * 参数4 是否自动删除,当我们最后一个消费者断开链接的时候是否会自动删除该队列,可以通过后台查看消费者数量
         * 此方法是一个封装好的通用的方法,不是针对当前1v1的情况的,所以会出现多个消费者或者Chanel的情况
         * */
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //发送消息
        String message="怎么冷酷却仍然美丽,得不到的,从来矜贵,身处劣势,如何不攻心计";
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());

        System.out.println("发送的内容是:   "+message);
        channel.close();
        connection.close();
    }
}
