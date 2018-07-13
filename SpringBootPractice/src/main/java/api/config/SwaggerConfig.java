package api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by helen on 2018/6/27
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiConfig(){
        //TODO
        return new Docket(DocumentationType.SWAGGER_2);
    }
}
