package com.blingabc.auto.dao;
import com.blingabc.auto.vo.RequestLogEntity;

public interface RequestLogEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RequestLogEntity record);

    int insertSelective(RequestLogEntity record);

    RequestLogEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RequestLogEntity record);

    int updateByPrimaryKey(RequestLogEntity record);
}