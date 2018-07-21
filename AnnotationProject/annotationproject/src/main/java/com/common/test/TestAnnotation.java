package com.common.test;

import com.common.annotation.AnnotationUtil;
import com.common.pojo.User;
import org.junit.Test;

/**
 * Created by Administrator on 2018/7/17.
 * 注解:是为了达到某种目的而预先定义好的一个特殊接口,它的实现是由jvm实现的动态代理对象
 * 要实现注解首先得知道自己需要什么,或者要做什么,然后做这个事情需要什么其他内容
 * 我们的目标:模拟spring的value注解,给一个字符串赋值
 * 功能分析:当前注解的作用是赋值,也就是说当我们发现变量上面有这个注解的时候就代表要给这个变量赋值
 * 赋值的内容是什么,我们规定好, value()的值就是我们要赋值的内容
 */
public class TestAnnotation {
    @Test
    public void test1() throws Exception {
       /* User user=new User();
        user.setName("adsdas");
        System.out.println(user.toString());*/
        User user=new User();
        AnnotationUtil.fillData(user);
        System.out.println(user);
    }
}
