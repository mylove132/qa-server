package com.okjiaoyu.auto.config;

import com.alibaba.fastjson.JSON;
import com.okjiaoyu.auto.aop.CommonException;
import com.okjiaoyu.auto.common.BaseResponse;
import com.okjiaoyu.auto.common.ErrorResponse;
import com.okjiaoyu.auto.common.errorcode.common.CommonErrorCode;
import com.okjiaoyu.auto.vo.RequestExceptionEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-19:12:24
 * Modify date: 2019-09-19:12:24
 */
@Slf4j
@ControllerAdvice
public class CommonControllerAdvice {

    /**
     * 全局异常处理，反正异常返回统一格式的map
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = CommonException.class)
    public BaseResponse exceptionHandler(CommonException exception){
        return new ErrorResponse(exception.getRequestExceptionEntity());
    }
}
