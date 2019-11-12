package com.blingabc.auto.dao;

import com.blingabc.auto.vo.JmeterEntity;

public interface JmeterEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JmeterEntity record);

    int insertSelective(JmeterEntity record);

    JmeterEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JmeterEntity record);

    int updateByPrimaryKey(JmeterEntity record);
}