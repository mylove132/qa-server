package com.okjiaoyu.auto.vo;

import java.util.Date;
import java.util.List;

public class CasLoginEntity {
    private Integer id;

    private String username;

    private String password;

    private Integer envId;

    private String domain;

    private String name;

    private Date createTime;

    private Date updateTime;

    private List<CookieEntity> cookies;

    private EnvEntity env;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getEnvId() {
        return envId;
    }

    public void setEnvId(Integer envId) {
        this.envId = envId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public List<CookieEntity> getCookies() {
        return cookies;
    }

    public void setCookies(List<CookieEntity> cookies) {
        this.cookies = cookies;
    }

    public EnvEntity getEnv() {
        return env;
    }

    public void setEnv(EnvEntity env) {
        this.env = env;
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
}
