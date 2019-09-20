package com.okjiaoyu.auto.config;

import com.okjiaoyu.auto.common.BaseResponse;
import com.okjiaoyu.auto.common.ErrorResponse;
import com.okjiaoyu.auto.common.errorcode.common.CommonErrorCode;
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
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public BaseResponse exceptionHandler(Exception ex){
        log.error("系统错误信息：{}",ex.getMessage());
        return new ErrorResponse(CommonErrorCode.OP_FAILED);
    }
}
