package com.qfedu.cxfserver.service;

import javax.jws.WebService;

/**
 * Created by helen on 2018/6/30
 */
@WebService
public class HelloService {

    public String sayHello(String name){

        return "hello " + name;
    }
}
