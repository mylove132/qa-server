package com.okjiaoyu.auto.common;


import com.okjiaoyu.auto.common.errorcode.BaseErrorCode;
import com.okjiaoyu.auto.common.errorcode.common.CommonErrorCode;
import lombok.Data;

@Data
public class ErrorResponse<T> extends BaseResponse {

    private T data;

    public ErrorResponse() {
        this.setCode(CommonErrorCode.OP_FAILED.key());
        String message = getMessage(CommonErrorCode.OP_FAILED.value(), null);
        this.setMessage(message);
    }

    public ErrorResponse(BaseErrorCode baseErrorCode){
        this.setCode(Integer.parseInt(baseErrorCode.key().toString()));
        this.setMessage(baseErrorCode.value().toString());
    }

    public ErrorResponse(T data, String message) {
        this.setCode(CommonErrorCode.OP_FAILED.key());
        this.setData(data);
        this.setMessage(message);
    }

    public ErrorResponse(T data) {
        this.setCode(CommonErrorCode.OP_FAILED.key());
        this.setData(data);
    }

    public ErrorResponse(Integer code, String message, Object[] params) {
        this.setCode(code);
        String msg = getMessage(message, params);
        this.setMessage(msg);
    }



}
