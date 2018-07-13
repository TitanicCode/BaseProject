package com.qfedu.cxfclient;

import com.qfedu.cxfserver.service.HelloService;
import com.qfedu.cxfserver.service.HelloServiceService;

/**
 * Created by helen on 2018/6/30
 */
public class CXFClientDemo {

    public static void main(String[] args){

        HelloServiceService helloServiceService = new HelloServiceService();
        HelloService helloService = helloServiceService.getHelloServicePort();

        String word = helloService.sayHello("老王");

        System.out.println(word);
    }
}
