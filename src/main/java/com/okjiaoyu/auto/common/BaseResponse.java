package com.okjiaoyu.auto.common;


/**
 * 响应基本信息
 */
public interface BaseResponse {

    /** 错误码*/
    int getResultCode();

    /** 错误描述*/
    String getResultMsg();

}
