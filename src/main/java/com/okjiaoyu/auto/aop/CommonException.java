package com.okjiaoyu.auto.aop;

import com.okjiaoyu.auto.vo.RequestExceptionEntity;
import lombok.Data;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-23:13:27
 * Modify date: 2019-09-23:13:27
 */
@Data
public class CommonException extends RuntimeException {

    private RequestExceptionEntity requestExceptionEntity;

    public CommonException(RequestExceptionEntity requestExceptionEntity){
        super(requestExceptionEntity.getMessage());
        this.requestExceptionEntity = requestExceptionEntity;
    }
}
