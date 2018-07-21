package com.customer.common.pojo;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by Administrator on 2018/7/17.
 */
public class User {
    private String name;
    private String password;
    private  int age;
    private Date birthday;
    private String[] hobby;
    private int abc[];

    public int[] getAbc() {
        return abc;
    }

    public void setAbc(int[] abc) {
        this.abc = abc;
    }

    public String[] getHobby() {
        return hobby;
    }

    public void setHobby(String[] hobby) {
        this.hobby = hobby;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", hobby=" + Arrays.toString(hobby) +
                ", abc=" + Arrays.toString(abc) +
                '}';
    }
}
