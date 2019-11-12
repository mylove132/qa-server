package com.blingabc.auto.dao;

import com.blingabc.auto.vo.EnvEntity;

import java.util.List;

public interface EnvEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EnvEntity record);

    int insertSelective(EnvEntity record);

    EnvEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EnvEntity record);

    int updateByPrimaryKey(EnvEntity record);

    List<EnvEntity> queryEnvList();
}