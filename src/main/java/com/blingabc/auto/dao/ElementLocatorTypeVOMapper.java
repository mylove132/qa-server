package com.blingabc.auto.dao;

import com.blingabc.auto.beans.ElementLocatorTypeVO;

public interface ElementLocatorTypeVOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ElementLocatorTypeVO record);

    int insertSelective(ElementLocatorTypeVO record);

    ElementLocatorTypeVO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ElementLocatorTypeVO record);

    int updateByPrimaryKey(ElementLocatorTypeVO record);
}