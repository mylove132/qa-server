package com.blingabc.auto.dao;

import com.blingabc.auto.beans.ElementOperateVO;

public interface ElementOperateVOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ElementOperateVO record);

    int insertSelective(ElementOperateVO record);

    ElementOperateVO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ElementOperateVO record);

    int updateByPrimaryKey(ElementOperateVO record);
}