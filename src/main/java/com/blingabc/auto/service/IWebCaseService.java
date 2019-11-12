package com.blingabc.auto.service;

import com.blingabc.auto.beans.WebCaseVO;
import org.openqa.selenium.WebDriver;

public interface IWebCaseService {

    void testWebCase(WebDriver driver, WebCaseVO webCaseVO);
}
