package com.blingabc.auto.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blingabc.auto.beans.AssertVO;
import com.blingabc.auto.beans.ElementVO;
import com.blingabc.auto.beans.PageVO;
import com.blingabc.auto.beans.WebCaseVO;
import com.blingabc.auto.dao.AssertTypeVOMapper;
import com.blingabc.auto.dao.AssertVOMapper;
import com.blingabc.auto.dao.ElementVOMapper;
import com.blingabc.auto.dao.PageVOMapper;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import java.util.*;

@Slf4j
public class WebDriverUtil {

    @Autowired
    private ElementVOMapper elementVOMapper;

    @Autowired
    private AssertVOMapper assertVOMapper;

    @Autowired
    private PageVOMapper pageVOMapper;
    /**
     * 元素点击操作
     * @param driver
     * @param elementVO
     */
    public void click(WebDriver driver, ElementVO elementVO){
      log.info("操作方式：元素点击");
      log.info("元素名称:{}",elementVO.getName());
      log.info("元素定位方式: {}", elementVO.getLocatorTypeId());
      log.info("元素值:{}",elementVO.getLocator());
      this.getElement(driver, elementVO).click();
    }

    /**
     * 元素赋值操作
     * @param driver
     * @param elementVO
     */
    public void setValue(WebDriver driver, ElementVO elementVO){
        log.info("操作方式：元素输入值");
        log.info("元素名称:{}",elementVO.getName());
        log.info("元素定位方式: {}", elementVO.getLocatorTypeId());
        log.info("元素值:{}",elementVO.getLocator());
        this.getElement(driver, elementVO).sendKeys(elementVO.getVal());
    }

    /**
     * 元素滑动操作
     * @param driver
     * @param elementVO
     */
    public void elementSwipe(WebDriver driver, ElementVO elementVO){
        log.info("操作方式：元素滑动");
        log.info("元素名称:{}",elementVO.getName());
        log.info("元素定位方式: {}", elementVO.getLocatorTypeId());
        log.info("元素值:{}",elementVO.getLocator());
        Actions actions = new Actions(driver);
        Map<String,String> swipeObject = JSONObject.parseObject(elementVO.getVal(), Map.class);
        actions.moveToElement(getElement(driver,elementVO),Integer.parseInt(swipeObject.get("x")),Integer.parseInt(swipeObject.get("y"))).build().perform();
    }

    /**
     * 断言操作
     * @param driver
     * @param assertVO
     */
    public void assertOperate(WebDriver driver, AssertVO assertVO){
        log.info("操作方式：断言");
        log.info("断言方式:{}",assertVO.getAssertTypeId());
        log.info("断言值:{}", assertVO.getVal());
        switch (assertVO.getAssertTypeId()){
            case 1:
                Assert.assertTrue(driver.getTitle().contains(assertVO.getVal()));
                break;
            case 2:
                Assert.assertTrue(driver.getPageSource().contains(assertVO.getVal()));
                break;
            case 3:
                ElementVO elementVO = elementVOMapper.selectByPrimaryKey(Integer.parseInt(assertVO.getVal()));
                Assert.assertTrue(elementVO != null);
                break;
            default:
                Assert.assertTrue(driver.getPageSource().contains(assertVO.getVal()));
                break;
        }
    }

    public void execCase(WebDriver driver, WebCaseVO webCaseVO){
        List<Map> cases = JSONArray.parseArray(webCaseVO.getCases(),Map.class);
        cases.sort(new Comparator<Map>() {
            @Override
            public int compare(Map o1, Map o2) {
                return Integer.parseInt(o1.get("index").toString())-Integer.parseInt(o2.get("index").toString());
            }
        });
        for (Map<String,String> map:cases){
           switch (map.get("type")){
               case "element":
                   int elementId = Integer.parseInt(map.get("id"));
                   ElementVO elementVO = elementVOMapper.selectByPrimaryKey(elementId);
                   if (elementVO != null){
                       switch (elementVO.getOperationId()){
                           case 1:
                               click(driver, elementVO);
                               break;
                           case 2:
                               setValue(driver,elementVO);
                               break;
                           case 3:
                               elementSwipe(driver,elementVO);
                               break;
                       }
                   }
                   break;
               case "assert":
                   int assertId = Integer.parseInt(map.get("id"));
                   AssertVO assertVO = assertVOMapper.selectByPrimaryKey(assertId);
                   if (assertVO != null){
                       assertOperate(driver,assertVO);
                   }
                   break;
               case "page":
                   int pageId = Integer.parseInt(map.get("id"));
                   PageVO pageVO = pageVOMapper.selectByPrimaryKey(pageId);
                   if (pageVO != null){
                       switch (pageVO.getPageOperateId()){
                           case 1:
                               open(driver,pageVO);
                               break;
                           case 2:
                               pageSwipe(driver,pageVO);
                               break;
                           case 3:
                               execJs(driver,pageVO);
                               break;
                           case 4:
                               swtichHandle(driver,pageVO);
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
    }

    public void open(WebDriver driver, PageVO pageVO){
        log.info("打开的url地址：{}",pageVO.getVal());
        driver.get(pageVO.getVal());
    }

    public void close(WebDriver driver){
        log.info("关闭窗口");
        driver.close();
    }

    public void quit(WebDriver driver){
        log.info("推出当前driver");
        driver.quit();
    }

    public void swtichHandle(WebDriver driver, PageVO pageVO){
        log.info("操作方式：切换窗口");
        log.info("窗口title包含值:{}",pageVO.getVal());
        Set<String> handles =  driver.getWindowHandles();
        Iterator<String> it = handles.iterator();
        while (it.hasNext()){
            driver.switchTo().window(it.next());
            if (driver.getTitle().contains(pageVO.getVal())){
                break;
            }
        }
    }

    public void execJs(WebDriver driver, PageVO pageVO){
        log.info("执行js片段：{}",pageVO.getVal());
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript(pageVO.getVal());
    }


    public void pageSwipe(WebDriver driver, PageVO pageVO){

        Map<String,Object> swipeObj = JSONObject.parseObject(pageVO.getVal(),Map.class);
        String direction = swipeObj.get("direction").toString(); //滑动的方向
        int distance = Integer.parseInt(swipeObj.get("distance").toString()); //每次滑动的距离
        Map<String,String> elementMap = (Map)swipeObj.get("element");
        int type = Integer.parseInt(elementMap.get("type"));
        String locator = elementMap.get("locator");
        log.info("操作方式：页面滑动");
        log.info("滑动方向:{}",direction);
        log.info("每次滑动距离：{}", distance);
        log.info("滑动期望出现的元素定位方式:{}",type);
        log.info("滑动期望出现的元素定位值:{}",locator);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        switch (direction){
            case "up":
                js.executeAsyncScript(String.format("window.scrollBy(0,{})", Integer.parseInt("-"+String.valueOf(distance))), driver.findElement(getBy(type,locator)));
                break;
            case "down":
                js.executeAsyncScript(String.format("window.scrollBy(0,{})", distance), driver.findElement(getBy(type,locator)));
                break;
            case "right":
                js.executeAsyncScript(String.format("window.scrollBy({},0)", distance), driver.findElement(getBy(type,locator)));
                break;
            case "left":
                js.executeAsyncScript(String.format("window.scrollBy({},0)", Integer.parseInt("-"+String.valueOf(distance))), driver.findElement(getBy(type,locator)));
                break;
        }
    }

    private WebElement getElement(WebDriver driver, ElementVO elementVO){
        WebDriverWait wait = new WebDriverWait(driver, elementVO.getTimeOut());
        WebElement element = wait.until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(getBy(elementVO.getLocatorTypeId(),elementVO.getLocator()));
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
    private By getBy(int type, String locator){
        By by = null;
        switch (type){
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
