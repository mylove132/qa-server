package com.blingabc.auto.vo.request;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-18:00:34
 * Modify date: 2019-09-18:00:34
 */
public class LoginRequestVo {

    private int id;
    private String url;
    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
