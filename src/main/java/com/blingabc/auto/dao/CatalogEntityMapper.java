package com.blingabc.auto.dao;

import com.blingabc.auto.vo.CatalogEntity;

import java.util.List;

public interface CatalogEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CatalogEntity record);

    int insertSelective(CatalogEntity record);

    CatalogEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CatalogEntity record);

    int updateByPrimaryKey(CatalogEntity record);

    List<CatalogEntity> queryCatalogList();
}