package com.wgw.redis.mq;



import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by jackiechan on 18-7-17/上午9:37
 * 用于创建和rabbitmq链接的工具类
 */
public class ConnectionUtil {

    public static Connection getConnection(){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("rabbitmq.qfjava.cn");//设置链接地址,注意没有http,因为这个地方需要的是一个ip,而我们通过使用域名的方式代替,所以这里写的是域名
        connectionFactory.setPort(5672);//设置rabbitmq的端口,默认5672
        connectionFactory.setUsername("test");//设置访问rabbitmq的用户
        connectionFactory.setPassword("test");//密码
        connectionFactory.setVirtualHost("/test");//设置要使用的虚拟主机,相当于我们的数据库的库
        try {
            return connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        return  null;
    }
}
