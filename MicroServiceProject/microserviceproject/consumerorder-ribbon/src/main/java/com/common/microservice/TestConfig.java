package com.common.microservice;


import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jackiechan on 18-7-30/下午12:14
 */
@Configuration
public class TestConfig {
    @Bean
    public IRule ribbonRule() {
        return new RandomRule();

    }
}
