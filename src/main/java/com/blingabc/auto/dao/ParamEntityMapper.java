package com.blingabc.auto.dao;

import com.blingabc.auto.vo.ParamEntity;

public interface ParamEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParamEntity record);

    int insertSelective(ParamEntity record);

    ParamEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParamEntity record);

    int updateByPrimaryKeyWithBLOBs(ParamEntity record);

    int updateByPrimaryKey(ParamEntity record);
}