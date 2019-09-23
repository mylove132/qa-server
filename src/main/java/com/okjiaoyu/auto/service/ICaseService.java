package com.okjiaoyu.auto.service;

import com.okjiaoyu.auto.vo.CaseEntity;

import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-23:14:49
 * Modify date: 2019-09-23:14:49
 */
public interface ICaseService {

    List<CaseEntity> getCasesByModuleIdService(Integer moduleId, Integer pageNum, Integer pageSize);
}
