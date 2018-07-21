package com.common.dto;

import com.common.constantcode.ConstantCodeUtil;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/7/18.
 * 为什么实现序列化,因为这个对象会在不同的服务器(service和controller)中传递,所以必须实现序列化
 */
public class ResultData implements Serializable {
    private int code;//错误码
    private String message;//错误描述
    private Object data;//数据

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    public  static  ResultData setOk(Object data){
        ResultData resultBean = new ResultData();
        resultBean.setCode(ConstantCodeUtil.SUCCESS);
        resultBean.setMessage("成功");
        resultBean.setData(data);
        return resultBean;
    }

    public  static  ResultData setError(Object data){
        return setError(ConstantCodeUtil.ERROR,"失败",data);
    }

    public  static  ResultData setError(int code,String message,Object data){
        ResultData resultBean = new ResultData();
        resultBean.setCode(code);
        resultBean.setMessage(message);
        resultBean.setData(data);
        return resultBean;
    }
}
