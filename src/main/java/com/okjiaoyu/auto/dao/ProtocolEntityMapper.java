package com.okjiaoyu.auto.dao;

import com.okjiaoyu.auto.vo.ProtocolEntity;

public interface ProtocolEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProtocolEntity record);

    int insertSelective(ProtocolEntity record);

    ProtocolEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProtocolEntity record);

    int updateByPrimaryKey(ProtocolEntity record);
}