package com.bsnbase.sdk.util.xuper;

import org.bouncycastle.crypto.digests.RIPEMD160Digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    public static MessageDigest newDigest(String algo) {
        try {
            return MessageDigest.getInstance(algo);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);  // Can't happen.
        }
    }

    public static byte[] sha256(byte[] msg) {
        final MessageDigest md = newDigest("SHA-256");
        return md.digest(msg);
    }

    public static byte[] doubleSha256(byte[] msg) {
        return doubleSha256(msg, 0, msg.length);
    }

    public static byte[] doubleSha256(byte[] msg, int offset, int length) {
        final MessageDigest md = newDigest("SHA-256");
        md.update(msg, offset, length);
        return md.digest(md.digest());
    }

    static public byte[] ripeMD128(byte[] msg) {
        RIPEMD160Digest digest = new RIPEMD160Digest();
        digest.update(msg, 0, msg.length);
        byte[] out = new byte[20];
        digest.doFinal(out, 0);
        return out;
    }
}
