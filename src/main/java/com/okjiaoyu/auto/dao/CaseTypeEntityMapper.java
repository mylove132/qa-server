package com.okjiaoyu.auto.dao;

import com.okjiaoyu.auto.vo.CaseTypeEntity;

public interface CaseTypeEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CaseTypeEntity record);

    int insertSelective(CaseTypeEntity record);

    CaseTypeEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CaseTypeEntity record);

    int updateByPrimaryKey(CaseTypeEntity record);
}