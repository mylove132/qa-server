package com.blingabc.auto.vo.request;

import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-24:16:36
 * Modify date: 2019-09-24:16:36
 */
@Data
public class AddCaseRequestVo {

    //case参数
    private String param;

    //参数是否json格式
    private boolean isJson;

    //测试url
    private String url;

    //测试环境
    private int envId;

    //case name
    private String name;

    //协议
    private int protocolId;

    //请求方式id
    private int requestWayId;


    //接口超时时间
    private int timeOut;

    //模块id
    private int moduleId;

    //header参数
    private String header;

    //用户id
    private Long systemId;

    //请求方式id
    private int typeId;

    private Date createTime;

    private Date updateTime;

    public static void main(String[] args) {

    }

}
