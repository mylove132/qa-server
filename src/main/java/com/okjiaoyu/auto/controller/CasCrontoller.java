package com.okjiaoyu.auto.controller;

import com.okjiaoyu.auto.annotion.Operation;
import com.okjiaoyu.auto.common.ResultBody;
import com.okjiaoyu.auto.service.ICasLoginService;
import com.okjiaoyu.auto.vo.CasLoginEntity;
import com.okjiaoyu.auto.vo.request.LoginRequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-10-22:15:33
 * Modify date: 2019-10-22:15:33
 */
@RequestMapping("/api/cas")
@RestController
public class CasCrontoller {

    @Autowired
    private ICasLoginService casLoginService;

    @Operation("单点登录")
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public ResultBody casLoginCrontroller(CasLoginEntity casLoginEntity){
        return casLoginService.casLoginService(casLoginEntity);
    }

    @Operation("单点登录测试")
    @RequestMapping(value = "test",method = RequestMethod.POST)
    public ResultBody casLoginTestCrontroller(LoginRequestVo loginRequestVo){
        return casLoginService.casLoginTestService(loginRequestVo);
    }


    @Operation("获取登录用户cookie")
    @RequestMapping(value = "cookies",method = RequestMethod.GET)
    public ResultBody casLoginUserCookiesCrontroller(@RequestParam(defaultValue = "1",value = "currentPage") Integer pageNum,
                                                       @RequestParam(defaultValue = "5",value = "pageSize") Integer pageSize){
        return casLoginService.queryUserCookiesService(pageNum,pageSize);
    }
}
