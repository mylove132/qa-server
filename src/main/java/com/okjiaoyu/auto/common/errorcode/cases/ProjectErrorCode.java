package com.okjiaoyu.auto.common.errorcode.cases;

import com.okjiaoyu.auto.common.errorcode.BaseErrorCode;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-20:10:47
 * Modify date: 2019-09-20:10:47
 */
public enum ProjectErrorCode implements BaseErrorCode{
    ADD_PROJECT_FAIL(2001, "添加项目失败"),
    ENABLED(1, "record has been enabled"),
    DELETES(9, "record has been deleted"),
    CAS_LOGIN_FAIL(1001,"单点登录失败"),
            ;

    private int code;

    private String desc;

    ProjectErrorCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public ProjectErrorCode get() {
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
