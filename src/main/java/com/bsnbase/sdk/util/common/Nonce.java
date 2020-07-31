package com.bsnbase.sdk.util.common;

import java.util.Base64;
import java.util.Random;

public class Nonce {
    public static byte[] getNonce(){
        byte[] arr = new byte[24];
        new Random().nextBytes(arr);
        return arr;
    }

    public static String getNonceString(){
        byte[] bytes = getNonce();
        return Base64.getEncoder().encodeToString(bytes);
    }
}
