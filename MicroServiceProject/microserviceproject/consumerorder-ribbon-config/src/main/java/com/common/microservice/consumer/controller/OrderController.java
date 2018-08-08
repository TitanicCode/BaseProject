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


import com.common.microservice.consumer.pojo.User;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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
    private LoadBalancerClient loadBalancerClient;//负载均衡

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
    @GetMapping("/test")
    public String test() {

        ServiceInstance instance = loadBalancerClient.choose("PROVIDERUSER");
        String host = instance.getHost();
        int port = instance.getPort();
        System.err.println("当前的主机是:====>" + host + "   端口是:===>" + port);
        ServiceInstance instance1 = loadBalancerClient.choose("PROVIDERUSER1");
        String host1 = instance1.getHost();
        int port1 = instance1.getPort();
        System.err.println("当前1111的主机是:====>" + host1 + "   端口是:===>" + port1);
        return "1";

    }
}
