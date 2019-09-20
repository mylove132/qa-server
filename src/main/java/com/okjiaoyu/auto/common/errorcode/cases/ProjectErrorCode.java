package com.okjiaoyu.auto.common.errorcode.cases;

import com.okjiaoyu.auto.common.errorcode.BaseErrorCode;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-20:10:47
 * Modify date: 2019-09-20:10:47
 */
public enum ProjectErrorCode implements BaseErrorCode{
    PROJECT_NOT_KNOWN_FAIL(2001, "添加未知错误"),
    PROJECT_NAME_EXIST(2002, "项目名称已存在"),
    PROJECT_ID_NOT_EXIST(2003, "项目id不存在"),
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
