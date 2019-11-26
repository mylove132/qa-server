package com.blingabc.auto.controller;

import com.blingabc.auto.common.ResultBody;
import com.blingabc.auto.exception.BizException;
import com.blingabc.auto.service.ICaseService;
import com.blingabc.auto.service.impl.WebServiceImpl;
import com.blingabc.auto.vo.request.ExecWebCaseVO;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/case")
public class CaseCrontroller {

    @Autowired
    private WebServiceImpl service;

    @Autowired
    private ICaseService caseService;

    @RequestMapping(value = "/web/run",method = RequestMethod.POST)
    public ResultBody execWebCaseCrontroller(@RequestBody List<ExecWebCaseVO> execWebCaseVOList){
        log.info(execWebCaseVOList.toString());
        try {
        for (ExecWebCaseVO execWebCaseVO:execWebCaseVOList){
            System.setProperty("webdriver.chrome.driver","/Users/liuzhanhui/lzh/tools/chromedriver");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            switch (execWebCaseVO.getPlatform()){
                case "mac":
                    capabilities.setPlatform(Platform.MAC);
                    break;
                case "windows":
                    capabilities.setPlatform(Platform.WINDOWS);
                    break;
                default:
                    capabilities.setPlatform(Platform.WINDOWS);
                    break;
            }
            capabilities.setBrowserName(execWebCaseVO.getBrowser());
            WebDriver driver = null;
            try {
                driver = new RemoteWebDriver(new URL(execWebCaseVO.getRegisterUrl()+"/wd/hub"), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            service.testWebCase(driver, execWebCaseVO.getWebCaseIds());
            }
        }catch (BizException e){
            throw new BizException(e.getErrorMsg());
        }
        return ResultBody.success();
    }

    @RequestMapping(value = "generate_script",method = RequestMethod.POST)
    public ResultBody generateScriptWebCaseCrontroller(int[] caseIds){
        try {

        }catch (BizException e){
            throw new BizException(e.getErrorMsg());
        }
        return ResultBody.success();
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResultBody caseListCrontroller(Integer catalogId, Integer caseTypeId,@RequestParam(defaultValue = "1",value = "currentPage") Integer pageNum,
                                           @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize){
        return caseService.caseListService(catalogId,caseTypeId,pageNum,pageSize);
    }
}
