package com.okjiaoyu.auto.vo;

public class ElementVO {
    private Integer id;

    private String name;

    private Integer locatorTypeId;

    private String locator;

    private Integer operationId;

    private String val;

    private Integer timeOut;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getLocatorTypeId() {
        return locatorTypeId;
    }

    public void setLocatorTypeId(Integer locatorTypeId) {
        this.locatorTypeId = locatorTypeId;
    }

    public String getLocator() {
        return locator;
    }

    public void setLocator(String locator) {
        this.locator = locator == null ? null : locator.trim();
    }

    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val == null ? null : val.trim();
    }

    public Integer getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }
}