package com.okjiaoyu.auto.dao;

import com.okjiaoyu.auto.vo.Jmeter;

public interface JmeterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Jmeter record);

    int insertSelective(Jmeter record);

    Jmeter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Jmeter record);

    int updateByPrimaryKey(Jmeter record);
}