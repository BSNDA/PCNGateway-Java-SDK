package com.bsnbase.sdk.util.common;

import com.alibaba.fastjson.JSON;
import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResArrayModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.base.IBody;
import com.bsnbase.sdk.util.enums.ResultInfoEnum;
import com.bsnbase.sdk.util.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jetbrains.annotations.NotNull;

import javax.json.JsonException;


@Slf4j
public class HttpService<T extends Object & IBody, K extends Object & IBody> {

    //Default network connection timeout
    private static final int CONNECTION_TIMEOUT = 20000;


    public BaseResModel<K> post(BaseReqModel<T> req, String url, Class<K> clazz) {

        String res;
        BaseResModel<K> resModel = new BaseResModel<K>();
        try {
            req.sign();
            res = doPost(req, url);
        } catch (GlobalException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(ResultInfoEnum.SYSTEM_ERROR);
        }

        if (res != null && res.length() > 0) {
            try {

                resModel = JSON.parseObject(res, resModel.getClass());

                String bodystr = JSON.toJSONString(resModel.getBody());

                boolean checkJson = JsonUtil.getJSONType(bodystr);

                resModel.setBody(checkJson ? JSON.parseObject(bodystr, clazz) : null);

            } catch (Exception e) {
                throw new GlobalException(ResultInfoEnum.DATA_CONVERSION_ERROR);
            }
            // Signature verfication, data transfer
            if (resModel == null) {
                throw new GlobalException(ResultInfoEnum.INVALID_RESPONSE_ERROR);
            }

            boolean result = false;
            try {
                result = resModel.verify();
            } catch (Exception e) {
                throw new GlobalException(ResultInfoEnum.RES_MAC_ERROR);
            }

            if (!result) {
                throw new GlobalException(ResultInfoEnum.RES_MAC_ERROR);
            }

            if (resModel.getHeader().getCode() != 0) {
                throw new GlobalException(resModel.getHeader().getMsg());
            } else {


            }

            return resModel;

        } else {
            throw new GlobalException(ResultInfoEnum.SYSTEM_ERROR);
        }

    }

    public BaseResModel<K> noSignPost(BaseReqModel<T> req, String url, Class<K> clazz) {

        String res;
        BaseResModel<K> resModel = new BaseResModel<K>();
        try {
            res = doPost(req, url);
        } catch (GlobalException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(ResultInfoEnum.SYSTEM_ERROR);
        }

        if (res != null && res.length() > 0) {
            try {

                resModel = JSON.parseObject(res, resModel.getClass());

                String bodystr = JSON.toJSONString(resModel.getBody());

                resModel.setBody(JSON.parseObject(bodystr, clazz));

            } catch (JsonException e) {
                throw new GlobalException(ResultInfoEnum.DATA_CONVERSION_ERROR);
            }
            // Signature verfication, data transfer
            if (resModel == null) {
                throw new GlobalException(ResultInfoEnum.INVALID_RESPONSE_ERROR);
            }

            if (resModel.getHeader().getCode() != 0) {
                throw new GlobalException(resModel.getHeader().getMsg());
            } else {
            }
            return resModel;
        } else {
            throw new GlobalException(ResultInfoEnum.SYSTEM_ERROR);
        }

    }


    public BaseResArrayModel<K> arrayPost(BaseReqModel<T> req, String url, Class<K> clazz) {

        String res;
        BaseResArrayModel<K> resModel = new BaseResArrayModel<K>();
        try {
            req.sign();
            res = doPost(req, url);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(ResultInfoEnum.SYSTEM_ERROR);
        }

        if (res != null && res.length() > 0) {
            try {

                resModel = JSON.parseObject(res, resModel.getClass());

                String bodystr = JSON.toJSONString(resModel.getBody());

                resModel.setBody(JSON.parseArray(bodystr, clazz));

            } catch (JsonException e) {
                throw new GlobalException(ResultInfoEnum.DATA_CONVERSION_ERROR);
            }
            // Signature verfication, data transfer
            if (resModel == null) {
                throw new GlobalException(ResultInfoEnum.INVALID_RESPONSE_ERROR);
            }

            boolean result = false;
            try {
                result = resModel.verify();
            } catch (Exception e) {
                throw new GlobalException(ResultInfoEnum.RES_MAC_ERROR);
            }

            if (!result) {
                throw new GlobalException(ResultInfoEnum.RES_MAC_ERROR);
            }

            if (resModel.getHeader().getCode() != 0) {
                throw new GlobalException(resModel.getHeader().getMsg());
            } else {


            }

            return resModel;

        } else {
            throw new GlobalException(ResultInfoEnum.SYSTEM_ERROR);
        }

    }


    private String doPost(@NotNull BaseReqModel<T> req, String url) throws Exception {
        String param = JSON.toJSONString(req);
        log.info("-------------Request Url-------------:\n" + url);
        log.info("-------------Sent Data-------------:\n" + param);
        HttpClient httpClient = getHttpClient();
        String res = HttpClientUtil.doPost(httpClient, url, param);
        log.info("-------------Response Data-------------:" + res);
        return res;
    }


    private HttpClient getHttpClient() throws Exception {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(
                CONNECTION_TIMEOUT
        ).build();
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig).build();

        return httpClient;
    }


}