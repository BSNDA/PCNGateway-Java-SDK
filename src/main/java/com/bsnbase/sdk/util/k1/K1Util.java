package com.bsnbase.sdk.util.k1;

import com.bsnbase.sdk.util.common.Common;

import java.io.*;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class K1Util {
    static final Base64.Decoder decoder = Base64.getDecoder();

    /**
     * k1签名
     * @param algorithm
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] signData(String algorithm, byte[] data, PrivateKey key) throws Exception {
        Signature signer = Signature.getInstance(algorithm);
        signer.initSign(key);
        signer.update(data);
        return (signer.sign());
    }

    /**
     * k1 验签
     * @param algorithm
     * @param data
     * @param key
     * @param sig
     * @return
     * @throws Exception
     */
    public static boolean verifySign(String algorithm, byte[] data, PublicKey key, byte[] sig) throws Exception {
        Signature signer = Signature.getInstance(algorithm);
        signer.initVerify(key);
        signer.update(data);
        return (signer.verify(sig));
    }



    public static PrivateKey loadECPrivateKey(String content, String algorithm) throws Exception {
        String privateKeyPEM = content.replaceAll("-----BEGIN PRIVATE KEY-----", "")
                .replaceAll("-----END PRIVATE KEY-----", "").replace("\r", "").replace("\n", "");
        byte[] asBytes = decoder.decode(privateKeyPEM.replace("\r\n", ""));
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(asBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory.generatePrivate(spec);
    }

    public static PublicKey loadECPublicKey(String content, String algorithm) throws Exception {
        String strPublicKey = content.replaceAll("-----BEGIN PUBLIC KEY-----", "")
                .replaceAll("-----END PUBLIC KEY-----", "").replace("\r", "").replace("\n", "");
        byte[] asBytes = decoder.decode(strPublicKey.replace("\r\n", ""));
        X509EncodedKeySpec spec = new X509EncodedKeySpec(asBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory.generatePublic(spec);
    }

    // readBytes代码
    public static byte[] readBytes(final InputStream inputStream) throws IOException {
        final int BUFFER_SIZE = 1024;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int readCount;
        byte[] data = new byte[BUFFER_SIZE];
        while ((readCount = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, readCount);
        }

        buffer.flush();
        return buffer.toByteArray();
    }


    public static void main(String[] args) throws Exception {
        testSignVerify();

    }

    public static void testSignVerify() throws Exception {
        // 需要签名的数据

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
        // curveName这里取值：secp256k1
        ECGenParameterSpec ecGenParameterSpec = new ECGenParameterSpec("secp256k1");
        keyPairGenerator.initialize(ecGenParameterSpec, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

//        PublicKey publicKey = keyPair.getPublic();
//        PrivateKey privateKey = keyPair.getPrivate();

//        K1Util.savePublicKeyAsPEM(publicKey, "publickey.pem");
//        K1Util.savePrivateKeyAsPEM(privateKey, "privatekey.pem");
        PrivateKey privateKey =K1Util.loadECPrivateKey(Common.readFile(Common.getClassPathResource("cert/k1/private_key.pem")),"EC");
        PublicKey publicKey =  K1Util.loadECPublicKey(Common.readFile(Common.getClassPathResource("cert/k1/public_key.pem")),"EC");
        String data = "-1用户已存在";
        // 生成秘钥，在实际业务中，应该加载秘钥
//        PublicKey publicKey1 = K1Util.loadECPublicKey(Common.readFile(Common.getClassPathResource("cert/k1/public_key.pem")), "EC");
//        PrivateKey privateKey1 = K1Util.loadECPrivateKey(Common.readFile(Common.getClassPathResource("cert/k1/private_Key.pem")), "EC");

        // 计算签名
        byte[] sign1 = signData("SHA256withECDSA", data.getBytes(), privateKey);

        // sign1和sign2的内容不同，因为ECDSA在计算的时候，加入了随机数k，因此每次的值不一样
        // 随机数k需要保密，并且每次不同

        boolean a = verifySign("SHA256withECDSA", data.getBytes(), publicKey, sign1);


        System.out.println(a);
    }

}
