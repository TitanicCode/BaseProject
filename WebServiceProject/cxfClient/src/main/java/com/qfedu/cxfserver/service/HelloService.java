package com.qfedu.cxfserver.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.7
 * 2018-06-30T10:50:34.534+08:00
 * Generated source version: 3.1.7
 * 
 */
@WebService(targetNamespace = "http://service.cxfserver.qfedu.com/", name = "HelloService")
@XmlSeeAlso({ObjectFactory.class})
public interface HelloService {

    @WebMethod
    @RequestWrapper(localName = "sayHello", targetNamespace = "http://service.cxfserver.qfedu.com/", className = "com.qfedu.cxfserver.service.SayHello")
    @ResponseWrapper(localName = "sayHelloResponse", targetNamespace = "http://service.cxfserver.qfedu.com/", className = "com.qfedu.cxfserver.service.SayHelloResponse")
    @WebResult(name = "return", targetNamespace = "")
    public String sayHello(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0
    );
}
