package com.blingabc.auto.controller;

import com.blingabc.auto.common.ResultBody;
import com.blingabc.auto.dao.RequestWayVOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/common")
@RestController
public class CommonCrontroller {

    @Autowired
    private RequestWayVOMapper requestWayVOMapper;

    @RequestMapping("request_way")
    public ResultBody requestWayListCrontroller(){
        return ResultBody.success(requestWayVOMapper.queryList());
    }
}
