package com.okjiaoyu.auto.common.errorcode;

import com.okjiaoyu.auto.common.BaseResponse;

/**
 * 公共常量
 */
public enum CommonCode implements BaseResponse {

    SUCCESS(0, "成功!"),
    BODY_NOT_MATCH(400, "请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH(401, "请求的数字签名不匹配!"),
    NOT_FOUND(404, "未找到该资源!"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
    SERVER_BUSY(503, "服务器正忙，请稍后再试!"),
    REQUEST_PARAM_ERROR(40000,"请求参数错误"),
    REQUEST_METHOD_SUPPORT_ERROR(40001,"当前请求方法不支持");
    /**
     * 错误码
     */
    private int resultCode;
    /**
     * 错误描述
     */
    private String resultMsg;

    CommonCode(int resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public int getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }

}
