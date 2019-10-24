package com.okjiaoyu.auto.dao;

import com.okjiaoyu.auto.vo.CasLoginEntity;

import java.util.List;

public interface CasLoginEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CasLoginEntity record);

    void insertSelective(CasLoginEntity record);

    CasLoginEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CasLoginEntity record);

    int updateByPrimaryKey(CasLoginEntity record);

    List<CasLoginEntity> queryUserCookiesList();


}
