package com.qfedu.wsclient;

import com.helen.ws.personservice.MyPerson;
import com.helen.ws.personservice.MyPersonServer;
import com.helen.ws.personservice.Person;

import java.util.List;

/**
 * Created by helen on 2018/6/30
 */
public class PersonClient {

    public static void main(String[] args){

        MyPersonServer myPersonServer = new MyPersonServer();
        MyPerson myPerson = myPersonServer.getMyPersonPort();



        Person person1 = new Person();
        person1.setAge(30);
        person1.setId(1);
        person1.setName("老王");

        Person person2 = new Person();
        person2.setAge(30);
        person2.setId(1);
        person2.setName("老王");


        myPerson.addPerson(person1);
        myPerson.addPerson(person2);

        List<Person> personAll = myPerson.getPersonAll();
        System.out.println(personAll);

    }
}
