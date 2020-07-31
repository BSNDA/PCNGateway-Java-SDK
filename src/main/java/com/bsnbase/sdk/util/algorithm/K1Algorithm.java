package com.bsnbase.sdk.util.algorithm;


import com.bsnbase.sdk.util.common.UserCertInfo;
import com.bsnbase.sdk.util.k1.K1Util;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * @author 2020-7-30
 */
public  class K1Algorithm implements AlgorithmTypeHandle {
    /**
     * @param stringPrivateKey
     * @param signString
     * @return
     */
    @Override
    public String sign(String stringPrivateKey, String signString) throws Exception {
        PrivateKey privateKey = K1Util.loadECPrivateKey(stringPrivateKey, "EC");
        byte[] signature = K1Util.signData("SHA256withECDSA", signString.getBytes(), privateKey);
        return java.util.Base64.getEncoder().encodeToString(signature);
    }

    /**
     * @param pemCertificateString
     * @param sign
     * @param str
     * @return
     */
    @Override
    public boolean verify(String pemCertificateString, String sign, String str) throws Exception {
        PublicKey publicKey = K1Util.loadECPublicKey(pemCertificateString, "EC");
        byte[] signByte = Base64.getDecoder().decode(sign);
        boolean verify = K1Util.verifySign("SHA256withECDSA", str.getBytes(), publicKey, signByte);
        return verify;
    }

    /**
     * 获取证书CSR
     *
     * @param DN
     * @return
     */
    @Override
    public UserCertInfo getUserCertInfo(String DN) throws Exception {
        return null;
    }
}