package com.blingabc.auto.service.impl;

import com.blingabc.auto.common.PageInfo;
import com.blingabc.auto.common.ResultBody;
import com.blingabc.auto.dao.CaseVOMapper;
import com.blingabc.auto.exception.BizException;
import com.blingabc.auto.service.ICaseService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaseServiceImpl implements ICaseService {

    @Autowired
    private CaseVOMapper caseVOMapper;

    @Override
    public ResultBody caseListService(Integer catalogId,Integer pageNum, Integer pageSize) throws BizException {
        PageHelper.startPage(pageNum, pageSize);
        return ResultBody.success(new PageInfo(caseVOMapper.queryCaseListFilter(catalogId)));
    }

    @Override
    public ResultBody delCaseListService(int[] caseIds) throws BizException {
        return ResultBody.success(caseVOMapper.delCaseListById(caseIds));
    }
}
