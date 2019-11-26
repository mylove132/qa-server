package com.blingabc.auto.dao;

import com.blingabc.auto.beans.CaseVO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CaseVOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CaseVO record);

    int insertSelective(CaseVO record);

    CaseVO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CaseVO record);

    int updateByPrimaryKeyWithBLOBs(CaseVO record);

    int updateByPrimaryKey(CaseVO record);

    List<CaseVO> queryCaseListFilter(@RequestParam(value = "catalogId",required = false) Integer catalogId, @RequestParam(value = "caseTypeId",required = false)Integer caseTypeId);
}