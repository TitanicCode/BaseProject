package com.qfedu.wsserver.service;

import com.qfedu.wsserver.domain.Person;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by helen on 2018/6/30
 */
@WebService(
        serviceName = "MyPersonServer",//服务访问点
        portName = "MyPersonPort",//端口名
        name = "MyPerson",//类名
        targetNamespace = "personservice.ws.helen.com"
)
public class PersonService {

    List<Person> personList = new ArrayList<Person>();

    public void addPerson(Person person){
        personList.add(person);
    }

    public List<Person> getPersonAll(){
        return personList;
    }
}
