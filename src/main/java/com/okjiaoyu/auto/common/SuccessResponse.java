package com.okjiaoyu.auto.common;

import com.okjiaoyu.auto.common.errorcode.common.CommonErrorCode;
import lombok.Data;

@Data
public class SuccessResponse<T> extends BaseResponse {

    private T data;

    public SuccessResponse(T data) {
        this.setCode(CommonErrorCode.OP_SUCCESS.key());
        this.setData(data);
        String message = getMessage(CommonErrorCode.OP_SUCCESS.value(), null);
        this.setMessage(message);
    }

    public SuccessResponse(T data, String message) {
        this.setCode(CommonErrorCode.OP_SUCCESS.key());
        this.setData(data);
        this.setMessage(message);
    }

}
