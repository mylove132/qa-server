package com.blingabc.auto.dao;

import com.blingabc.auto.beans.RequestWayVO;

import java.util.List;

public interface RequestWayVOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RequestWayVO record);

    int insertSelective(RequestWayVO record);

    RequestWayVO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RequestWayVO record);

    int updateByPrimaryKey(RequestWayVO record);

    List<RequestWayVO> queryList();
}