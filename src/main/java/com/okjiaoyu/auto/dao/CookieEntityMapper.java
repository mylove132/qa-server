package com.okjiaoyu.auto.dao;

import com.okjiaoyu.auto.vo.CookieEntity;

public interface CookieEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CookieEntity record);

    int insertSelective(CookieEntity record);

    CookieEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CookieEntity record);

    int updateByPrimaryKeyWithBLOBs(CookieEntity record);

    int updateByPrimaryKey(CookieEntity record);
}