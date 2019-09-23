package com.okjiaoyu.auto.service.impl;

import com.okjiaoyu.auto.common.BaseResponse;
import com.okjiaoyu.auto.common.SuccessResponse;
import com.okjiaoyu.auto.dao.CookieEntityMapper;
import com.okjiaoyu.auto.service.ICookieService;
import com.okjiaoyu.auto.vo.CookieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-23:15:31
 * Modify date: 2019-09-23:15:31
 */
@Service
public class CookieServiceImpl implements ICookieService {

    @Autowired
    private CookieEntityMapper cookieEntityMapper;

    @Override
    public BaseResponse checkCookieExistService(Long systemId) {
        List<CookieEntity> cookieEntity =  cookieEntityMapper.queryCookieBySystemId(systemId);
        return new SuccessResponse(cookieEntity != null && cookieEntity.size() > 0);
    }

    @Override
    public BaseResponse getCookieBySystemIdService(Long systemId) {
        List<CookieEntity> cookieEntities =  cookieEntityMapper.queryCookieBySystemId(systemId);
        return new SuccessResponse(cookieEntities);
    }
}
