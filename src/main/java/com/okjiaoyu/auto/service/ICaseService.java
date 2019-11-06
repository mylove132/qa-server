package com.okjiaoyu.auto.service;

import com.okjiaoyu.auto.common.ResultBody;
import com.okjiaoyu.auto.vo.CaseEntity;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-11-04:09:59
 * Modify date: 2019-11-04:09:59
 */
public interface ICaseService {

    ResultBody addCaseService(CaseEntity caseEntity);
}
