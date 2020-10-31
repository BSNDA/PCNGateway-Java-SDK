package com.bsnbase.sdk.util.sm2;

import com.bsnbase.sdk.util.algorithm.SM2Algorithm;
import com.bsnbase.sdk.util.common.Common;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.gm.GMObjectIdentifiers;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

public class Sm2Util {
    public static Signature signature;
    public static KeyPair keyPair;



    public static void createKeyPair() {
        // 获取SM2 椭圆曲线推荐参数
        X9ECParameters ecParameters = GMNamedCurves.getByName("sm2p256v1");
        // 构造EC 算法参数
        ECNamedCurveParameterSpec sm2Spec = new ECNamedCurveParameterSpec(
                // 设置SM2 算法的 OID
                GMObjectIdentifiers.sm2p256v1.toString()
                // 设置曲线方程
                , ecParameters.getCurve()
                // 椭圆曲线G点
                , ecParameters.getG()
                // 大整数N
                , ecParameters.getN());
        try {
            // 创建 密钥对生成器
            KeyPairGenerator gen = KeyPairGenerator.getInstance("EC", new BouncyCastleProvider());
            // 使用SM2的算法区域初始化密钥生成器
            gen.initialize(sm2Spec, new SecureRandom());
            // 获取密钥对
            keyPair = gen.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 签名
     *
     * @param privateKey 签名私钥
     * @param plainText  明文
     * @return
     */
    public static byte[]  encrypt(PrivateKey privateKey, byte[] plainText) {

        try {
            signature.initSign(privateKey);
            signature.update(plainText);
            byte[] bytes = signature.sign();
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 验证签名
     *
     * @param publicKey  签名公钥
     * @param signResult 签名结果
     * @param plainText  明文
     * @return
     */
    public static boolean verify(PublicKey publicKey, byte[] signResult, String plainText) {
        boolean result = false;
        try {
            signature.initVerify(publicKey);
            signature.update(plainText.getBytes());
            result = signature.verify(signResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * Hex字符串转byte
     * @param inHex 待转换的Hex字符串
     * @return  转换后的byte
     */
    public static byte hexToByte(String inHex) {
        return (byte) Integer.parseInt(inHex, 16);
    }


        public static byte[] hexToByteArray(String inHex){
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1){
            //奇数
            hexlen++;
            result = new byte[(hexlen/2)];
            inHex="0"+inHex;
        }else {
            //偶数
            result = new byte[(hexlen/2)];
        }
        int j=0;
        for (int i = 0; i < hexlen; i+=2){
            result[j]=hexToByte(inHex.substring(i,i+2));
            j++;
        }
        return result;
    }
}
