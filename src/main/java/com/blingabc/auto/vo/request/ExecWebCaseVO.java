package com.blingabc.auto.vo.request;

public class ExecWebCaseVO {
    private String registerUrl;
    private String platform;
    private String browser;
    private String browserVersion;
    private int[] webCaseIds;

    public String getRegisterUrl() {
        return registerUrl;
    }

    public void setRegisterUrl(String registerUrl) {
        this.registerUrl = registerUrl;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    public int[] getWebCaseIds() {
        return webCaseIds;
    }

    public void setWebCaseIds(int[] webCaseIds) {
        this.webCaseIds = webCaseIds;
    }
}
