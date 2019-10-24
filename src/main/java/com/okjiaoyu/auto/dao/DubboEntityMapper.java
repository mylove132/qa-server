package com.okjiaoyu.auto.dao;

import com.okjiaoyu.auto.vo.DubboEntity;

public interface DubboEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DubboEntity record);

    int insertSelective(DubboEntity record);

    DubboEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DubboEntity record);

    int updateByPrimaryKey(DubboEntity record);
}