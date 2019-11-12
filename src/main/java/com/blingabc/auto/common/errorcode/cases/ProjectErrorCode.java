package com.blingabc.auto.common.errorcode.cases;

import com.blingabc.auto.common.BaseResponse;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-20:10:47
 * Modify date: 2019-09-20:10:47
 */
public enum ProjectErrorCode implements BaseResponse{
    PROJECT_NOT_KNOWN_FAIL(2001, "添加未知错误"),
    PROJECT_NAME_EXIST(2002, "项目名称已存在"),
    PROJECT_ID_NOT_EXIST(2003, "项目id不存在"),
            ;

    private int resultCode;

    private String resultMsg;

    ProjectErrorCode(int resultCode, String resultMsg){
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
