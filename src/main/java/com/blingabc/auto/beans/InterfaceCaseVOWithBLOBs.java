package com.blingabc.auto.beans;

public class InterfaceCaseVOWithBLOBs extends InterfaceCaseVO {
    private String param;

    private String header;

    private String cookie;

    private String assertObj;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param == null ? null : param.trim();
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header == null ? null : header.trim();
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie == null ? null : cookie.trim();
    }

    public String getAssertObj() {
        return assertObj;
    }

    public void setAssertObj(String assertObj) {
        this.assertObj = assertObj;
    }
}