package com.blingabc.auto.beans;

import java.util.Date;

public class InterfaceCaseVO {
    private Integer id;

    private String name;

    private String url;

    private Integer assertId;

    private Integer protocolId;

    private Integer catalogId;

    private Integer requestWayId;

    private String paramType;

    private Date createTime;

    private Date updateTime;

    private AssertVO assertVO;

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

    public String getUrl() {
        return url;
    }

    public Integer getRequestWayId() {
        return requestWayId;
    }

    public Integer getAssertId() {
        return assertId;
    }

    public void setAssertId(Integer assertId) {
        this.assertId = assertId;
    }

    public void setRequestWayId(Integer requestWayId) {
        this.requestWayId = requestWayId;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Integer protocolId) {
        this.protocolId = protocolId;
    }

    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType == null ? null : paramType.trim();
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

    public AssertVO getAssertVO() {
        return assertVO;
    }

    public void setAssertVO(AssertVO assertVO) {
        this.assertVO = assertVO;
    }
}