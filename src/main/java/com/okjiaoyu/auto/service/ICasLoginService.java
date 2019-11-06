package com.okjiaoyu.auto.service;

import com.okjiaoyu.auto.common.ResultBody;
import com.okjiaoyu.auto.exception.BizException;
import com.okjiaoyu.auto.vo.CasLoginEntity;
import com.okjiaoyu.auto.vo.request.LoginRequestVo;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-10-22:14:55
 * Modify date: 2019-10-22:14:55
 */
public interface ICasLoginService {

    ResultBody casLoginService(CasLoginEntity casLoginEntity) throws BizException;

    ResultBody casLoginTestService(LoginRequestVo loginRequestVo) throws BizException;

    ResultBody casLoginUpdateService(LoginRequestVo loginRequestVo) throws BizException;

    ResultBody queryUserCookiesService(Integer pageNum, Integer pageSize) throws BizException;
}
