package com.okjiaoyu.auto.common.errorcode.user;

import com.okjiaoyu.auto.common.errorcode.BaseErrorCode;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-19:11:07
 * Modify date: 2019-09-19:11:07
 */
public enum  UserErrorCode implements BaseErrorCode {
    DISABLED(0, "record has been disabled"),
    ENABLED(1, "record has been enabled"),
    DELETES(9, "record has been deleted"),
    CAS_LOGIN_FAIL(1001,"单点登录失败"),
    NOT_LOGIN(1002,"未登录"),
    ;

    private int code;

    private String desc;

    UserErrorCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public UserErrorCode get() {
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
