package com.okjiaoyu.auto.service.impl;

import com.github.pagehelper.PageHelper;
import com.okjiaoyu.auto.dao.ModuleEntityMapper;
import com.okjiaoyu.auto.service.ISearchService;
import com.okjiaoyu.auto.vo.ModuleEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-20:17:47
 * Modify date: 2019-09-20:17:47
 */
@Slf4j
@Service
public class SearchServiceImpl implements ISearchService {

    @Autowired
    private ModuleEntityMapper moduleEntityMapper;

    @Override
    public List<ModuleEntity> filterModuleListByEnvIdService(Integer envId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ModuleEntity> moduleEntities = moduleEntityMapper.filterModuleListByEnvId(envId);
        return moduleEntities;
    }

    @Override
    public List<ModuleEntity> filterModuleListByProjectIdService(Integer projectId,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ModuleEntity> moduleEntities = moduleEntityMapper.filterModuleListByProjectId(projectId);
        return moduleEntities;
    }

    @Override
    public List<ModuleEntity> filterModuleListByProjectIdAndEnvIdService(Integer projectId, Integer envId,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ModuleEntity> moduleEntities = moduleEntityMapper.filterModuleListByProjectIdAndEnvId(projectId,envId);
        return moduleEntities;
    }
}
