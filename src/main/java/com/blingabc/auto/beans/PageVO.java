package com.blingabc.auto.beans;

import java.util.Date;

public class PageVO {
    private Integer id;

    private String name;

    private String val;

    private Date createTime;

    private Date updateTime;

    private Integer pageOperateId;

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

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val == null ? null : val.trim();
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

    public Integer getPageOperateId() {
        return pageOperateId;
    }

    public void setPageOperateId(Integer pageOperateId) {
        this.pageOperateId = pageOperateId;
    }
}