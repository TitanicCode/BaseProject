package com.customer.common.convert;

/**
 * Created by Administrator on 2018/7/17.
 */

import java.text.ParseException;

/**
 * Created by jackiechan on 18-7-13/上午10:14.
 * 接口回调
 * 为什么使用接口?接口中都是没有任何实现的方法
 * 1限制参数类型
 * 2限制方法
 */
public interface MyConvert<S,T>{
    T convert(S source) throws ParseException;
}
