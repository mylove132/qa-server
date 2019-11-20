package com.blingabc.auto.util;

import com.alibaba.fastjson.JSONObject;
import com.blingabc.auto.exception.BizException;
import com.blingabc.auto.vo.request.HttpRequestVO;
import com.blingabc.auto.vo.response.HttpResponseVO;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import okhttp3.*;
import org.apache.commons.collections4.MapUtils;
import javax.net.ssl.*;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class OkHttpUtil {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public static HttpResponseVO httpGet(HttpRequestVO requestVO){
        log.info("请求的接口信息:{}",requestVO.toString());
        HttpResponseVO responseVO = new HttpResponseVO();
        OkHttpClient client = new OkHttpClient.Builder()
                .sslSocketFactory(createSSLSocketFactory())
                .hostnameVerifier(new TrustAllHostnameVerifier())
                .connectTimeout(requestVO.getConnectionTimeOut(),TimeUnit.MILLISECONDS)
                .build();
        Request.Builder build = new Request.Builder().url(requestVO.getUrl());
        if (requestVO.getHeader() != null && !requestVO.getHeader().toString().equals("")){
            Map<String,Object> headerMap = JSONObject.parseObject(requestVO.getHeader().toString(),Map.class);
            for (Map.Entry<String, Object> entry:headerMap.entrySet()){
                build.addHeader(entry.getKey(),entry.getValue().toString());
            }
        }
        Request request = build.build();
        Call call = client.newCall(request);
        Response response = null;
        try {
            response = call.execute();
            responseVO.setStatusCode(response.code());
            responseVO.setResult(response.body().string());
            responseVO.setErrMsg(null);
        } catch (IOException e) {
            responseVO.setStatusCode(response.code());
            responseVO.setResult(null);
            responseVO.setErrMsg(e.getMessage());
            log.error("接口请求出错：",e.getMessage());
            throw new BizException(String.format("失败的请求：{}", requestVO.toString()));
        }
        return responseVO;
    }

    public static HttpResponseVO httpPost(HttpRequestVO requestVO){
        log.info("请求的接口信息:{}",requestVO.toString());
        HttpResponseVO responseVO = new HttpResponseVO();
        OkHttpClient client = new OkHttpClient.Builder()
                .sslSocketFactory(createSSLSocketFactory())
                .hostnameVerifier(new TrustAllHostnameVerifier())
                .connectTimeout(requestVO.getConnectionTimeOut(), TimeUnit.MILLISECONDS)
                .build();
        Request.Builder build = new Request.Builder().url(requestVO.getUrl());
        if (requestVO.getHeader() != null && !requestVO.getHeader().toString().equals("")){
            Map<String,Object> headerMap = JSONObject.parseObject(requestVO.getHeader().toString(),Map.class);
            for (Map.Entry<String, Object> entry:headerMap.entrySet()){
                build.addHeader(entry.getKey(),entry.getValue().toString());
            }
        }
        if (requestVO.getParamType().equalsIgnoreCase("json")){
            RequestBody body = RequestBody.create(JSON,requestVO.getParam().toString());
            build.post(body).build();
        }else {
            Map<String,String> param = JSONObject.parseObject(requestVO.getParam().toString(),Map.class);
            FormBody.Builder form = new FormBody.Builder();
            for (Map.Entry<String,String> entry:param.entrySet()){
                form.add(entry.getKey(),entry.getValue());
            }
            RequestBody body = form.build();
            build.post(body).build();
        }
        Request request = build.build();
        Call call = client.newCall(request);
        Response response = null;
        try {
            response = call.execute();
            responseVO.setStatusCode(response.code());
            responseVO.setResult(response.body().string());
            responseVO.setErrMsg(null);
        } catch (IOException e) {
            responseVO.setStatusCode(response.code());
            responseVO.setResult(null);
            responseVO.setErrMsg(e.getMessage());
            log.error("接口请求出错：",e.getMessage());
            throw new BizException(String.format("失败的请求：{}", requestVO.toString()));
        }
        return responseVO;
    }

    public static void main(String[] args) {
        HttpRequestVO requestVO = new HttpRequestVO();
        requestVO.setUrl("https://fe-api.zhaopin.com/c/i/adv?_v=0.21476925&x-zp-page-request-id=1dacf08721e34da6b85f0ffc2ae7a55a-1573721834734-514668&x-zp-client-id=44723bfa-7cd0-4280-85fd-5e6d4258e8cb");
        requestVO.setRequestWay("GET");
        HttpResponseVO responseVO = httpGet(requestVO);
        System.out.println(responseVO.toString());
    }

    private static class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

        @Override
        public X509Certificate[] getAcceptedIssuers() {return new X509Certificate[0];}
    }
    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    /**
     * 免https证书验证
     * @return
     */
    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null,  new TrustManager[] { new TrustAllCerts() }, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }
        return ssfFactory;
    }


    /**
     * http请求转换为curl
     * @param requestVO
     * @return
     */
    public static String httpConvertCurl(HttpRequestVO requestVO){
        StringBuffer result = new StringBuffer();
        switch (requestVO.getRequestWay().toUpperCase()){
            case "GET":
                result.append("curl -X GET ").append("'"+requestVO.getUrl()+"' ");
                if (requestVO.getHeader() != null && !requestVO.getHeader().toString().equals("")){
                    Map<String,Object> hMap = JSONObject.parseObject(requestVO.getHeader().toString(),Map.class);
                    for (Map.Entry<String, Object> headerMap:hMap.entrySet()){
                        result.append("-H '"+headerMap.getKey()+":"+headerMap.getValue().toString()+"' ");
                    }
                    return result.toString();
                }
                break;
            case "POST":
                result.append("curl -X POST "+"\r\n").append("'"+requestVO.getUrl()+"' "+"\r\n");
                if (requestVO.getHeader() != null && !requestVO.getHeader().toString().equals("")){
                    Map<String,Object> hMap = JSONObject.parseObject(requestVO.getHeader().toString(),Map.class);
                    for (Map.Entry<String, Object> headerMap:hMap.entrySet()){
                        result.append("-H '"+headerMap.getKey()+":"+headerMap.getValue().toString()+"' "+"\r\n");
                    }
                }
                if (requestVO.getParam() != null && "".equals(requestVO.getParam().toString())){
                    switch (requestVO.getParamType().toUpperCase()){
                        case "JSON":
                            result.append("--data '"+requestVO.getParam().toString()+"'");
                            break;
                        case "FORM":
                            break;
                    }
                }
                break;
        }
        return result.toString();
    }

}
