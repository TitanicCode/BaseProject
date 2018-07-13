package com.qfedu.wsclient;


import com.helen.ws.helloservice.MyHello;
import com.helen.ws.helloservice.MyHelloServer;

/**
 * Created by helen on 2018/6/30
 */
public class HelloClient {

    public static void main(String[] args){

        MyHelloServer myHelloServer = new MyHelloServer();
        MyHello myHello = myHelloServer.getMyHelloPort();

        String hello = myHello.sayHello("Helen");
        String bye = myHello.sayBye("Helen");

        System.out.println(hello);
        System.out.println(bye);
    }
}
