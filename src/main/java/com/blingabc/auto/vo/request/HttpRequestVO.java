package com.blingabc.auto.vo.request;

import java.util.Map;

public class HttpRequestVO {

    private String url;

    private String requestWay;

    private Object header;

    private String paramType;

    private Object param;

    private long connectionTimeOut = 1000;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getHeader() {
        return header;
    }

    public void setHeader(Object header) {
        this.header = header;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public String getRequestWay() {
        return requestWay;
    }

    public void setRequestWay(String requestWay) {
        this.requestWay = requestWay;
    }

    public long getConnectionTimeOut() {
        return connectionTimeOut;
    }

    public void setConnectionTimeOut(long connectionTimeOut) {
        this.connectionTimeOut = connectionTimeOut;
    }

    @Override
    public String toString() {
        return "HttpRequestVO{" +
                "url='" + url + '\'' +
                ", requestWay='" + requestWay + '\'' +
                ", header=" + header +
                ", paramType='" + paramType + '\'' +
                ", param=" + param +
                ", connectionTimeOut=" + connectionTimeOut +
                '}';
    }
}
