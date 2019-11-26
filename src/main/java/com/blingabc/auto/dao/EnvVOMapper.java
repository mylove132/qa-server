package com.blingabc.auto.dao;

import com.blingabc.auto.beans.EnvVO;
import com.sun.tools.javac.comp.Env;

import java.util.List;

public interface EnvVOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EnvVO record);

    int insertSelective(EnvVO record);

    EnvVO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EnvVO record);

    int updateByPrimaryKey(EnvVO record);

    List<Env> queryEnvList();
}