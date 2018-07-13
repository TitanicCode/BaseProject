package com.qfedu.cxfserver.service;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**
 * Created by helen on 2018/6/30
 */
public class MyServer {

    public static void main(String[] args){
        //创建服务工厂
        JaxWsServerFactoryBean factoryBean = new JaxWsServerFactoryBean();
        //指定服务地址
        factoryBean.setAddress("http://127.0.0.1:8888/hello");
        //设置服务类
        factoryBean.setServiceClass(HelloService.class);
        //设置是服务类的实例对象
        factoryBean.setServiceBean(new HelloService());
        //发布服务
        factoryBean.create();
        //jdk发布一个webservice服务
        System.out.println("web服务已启动......");
    }
}
