package com.blingabc.auto.dao;

import com.blingabc.auto.beans.PageOperateVO;

public interface PageOperateVOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PageOperateVO record);

    int insertSelective(PageOperateVO record);

    PageOperateVO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PageOperateVO record);

    int updateByPrimaryKey(PageOperateVO record);
}