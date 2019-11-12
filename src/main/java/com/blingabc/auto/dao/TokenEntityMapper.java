package com.blingabc.auto.dao;

import com.blingabc.auto.vo.TokenEntity;

public interface TokenEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TokenEntity record);

    int insertSelective(TokenEntity record);

    TokenEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TokenEntity record);

    int updateByPrimaryKey(TokenEntity record);

    TokenEntity selectByToken(String token);

    TokenEntity selectByUserId(int userId);
}
