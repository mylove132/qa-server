package com.okjiaoyu.auto.common;

import com.okjiaoyu.auto.util.MessageUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 响应基本信息
 */
@Data
@Slf4j
public class BaseResponse {

    private Integer code;

    private String message;

    public String getMessage(String result, Object[] params) {
        return MessageUtil.getMessage(result, params);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
