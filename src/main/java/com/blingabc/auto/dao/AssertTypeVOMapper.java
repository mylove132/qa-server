package com.blingabc.auto.dao;

import com.blingabc.auto.beans.AssertTypeVO;

public interface AssertTypeVOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AssertTypeVO record);

    int insertSelective(AssertTypeVO record);

    AssertTypeVO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AssertTypeVO record);

    int updateByPrimaryKey(AssertTypeVO record);
}