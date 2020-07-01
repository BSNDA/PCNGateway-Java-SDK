package com.bsnbase.sdk.util.common;

import com.alibaba.fastjson.JSON;
import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.base.BaseResArrayModel;
import com.bsnbase.sdk.entity.base.IBody;
import com.bsnbase.sdk.util.enums.ResultInfoEnum;
import com.bsnbase.sdk.util.exception.GlobalException;
import org.apache.http.client.HttpClient;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.json.JsonException;
import java.io.File;


public class HttpService<T extends Object&IBody,K extends Object&IBody> {

    public BaseResModel<K> post(BaseReqModel<T> req,String url,String cert ,Class<K> clazz){

        String res;
        BaseResModel<K> resModel =new BaseResModel<K>();
        try {
            res = doPost(req,url,cert);

            System.out.println("response results：" + res);
        }catch (GlobalException e){
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(ResultInfoEnum.SYSTEM_ERROR);
        }

        if (res != null && res.length() > 0) {
            try {

                resModel = JSON.parseObject(res, resModel.getClass());

                String bodystr = JSON.toJSONString(resModel.getBody());

                resModel.setBody(JSON.parseObject(bodystr,clazz));

            } catch (JsonException e) {
                throw new GlobalException("failed to convert data, invalid response results");
            }
            // verify the signature and convert data
            if (resModel == null) {
                throw new GlobalException("invalid response results");
            }

            boolean result = false;
            try {
                result =resModel.verify() ;
            } catch (Exception e) {
                throw new GlobalException(ResultInfoEnum.RES_MAC_ERROR);
            }

            if (!result) {
                throw new GlobalException("failed to verify the signature");
            }

            if (resModel.getHeader().getCode() != 0) {
                throw new GlobalException(resModel.getHeader().getMsg());
            }else {


            }

            return resModel;

        } else {
            throw new GlobalException(ResultInfoEnum.SYSTEM_ERROR);
        }

    }

    public BaseResArrayModel<K> arrayPost(BaseReqModel<T> req, String url, String cert , Class<K> clazz){

        String res ;
        BaseResArrayModel<K> resModel =new BaseResArrayModel<K>();
        try {
            res = doPost(req,url,cert);

            System.out.println("response result：" + res);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(ResultInfoEnum.SYSTEM_ERROR);
        }

        if (res != null && res.length() > 0) {
            try {

                resModel = JSON.parseObject(res, resModel.getClass());

                String bodystr = JSON.toJSONString(resModel.getBody());

                resModel.setBody(JSON.parseArray(bodystr,clazz));

            } catch (JsonException e) {
                throw new GlobalException("failed to convert data, invalid response results");
            }
            // verify the signature, convert data 
            if (resModel == null) {
                throw new GlobalException("invalid response results");
            }

            boolean result = false;
            try {
                result =resModel.verify() ;
            } catch (Exception e) {
                throw new GlobalException(ResultInfoEnum.RES_MAC_ERROR);
            }

            if (!result) {
                throw new GlobalException("failed to verify the signature");
            }

            if (resModel.getHeader().getCode() != 0) {
                throw new GlobalException(resModel.getHeader().getMsg());
            }else {


            }

            return resModel;

        } else {
            throw new GlobalException(ResultInfoEnum.SYSTEM_ERROR);
        }

    }

    private String doPost(@NotNull BaseReqModel<T> req, String url, String cert) throws Exception {
        req.sign();
        String param = JSON.toJSONString(req);
        HttpClient httpClient = getHttpClient(cert);
        String res = HTTPSClientUtil.doPost(httpClient, url, param);

        System.out.println("response results：" + res);
        return res;
    }

    private HttpClient getHttpClient(String httpCertPath) throws Exception {
        Resource keystoreResource = new ClassPathResource(httpCertPath);
        File keystoreFile = keystoreResource.getFile();
        return new HTTPSCertifiedClient().init(keystoreFile.getAbsolutePath());
    }

}
