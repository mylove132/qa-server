package com.okjiaoyu.auto.service;

import com.okjiaoyu.auto.vo.ModuleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-20:17:36
 * Modify date: 2019-09-20:17:36
 */
public interface ISearchService {

    List<ModuleEntity> filterModuleListByEnvIdService(Integer envId,int pageNum, int pageSize);

    List<ModuleEntity> filterModuleListByProjectIdService(Integer projectId,int pageNum, int pageSize);

    List<ModuleEntity> filterModuleListByProjectIdAndEnvIdService(@Param("projectId") Integer projectId, @Param("envId")Integer envId,int pageNum, int pageSize);
}
