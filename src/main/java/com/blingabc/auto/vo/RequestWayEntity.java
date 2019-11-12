package com.blingabc.auto.vo;

public class RequestWayEntity {
    private Integer id;

    private String way;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way == null ? null : way.trim();
    }
}