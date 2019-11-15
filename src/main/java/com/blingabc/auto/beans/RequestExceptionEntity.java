package com.blingabc.auto.beans;

import java.util.Date;

public class RequestExceptionEntity {
    private Integer id;

    private Date happendTime;

    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getHappendTime() {
        return happendTime;
    }

    public void setHappendTime(Date happendTime) {
        this.happendTime = happendTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
}
