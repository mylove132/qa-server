package com.blingabc.auto.dao;

import com.blingabc.auto.vo.CaseEntity;

public interface CaseEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CaseEntity record);

    int insertSelective(CaseEntity record);

    CaseEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CaseEntity record);

    int updateByPrimaryKey(CaseEntity record);
}