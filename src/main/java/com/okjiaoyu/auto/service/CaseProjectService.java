package com.okjiaoyu.auto.service;
import com.okjiaoyu.auto.common.BaseResponse;
import com.okjiaoyu.auto.vo.Project;

import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-19:15:42
 * Modify date: 2019-09-19:15:42
 */
public interface CaseProjectService {

    List<Project> caseProjectList(int pageNum, int pageSize);

    BaseResponse addProject(Project project);
}
