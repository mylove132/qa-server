package com.blingabc.auto.dao;

import com.blingabc.auto.beans.AssertVO;

public interface AssertVOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AssertVO record);

    int insertSelective(AssertVO record);

    AssertVO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AssertVO record);

    int updateByPrimaryKey(AssertVO record);

    void insertAssertReturnId(AssertVO assertVO);

}