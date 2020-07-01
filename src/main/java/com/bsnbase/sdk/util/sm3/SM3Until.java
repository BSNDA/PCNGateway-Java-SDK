package com.bsnbase.sdk.util.sm3;

import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Arrays;


public class SM3Until {
    private static final String ENCODING = "UTF-8";
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * sm3 encryption algorithm
     */
    public static String encrypt(String paramStr){
        //Convert the returned hash value to a hexadecimal string
        String resultHexString = "";
        //convert string to byte array
        byte[] srcData = paramStr.getBytes();
        byte[] resultHash = hash(srcData);
        //convert returned value to a hexadecimal string
        resultHexString = ByteUtils.toHexString(resultHash);
        return resultHexString;
    }

    /**
     * return length=32 byte array
     * @explain generate the corresponding hash value
     * @param srcData
     * @return
     */
    private static byte[] hash(byte[] srcData) {
        SM3Digest digest = new SM3Digest();
        digest.update(srcData,0,srcData.length);
        byte[] hash = new byte[digest.getDigestSize()];
        digest.doFinal(hash,0);
        return  hash;
    }

    /**
     * Encryption by key
     * @param prk
     * @param srcData
     * @return
     */
    public static byte[] hmac(byte[] prk,byte[] srcData){
        KeyParameter kp = new KeyParameter(prk);
        SM3Digest digest = new SM3Digest();
        HMac mac = new HMac(digest);
        mac.init(kp);
        mac.update(srcData,0,srcData.length);
        byte[] result = new byte[mac.getMacSize()];
        mac.doFinal(result,0);
        return  result;
    }

    /**
     * Determine if the source data is consistent with the encrypted data
     * @param strStr
     * @param sm3HexString
     * @return
     */
    public static boolean verify(String strStr,String sm3HexString){
        boolean flag = false;
        try {
            byte[] srcData = strStr.getBytes(ENCODING);
            byte[] sm3Hash = ByteUtils.fromHexString(sm3HexString);
            byte[] newHash = hash(srcData);
            if(Arrays.equals(newHash,sm3Hash)){
                flag = true;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static void main(String[] args) {
        String str = "user01app01abcabcxyz";
        byte[] ss = str.getBytes();
        System.out.println(hmac("asdasd".getBytes(),ss));
        System.out.println(encrypt("user01app01abcabcxyz"));
        System.out.println(verify("user01app01abcabcxyz",encrypt("user01app01abcabcxyz")));
    }
}
