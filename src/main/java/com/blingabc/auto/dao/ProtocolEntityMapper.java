package com.blingabc.auto.dao;

import com.blingabc.auto.vo.ProtocolEntity;

public interface ProtocolEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProtocolEntity record);

    int insertSelective(ProtocolEntity record);

    ProtocolEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProtocolEntity record);

    int updateByPrimaryKey(ProtocolEntity record);
}