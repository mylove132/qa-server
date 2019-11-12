package com.blingabc.auto.common.errorcode.catalog;
import com.blingabc.auto.common.BaseResponse;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-19:11:50
 * Modify date: 2019-09-19:11:50
 */
public enum CatalogErrorCode implements BaseResponse {

    EXIST_SOON_ID(2001, "存在子级目录，不能直接删除父级目录"),
    ;

    private int resultCode;

    private String resultMsg;

    CatalogErrorCode(int resultCode, String resultMsg){
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
