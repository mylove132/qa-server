package com.okjiaoyu.auto.vo;

import java.util.List;

public class ProjectEntity {
    private Integer id;

    private String name;

    private List<ModuleEntity> moduleList;

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

    public List<ModuleEntity> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<ModuleEntity> moduleList) {
        this.moduleList = moduleList;
    }
}
