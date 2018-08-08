package com.common.microservice.consumer.controller;

//
//                            _ooOoo_  
//                           o8888888o  
//                           88" . "88  
//                           (| -_- |)  
//                            O\ = /O  
//                        ____/`---'\____  
//                      .   ' \\| |// `.  
//                       / \\||| : |||// \  
//                     / _||||| -:- |||||- \  
//                       | | \\\ - /// | |  
//                     | \_| ''\---/'' | |  
//                      \ .-\__ `-` ___/-. /  
//                   ___`. .' /--.--\ `. . __  
//                ."" '< `.___\_<|>_/___.' >'"".  
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |  
//                 \ \ `-. \_ __\ /__ _/ .-` / /  
//         ======`-.____`-.___\_____/___.-`____.-'======  
//                            `=---='  
//  
//         .............................................  
//                  佛祖镇楼                  BUG辟易  
//          佛曰:  
//                  写字楼里写字间，写字间里程序员；  
//                  程序人员写程序，又拿程序换酒钱。  
//                  酒醒只在网上坐，酒醉还来网下眠；  
//                  酒醉酒醒日复日，网上网下年复年。  
//                  但愿老死电脑间，不愿鞠躬老板前；  
//                  奔驰宝马贵者趣，公交自行程序员。  
//                  别人笑我忒疯癫，我笑自己命太贱；  
//  


import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.qianfeng.microservice.consumer.feign.FeignClientInterface;
import com.qianfeng.microservice.consumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * Created by jackiechan on 18-7-30/上午11:14
 */
@RestController
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;//访问rest请求的模板对象
    @Autowired
    private FeignClientInterface feignClientInterface;

    @Autowired
    private EurekaClient eurekaClient;
    @GetMapping("/order/{id}")
    public User getOrderInfo(@PathVariable  int id) {
        //调用用户的远程服务,获取数据
       // User user = restTemplate.getForObject("http://localhost:7900/user/" + id, User.class);
        //通过eureka获取到服务提供者的地址,然后调用
//        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("PROVIDER-USER", false);
//        String homePageUrl = instanceInfo.getHomePageUrl();
//        System.err.println(homePageUrl);
//        User user = restTemplate.getForObject(homePageUrl + "/user/" + id, User.class);
        User user = feignClientInterface.abc(id);

        return user;

    }
}
