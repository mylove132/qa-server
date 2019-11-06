package com.okjiaoyu.auto.service.impl;

import com.github.pagehelper.PageHelper;
import com.okjiaoyu.auto.common.PageInfo;
import com.okjiaoyu.auto.common.ResultBody;
import com.okjiaoyu.auto.common.errorcode.CommonCode;
import com.okjiaoyu.auto.common.errorcode.user.UserErrorCode;
import com.okjiaoyu.auto.dao.CasLoginEntityMapper;
import com.okjiaoyu.auto.dao.CookieEntityMapper;
import com.okjiaoyu.auto.exception.BizException;
import com.okjiaoyu.auto.service.ICasLoginService;
import com.okjiaoyu.auto.util.HttpRequestUtil;
import com.okjiaoyu.auto.vo.CasLoginEntity;
import com.okjiaoyu.auto.vo.CaseEntity;
import com.okjiaoyu.auto.vo.CookieEntity;
import com.okjiaoyu.auto.vo.request.LoginRequestVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.cookie.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-10-22:14:56
 * Modify date: 2019-10-22:14:56
 */
@Slf4j
@Service
public class CasLoginServiceImpl implements ICasLoginService {

    @Autowired
    private CasLoginEntityMapper casLoginEntityMapper;

    @Autowired
    private CookieEntityMapper cookieEntityMapper;

    @Transactional
    @Override
    public ResultBody casLoginService(CasLoginEntity casLoginEntity) throws BizException {
        if (casLoginEntity.getCreateTime() == null){
            casLoginEntity.setCreateTime(new Date());
        }
        if (casLoginEntity.getUpdateTime() == null){
            casLoginEntity.setUpdateTime(new Date());
        }
        if (casLoginEntity == null)
            return ResultBody.error(CommonCode.BODY_NOT_MATCH);
        String username = casLoginEntity.getUsername();
        String password = casLoginEntity.getPassword();
        String domain = casLoginEntity.getDomain();
        if (username == "" || username == null)
            return ResultBody.error(CommonCode.BODY_NOT_MATCH);
        if (password == "" || password == null)
            return ResultBody.error(CommonCode.BODY_NOT_MATCH);
        if (domain == "" || domain == null)
            return ResultBody.error(CommonCode.BODY_NOT_MATCH);
        List<Cookie> cookies = null;
        try {
            LoginRequestVo loginRequestVo = new LoginRequestVo();
            loginRequestVo.setPassword(password);
            loginRequestVo.setUsername(username);
            loginRequestVo.setUrl(domain);
            cookies = HttpRequestUtil.casLogin(loginRequestVo);
        }catch (Exception e){
            throw new RuntimeException(String.format("单点登录失败：{}", e.getMessage()));
        }
        if (cookies == null || cookies.size() <= 0){
            return ResultBody.error(UserErrorCode.CAS_LOGIN_FAIL);
        }
        casLoginEntityMapper.insertSelective(casLoginEntity);
        for (Cookie cookie:cookies){
            CookieEntity cookieEntity = new CookieEntity();
            cookieEntity.setCreateTime(new Date());
            cookieEntity.setUpdateTime(new Date());
            cookieEntity.setcKey(cookie.getName());
            cookieEntity.setcValue(cookie.getValue());
            cookieEntity.setDomain(cookie.getDomain());
            cookieEntity.setExpireTime(cookie.getExpiryDate());
            cookieEntity.setCasLoginId(casLoginEntity.getId());
            cookieEntityMapper.insertSelective(cookieEntity);
        }
        return ResultBody.success(true);
    }

    @Override
    public ResultBody casLoginTestService(LoginRequestVo loginRequestVo) throws BizException {
        try {
            return ResultBody.success(HttpRequestUtil.casLogin(loginRequestVo));
        } catch (Exception e) {
            throw new BizException(e.getMessage());
        }
    }

    @Override
    public ResultBody casLoginUpdateService(LoginRequestVo loginRequestVo) throws BizException {
        int id = loginRequestVo.getId();
        CasLoginEntity casLoginEntity = casLoginEntityMapper.selectByPrimaryKey(id);
        if (casLoginEntity == null){
            return ResultBody.error(CommonCode.REQUEST_PARAM_ERROR);
        }
        List<CookieEntity> oldCookies = cookieEntityMapper.queryCookiesByCasLoginId(id);
        if (oldCookies == null || oldCookies.size()<=0){
            return ResultBody.error("cookie不存在");
        }
        List<Cookie> cookies = null;
        try {
            cookies = HttpRequestUtil.casLogin(loginRequestVo);
        }catch (Exception e){
            throw new BizException("更新cookie失败:"+e.getMessage());
        }
        List<CookieEntity> newCookies = new ArrayList<>();
        for (CookieEntity oldCookie:oldCookies){
            for (Cookie cookie:cookies){
                if (oldCookie.getcKey().equals(cookie.getName())){
                    CookieEntity ck = new CookieEntity();
                    ck.setcKey(cookie.getName());
                    ck.setcValue(cookie.getValue());
                    ck.setExpireTime(cookie.getExpiryDate());
                    ck.setCreateTime(oldCookie.getCreateTime());
                    ck.setUpdateTime(new Date());
                    ck.setCasLoginId(oldCookie.getCasLoginId());
                    ck.setCaseId(oldCookie.getCaseId());
                    ck.setDomain(oldCookie.getDomain());
                    ck.setId(oldCookie.getId());
                    newCookies.add(ck);
                }
            }
        }
        for (CookieEntity newCookie:newCookies){
            log.info("新的cookie key:{},value:{},expire:{},domain:{},id:{}",newCookie.getcKey(),newCookie.getcValue(),
                    newCookie.getExpireTime(),newCookie.getDomain(),newCookie.getId());
        }
        cookieEntityMapper.updateCookieBatch(newCookies);
        return ResultBody.success(true);
    }

    @Override
    public ResultBody queryUserCookiesService(Integer pageNum, Integer pageSize) throws BizException {
        PageHelper.startPage(pageNum, pageSize);
        return ResultBody.success(new PageInfo(casLoginEntityMapper.queryUserCookiesList()));
    }
}
