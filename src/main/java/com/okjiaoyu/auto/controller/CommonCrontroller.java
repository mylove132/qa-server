package com.okjiaoyu.auto.controller;
import com.okjiaoyu.auto.annotion.Operation;
import com.okjiaoyu.auto.common.ResultBody;
import com.okjiaoyu.auto.dao.EnvEntityMapper;
import com.okjiaoyu.auto.dao.RequestWayEntityMapper;
import com.okjiaoyu.auto.vo.EnvEntity;
import com.okjiaoyu.auto.vo.RequestWayEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-20:17:30
 * Modify date: 2019-09-20:17:30
 */
@Slf4j
@RestController
@RequestMapping("/api/common")
public class CommonCrontroller {

    @Autowired
    private EnvEntityMapper envEntityMapper;

    @Autowired
    private RequestWayEntityMapper requestWayEntityMapper;

    @Operation("获取环境列表")
    @RequestMapping(value = "env",method = RequestMethod.GET)
    public ResultBody getEnvListCrontroller(){
        List<EnvEntity> envEntities =  envEntityMapper.queryEnvList();
        return ResultBody.success(envEntities);
    }

    @Operation("获取请求方式")
    @RequestMapping(value = "requestWay",method = RequestMethod.GET)
    public ResultBody getRequestWayCrontroller(){
        List<RequestWayEntity> requestWayEntities =  requestWayEntityMapper.queryRequestWay();
        return ResultBody.success(requestWayEntities);
    }
}
