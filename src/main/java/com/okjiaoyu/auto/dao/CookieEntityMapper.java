package com.okjiaoyu.auto.dao;

import com.okjiaoyu.auto.vo.CookieEntity;

import java.util.List;

public interface CookieEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CookieEntity record);

    int insertSelective(CookieEntity record);

    CookieEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CookieEntity record);

    int updateByPrimaryKeyWithBLOBs(CookieEntity record);

    int updateByPrimaryKey(CookieEntity record);

    int updateBySystemId(CookieEntity record);

    List<CookieEntity> queryCookieBySystemId(Long systemId);
}
