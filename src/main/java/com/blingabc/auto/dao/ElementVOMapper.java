package com.blingabc.auto.dao;

import com.blingabc.auto.beans.ElementVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ElementVOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ElementVO record);

    int insertSelective(ElementVO record);

    ElementVO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ElementVO record);

    int updateByPrimaryKey(ElementVO record);
}