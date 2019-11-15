package com.blingabc.auto.controller;

import com.blingabc.auto.common.ResultBody;
import com.blingabc.auto.util.OkHttpUtil;
import com.blingabc.auto.vo.request.HttpRequestVO;
import com.blingabc.auto.vo.response.HttpResponseVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/interface")
@RestController
public class InterfaceCrontroller {

    @RequestMapping(value = "http",method = RequestMethod.POST)
    public ResultBody execHttpRequestCrontroller(HttpRequestVO requestVO){
        if ("GET".equalsIgnoreCase(requestVO.getRequestWay())){
            HttpResponseVO responseVO = OkHttpUtil.httpGet(requestVO);
            return ResultBody.success(responseVO);
        }else if("POST".equalsIgnoreCase(requestVO.getRequestWay())){
            HttpResponseVO responseVO = OkHttpUtil.httpPost(requestVO);
            return ResultBody.success(responseVO);
        }else {
            HttpResponseVO responseVO = OkHttpUtil.httpGet(requestVO);
            return ResultBody.success(responseVO);
        }
    }

    @RequestMapping(value = "covert",method = RequestMethod.POST)
    public ResultBody covertHttpToCurlCrontroller(HttpRequestVO requestVO){
        return ResultBody.success(OkHttpUtil.httpConvertCurl(requestVO));
    }
}
