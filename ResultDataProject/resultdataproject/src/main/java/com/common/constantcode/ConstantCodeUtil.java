package com.common.constantcode;

/**
 * Created by Administrator on 2018/7/18.
 */
public interface ConstantCodeUtil {
    int SUCCESS = 1;//正常的成功
    int ERROR = 0;//失败未知原因
    int PHONECODEERROR = 2;//手机验证码错误
    int PICCODEERROR = 3;//图片验证码错误
    int NOTLOGINORTIMEOUT=4;//未登录或者超时
}
