package com.qfedu.wsclient;

import cn.com.webxml.ArrayOfString;
import cn.com.webxml.MobileCodeWS;
import cn.com.webxml.MobileCodeWSSoap;

/**
 * Created by helen on 2018/6/30
 */
public class MobileCodeClient {

    public static void main(String[] args){

        MobileCodeWS mobileCodeWS = new MobileCodeWS();
        MobileCodeWSSoap soapService = mobileCodeWS.getMobileCodeWSSoap();

        ArrayOfString databaseInfo = soapService.getDatabaseInfo();

        String mobileCodeInfo = soapService.getMobileCodeInfo("13766816630", null);


    }
}
