package com.blingabc.auto.dao;

import com.blingabc.auto.beans.PageVO;

public interface PageVOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PageVO record);

    int insertSelective(PageVO record);

    PageVO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PageVO record);

    int updateByPrimaryKey(PageVO record);
}