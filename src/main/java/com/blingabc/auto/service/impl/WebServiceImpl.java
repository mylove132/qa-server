package com.blingabc.auto.service.impl;
import com.blingabc.auto.common.ResultBody;
import com.blingabc.auto.dao.CaseVOMapper;
import com.blingabc.auto.exception.BizException;
import com.blingabc.auto.util.WebDriverUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WebServiceImpl{

    @Autowired
    private WebDriverUtil webDriverUtil;

    @Autowired
    private CaseVOMapper caseVOMapper;

    public void testWebCase(WebDriver driver, int[] webCaseIds) {
        webDriverUtil.execCase(driver,webCaseIds);
    }

    public ResultBody generateScriptService(int[] caseIds){
        if (caseIds.length <= 0 || caseIds == null ){
            throw new BizException("caseIds参数不能为空");
        }
        for (int i=0;i<caseIds.length;i++){
            if (caseVOMapper.selectByPrimaryKey(caseIds[i]) != null){
            }
        }
        return null;

    }
}
