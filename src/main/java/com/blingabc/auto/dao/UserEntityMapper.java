package com.blingabc.auto.dao;

import com.blingabc.auto.beans.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserEntity record);

    int insertSelective(UserEntity record);

    UserEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserEntity record);

    int updateByPrimaryKey(UserEntity record);

    List<UserEntity> queryUserList();

    UserEntity selectByEmail(@Param("email") String email);

    UserEntity selectByAccount(@Param("account") String account);
}
