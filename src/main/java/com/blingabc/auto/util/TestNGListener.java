package com.blingabc.auto.util;

import lombok.extern.slf4j.Slf4j;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Slf4j
public class TestNGListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
      log.info("请求的方法：{},参数：{}",result.getName(),result.getParameters());
    }
}
