package com.okjiaoyu.auto.service.impl;

import com.github.pagehelper.PageHelper;
import com.okjiaoyu.auto.common.BaseResponse;
import com.okjiaoyu.auto.common.SuccessResponse;
import com.okjiaoyu.auto.dao.ModuleEntityMapper;
import com.okjiaoyu.auto.service.IModuleService;
import com.okjiaoyu.auto.vo.CaseEntity;
import com.okjiaoyu.auto.vo.ModuleEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-20:14:30
 * Modify date: 2019-09-20:14:30
 */
@Slf4j
@Service
public class ModuleServiceImpl implements IModuleService {


    @Autowired
    private ModuleEntityMapper moduleEntityMapper;

    @Override
    public List<ModuleEntity> caseModuleList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ModuleEntity> list=moduleEntityMapper.queryModuleList();
        return list;
    }

    @Transactional
    @Override
    public BaseResponse addModule(ModuleEntity moduleEntity) {
        return new SuccessResponse(moduleEntityMapper.insertSelective(moduleEntity));
    }

    @Transactional
    @Override
    public BaseResponse updateModule(ModuleEntity moduleEntity) {
        return new SuccessResponse(moduleEntityMapper.updateByPrimaryKeySelective(moduleEntity));
    }

    @Transactional
    @Override
    public BaseResponse delModule(Integer moduleId) {
        return new SuccessResponse(moduleEntityMapper.deleteByPrimaryKey(moduleId));
    }

}
