package com.blingabc.auto.service;


import com.blingabc.auto.common.ResultBody;
import com.blingabc.auto.exception.BizException;

public interface ICaseService {

    ResultBody caseListService(Integer catalogId,Integer pageNum, Integer pageSize) throws BizException;

    ResultBody delCaseListService(int[] caseIds) throws BizException;
}
