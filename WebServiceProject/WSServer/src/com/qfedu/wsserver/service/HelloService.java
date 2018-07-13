package com.qfedu.wsserver.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * Created by helen on 2018/6/30
 */
@WebService(
        serviceName = "MyHelloServer",//服务访问点
        portName = "MyHelloPort",//端口名
        name = "MyHello",//类名
        targetNamespace = "helloservice.ws.helen.com"
)
public class HelloService {

    public @WebResult(name="helloResult") String sayHello(@WebParam(name="username") String name){

        return "Hello " + name;
    }


    public String sayBye(String name){

        return "Bye " + name;
    }


    @WebMethod(exclude = true)
    public String sayHi(){
        return "hi";
    }
}
