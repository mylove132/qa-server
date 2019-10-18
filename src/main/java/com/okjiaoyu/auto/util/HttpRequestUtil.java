package com.okjiaoyu.auto.util;

import com.google.common.base.Charsets;
import com.okjiaoyu.auto.aop.CommonException;
import com.okjiaoyu.auto.vo.request.LoginRequestVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http请求工具
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-18:00:23
 * Modify date: 2019-09-18:00:23
 */
@Slf4j
public class HttpRequestUtil {

    /**
     * 发送get请求
     * @param headers
     * @param cookies
     * @param path
     * @param connTime
     * @param parametersBody
     * @return
     * @throws URISyntaxException
     */
    public static String getRequest(Map<String,String> headers, Map<String,String> cookies, String path, Integer connTime, List<NameValuePair> parametersBody) throws URISyntaxException {
        log.info("[getRequest] resourceUrl: {}", path);
        if (connTime == null)
            connTime = 1000;
        URIBuilder uriBuilder = new URIBuilder(path);
        if (parametersBody != null && parametersBody.size()>0)
            uriBuilder.setParameters(parametersBody);
        HttpGet get = new HttpGet(uriBuilder.build());
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(connTime).setConnectTimeout(connTime)
                .setRedirectsEnabled(true)
                .build();
        get.setConfig(requestConfig);
        if (MapUtils.isNotEmpty(headers)) {
            headers.forEach(
                    (k, v) -> {
                        get.setHeader(k, v);
                    }
            );
        }
        String cs = "";
        if (MapUtils.isNotEmpty(cookies)){
            for (Map.Entry<String,String> entry : cookies.entrySet()){
                cs += entry.getKey()+"="+entry.getValue()+";";
            }
            get.addHeader("Cookie",cs);
        }
        HttpClient client = HttpClientBuilder.create().build();
        try {
            HttpResponse response = client.execute(get);
            int code = response.getStatusLine().getStatusCode();
            if (code >= 400)
                throw new CommonException((new StringBuilder()).append("Could not access protected resource. Server returned http code: ").append(code).toString());
            log.info("请求返回的结果：{}", EntityUtils.toString(response.getEntity()));
            return EntityUtils.toString(response.getEntity());
        }
        catch (ClientProtocolException e) {
            log.error("postRequest -- Client protocol exception!"+e.getMessage());
            throw new CommonException("postRequest -- Client protocol exception!"+e.getMessage());
        }
        catch (IOException e) {
            log.error("请求错误："+e.getMessage());
            throw new CommonException("请求错误："+e.getMessage());
        }
        finally {
            log.error("释放请求连接");
            get.releaseConnection();
        }
    }

    /**
     * 发送post请求(表单请求)
     * @param path
     * @param parametersBody
     * @param headers
     * @param cookies
     * @param connTime
     * @return
     */
    public static String postForm(String path, List<NameValuePair> parametersBody,Map<String,String> headers,Map<String,String> cookies,Integer connTime) {
        if (connTime == null)
            connTime = 1000;
        HttpEntity entity = new UrlEncodedFormEntity(parametersBody, Charsets.UTF_8);
        return postRequest(headers,cookies,connTime,path, "application/x-www-form-urlencoded", entity);
    }


    /**
     * 发送post请求(json格式)
     * @param path
     * @param json
     * @param headers
     * @param cookies
     * @param connTime
     * @return
     */
    public static String postJSON(String path, String json,Map<String,String> headers,Map<String,String> cookies,Integer connTime)  {
        if (connTime == null)
            connTime = 1000;
        StringEntity entity = new StringEntity(json, Charsets.UTF_8);
        entity.setContentType("application/json");
        return postRequest(headers,cookies,connTime,path, "application/json", entity);
    }


    /**
     * 发送post请求
     * @param headers
     * @param cookies
     * @param connTime
     * @param path
     * @param mediaType
     * @param entity
     * @return
     */
    public static String postRequest(Map<String,String> headers,Map<String,String> cookies,Integer connTime,String path, String mediaType, HttpEntity entity) {
        log.info("[postRequest] resourceUrl: {}", path);
        if (connTime == null)
            connTime = 1000;
        HttpPost post = new HttpPost(path);
        post.addHeader("Content-Type", mediaType);
        post.addHeader("Accept", "application/json");
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(connTime).setConnectTimeout(connTime)
                .setRedirectsEnabled(true)
                .build();
        post.setConfig(requestConfig);
        if (MapUtils.isNotEmpty(headers)){
            headers.forEach(
                    (k, v) -> {
                        post.setHeader(k, v);
                    }
            );
        }
        String cs = "";
        if (MapUtils.isNotEmpty(cookies)){
            for (Map.Entry<String,String> entry : cookies.entrySet()){
                cs += entry.getKey()+"="+entry.getValue()+";";
            }
            post.addHeader("Cookie",cs);
        }
        post.setEntity(entity);
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(post);
            int code = response.getStatusLine().getStatusCode();
            if (code >= 400) {
                log.error("post请求地址：{},返回code码：{}",path,code);
                throw new CommonException(EntityUtils.toString(response.getEntity()));
            }
            log.info("请求返回结果：{}",EntityUtils.toString(response.getEntity()));
            return EntityUtils.toString(response.getEntity());
        }
        catch (ClientProtocolException e) {
            log.error("post请求地址：{},错误信息：{}",path,e.getMessage());
            throw new CommonException(e.getMessage());
        }
        catch (IOException e) {
            log.error("post请求地址：{},错误信息：{}",path,e.getMessage());
            throw new CommonException(e.getMessage());
        }
        finally {
            post.releaseConnection();
        }
    }

    /**
     * 获取单点登录页面参数
     * @param url
     * @return
     * @throws IOException
     */
    private static Map<String,String> getLoginRequestLTAndExecution(String url) throws IOException {
        log.info("请求单点登录地址:{}",url);
        Map<String,String> result = new HashMap<>();
        Document doc = Jsoup.connect(url).get();
        String lt = doc.select("input[name='lt']").first().val();
        String execution = doc.select("input[name='execution']").first().val();
        String platformType = doc.select("input[name='platformType']").first().val();
        String loginType = doc.select("input[name='loginType']").first().val();
        log.info("获取登录页面的lt:{},execution:{},platformType:{},loginType:{}",lt,execution,platformType,loginType);
        if ((lt == null||"".equals(lt)) || (execution==null||"".equals(execution)))
            return null;
        result.put("lt",lt);
        result.put("execution",execution);
        result.put("platformType",platformType);
        result.put("loginType",loginType);
        return result;
    }


    /**
     * 获取ticket地址
     * @param loginRequestVo
     * @return
     * @throws Exception
     */
    private static String casLoginGetTicket(LoginRequestVo loginRequestVo) throws Exception {
        Map<String,String> ltMap = getLoginRequestLTAndExecution(loginRequestVo.getUrl());
        try {
            if (ltMap == null) {
                throw new RuntimeException("访问页面源码出现错误");
            }
            DefaultHttpClient httpClient = new SSLClient();
            httpClient.getParams().setParameter(HttpMethodParams.USER_AGENT, "Mozilla/5.0");
            HttpPost post = new HttpPost(loginRequestVo.getUrl());
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("platformType", ltMap.get("platformType")));
            nvps.add(new BasicNameValuePair("loginType", ltMap.get("loginType")));
            nvps.add(new BasicNameValuePair("username", loginRequestVo.getUsername()));
            nvps.add(new BasicNameValuePair("password", loginRequestVo.getPassword()));
            nvps.add(new BasicNameValuePair("lt", ltMap.get("lt")));
            nvps.add(new BasicNameValuePair("execution", ltMap.get("execution")));
            nvps.add(new BasicNameValuePair("pictureVerifyCode", "performance"));
            nvps.add(new BasicNameValuePair("_eventId", "submit"));
            post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));

            HttpResponse response = httpClient.execute(post);
            String Location = response.getFirstHeader("Location").getValue();
            if (Location != null && !"".equals(Location))
                return Location;
            return null;
        }catch (CommonException e){
            throw new CommonException("获取登录首页信息错误");
        }
    }


    /**
     * 请求ticket地址，获取登录cookie
     * @param loginRequestVo
     * @return
     * @throws Exception
     */
    public static List<Cookie> casLogin(LoginRequestVo loginRequestVo) throws Exception {
        DefaultHttpClient httpClient = new SSLClient();
        Map<String,String> cookieMap = new HashMap<>();
        httpClient.getParams().setParameter(HttpMethodParams.USER_AGENT, "Mozilla/5.0");
        log.info("登录用户名：{}",loginRequestVo.getUsername());
        HttpGet get = new HttpGet(casLoginGetTicket(loginRequestVo));
        HttpResponse response = httpClient.execute(get);
        log.info("登录请求返回码：{}",response.getStatusLine().getStatusCode());
        log.info("登录请求返回信息：{}",EntityUtils.toString(response.getEntity()));
        return httpClient.getCookieStore().getCookies();
    }

}
