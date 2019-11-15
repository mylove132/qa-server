package com.blingabc.auto.service.impl;
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

    public void testWebCase(WebDriver driver, int[] webCaseIds) {
        webDriverUtil.execCase(driver,webCaseIds);
    }
}
