package com.okjiaoyu.auto.controller;

import com.okjiaoyu.auto.common.BaseResponse;
import com.okjiaoyu.auto.common.SuccessResponse;
import com.okjiaoyu.auto.dao.EnvEntityMapper;
import com.okjiaoyu.auto.vo.EnvEntity;
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

    @RequestMapping(value = "/env",method = RequestMethod.GET)
    public BaseResponse envList(){
        log.info("获取环境列表");
        List<EnvEntity> envEntities = envEntityMapper.queryEnvList();
        log.info("环境列表：{}",envEntities);
        return new SuccessResponse(envEntities);
    }
}
