package com.bsnbase.sdk.util.xuper;

public class Account {
    public static byte[] subBytes(byte[] src, int begin, int count) {
        byte[] bs = new byte[count];
        System.arraycopy(src, begin, bs, 0, count);
        return bs;
    }

    public static byte[] byteMerger(byte[] bt1, byte[] bt2) {
        byte[] bt3 = new byte[bt1.length + bt2.length];
        int i = 0;
        for (byte bt : bt1) {
            bt3[i] = bt;
            i++;
        }

        for (byte bt : bt2) {
            bt3[i] = bt;
            i++;
        }
        return bt3;
    }

}