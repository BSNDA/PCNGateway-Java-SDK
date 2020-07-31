package com.bsnbase.sdk.util.algorithm;

import com.bsnbase.sdk.util.common.UserCertInfo;

import java.security.KeyPair;

public interface AlgorithmTypeHandle {
    /**
     * 签名
     * @param stringPrivateKey
     * @param signString
     * @return
     */
    String sign(String stringPrivateKey, String signString) throws Exception;

    /**
     * 验签
     * @param pemCertificateString
     * @param signatureString
     * @param unsignedString
     * @return
     */
    boolean verify(String pemCertificateString, String signatureString, String unsignedString) throws Exception;

    /**
     * 获取证书CSR
     * @param DN
     * @return
     */
     UserCertInfo getUserCertInfo(String DN) throws Exception;




}
