package com.okjiaoyu.auto.dao;

import com.okjiaoyu.auto.vo.CookieEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CookieEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CookieEntity record);

    int insertSelective(CookieEntity record);

    CookieEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CookieEntity record);

    int updateByPrimaryKeyWithBLOBs(CookieEntity record);

    int updateByPrimaryKey(CookieEntity record);

    List<CookieEntity> queryCookiesByCasLoginId(@Param("casLoginId") int casLoginId);

    void updateCookieBatch(List<CookieEntity> cookies);

    void delCookiesById(List<Integer> ids);
}
