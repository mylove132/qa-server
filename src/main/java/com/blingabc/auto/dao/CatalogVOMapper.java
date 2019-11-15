package com.blingabc.auto.dao;

import com.blingabc.auto.beans.CatalogVO;

import java.util.List;

public interface CatalogVOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CatalogVO record);

    int insertSelective(CatalogVO record);

    CatalogVO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CatalogVO record);

    int updateByPrimaryKey(CatalogVO record);

    List<CatalogVO> queryCatalogList();
}