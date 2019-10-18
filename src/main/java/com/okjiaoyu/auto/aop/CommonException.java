package com.okjiaoyu.auto.aop;

import com.okjiaoyu.auto.vo.RequestExceptionEntity;
import lombok.Data;

import java.util.Date;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-23:13:27
 * Modify date: 2019-09-23:13:27
 */
@Data
public class CommonException extends RuntimeException {

    private RequestExceptionEntity requestExceptionEntity;

    public CommonException(String message){
        super(message);
        RequestExceptionEntity re = new RequestExceptionEntity();
        re.setMessage(message);
        re.setHappendTime(new Date());
        this.requestExceptionEntity = re;
    }

    public CommonException(RequestExceptionEntity requestExceptionEntity){
        super(requestExceptionEntity.getMessage());
        this.requestExceptionEntity = requestExceptionEntity;
    }
}
