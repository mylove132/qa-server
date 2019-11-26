package com.blingabc.auto.controller;

import com.blingabc.auto.common.ResultBody;
import com.blingabc.auto.dao.CaseTypeVOMapper;
import com.blingabc.auto.dao.EnvVOMapper;
import com.blingabc.auto.dao.RequestWayVOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/common")
@RestController
public class CommonCrontroller {

    @Autowired
    private RequestWayVOMapper requestWayVOMapper;

    @Autowired
    private CaseTypeVOMapper caseTypeVOMapper;

    @Autowired
    private EnvVOMapper envVOMapper;

    @RequestMapping("request_way")
    public ResultBody requestWayListCrontroller(){
        return ResultBody.success(requestWayVOMapper.queryList());
    }

    @RequestMapping("env")
    public ResultBody envListCrontroller(){
        return ResultBody.success(envVOMapper.queryEnvList());
    }

    @RequestMapping("case_type")
    public ResultBody caseTypeListCrontroller(){
        return ResultBody.success(caseTypeVOMapper.queryList());
    }
}
