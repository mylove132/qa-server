package com.okjiaoyu.auto.service;

import com.okjiaoyu.auto.common.BaseResponse;
import com.okjiaoyu.auto.vo.CaseEntity;
import com.okjiaoyu.auto.vo.ModuleEntity;

import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-20:14:28
 * Modify date: 2019-09-20:14:28
 */
public interface IModuleService {

    List<ModuleEntity> caseModuleList(int pageNum, int pageSize);

    BaseResponse addModule(ModuleEntity moduleEntity);

    BaseResponse updateModule(ModuleEntity moduleEntity);

    BaseResponse delModule(Integer moduleId);

}
