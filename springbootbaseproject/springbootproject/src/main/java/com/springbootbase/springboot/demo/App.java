package com.springbootbase.springboot.demo;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by jackiechan on 18-7-18/下午3:31
 */
//该类为springboot的启动类，自带tomcat，启动springboot项目的时候直接启动该类就行
//@SpringBootApplication作为项目启动类加扫描类不能直接出现在java文件夹下，即至少需要在Java文件夹下建一层包放这个类
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
