package com.blingabc.auto.dao;

import com.blingabc.auto.beans.CaseTypeVO;

import java.util.List;

public interface CaseTypeVOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CaseTypeVO record);

    int insertSelective(CaseTypeVO record);

    CaseTypeVO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CaseTypeVO record);

    int updateByPrimaryKey(CaseTypeVO record);

    List<CaseTypeVO> queryList();
}