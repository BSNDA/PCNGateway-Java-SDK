package com.bsnbase.sdk.util.des;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;

/**
 * 对称加密des
 */
public class DES {

    //JDK的实现
    public static void main(String[] args) {
        //定义一个要加密的字符串
        String prk = "xiehuaxin123456";
        jdkDES(prk);
    }
    public static byte[] jdkDES(String prk) {
        try {
            //1.生成KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");//Key的生成器
            keyGenerator.init(56);//指定keySize
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();

            //2.KEY转换
            DESKeySpec desKeySpec = new DESKeySpec(bytesKey);//实例化DESKey秘钥的相关内容
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");//实例一个秘钥工厂，指定加密方式
            Key convertSecretKey = factory.generateSecret(desKeySpec);

            //3.加密    DES/ECB/PKCS5Padding--->算法/工作方式/填充方式
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");//通过Cipher这个类进行加解密相关操作
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] result = cipher.doFinal(prk.getBytes());//输入要加密的内容
            System.out.println("加密的结果：" + Hex.encodeHexString(result));

            //4.解密
            cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
            result = cipher.doFinal(result);
            System.out.println("解密结果：" + new String(result));
            return result;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
