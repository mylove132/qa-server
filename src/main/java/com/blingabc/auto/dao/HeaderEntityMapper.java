package com.blingabc.auto.dao;

import com.blingabc.auto.vo.HeaderEntity;

public interface HeaderEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HeaderEntity record);

    int insertSelective(HeaderEntity record);

    HeaderEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HeaderEntity record);

    int updateByPrimaryKey(HeaderEntity record);
}