package com.okjiaoyu.auto.common.errorcode.common;
import com.okjiaoyu.auto.common.errorcode.BaseErrorCode;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-19:11:50
 * Modify date: 2019-09-19:11:50
 */
public enum CommonErrorCode implements BaseErrorCode {

    OP_SUCCESS(0, "operation.success"),
    OP_FAILED(-1, "operation.failed"),
    ;

    private int code;

    private String desc;

    CommonErrorCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public CommonErrorCode get() {
        return this;
    }

    @Override
    public Integer key() {
        return this.code;
    }

    @Override
    public String value() {
        return this.desc;
    }
}
