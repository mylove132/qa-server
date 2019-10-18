package com.okjiaoyu.auto.common.errorcode.catalog;
import com.okjiaoyu.auto.common.errorcode.BaseErrorCode;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-19:11:50
 * Modify date: 2019-09-19:11:50
 */
public enum CatalogErrorCode implements BaseErrorCode {

    EXIST_SOON_ID(2001, "存在子级目录，不能直接删除父级目录"),
    ;

    private int code;

    private String desc;

    CatalogErrorCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public CatalogErrorCode get() {
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
