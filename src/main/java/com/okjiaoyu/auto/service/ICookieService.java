package com.okjiaoyu.auto.service;

import com.okjiaoyu.auto.common.BaseResponse;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-23:15:30
 * Modify date: 2019-09-23:15:30
 */
public interface ICookieService {

    BaseResponse checkCookieExistService(Long systemId);

    BaseResponse getCookieBySystemIdService(Long systemId);
}
