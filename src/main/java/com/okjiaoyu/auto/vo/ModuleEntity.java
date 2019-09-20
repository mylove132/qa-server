package com.okjiaoyu.auto.vo;

import com.okjiaoyu.auto.util.DateUtil;
import java.util.Date;
import java.util.Map;

public class ModuleEntity {
    private Integer id;

    private String name;

    private Integer projectId;

    private Integer envId;

    private Date createTime;

    private Map<String,Object> project;

    private EnvEntity env;

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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getEnvId() {
        return envId;
    }

    public void setEnvId(Integer envId) {
        this.envId = envId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



    public EnvEntity getEnv() {
        return env;
    }

    public void setEnv(EnvEntity env) {
        this.env = env;
    }

    @Override
    public String toString() {
        return "ModuleEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", projectId=" + projectId +
                ", envId=" + envId +
                ", createTime=" + DateUtil.dateFormat(createTime) +
                ", project=" + project +
                ", env=" + env +
                '}';
    }
}
