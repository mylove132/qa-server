package com.okjiaoyu.auto.service.impl;

import com.github.pagehelper.PageHelper;
import com.okjiaoyu.auto.common.BaseResponse;
import com.okjiaoyu.auto.common.ErrorResponse;
import com.okjiaoyu.auto.common.SuccessResponse;
import com.okjiaoyu.auto.common.errorcode.cases.ProjectErrorCode;
import com.okjiaoyu.auto.dao.ProjectMapper;
import com.okjiaoyu.auto.service.CaseProjectService;
import com.okjiaoyu.auto.vo.Project;
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
    private ProjectMapper projectMapper;

    @Override
    public List<Project> caseProjectList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Project> list=projectMapper.queryProject();
        return list;
    }

    @Transactional
    @Override
    public BaseResponse addProject(Project project) {
        try {
            projectMapper.insert(project);
            return new SuccessResponse(true);
        }catch (Exception e){
            log.error("添加项目失败：{}",e.getMessage());
            return new ErrorResponse(ProjectErrorCode.ADD_PROJECT_FAIL);
        }
    }
}
