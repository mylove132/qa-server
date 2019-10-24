package com.okjiaoyu.auto.dao;

import com.okjiaoyu.auto.vo.JmeterEntity;

public interface JmeterEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JmeterEntity record);

    int insertSelective(JmeterEntity record);

    JmeterEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JmeterEntity record);

    int updateByPrimaryKey(JmeterEntity record);
}