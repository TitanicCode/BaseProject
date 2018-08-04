package com.common.microservice.consumer.controller;




import com.common.microservice.consumer.pojo.User;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by jackiechan on 18-7-30/上午11:14
 */
@RestController
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;//访问rest请求的模板对象

    @Autowired
    private EurekaClient eurekaClient;
    @GetMapping("/order/{id}")
    public User getOrderInfo(@PathVariable  int id) {
        //调用用户的远程服务,获取数据
       // User user = restTemplate.getForObject("http://localhost:7900/user/" + id, User.class);
        //通过eureka获取到服务提供者的地址,然后调用
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("PROVIDERUSER", false);
        String homePageUrl = instanceInfo.getHomePageUrl();
        System.err.println(homePageUrl);
        User user = restTemplate.getForObject(homePageUrl + "/user/" + id, User.class);
        return user;

    }
}
