package com.blingabc.auto.service.impl;

import com.blingabc.auto.beans.WebCaseVO;
import com.blingabc.auto.service.IWebCaseService;
import com.blingabc.auto.util.WebDriverUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Service;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

@Service
@Slf4j
public class WebServiceImpl implements IWebCaseService {


    private WebDriverUtil webDriverUtil = new WebDriverUtil();

    @Test
    @Override
    public void testWebCase(WebDriver driver, WebCaseVO webCaseVO) {
        webDriverUtil.execCase(driver,webCaseVO);
    }
}
