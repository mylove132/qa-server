package com.okjiaoyu.auto.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.okjiaoyu.auto.annotion.Operation;
import com.okjiaoyu.auto.config.CommonControllerAdvice;
import com.okjiaoyu.auto.dao.RequestExceptionEntityMapper;
import com.okjiaoyu.auto.dao.RequestLogEntityMapper;
import com.okjiaoyu.auto.util.DateUtil;
import com.okjiaoyu.auto.util.RequestUtil;
import com.okjiaoyu.auto.vo.RequestExceptionEntity;
import com.okjiaoyu.auto.vo.RequestLogEntity;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-23:10:35
 * Modify date: 2019-09-23:10:35
 */
@Slf4j
@Aspect
@Component
public class RequestLogAspect {

    private RequestLogEntity requestLogEntity = new RequestLogEntity();

    private RequestExceptionEntity requestExceptionEntity = new RequestExceptionEntity();

    private long startTime;

    private long returnTime;

    @Autowired
    private RequestExceptionEntityMapper requestExceptionEntityMapper;

    @Autowired
    private RequestLogEntityMapper requestLogEntityMapper;

    @Autowired
    private HttpServletRequest request;

    @Pointcut(value = "@annotation(operation)")
    public void serviceStatistics(Operation operation){

    }

    @Before("serviceStatistics(operation)")
    public void doBefore(JoinPoint joinPoint, Operation operation){
        Map<String,Object> joinPointInfo = RequestUtil.getJoinPointInfoMap(joinPoint);
        startTime = System.currentTimeMillis();
        requestLogEntity.setStartTime(DateUtil.parse(startTime));
        requestLogEntity.setIp(RequestUtil.getRequetIp(request));
        requestLogEntity.setClassPath(joinPointInfo.get("classPath").toString());
        requestLogEntity.setMethodName(joinPointInfo.get("methodName").toString());
        requestLogEntity.setWay(request.getMethod());
        requestLogEntity.setParam((String) joinPointInfo.get("paramMap"));
        requestLogEntity.setType(RequestUtil.getRequestType(request));
        requestLogEntity.setSessionid(request.getSession().getId());
        requestLogEntity.setUrl(request.getRequestURL().toString());
        requestLogEntity.setOperation(operation.value());
        log.info("request====>"+JSON.toJSONString(requestLogEntity,
                SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteMapNullValue));
    }

    @AfterReturning(value = "serviceStatistics(operation)",returning = "returnObj")
    public void doAfterReturning(Operation operation,Object returnObj){
        returnTime = System.currentTimeMillis();
        requestLogEntity.setReturnTime(DateUtil.parse(returnTime));
        requestLogEntity.setFinishTime(DateUtil.timeDifferLong(startTime,returnTime));
        requestLogEntity.setReturnData(JSON.toJSONString(returnObj,
                SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteMapNullValue));
        log.info("response====>"+JSON.toJSONString(returnObj,
                SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteMapNullValue));
        requestLogEntityMapper.insertSelective(requestLogEntity);
    }

    @AfterThrowing(value = "serviceStatistics(operation)",throwing = "e")
    public void doAfterThrowing(Operation operation, Throwable e){
        long happendTime = System.currentTimeMillis();
        requestExceptionEntity.setHappendTime(DateUtil.parse(happendTime));
        requestExceptionEntity.setMessage(e.getMessage());
        log.error("exception====>"+JSON.toJSONString(e,
                SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteMapNullValue));
        requestExceptionEntityMapper.insertSelective(requestExceptionEntity);
        throw new CommonException(requestExceptionEntity);

    }
}
