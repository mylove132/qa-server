package com.okjiaoyu.auto.dao;

import com.okjiaoyu.auto.vo.RoleEntity;

public interface RoleEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleEntity record);

    int insertSelective(RoleEntity record);

    RoleEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleEntity record);

    int updateByPrimaryKey(RoleEntity record);
}