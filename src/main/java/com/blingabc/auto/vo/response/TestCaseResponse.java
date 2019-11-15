package com.blingabc.auto.vo.response;

public class TestCaseResponse {

    private long startTime;

    private long endTime;

    private String caseName;

    private byte[] errorImg;

    private String status;

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public byte[] getErrorImg() {
        return errorImg;
    }

    public void setErrorImg(byte[] errorImg) {
        this.errorImg = errorImg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
