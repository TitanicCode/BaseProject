package com.customer.common.reflect;

import com.customer.common.convert.MyConvert;

import javax.servlet.http.HttpServletRequest;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2018/7/17.
 */
public class ReflectUtil {

    private static Map<Class, MyConvert> classMyConvertMap = new HashMap<>();
    public static void registConvert(Class clazz,MyConvert convert){
        classMyConvertMap.put(clazz,convert);
    }
    private static SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd");

    /**
     * 当前方法的作用是将request中的参数封装到对象中
     * 泛型需要先定义后使用，<T>就是在定义T为泛型，<E>就是再定义E为泛型c
     * request接收的是一个map集合，并且值是存在于数组的
     */
    public <T> T reflectUser(Map<String,String[]> map, Class<T> clazz) throws Exception {
        //最终的目的是将map中的数据封装到clzzz对应的类型对象上面,然后返回
        T tClass = clazz.newInstance();

        //解析参数
        //获取到每个参数的名字,然后将这个参数对应的值封装到这个对象上面的对应的属性上面
        //要求 form表单中的参数的名字必须和对象上面的属性名一致

        //获取到所有参数的键值对,而且这个的键就是参数的名字,也就是对象上面对应的属性名
        Set<Map.Entry<String, String[]>> entries = map.entrySet();

        for (Map.Entry<String,String[]> entry:entries) {
            //获取到key
            String key = entry.getKey();
            PropertyDescriptor descriptor=new PropertyDescriptor(key,clazz);
            if(descriptor!=null){
                //根据key去找刚才我们用于封装参数的对象上面的与key的值一样的属性名
                Method writeMethod = descriptor.getWriteMethod();

                //调用set方法,然后将这个key对应的值设置进去,那么就到了对象上面
                // entry.getValue() form表单中传递过来的与key对应的具体值,我们需要设置给对象
                String[] value = entry.getValue();

                //为了保证参数的长度或者类型是匹配的.我们需要将form表单传递过来的数据 转换成为对象setter方法相对应的参数类型
                //获取setter的方法的参数类型
                Class<?>[] parameterTypes = writeMethod.getParameterTypes();

                //进行参数类型转换
                if(parameterTypes[0]==int.class||parameterTypes[0]==Integer.class){
                    if (value.length!=1){
                        throw new RuntimeException("参数"+key+"的长度不匹配");
                    }else {
                        int i = Integer.parseInt(value[0]);
                        writeMethod.invoke(tClass,i);
                    }

                }else if (parameterTypes[0]==String.class){
                    if (value!=null){
                        //value原本在map中定义的是String数组，故在此使用字符串工具类函数将数组转换为字符串，例如传过来的参数为 map.put("userName",new String[]{"小花","小兰"});
                        writeMethod.invoke(tClass, Arrays.toString(value).replace('[',' ').replace(']',' '));
                    }
                }
                //例如实体类属性为private String[] hobby;
                else if (parameterTypes[0]==String[].class){
                    //数组类型会抛出长度异常
                    //java反射规范中,数组参数的传递需要进行转换,转换为object[]
                    writeMethod.invoke(tClass,new Object[]{value});
                }else if (parameterTypes[0]==int[].class||parameterTypes[0]==Integer[].class){
                    //因为value是String数组，而set要求的是整型数组的写入，所以你new了Object数组里面依然是个String数组，所以必然会报参数类型不匹配错误
                    //writeMethod.invoke(newInstance,new Object[]{value});
                    int[] ints=new int[value.length];
                    for (int i = 0; i < value.length; i++) {
                        ints[i]=Integer.parseInt(value[i]);
                    }
                    //如果是手动new的对象,可以不用再转换为object[]
                    writeMethod.invoke(tClass,ints);
                }
                //当你引入Date类型时，会发现有两个jar包引入的Date功能相同，所以需要判断两个类型的Date
                else if (parameterTypes[0] == Date.class || parameterTypes[0] == java.sql.Date.class) {
                    if (value == null || value.length != 1) {
                        throw new RuntimeException("参数:" + key + "的长度必须为1");
                    } else {
                        MyConvert convert = classMyConvertMap.get(parameterTypes[0]);
                        if (convert != null) {
                            Object o = convert.convert(value[0]);
                            writeMethod.invoke(tClass, o);
                        } else {
                            Date date = simpleDateFormat.parse(value[0]);
                            writeMethod.invoke(tClass, date);
                        }
                    }
                }else{//如果是其他的我们不知道的类型,请自己提供转换器转换
                            MyConvert convert = classMyConvertMap.get(parameterTypes[0]);//获取当前类型的转换器
                            if (convert != null) {
                                Object o = convert.convert(value);//对数据进行转换
                                writeMethod.invoke(tClass, o);
                            }
                }

            }
        }
        //返回带有数据的对象,也就是我们创建的对象
        return tClass;
    }
}
