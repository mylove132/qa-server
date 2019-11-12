package com.blingabc.auto.dao;

import com.blingabc.auto.beans.WebCaseVO;

public interface WebCaseVOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WebCaseVO record);

    int insertSelective(WebCaseVO record);

    WebCaseVO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WebCaseVO record);

    int updateByPrimaryKeyWithBLOBs(WebCaseVO record);

    int updateByPrimaryKey(WebCaseVO record);
}