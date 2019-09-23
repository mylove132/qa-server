package com.okjiaoyu.auto.dao;

import com.okjiaoyu.auto.vo.RequestExceptionEntity;

public interface RequestExceptionEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RequestExceptionEntity record);

    int insertSelective(RequestExceptionEntity record);

    RequestExceptionEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RequestExceptionEntity record);

    int updateByPrimaryKeyWithBLOBs(RequestExceptionEntity record);

    int updateByPrimaryKey(RequestExceptionEntity record);
}