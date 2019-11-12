package com.blingabc.auto.vo;

public class DubboEntity {
    private Integer id;

    private String requestServiceName;

    private String requestMethodName;

    private String requestBeanName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRequestServiceName() {
        return requestServiceName;
    }

    public void setRequestServiceName(String requestServiceName) {
        this.requestServiceName = requestServiceName == null ? null : requestServiceName.trim();
    }

    public String getRequestMethodName() {
        return requestMethodName;
    }

    public void setRequestMethodName(String requestMethodName) {
        this.requestMethodName = requestMethodName == null ? null : requestMethodName.trim();
    }

    public String getRequestBeanName() {
        return requestBeanName;
    }

    public void setRequestBeanName(String requestBeanName) {
        this.requestBeanName = requestBeanName == null ? null : requestBeanName.trim();
    }
}