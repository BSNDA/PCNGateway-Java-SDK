package com.bsnbase.sdk.util.common;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HttpClientUtil {
    private static final String DEFAULT_CHARSET = "UTF-8";


    public static String doPost(HttpClient httpClient, String url, String bodyJson) throws Exception {
        Map<String, String> paramHeader = new HashMap<>();
        paramHeader.put("Accept", "application/json");
        paramHeader.put("Content-Type",
                "application/json;charset=UTF-8");
        return doPost(httpClient, url, paramHeader, bodyJson);
    }

    public static String doPost(HttpClient httpClient, String url, Map<String, String> paramHeader,
                                String bodyJson) throws Exception {
        return doPost(httpClient, url, paramHeader, bodyJson, DEFAULT_CHARSET);
    }


    public static String doPost(HttpClient httpClient, String url, Map<String, String> paramHeader,
                                String bodyJson, String charset) throws Exception {

        String result = null;
        HttpPost httpPost = new HttpPost(url);
        setHeader(httpPost, paramHeader);
        setBody(httpPost, bodyJson, charset);
        HttpResponse response = httpClient.execute(httpPost);
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, charset);
            }
        }

        return result;
    }

    public static String doGet(HttpClient httpClient, String url, Map<String, String> paramHeader,
                               Map<String, String> paramBody) throws Exception {
        return doGet(httpClient, url, paramHeader, paramBody, DEFAULT_CHARSET);
    }

    public static String doGet(HttpClient httpClient, String url, Map<String, String> paramHeader,
                               Map<String, String> paramBody, String charset) throws Exception {

        String result = null;
        HttpGet httpGet = new HttpGet(url);
        setHeader(httpGet, paramHeader);
        HttpResponse response = httpClient.execute(httpGet);
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, charset);
            }
        }

        return result;
    }

    private static void setHeader(HttpRequestBase request, Map<String, String> paramHeader) {
        // Configure Header
        if (paramHeader != null) {
            Set<String> keySet = paramHeader.keySet();
            for (String key : keySet) {
                request.addHeader(key, paramHeader.get(key));
            }
        }
    }

    private static void setBody(HttpPost httpPost, String bodyJson, String charset) throws Exception {
        // Configure parameter
        if (bodyJson != null && bodyJson.length() > 0) {
            StringEntity stringEntity = new StringEntity(bodyJson, charset);
            httpPost.setEntity(stringEntity);

        }
    }

}