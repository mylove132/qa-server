package com.blingabc.auto.vo.response;

public class HttpResponseVO {

    private int statusCode;

    private String result;

    private String errMsg;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "HttpResponseVO{" +
                "statusCode=" + statusCode +
                ", result='" + result + '\'' +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}
