package com.blingabc.auto.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blingabc.auto.beans.AssertVO;
import com.blingabc.auto.beans.ElementVO;
import com.blingabc.auto.beans.PageVO;
import com.blingabc.auto.beans.WebCaseVO;
import com.blingabc.auto.dao.AssertVOMapper;
import com.blingabc.auto.dao.ElementVOMapper;
import com.blingabc.auto.dao.PageVOMapper;
import com.blingabc.auto.dao.WebCaseVOMapper;
import com.blingabc.auto.exception.BizException;
import com.blingabc.auto.vo.response.TestCaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Slf4j
@Component
@PropertySource(value = {"classpath:data.properties"})
public class WebDriverUtil {

    @Autowired
    private ElementVOMapper elementVOMapper;

    @Autowired
    private AssertVOMapper assertVOMapper;

    @Autowired
    private PageVOMapper pageVOMapper;

    @Autowired
    private WebCaseVOMapper webCaseVOMapper;

    @Value("${fail_img_path}")
    private String failImgPath;

    /**
     * 元素点击操作
     *
     * @param driver
     * @param elementVO
     */
    public void click(WebDriver driver, ElementVO elementVO) {
        log.info("操作方式：元素点击");
        log.info("元素名称:{}", elementVO.getName());
        log.info("元素定位方式: {}", elementVO.getLocatorTypeId());
        log.info("元素值:{}", elementVO.getLocator());
        this.getElement(driver, elementVO).click();
    }

    /**
     * 元素赋值操作
     *
     * @param driver
     * @param elementVO
     */
    public void setValue(WebDriver driver, ElementVO elementVO) {
        log.info("操作方式：元素输入值");
        log.info("元素名称:{}", elementVO.getName());
        log.info("元素定位方式: {}", elementVO.getLocatorTypeId());
        log.info("元素值:{}", elementVO.getLocator());
        this.getElement(driver, elementVO).sendKeys(elementVO.getVal());
    }

    /**
     * 元素滑动操作
     *
     * @param driver
     * @param elementVO
     */
    public void elementSwipe(WebDriver driver, ElementVO elementVO) {
        log.info("操作方式：元素滑动");
        log.info("元素名称:{}", elementVO.getName());
        log.info("元素定位方式: {}", elementVO.getLocatorTypeId());
        log.info("元素值:{}", elementVO.getLocator());
        Actions actions = new Actions(driver);
        Map<String, String> swipeObject = JSONObject.parseObject(elementVO.getVal(), Map.class);
        actions.moveToElement(getElement(driver, elementVO), Integer.parseInt(swipeObject.get("x")), Integer.parseInt(swipeObject.get("y"))).build().perform();
    }

    /**
     * 断言操作
     *
     * @param driver
     * @param assertVO
     */
    public void assertOperate(WebDriver driver, AssertVO assertVO) {
        log.info("操作方式：断言");
        switch (assertVO.getAssertTypeId()) {
            case 1:
                log.info("断言页面title包含:{}", assertVO.getVal());
                Assert.assertTrue(driver.getTitle().contains(assertVO.getVal()));
                break;
            case 2:
                log.info("断言页面出现值:{}", assertVO.getVal());
                Assert.assertTrue(driver.getPageSource().contains(assertVO.getVal()));
                break;
            case 3:
                ElementVO elementVO = elementVOMapper.selectByPrimaryKey(Integer.parseInt(assertVO.getVal()));
                log.info("断言出现元素:{}", elementVO.getName());
                Assert.assertTrue(elementVO != null);
                break;
            default:
                Assert.assertTrue(driver.getPageSource().contains(assertVO.getVal()));
                break;
        }
    }

    private List<TestCaseResponse> exec(List<TestCaseResponse> responses, WebDriver driver, int webCaseId){
        TestCaseResponse testCaseResponse = new TestCaseResponse();
        try {
            WebCaseVO webCaseVO = webCaseVOMapper.selectByPrimaryKey(webCaseId);
            if (webCaseVO == null){
                throw new BizException(String.format("执行的用例id：{}不存在", webCaseId));
            }
            testCaseResponse.setStartTime(System.currentTimeMillis());
            testCaseResponse.setCaseName(webCaseVO.getName());
            System.out.println(webCaseVO.getCases());
            List<Map> cases = JSONArray.parseArray(webCaseVO.getCases(), Map.class);
            cases.sort(new Comparator<Map>() {
                @Override
                public int compare(Map o1, Map o2) {
                    return Integer.parseInt(o1.get("index").toString()) - Integer.parseInt(o2.get("index").toString());
                }
            });
            for (Map<String, Object> map : cases) {
                int id = Integer.parseInt(map.get("id").toString());
                String type = map.get("type").toString();
                switch (map.get("type").toString()) {
                    case "element":
                        ElementVO elementVO = elementVOMapper.selectByPrimaryKey(id);
                        if (elementVO != null) {
                            switch (elementVO.getOperationId()) {
                                case 1:
                                    click(driver, elementVO);
                                    break;
                                case 2:
                                    setValue(driver, elementVO);
                                    break;
                                case 3:
                                    elementSwipe(driver, elementVO);
                                    break;
                            }
                        }
                        break;
                    case "assert":
                        AssertVO assertVO = assertVOMapper.selectByPrimaryKey(id);
                        if (assertVO != null) {
                            assertOperate(driver, assertVO);
                        }
                        break;
                    case "page":
                        PageVO pageVO = pageVOMapper.selectByPrimaryKey(id);
                        if (pageVO != null) {
                            switch (pageVO.getPageOperateId()) {
                                case 1:
                                    open(driver, pageVO);
                                    break;
                                case 2:
                                    pageSwipe(driver, pageVO);
                                    break;
                                case 3:
                                    execJs(driver, pageVO);
                                    break;
                                case 4:
                                    swtichHandle(driver, pageVO);
                                    break;
                                case 5:
                                    close(driver);
                                    break;
                                case 6:
                                    quit(driver);
                                    break;
                            }
                        }
                        break;
                }
            }
            testCaseResponse.setEndTime(System.currentTimeMillis());
            testCaseResponse.setStatus("success");
        } catch (Exception e) {
            File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);  //执行屏幕截取
            try {
                FileUtils.copyFile(srcFile, new File(failImgPath+testCaseResponse.getCaseName()+".png"));
            }catch (IOException eo){
                throw new BizException("截图功能出现错误");
            }
            testCaseResponse.setEndTime(System.currentTimeMillis());
            testCaseResponse.setStatus("fail");
        }
        responses.add(testCaseResponse);
        return responses;
    }

    public List<TestCaseResponse> execCase(WebDriver driver, int[] webCaseIds){
        List<TestCaseResponse> responses = new ArrayList<>();

        if (webCaseIds == null || webCaseIds.length < 1) {
            throw new BizException("执行的用例不能为空");
        }
        for (Integer webCaseId : webCaseIds) {
            WebCaseVO webCaseVO = webCaseVOMapper.selectByPrimaryKey(webCaseId);
            if (webCaseVO == null) {
                throw new BizException("执行的用例id不存在");
            }
            log.info("执行用例:{}", webCaseVO.getName());
            if (webCaseVO.getDepend() != null) {
                List<Map> dependList = JSONArray.parseArray(webCaseVO.getDepend(), Map.class);
                if (dependList.size() > 1) {
                    dependList.sort(new Comparator<Map>() {
                        @Override
                        public int compare(Map o1, Map o2) {
                            return Integer.parseInt(o1.get("index").toString()) - Integer.parseInt(o2.get("index").toString());
                        }
                    });
                }
                for (Map<String, Object> dp : dependList) {
                    int caseId = Integer.parseInt(dp.get("id").toString());
                    WebCaseVO webBean = webCaseVOMapper.selectByPrimaryKey(caseId);
                    int i = 0;
                    if (webBean != null) {
                        int[] lw = new int[]{caseId};
                        execCase(driver, lw);
                    }
                }
            }
            exec(responses,driver,webCaseId);
        }
        return responses;
    }

    public void open(WebDriver driver, PageVO pageVO) {
        log.info("打开的url地址：{}", pageVO.getVal());
        driver.get(pageVO.getVal());
    }

    public void close(WebDriver driver) {
        log.info("关闭窗口");
        driver.close();
    }

    public void quit(WebDriver driver) {
        log.info("推出当前driver");
        driver.quit();
    }

    public void swtichHandle(WebDriver driver, PageVO pageVO) {
        log.info("操作方式：切换窗口");
        log.info("窗口title包含值:{}", pageVO.getVal());
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> it = handles.iterator();
        while (it.hasNext()) {
            driver.switchTo().window(it.next());
            if (driver.getTitle().contains(pageVO.getVal())) {
                break;
            }
        }
    }

    public void execJs(WebDriver driver, PageVO pageVO) {
        log.info("执行js片段：{}", pageVO.getVal());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(pageVO.getVal());
    }


    public void pageSwipe(WebDriver driver, PageVO pageVO) {

        Map<String, Object> swipeObj = JSONObject.parseObject(pageVO.getVal(), Map.class);
        String direction = swipeObj.get("direction").toString(); //滑动的方向
        int distance = Integer.parseInt(swipeObj.get("distance").toString()); //每次滑动的距离
        Map<String, String> elementMap = (Map) swipeObj.get("element");
        int type = Integer.parseInt(elementMap.get("type"));
        String locator = elementMap.get("locator");
        log.info("操作方式：页面滑动");
        log.info("滑动方向:{}", direction);
        log.info("每次滑动距离：{}", distance);
        log.info("滑动期望出现的元素定位方式:{}", type);
        log.info("滑动期望出现的元素定位值:{}", locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        switch (direction) {
            case "up":
                js.executeAsyncScript(String.format("window.scrollBy(0,{})", Integer.parseInt("-" + String.valueOf(distance))), driver.findElement(getBy(type, locator)));
                break;
            case "down":
                js.executeAsyncScript(String.format("window.scrollBy(0,{})", distance), driver.findElement(getBy(type, locator)));
                break;
            case "right":
                js.executeAsyncScript(String.format("window.scrollBy({},0)", distance), driver.findElement(getBy(type, locator)));
                break;
            case "left":
                js.executeAsyncScript(String.format("window.scrollBy({},0)", Integer.parseInt("-" + String.valueOf(distance))), driver.findElement(getBy(type, locator)));
                break;
        }
    }

    private WebElement getElement(WebDriver driver, ElementVO elementVO) {
        if (elementVO.getTimeOut() == null) {
            elementVO.setTimeOut(10);
        }
        WebDriverWait wait = new WebDriverWait(driver, elementVO.getTimeOut());
        WebElement element = wait.until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(getBy(elementVO.getLocatorTypeId(), elementVO.getLocator()));
            }
        });
        return element;
    }

    //    private By getBy(ElementVO elementVO){
//        By by = null;
//        switch (elementVO.getLocatorTypeId()){
//            case 1:
//                by = By.xpath(elementVO.getLocator());
//                break;
//            case 2:
//                by = By.name(elementVO.getLocator());
//                break;
//            case 3:
//                by = By.id(elementVO.getLocator());
//                break;
//            case 4:
//                by = By.className(elementVO.getLocator());
//                break;
//            case 5:
//                by = By.linkText(elementVO.getLocator());
//                break;
//            case 6:
//                by = By.partialLinkText(elementVO.getLocator());
//                break;
//            case 7:
//                by = By.cssSelector(elementVO.getLocator());
//                break;
//            case 8:
//                by = By.tagName(elementVO.getLocator());
//                break;
//            default:
//                by = By.xpath(elementVO.getLocator());
//                break;
//        }
//        return by;
//    }
//
    private By getBy(int type, String locator) {
        By by = null;
        switch (type) {
            case 1:
                by = By.xpath(locator);
                break;
            case 2:
                by = By.name(locator);
                break;
            case 3:
                by = By.id(locator);
                break;
            case 4:
                by = By.className(locator);
                break;
            case 5:
                by = By.linkText(locator);
                break;
            case 6:
                by = By.partialLinkText(locator);
                break;
            case 7:
                by = By.cssSelector(locator);
                break;
            case 8:
                by = By.tagName(locator);
                break;
            default:
                by = By.xpath(locator);
                break;
        }
        return by;
    }

}
