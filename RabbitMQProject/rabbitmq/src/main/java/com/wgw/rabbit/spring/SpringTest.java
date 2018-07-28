package com.wgw.rabbit.spring;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by Administrator on 2018/7/28.
 */
public class SpringTest {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        RabbitTemplate template = context.getBean(RabbitTemplate.class);
        // template.setRoutingKey("");
        //template.setExchange();
        template.convertAndSend("太阳当空照,花儿对我笑,小鸟说,早早早,你为什么背上小书包");
        Thread.sleep(10000);
    }
}
