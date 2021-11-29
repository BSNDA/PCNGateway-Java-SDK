package com.bsnbase.sdk.util.algorithm;

import com.bsnbase.sdk.util.common.UserCertInfo;
import com.bsnbase.sdk.util.sign.Sm2SignUtil;
import com.bsnbase.sdk.util.sign.Sm2Util;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.util.encoders.Base64;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Provider;

public class SM2Algorithm implements AlgorithmTypeHandle {
    /**
     * @param privateKey
     * @param signString
     * @return
     */
    @Override
    public String sign(String privateKey, String signString) throws Exception {
        byte[] sm3DigestBytes = Sm2SignUtil.SM3Digest(signString.getBytes(StandardCharsets.UTF_8));
        return Sm2SignUtil.signSM2ByPKStr(privateKey, sm3DigestBytes);
    }

    /**
     * @param publicKey
     * @param signatureString
     * @return
     */
    @Override
    public boolean verify(String publicKey, String mac, String signatureString) throws Exception {
        byte[] sm3DigestBytes = Sm2SignUtil.SM3Digest(signatureString.getBytes(StandardCharsets.UTF_8));
        boolean verify = Sm2SignUtil.verifySM2ByPubKeyStr(publicKey, mac, sm3DigestBytes);
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
        Sm2Util.createKeyPair();
        KeyPair kp = Sm2Util.keyPair;
        PrivateKey privateKey = kp.getPrivate();
        PKCS10CertificationRequestBuilder builder = new PKCS10CertificationRequestBuilder(new X500Name(DN), SubjectPublicKeyInfo.getInstance(kp.getPublic().getEncoded()));
        JcaContentSignerBuilder jcaContentSignerBuilder = new JcaContentSignerBuilder("SM3withSM2");
        Provider BC = new BouncyCastleProvider();
        jcaContentSignerBuilder.setProvider(BC);
        ContentSigner contentSigner = jcaContentSignerBuilder.build(kp.getPrivate());
        PKCS10CertificationRequest csr = builder.build(contentSigner);
        byte[] der = csr.getEncoded();
        String strPEMCSR = "-----BEGIN CERTIFICATE REQUEST-----\n";
        strPEMCSR += new String(Base64.encode(der));
        strPEMCSR += "\n-----END CERTIFICATE REQUEST-----\n";
        UserCertInfo user = new UserCertInfo();
        user.setCSRPem(strPEMCSR);
        user.setKey(privateKey);
        return user;
    }

}