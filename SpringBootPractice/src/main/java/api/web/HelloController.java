package api.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by helen on 2018/6/27
 */
@RestController
public class HelloController {

    @GetMapping("hello")
    public String world(){


        return "hello world";
    }
}