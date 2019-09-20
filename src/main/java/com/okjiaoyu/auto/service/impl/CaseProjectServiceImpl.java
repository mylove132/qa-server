package com.okjiaoyu.auto.service.impl;

import com.github.pagehelper.PageHelper;
import com.okjiaoyu.auto.common.BaseResponse;
import com.okjiaoyu.auto.common.ErrorResponse;
import com.okjiaoyu.auto.common.SuccessResponse;
import com.okjiaoyu.auto.common.errorcode.cases.ProjectErrorCode;
import com.okjiaoyu.auto.common.errorcode.common.CommonErrorCode;
import com.okjiaoyu.auto.dao.ProjectEntityMapper;
import com.okjiaoyu.auto.service.CaseProjectService;
import com.okjiaoyu.auto.vo.ProjectEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-19:15:45
 * Modify date: 2019-09-19:15:45
 */
@Slf4j
@Service
public class CaseProjectServiceImpl implements CaseProjectService {

    @Autowired
    private ProjectEntityMapper projectEntityMapper;

    @Override
    public List<ProjectEntity> caseProjectList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProjectEntity> list=projectEntityMapper.queryProject();
        return list;
    }

    @Transactional
    @Override
    public BaseResponse updateProject(ProjectEntity projectEntity) {
        if (projectEntity.getId() == null||projectEntity.getId() == 0||projectEntity.getName() == null || "".equals(projectEntity.getName())){
            log.error("更新项目名称name不能为空");
            return new ErrorResponse(CommonErrorCode.PARAM_NULL);
        }
        ProjectEntity pe = projectEntityMapper.selectByName(projectEntity.getName());
        if (pe != null && pe.getId() != projectEntity.getId()){
            log.error("项目名称{}已存在！！！",projectEntity.getName());
            return new ErrorResponse(ProjectErrorCode.PROJECT_NAME_EXIST);
        }
        try {
            projectEntityMapper.updateByPrimaryKey(projectEntity);
            return new SuccessResponse(true);
        }catch (Exception e){
            log.error("更新项目失败：{}",e.getMessage());
            return new ErrorResponse(ProjectErrorCode.PROJECT_NOT_KNOWN_FAIL);
        }
    }


    @Transactional
    @Override
    public BaseResponse addProject(ProjectEntity projectEntity) {
        if (projectEntity.getName() == null || "".equals(projectEntity.getName())){
            log.error("添加项目名称name不能为空");
            return new ErrorResponse(CommonErrorCode.PARAM_NULL);
        }
        ProjectEntity pe = projectEntityMapper.selectByName(projectEntity.getName());
        if (pe != null){
            log.error("项目名称{}已存在！！！",projectEntity.getName());
            return new ErrorResponse(ProjectErrorCode.PROJECT_NAME_EXIST);
        }
        try {
            projectEntityMapper.insertSelective(projectEntity);
            return new SuccessResponse(true);
        }catch (Exception e){
            log.error("添加项目失败：{}",e.getMessage());
            return new ErrorResponse(ProjectErrorCode.PROJECT_NOT_KNOWN_FAIL);
        }
    }

    @Transactional
    @Override
    public BaseResponse delProject(Integer projectId) {
        if (projectId == null || projectId == 0){
            log.error("删除项目名称id不能为空");
            return new ErrorResponse(CommonErrorCode.PARAM_NULL);
        }
        ProjectEntity pe = projectEntityMapper.selectByPrimaryKey(projectId);
        if (pe == null){
            log.error("删除项目id{}不存在！！！",projectId);
            return new ErrorResponse(ProjectErrorCode.PROJECT_ID_NOT_EXIST);
        }
        try {
            projectEntityMapper.deleteByPrimaryKey(projectId);
            return new SuccessResponse(true);
        }catch (Exception e){
            log.error("删除项目失败：{}",e.getMessage());
            return new ErrorResponse(ProjectErrorCode.PROJECT_NOT_KNOWN_FAIL);
        }
    }
}
