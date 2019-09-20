package com.okjiaoyu.auto.common;


import com.okjiaoyu.auto.common.errorcode.BaseErrorCode;
import com.okjiaoyu.auto.common.errorcode.common.CommonErrorCode;

public class ErrorResponse extends BaseResponse {

    public ErrorResponse() {
        this.setCode(CommonErrorCode.OP_FAILED.key());
        String message = getMessage(CommonErrorCode.OP_FAILED.value(), null);
        this.setMessage(message);
    }

    public ErrorResponse(BaseErrorCode baseErrorCode){
        this.setCode(Integer.parseInt(baseErrorCode.key().toString()));
        this.setMessage(baseErrorCode.value().toString());
    }
    public ErrorResponse(Integer code, String message, Object[] params) {
        this.setCode(code);
        String msg = getMessage(message, params);
        this.setMessage(msg);
    }

}
