package com.okjiaoyu.auto.vo;

import java.util.List;

public class CatalogEntity {
    private Integer id;

    private String name;

    private Integer pid;

    private Integer protocolId;

    private String icon;

    private Integer level;

    private Integer envId;

    private EnvEntity env;

    private List<CatalogEntity> children;

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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Integer protocolId) {
        this.protocolId = protocolId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getEnvId() {
        return envId;
    }

    public void setEnvId(Integer envId) {
        this.envId = envId;
    }

    public List<CatalogEntity> getChildren() {
        return children;
    }

    public void setChildren(List<CatalogEntity> children) {
        this.children = children;
    }

    public EnvEntity getEnv() {
        return env;
    }

    public void setEnv(EnvEntity env) {
        this.env = env;
    }
}
