package com.common.microservice.consumer.pojo;



import java.util.Date;

/**
 * Created by jackiechan on 18-7-30/上午11:14
 */
public class User {
    private  int id;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
