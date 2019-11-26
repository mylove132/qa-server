package com.blingabc.auto.controller;

import com.blingabc.auto.beans.InterfaceCaseVO;
import com.blingabc.auto.beans.InterfaceCaseVOWithBLOBs;
import com.blingabc.auto.common.ResultBody;
import com.blingabc.auto.service.IInterfaceCaseService;
import com.blingabc.auto.util.OkHttpUtil;
import com.blingabc.auto.vo.request.HttpRequestVO;
import com.blingabc.auto.vo.response.HttpResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RequestMapping("/api/interface")
@RestController
public class InterfaceCrontroller {

    @Autowired
    private IInterfaceCaseService iInterfaceCaseService;

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

    @RequestMapping(value = "case",method = RequestMethod.POST)
    public ResultBody addInterfaceCase(InterfaceCaseVOWithBLOBs interfaceCaseVO){
      return iInterfaceCaseService.addInterfaceCseService(interfaceCaseVO);
    }

    @RequestMapping(value = "case",method = RequestMethod.GET)
    public ResultBody getInterfaceCasesByCatalogIdCrontroller(int catalogId,@RequestParam(defaultValue = "1",value = "currentPage") Integer pageNum,
                                           @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize){
        return iInterfaceCaseService.getInterfaceCasesByCatalogIdService(catalogId, pageNum, pageSize);
    }

    @RequestMapping(value = "covert",method = RequestMethod.POST)
    public ResultBody covertHttpToCurlCrontroller(HttpRequestVO requestVO){
        return ResultBody.success(OkHttpUtil.httpConvertCurl(requestVO));
    }
}
