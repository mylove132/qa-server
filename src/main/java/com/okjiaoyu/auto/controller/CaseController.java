package com.okjiaoyu.auto.controller;
import com.okjiaoyu.auto.common.BaseResponse;
import com.okjiaoyu.auto.common.ErrorResponse;
import com.okjiaoyu.auto.common.PageInfo;
import com.okjiaoyu.auto.common.SuccessResponse;
import com.okjiaoyu.auto.common.errorcode.user.UserErrorCode;
import com.okjiaoyu.auto.service.CaseProjectService;
import com.okjiaoyu.auto.service.IModuleService;
import com.okjiaoyu.auto.util.HttpRequestUtil;
import com.okjiaoyu.auto.vo.ModuleEntity;
import com.okjiaoyu.auto.vo.ProjectEntity;
import com.okjiaoyu.auto.vo.request.LoginRequestVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.cookie.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-19:13:42
 * Modify date: 2019-09-19:13:42
 */
@Slf4j
@RequestMapping("/api/case")
@RestController
public class CaseController {

    @Autowired
    private CaseProjectService caseProjectService;

    @Autowired
    private IModuleService moduleService;

    @RequestMapping(value = "/caslogin",method = RequestMethod.POST)
    public BaseResponse casLoginCrontroller(LoginRequestVo loginRequestVo) throws Exception {
        List<Cookie> cookies = HttpRequestUtil.casLogin(loginRequestVo);
        if (cookies == null || cookies.size() == 0)
            return new ErrorResponse(UserErrorCode.CAS_LOGIN_FAIL);
        return new SuccessResponse(cookies);
    }

    @RequestMapping(value = "/project",method = RequestMethod.GET)
    public BaseResponse projectCrontroller(@RequestParam(defaultValue = "1",value = "currentPage") Integer pageNum,
                                           @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize){
        log.info("请求页码：{},请求条数：{}",pageNum,pageSize);
        List<ProjectEntity> projects = caseProjectService.caseProjectList(pageNum, pageSize);
        log.info("获取项目列表，返回结果：{}",projects);
        return new SuccessResponse(new PageInfo(projects));
    }

    @RequestMapping(value = "/project",method = RequestMethod.POST)
    public BaseResponse projectCrontroller(ProjectEntity projectEntity){
        log.info("添加case项目：{}", projectEntity.getName());
        return caseProjectService.addProject(projectEntity);
    }

    @RequestMapping(value = "/project",method = RequestMethod.PUT)
    public BaseResponse updateProjectCrontroller(ProjectEntity projectEntity){
        log.info("更新case项目：{]", projectEntity.getName());
        return caseProjectService.updateProject(projectEntity);
    }

    @RequestMapping(value = "/project",method = RequestMethod.DELETE)
    public BaseResponse updateProjectCrontroller(Integer projectId){
        log.info("删除case项目：{]", projectId);
        return caseProjectService.delProject(projectId);
    }

    @RequestMapping(value = "/module",method = RequestMethod.GET)
    public BaseResponse moduleCrontroller(@RequestParam(defaultValue = "1",value = "currentPage") Integer pageNum,
                                           @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize){
        log.info("请求页码：{},请求条数：{}",pageNum,pageSize);
        List<ModuleEntity> moduleList = moduleService.caseModuleList(pageNum, pageSize);
        log.info("获取项目列表，返回结果：{}",moduleList);
        return new SuccessResponse(new PageInfo(moduleList));
    }
}
