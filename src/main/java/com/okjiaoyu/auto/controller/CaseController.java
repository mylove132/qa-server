package com.okjiaoyu.auto.controller;
import com.okjiaoyu.auto.common.BaseResponse;
import com.okjiaoyu.auto.common.ErrorResponse;
import com.okjiaoyu.auto.common.PageInfo;
import com.okjiaoyu.auto.common.SuccessResponse;
import com.okjiaoyu.auto.common.errorcode.user.UserErrorCode;
import com.okjiaoyu.auto.service.CaseProjectService;
import com.okjiaoyu.auto.util.HttpRequestUtil;
import com.okjiaoyu.auto.vo.Project;
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
        List<Project> projects = caseProjectService.caseProjectList(pageNum, pageSize);
        log.info("获取项目列表，返回结果：{}",projects);
        return new SuccessResponse(new PageInfo(projects));
    }
}
