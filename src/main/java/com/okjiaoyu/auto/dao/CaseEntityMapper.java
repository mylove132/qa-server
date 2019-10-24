package com.okjiaoyu.auto.dao;

import com.okjiaoyu.auto.vo.CaseEntity;

import java.util.List;

public interface CaseEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CaseEntity record);

    int insertSelective(CaseEntity record);

    CaseEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CaseEntity record);

    int updateByPrimaryKey(CaseEntity record);

    List<CaseEntity> queryCasesByCatalogId(int catalogId);
}