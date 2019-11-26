package com.blingabc.auto.dao;

import com.blingabc.auto.beans.InterfaceCaseVO;
import com.blingabc.auto.beans.InterfaceCaseVOWithBLOBs;
import com.blingabc.auto.common.ResultBody;

import java.util.List;

public interface InterfaceCaseVOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InterfaceCaseVOWithBLOBs record);

    int insertSelective(InterfaceCaseVOWithBLOBs record);

    InterfaceCaseVOWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InterfaceCaseVOWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(InterfaceCaseVOWithBLOBs record);

    int updateByPrimaryKey(InterfaceCaseVO record);

    List<InterfaceCaseVO> queryInterfaceCasesByCatalogId(int catalogId);
}