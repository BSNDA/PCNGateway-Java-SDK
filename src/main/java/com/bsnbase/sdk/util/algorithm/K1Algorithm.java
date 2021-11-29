package com.bsnbase.sdk.util.algorithm;


import com.bsnbase.sdk.util.common.Common;
import com.bsnbase.sdk.util.common.UserCertInfo;
import com.bsnbase.sdk.util.sign.EcdsaSign;

import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

public class K1Algorithm implements AlgorithmTypeHandle {
    /**
     * @param stringPrivateKey
     * @param signString
     * @return
     */
    @Override
    public String sign(String stringPrivateKey, String signString) throws Exception {
        return EcdsaSign.sign("secp256k1", stringPrivateKey, signString);
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
        Signature signer = Signature.getInstance("SHA256withECDSA");
        signer.initVerify(publicKey);
        signer.update(str.getBytes());
        boolean verify = (signer.verify(signByte));
        return verify;
    }

    /**
     * Get certificate CSR
     *
     * @param DN
     * @return
     */
    @Override
    public UserCertInfo getUserCertInfo(String DN) throws Exception {
        return null;
    }


}