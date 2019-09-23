package com.okjiaoyu.auto.controller;

import com.okjiaoyu.auto.common.BaseResponse;
import com.okjiaoyu.auto.service.ICookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-23:15:27
 * Modify date: 2019-09-23:15:27
 */
@RestController
@RequestMapping("/api/cookie")
public class CookieCrontroller {

    @Autowired
    private ICookieService cookieService;

    @RequestMapping("checkCookie")
    public BaseResponse checkCookieExistCrontroll(Long systemId){
        return cookieService.checkCookieExistService(systemId);
    }

    @RequestMapping("getCookies")
    public BaseResponse getCookiesCrontroll(Long systemId){
        return cookieService.getCookieBySystemIdService(systemId);
    }
}
