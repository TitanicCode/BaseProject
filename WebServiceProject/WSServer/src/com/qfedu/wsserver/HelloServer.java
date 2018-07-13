package com.qfedu.wsserver;

import com.qfedu.wsserver.service.HelloService;

import javax.xml.ws.Endpoint;

/**
 * Created by helen on 2018/6/30
 */
public class HelloServer {

    public static void main(String[] args){
        //JDK自带函数
        Endpoint.publish("http://127.0.0.1:8089/hello", new HelloService());

        System.out.println("服务已启动......");
    }
}
