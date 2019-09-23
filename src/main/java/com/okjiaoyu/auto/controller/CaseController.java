package com.okjiaoyu.auto.controller;
import com.alibaba.fastjson.JSON;
import com.okjiaoyu.auto.annotion.AuthPermission;
import com.okjiaoyu.auto.annotion.Operation;
import com.okjiaoyu.auto.common.BaseResponse;
import com.okjiaoyu.auto.common.ErrorResponse;
import com.okjiaoyu.auto.common.PageInfo;
import com.okjiaoyu.auto.common.SuccessResponse;
import com.okjiaoyu.auto.common.constant.PermissionConstant;
import com.okjiaoyu.auto.common.errorcode.user.UserErrorCode;
import com.okjiaoyu.auto.dao.CookieEntityMapper;
import com.okjiaoyu.auto.service.CaseProjectService;
import com.okjiaoyu.auto.service.ICaseService;
import com.okjiaoyu.auto.service.ICookieService;
import com.okjiaoyu.auto.service.IModuleService;
import com.okjiaoyu.auto.util.HttpRequestUtil;
import com.okjiaoyu.auto.vo.CaseEntity;
import com.okjiaoyu.auto.vo.CookieEntity;
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

import java.util.Date;
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

    @Autowired
    private ICaseService caseService;

    @Autowired
    private CookieEntityMapper cookieEntityMapper;


    @Operation("单点登录")
    @RequestMapping(value = "/caslogin",method = RequestMethod.POST)
    public BaseResponse casLoginCrontroller(LoginRequestVo loginRequestVo) throws Exception {
        List<Cookie> cookies = HttpRequestUtil.casLogin(loginRequestVo);
        if (cookies == null || cookies.size() == 0)
            return new ErrorResponse(UserErrorCode.CAS_LOGIN_FAIL);
        if (cookieEntityMapper.queryCookieBySystemId(Long.parseLong(loginRequestVo.getUsername())) == null ||
                cookieEntityMapper.queryCookieBySystemId(Long.parseLong(loginRequestVo.getUsername())).size() == 0){
            for (Cookie cookie: cookies){
                CookieEntity cookieEntity = new CookieEntity();
                cookieEntity.setSystemId(Long.parseLong(loginRequestVo.getUsername()));
                cookieEntity.setcKey(cookie.getName());
                cookieEntity.setcValue(cookie.getValue());
                cookieEntity.setCreateTime(new Date());
                cookieEntity.setUpdateTime(new Date());
                cookieEntity.setExpireTime(cookie.getExpiryDate());
                cookieEntity.setEnvId(loginRequestVo.getEnvId());
                cookieEntityMapper.insertSelective(cookieEntity);
            }
        }else {
            for (Cookie cookie: cookies){
                CookieEntity cookieEntity = new CookieEntity();
                cookieEntity.setSystemId(Long.parseLong(loginRequestVo.getUsername()));
                cookieEntity.setcKey(cookie.getName());
                cookieEntity.setCreateTime(new Date());
                cookieEntity.setcValue(cookie.getValue());
                cookieEntity.setExpireTime(cookie.getExpiryDate());
                cookieEntity.setEnvId(loginRequestVo.getEnvId());
                cookieEntity.setUpdateTime(new Date());
                cookieEntityMapper.updateBySystemId(cookieEntity);
            }
        }

        return new SuccessResponse(cookies);
    }

    @Operation("获取项目列表")
    @RequestMapping(value = "/project",method = RequestMethod.GET)
    public BaseResponse projectCrontroller(@RequestParam(defaultValue = "1",value = "currentPage") Integer pageNum,
                                           @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize){
        List<ProjectEntity> projects = caseProjectService.caseProjectList(pageNum, pageSize);
        return new SuccessResponse(new PageInfo(projects));
    }

    //@AuthPermission(PermissionConstant.VIP)
    @Operation("添加项目")
    @RequestMapping(value = "/project",method = RequestMethod.POST)
    public BaseResponse projectCrontroller(ProjectEntity projectEntity){
        return caseProjectService.addProject(projectEntity);
    }

    //@AuthPermission(PermissionConstant.VIP)
    @Operation("更新项目")
    @RequestMapping(value = "/project",method = RequestMethod.PUT)
    public BaseResponse updateProjectCrontroller(ProjectEntity projectEntity){
        return caseProjectService.updateProject(projectEntity);
    }

    //@AuthPermission(PermissionConstant.VIP)
    @Operation("删除项目")
    @RequestMapping(value = "/project",method = RequestMethod.DELETE)
    public BaseResponse updateProjectCrontroller(Integer projectId){
        return caseProjectService.delProject(projectId);
    }

    //@Operation("获取模块列表")
    @RequestMapping(value = "/module",method = RequestMethod.GET)
    public BaseResponse moduleCrontroller(@RequestParam(defaultValue = "1",value = "currentPage") Integer pageNum,
                                           @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize){
        List<ModuleEntity> moduleList = moduleService.caseModuleList(pageNum, pageSize);
        return new SuccessResponse(new PageInfo(moduleList));
    }

    //@AuthPermission(PermissionConstant.VIP)
    @Operation("添加模块")
    @RequestMapping(value = "/module",method = RequestMethod.POST)
    public BaseResponse addModuleCrontroller(ModuleEntity moduleEntity){
        BaseResponse response = moduleService.addModule(moduleEntity);
        return response;
    }

    //@AuthPermission(PermissionConstant.VIP)
    @Operation("删除模块")
    @RequestMapping(value = "/module",method = RequestMethod.DELETE)
    public BaseResponse delModuleCrontroller(int moduleId){
        BaseResponse response = moduleService.delModule(moduleId);
        return response;
    }

    //@AuthPermission(PermissionConstant.VIP)
    @Operation("更新模块")
    @RequestMapping(value = "/module",method = RequestMethod.PUT)
    public BaseResponse updateModuleCrontroller(ModuleEntity moduleEntity){
        BaseResponse response = moduleService.updateModule(moduleEntity);
        return response;
    }

    //@AuthPermission(PermissionConstant.VIP)
    @Operation("通过模块id获取关联的case")
    @RequestMapping(value = "/getCases",method = RequestMethod.PUT)
    public BaseResponse getCasesByModuleIdCrontroller(Integer moduleId,@RequestParam(defaultValue = "1",value = "currentPage") Integer pageNum,
                                                      @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize){
        List<CaseEntity> caseEntities = caseService.getCasesByModuleIdService(moduleId,pageNum, pageSize);
        return new SuccessResponse(new PageInfo(caseEntities));
    }
}
