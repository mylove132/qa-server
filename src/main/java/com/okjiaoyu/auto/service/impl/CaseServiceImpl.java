package com.okjiaoyu.auto.service.impl;

import com.github.pagehelper.PageHelper;
import com.okjiaoyu.auto.dao.CaseEntityMapper;
import com.okjiaoyu.auto.service.ICaseService;
import com.okjiaoyu.auto.vo.CaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-23:14:50
 * Modify date: 2019-09-23:14:50
 */
@Service
public class CaseServiceImpl implements ICaseService {

    @Autowired
    private CaseEntityMapper caseEntityMapper;

    @Override
    public List<CaseEntity> getCasesByModuleIdService(Integer moduleId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CaseEntity> list=caseEntityMapper.queryCaseListWithModuleId(moduleId);
        return list;
    }
}
