//package com.common.freemarker;
//
////
////                            _ooOoo_
////                           o8888888o
////                           88" . "88
////                           (| -_- |)
////                            O\ = /O
////                        ____/`---'\____
////                      .   ' \\| |// `.
////                       / \\||| : |||// \
////                     / _||||| -:- |||||- \
////                       | | \\\ - /// | |
////                     | \_| ''\---/'' | |
////                      \ .-\__ `-` ___/-. /
////                   ___`. .' /--.--\ `. . __
////                ."" '< `.___\_<|>_/___.' >'"".
////               | | : `- \`.;`\ _ /`;.`/ - ` : | |
////                 \ \ `-. \_ __\ /__ _/ .-` / /
////         ======`-.____`-.___\_____/___.-`____.-'======
////                            `=---='
////
////         .............................................
////                  佛祖镇楼                  BUG辟易
////          佛曰:
////                  写字楼里写字间，写字间里程序员；
////                  程序人员写程序，又拿程序换酒钱。
////                  酒醒只在网上坐，酒醉还来网下眠；
////                  酒醉酒醒日复日，网上网下年复年。
////                  但愿老死电脑间，不愿鞠躬老板前；
////                  奔驰宝马贵者趣，公交自行程序员。
////                  别人笑我忒疯癫，我笑自己命太贱；
////
//
//
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import freemarker.template.TemplateException;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
//
//import java.io.*;
//import java.util.*;
//
///**
// * Created by jackiechan on 18-7-25/上午9:20
// */
//public class TestFreeMarker {
//    @Test
//    public void test1() throws IOException, TemplateException {
//        //1创建对象
//        Configuration configuration=new Configuration(Configuration.getVersion());
//        //指定模板所在的目录,这个目录下面可能会有很多模板
//        configuration.setDirectoryForTemplateLoading(new File("C:\\Users\\Administrator.PC-20180226XFFD\\Desktop\\TitanicWang\\BaseProject\\FreeMarkerProject\\freemarkerproject\\src\\main\\resources"));
//        configuration.setDefaultEncoding("UTF-8");
//        //获取具体要操作的模板
//        Template template = configuration.getTemplate("hello.ftl");
//        //设置数据
//        Map<String, Object> model = new HashMap<>();
//        model.put("hello","知名企业家冤狱11年,现像国家索赔20亿人民币");
//        User user=new User();
//        user.setName("婷姐");
//        user.setPassword("0");
//        model.put("user", user);
//        List<User> users = new ArrayList<>();
//        for (int i = 1; i < 11; i++) {
//            user = new User();
//            user.setPassword("密码:--->" + i);
//            user.setName("婷姐:---->"+i);
//            users.add(user);
//        }
//        model.put("users", users);
//        Date date = new Date();
//        model.put("fadsfadsadfs", date);
//        model.put("nulldata", null);//此处null代表我们实际获取到的数据是空的
//        //限高//黑名单
//        //写成文件
//        //Writer writer = new FileWriter("C:\\Users\\Administrator.PC-20180226XFFD\\Desktop\\wgw\\123.html");
//        String path = "C:\\Users\\Administrator.PC-20180226XFFD\\Desktop\\wgw\\123.html";
//        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
//
//        template.process(model,writer);
//        writer.close();
//    }
//
////    /**
////     * TODO 测试未成功
////     * 直接测试无法执行,需要放到mvc中通过接口访问
////     * 因为内部我们的test创建了一个加载器,然后我们的webmvc又创建了一个加载器
////     * @throws IOException
////     * @throws TemplateException
////     */
////    @Test
////    public void test2() throws IOException, TemplateException {
////        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
////        FreeMarkerConfigurer configurer = applicationContext.getBean(FreeMarkerConfigurer.class);
////
////        Configuration configuration = configurer.getConfiguration();
////        //获取具体要操作的模板
////        Template template = configuration.getTemplate("hello.ftl");
////        //设置数据
////        Map<String, Object> model = new HashMap<>();
////        model.put("hello","知名企业家冤狱11年,现像国家索赔20亿人民币");
////        User user=new User();
////        user.setName("婷姐");
////        user.setPassword("0");
////        model.put("user", user);
////        List<User> users = new ArrayList<>();
////        for (int i = 1; i < 11; i++) {
////            user = new User();
////            user.setPassword("密码:--->" + i);
////            user.setName("婷姐:---->"+i);
////            users.add(user);
////        }
////        model.put("users", users);
////        Date date = new Date();
////        model.put("fadsfadsadfs", date);
////        model.put("nulldata", null);//此处null代表我们实际获取到的数据是空的
////        //限高//黑名单
////        //写成文件
////        Writer writer = new FileWriter("/home/jackiechan/Desktop/234.html");
////        template.process(model,writer);
////        writer.close();
////    }
//}
