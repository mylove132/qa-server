package com.blingabc.auto.dao;

import com.blingabc.auto.vo.RequestWayEntity;

import java.util.List;

public interface RequestWayEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RequestWayEntity record);

    int insertSelective(RequestWayEntity record);

    RequestWayEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RequestWayEntity record);

    int updateByPrimaryKey(RequestWayEntity record);

    List<RequestWayEntity> queryRequestWay();
}