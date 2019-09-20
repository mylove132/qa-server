package com.okjiaoyu.auto.dao;

import com.okjiaoyu.auto.vo.ModuleEntity;

import java.util.List;

public interface ModuleEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ModuleEntity record);

    int insertSelective(ModuleEntity record);

    ModuleEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ModuleEntity record);

    int updateByPrimaryKey(ModuleEntity record);

    List<ModuleEntity> queryModuleList();
}
