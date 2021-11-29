package com.bsnbase.sdk.entity.base;

public interface IBaseReqModel {
    /**
     * Request the gateway data signature
     */
    void sign() throws Exception;

    /**
     * Set the user information in header
     */
    void setReqHeader(String userCode, String appCode);
}
