package com.bsnbase.sdk.util.algorithm;

import com.bsnbase.sdk.util.common.UserCertInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.hyperledger.fabric.sdk.helper.Config;
import org.hyperledger.fabric.sdk.security.CryptoPrimitives;
import sun.security.pkcs10.PKCS10;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.security.*;
import java.security.cert.Certificate;
import java.util.Base64;

public class R1Algorithm implements AlgorithmTypeHandle {
    /**
     * @param stringPrivateKey
     * @param signString
     * @return
     */
    @Override
    public String sign(String stringPrivateKey, String signString) throws Exception {

        CryptoPrimitives cryptoPrimitives = new CryptoPrimitives();
        cryptoPrimitives.init();
        PrivateKey privateKey = cryptoPrimitives.bytesToPrivateKey(stringPrivateKey.getBytes());

        byte[] signature = cryptoPrimitives.sign(privateKey, signString.getBytes());

        return java.util.Base64.getEncoder().encodeToString(signature);
    }

    /**
     * @param pemCertificateString
     * @param signatureString
     * @param unsignedString
     * @return
     */
    @Override
    public boolean verify(String pemCertificateString, String signatureString, String unsignedString) throws Exception {

        CryptoPrimitives cryptoPrimitives = new CryptoPrimitives();
        cryptoPrimitives.init();
        Certificate publicKey = cryptoPrimitives.bytesToCertificate(pemCertificateString.getBytes());
        // 执行签名
        Signature signature = Signature.getInstance(Config.getConfig().getSignatureAlgorithm());
        // 验证签名
        signature.initVerify(publicKey);
        signature.update(unsignedString.getBytes());

        byte[] signByte = Base64.getDecoder().decode(signatureString);
        return signature.verify(signByte);
    }

    /**
     * 获取证书CSR
     *
     * @param DN
     * @return
     */
    @Override
    public UserCertInfo getUserCertInfo(String DN) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        //"SHA256withECDSA";
        String sigAlg = "SHA256withECDSA";
        int algSize = 256;
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("ECDSA");
        kpg.initialize(algSize, new SecureRandom());
        KeyPair kp = kpg.generateKeyPair();
        Security.addProvider(new BouncyCastleProvider());
        PublicKey publicKey = kp.getPublic();
        PrivateKey privateKey = kp.getPrivate();
        PKCS10 pkcs10 = new sun.security.pkcs10.PKCS10(publicKey);
        Signature signature = Signature.getInstance(sigAlg);
        signature.initSign(privateKey);
        @SuppressWarnings("restriction")
        sun.security.x509.X500Name x500Name = new sun.security.x509.X500Name(DN);
        pkcs10.encodeAndSign(x500Name, signature);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        pkcs10.print(ps);
        String strPEMCSR = baos.toString();
        UserCertInfo user = new UserCertInfo();
        user.setCSRPem(strPEMCSR);
        user.setKey(privateKey);
        return user;
    }


}