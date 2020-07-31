package com.bsnbase.sdk.util.trans;

import com.bsnbase.sdk.util.enums.AlgorithmTypeEnum;
import com.bsnbase.sdk.util.sm2.Sm2SignUtil;
import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.security.CryptoPrimitives;

import java.security.*;

public class FabricTransUtil {


    public static  byte[] getTransSign(AlgorithmTypeEnum algorithmTypeEnum, String stringPrivateKey, byte[] signString) throws Exception {
        byte[]  transSign = null;
        switch (algorithmTypeEnum) {
            case AppAlgorithmType_SM2:
                transSign =   Sm2TransSign(stringPrivateKey,signString);
                break;
            case AppAlgorithmType_R1:
                transSign =  R1TransSign(stringPrivateKey,signString);
                break;
            default:
        }
        return transSign;

    }

    /**
     * R1 交易签名
     * @param stringPrivateKey
     * @param signString
     * @return
     */
    public static byte[] R1TransSign(String stringPrivateKey, byte[] signString) throws CryptoException, IllegalAccessException, InstantiationException, ClassNotFoundException, InvalidArgumentException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException {
            CryptoPrimitives cryptoPrimitives = new CryptoPrimitives();
            cryptoPrimitives.init();
            PrivateKey privateKey = cryptoPrimitives.bytesToPrivateKey(stringPrivateKey.getBytes());
            byte[] signature = cryptoPrimitives.sign(privateKey, signString);
            return signature;
    }


    /**
     * sm2 交易签名
     * @param stringPrivateKey
     * @param signString
     * @return
     */
    public static byte[] Sm2TransSign(String stringPrivateKey, byte[] signString) throws CryptoException, IllegalAccessException, InstantiationException, ClassNotFoundException, InvalidArgumentException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException {
        byte[] sm3DigestBytes = Sm2SignUtil.SM3Digest(signString);
        byte[] signBytes = new byte[0];
        try {
            signBytes = Sm2SignUtil.SM2Sign(sm3DigestBytes, Sm2SignUtil.getPrivateKey(stringPrivateKey.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return signBytes;
    }



}