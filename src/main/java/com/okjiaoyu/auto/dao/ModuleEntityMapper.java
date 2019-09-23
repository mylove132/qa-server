package com.okjiaoyu.auto.dao;
import com.okjiaoyu.auto.vo.ModuleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ModuleEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ModuleEntity record);

    int insertSelective(ModuleEntity record);

    ModuleEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ModuleEntity record);

    int updateByPrimaryKey(ModuleEntity record);

    List<ModuleEntity> queryModuleList();

    List<ModuleEntity> filterModuleListByEnvId(Integer envId);

    List<ModuleEntity> filterModuleListByProjectId(Integer projectId);

    List<ModuleEntity> filterModuleListByProjectIdAndEnvId(@Param("projectId") Integer projectId, @Param("envId")Integer envId);

    List<ModuleEntity> filterModuleListByKeyword(@Param("keyword") String keyword);

}
