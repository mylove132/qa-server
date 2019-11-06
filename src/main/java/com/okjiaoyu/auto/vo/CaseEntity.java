package com.okjiaoyu.auto.vo;

import java.util.Date;

public class CaseEntity {
    private Integer id;

    private String name;

    private Integer catalogId;

    private Integer timeout;

    private String url;

    private Byte iscas;

    private String dependIds;

    private Date createTime;

    private Date updateTime;

    private Integer requestWayId;

    private Integer dubboId;

    private Integer jmeterId;

    private Integer userId;

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

    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Byte getIscas() {
        return iscas;
    }

    public void setIscas(Byte iscas) {
        this.iscas = iscas;
    }

    public String getDependIds() {
        return dependIds;
    }

    public void setDependIds(String dependIds) {
        this.dependIds = dependIds == null ? null : dependIds.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getRequestWayId() {
        return requestWayId;
    }

    public void setRequestWayId(Integer requestWayId) {
        this.requestWayId = requestWayId;
    }

    public Integer getDubboId() {
        return dubboId;
    }

    public void setDubboId(Integer dubboId) {
        this.dubboId = dubboId;
    }

    public Integer getJmeterId() {
        return jmeterId;
    }

    public void setJmeterId(Integer jmeterId) {
        this.jmeterId = jmeterId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}