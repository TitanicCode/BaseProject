package com.springbootbase.springboot.demo;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jackiechan on 18-7-18/下午3:26
 */
//当访问/test/abc时，该controller会返回abc，但是为了能确定扫描到该类，应当把这个类和配置类放置在同一级目录下，或者放置在配置类的子目录下
//当在springboot项目中引入第三方jar包时，可以在这个类中创建对象，这样就可以在其他类中自动注入了
//当然，启动类也可以用来创建对象，启动类的注解是无比强大的
@Configuration
public class Conf {

    @Bean
    public A getA() {
        A a = new A();
        return a;
    }
}
