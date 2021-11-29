package com.bsnbase.sdk.util.algorithm;

import com.bsnbase.sdk.util.common.UserCertInfo;

public interface AlgorithmTypeHandle {
    /**
     * Signature
     *
     * @param stringPrivateKey Private key
     * @param signString       String to be signed
     * @return
     */
    String sign(String stringPrivateKey, String signString) throws Exception;

    /**
     * Signature verification
     *
     * @param pemCertificateString Public key
     * @param signatureString      Signed string
     * @param unsignedString       Unsigned string
     * @return
     */
    boolean verify(String pemCertificateString, String signatureString, String unsignedString) throws Exception;

    /**
     * Get certificate CSR
     *
     * @param DN
     * @return
     */
    UserCertInfo getUserCertInfo(String DN) throws Exception;


}
