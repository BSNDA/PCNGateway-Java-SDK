package com.bsnbase.sdk.util.sign;

import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.helper.Config;
import org.hyperledger.fabric.sdk.security.CryptoPrimitives;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.util.Base64;


public class SignUtil {


    /**
     * @param privateKeyPath 
     * @param signString     
     * @return java.lang.String 
     * @description：sign
     * @created by 2019/5/15 17:27
     */
    public static byte[] signByPKPath(String privateKeyPath, byte[] signString) throws CryptoException, IllegalAccessException, InstantiationException, ClassNotFoundException, InvalidArgumentException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException {
        String privatekey = readFile(privateKeyPath);
        return sign(privatekey, signString);
    }

    /**
     * @param stringPrivateKey 
     * @param signString       
     * @return java.lang.String 
     * @description：sign
     * @created by 2019/5/15 17:28
     */
    public static byte[] sign(String stringPrivateKey, byte[] signString) throws CryptoException, IllegalAccessException, InstantiationException, ClassNotFoundException, InvalidArgumentException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException {

        CryptoPrimitives cryptoPrimitives = new CryptoPrimitives();
        cryptoPrimitives.init();
        PrivateKey privateKey = cryptoPrimitives.bytesToPrivateKey(stringPrivateKey.getBytes());

        byte[] signature = cryptoPrimitives.sign(privateKey, signString);

        //String sign = Base64.getEncoder().encodeToString(signature);
        return signature;
    }

    /**
     * @param filePath 
     * @return java.lang.String 
     * @description：read File
     * @created by 2019/5/15 17:29
     */
    public static String readFile(String filePath) {
        StringBuffer info = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            int line = 1;
            String tempString = null;
            while ((tempString = br.readLine()) != null) {
                info.append(tempString).append("\n");
                line++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return info.toString();
        }
    }


    public static boolean verifyByCertPath(String certPath, String signatureString, String signString) throws ClassNotFoundException, InvalidArgumentException, InstantiationException, CryptoException, IllegalAccessException, CertificateEncodingException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        String cert = readFile(certPath);
        return verify(cert, signatureString, signString);
    }


    public static boolean verify(String pemCertificateString, String signatureString, String unsignedString) throws IllegalAccessException, InstantiationException, ClassNotFoundException, CryptoException, InvalidArgumentException, CertificateEncodingException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {

        CryptoPrimitives cryptoPrimitives = new CryptoPrimitives();
        cryptoPrimitives.init();
        Certificate publicKey = cryptoPrimitives.bytesToCertificate(pemCertificateString.getBytes());

        Signature signature = Signature.getInstance(Config.getConfig().getSignatureAlgorithm());
        signature.initVerify(publicKey);
        signature.update(unsignedString.getBytes());

        byte[] signByte = Base64.getDecoder().decode(signatureString);
        return  signature.verify(signByte);
    }

}
