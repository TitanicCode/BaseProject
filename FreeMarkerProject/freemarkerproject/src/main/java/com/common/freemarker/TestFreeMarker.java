package com.common.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created by jackiechan on 18-7-25/上午9:20
 */
@Controller
@RequestMapping("/freemarker")
public class TestFreeMarker {

    /********************测试freemarker********************/
    public void test1() throws IOException, TemplateException {
        //1创建对象
        Configuration configuration=new Configuration(Configuration.getVersion());
        //指定模板所在的目录,这个目录下面可能会有很多模板
        configuration.setDirectoryForTemplateLoading(new File("C:\\Users\\Administrator.PC-20180226XFFD\\Desktop\\TitanicWang\\BaseProject\\FreeMarkerProject\\freemarkerproject\\src\\main\\resources"));
        configuration.setDefaultEncoding("UTF-8");
        //获取具体要操作的模板
        Template template = configuration.getTemplate("hello.ftl");
        //设置数据
        Map<String, Object> model = new HashMap<>();
        model.put("hello","知名企业家冤狱11年,现像国家索赔20亿人民币");
        User user=new User();
        user.setName("婷姐");
        user.setPassword("0");
        model.put("user", user);
        List<User> users = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            user = new User();
            user.setPassword("密码:--->" + i);
            user.setName("婷姐:---->"+i);
            users.add(user);
        }
        model.put("users", users);
        Date date = new Date();
        model.put("fadsfadsadfs", date);
        model.put("nulldata", null);//此处null代表我们实际获取到的数据是空的
        //限高//黑名单
        //写成文件
        //Writer writer = new FileWriter("C:\\Users\\Administrator.PC-20180226XFFD\\Desktop\\wgw\\123.html");
        String path = "C:\\Users\\Administrator.PC-20180226XFFD\\Desktop\\wgw\\123.html";
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));

        template.process(model,writer);
        writer.close();
    }


    /********************spring整合freemarker********************/

    /**
     * TODO 测试未成功
     * 直接测试无法执行,需要放到mvc中通过接口访问
     * 因为内部我们的test创建了一个加载器,然后我们的webmvc又创建了一个加载器
     * @throws IOException
     * @throws TemplateException
     */
    @Autowired
    private  FreeMarkerConfigurer configurer;

    @RequestMapping("/test")
    public void test2(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateException {
        //ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-MVC.xml");
        //FreeMarkerConfigurer configurer = applicationContext.getBean(FreeMarkerConfigurer.class);
        Configuration configuration = configurer.getConfiguration();
        //获取具体要操作的模板
        Template template = configuration.getTemplate("hello.ftl");
        //设置数据
        Map<String, Object> model = new HashMap<>();
        model.put("hello","知名企业家冤狱11年,现像国家索赔20亿人民币");
        User user=new User();
        user.setName("婷姐");
        user.setPassword("0");
        model.put("user", user);
        List<User> users = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            user = new User();
            user.setPassword("密码:--->" + i);
            user.setName("婷姐:---->"+i);
            users.add(user);
        }
        model.put("users", users);
        Date date = new Date();
        model.put("fadsfadsadfs", date);
        model.put("nulldata", null);//此处null代表我们实际获取到的数据是空的
        //限高//黑名单
        //写成文件
        Writer writer = new FileWriter("C:\\Users\\Administrator.PC-20180226XFFD\\Desktop\\wgw\\234.html");
        template.process(model,writer);
        writer.close();
    }
}
