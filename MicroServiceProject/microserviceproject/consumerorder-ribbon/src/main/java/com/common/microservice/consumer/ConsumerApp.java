package com.common.microservice.consumer;





import com.common.microservice.TestConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by jackiechan on 18-7-30/上午11:17
 */
@SpringBootApplication
@EnableEurekaClient
//NAME代表要给哪个服务设置负载均衡
@RibbonClient(name = "PROVIDERUSER",configuration = TestConfig.class)
public class ConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
