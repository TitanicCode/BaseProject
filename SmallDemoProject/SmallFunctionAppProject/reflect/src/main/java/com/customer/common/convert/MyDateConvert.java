package com.customer.common.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/7/17.
 */
public class MyDateConvert implements MyConvert<String,Date>{
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date convert(String source) throws ParseException {
        return source==null?null:simpleDateFormat.parse(source);
    }
}
