package com.okjiaoyu.auto.dao;

import com.okjiaoyu.auto.vo.ElementVO;

public interface ElementVOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ElementVO record);

    int insertSelective(ElementVO record);

    ElementVO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ElementVO record);

    int updateByPrimaryKey(ElementVO record);
}