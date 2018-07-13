package com.qfedu.wsserver;

import com.qfedu.wsserver.service.PersonService;

import javax.xml.ws.Endpoint;

/**
 * Created by helen on 2018/6/30
 */
public class PersonServer {

    public static void main(String[] args){

        Endpoint.publish("http://127.0.0.1:8090/person", new PersonService());

        System.out.println("服务已启动......");
    }
}
