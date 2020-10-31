package com.bsnbase.sdk.util.algorithm;


import com.bsnbase.sdk.util.common.Common;
import com.bsnbase.sdk.util.common.UserCertInfo;
import com.bsnbase.sdk.util.k1.K1Util;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public  class K1Algorithm implements AlgorithmTypeHandle {
    /**
     * @param stringPrivateKey
     * @param signString
     * @return
     */
    @Override
    public String sign(String stringPrivateKey, String signString) throws Exception {
        byte[] signByte = K1Util.signData(stringPrivateKey, signString.getBytes());
        return Base64.getEncoder().encodeToString(signByte);
    }

    /**
     * @param pemCertificateString
     * @param sign
     * @param str
     * @return
     */
    @Override
    public boolean verify(String pemCertificateString, String sign, String str) throws Exception {
        PublicKey publicKey = Common.loadPublicKey(pemCertificateString, "EC");
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