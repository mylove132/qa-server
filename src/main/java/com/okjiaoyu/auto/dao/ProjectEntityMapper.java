package com.okjiaoyu.auto.dao;

import com.okjiaoyu.auto.vo.ProjectEntity;

import java.util.List;

public interface ProjectEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectEntity record);

    int insertSelective(ProjectEntity record);

    ProjectEntity selectByPrimaryKey(Integer id);

    ProjectEntity selectByName(String name);

    int updateByPrimaryKeySelective(ProjectEntity record);

    int updateByPrimaryKey(ProjectEntity record);

    List<ProjectEntity> queryProject();
}
