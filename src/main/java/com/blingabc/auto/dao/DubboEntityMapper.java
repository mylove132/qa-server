package com.blingabc.auto.dao;

import com.blingabc.auto.vo.DubboEntity;

public interface DubboEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DubboEntity record);

    int insertSelective(DubboEntity record);

    DubboEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DubboEntity record);

    int updateByPrimaryKey(DubboEntity record);
}