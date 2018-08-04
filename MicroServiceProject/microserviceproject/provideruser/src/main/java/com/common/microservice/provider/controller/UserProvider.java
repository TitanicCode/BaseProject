package com.common.microservice.provider.controller;




import com.common.microservice.provider.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by jackiechan on 18-7-30/上午11:06
 */
@RestController
public class UserProvider {

    @GetMapping("/user/{id}")
    public User getUserInfo(@PathVariable  int id) throws InterruptedException {
        User user = new User();
        user.setId(id);
        user.setDate(new Date());
           // Thread.sleep(10000);
        return user;
    }
}
