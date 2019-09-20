package com.okjiaoyu.auto.dao;

import com.okjiaoyu.auto.vo.Env;

public interface EnvMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Env record);

    int insertSelective(Env record);

    Env selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Env record);

    int updateByPrimaryKey(Env record);
}