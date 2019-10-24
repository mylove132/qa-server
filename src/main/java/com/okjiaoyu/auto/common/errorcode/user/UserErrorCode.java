package com.okjiaoyu.auto.common.errorcode.user;
import com.okjiaoyu.auto.common.BaseResponse;


/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-19:11:07
 * Modify date: 2019-09-19:11:07
 */
public enum  UserErrorCode implements BaseResponse {
    DISABLED(0, "record has been disabled"),
    ENABLED(1, "record has been enabled"),
    DELETES(9, "record has been deleted"),
    CAS_LOGIN_FAIL(1001,"单点登录失败"),
    NOT_LOGIN(1002,"未登录"),
    ;
    /**
     * 错误码
     */
    private int resultCode;
    /**
     * 错误描述
     */
    private String resultMsg;

    UserErrorCode(int resultCode, String resultMsg){
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
