package com.bsnbase.sdk.util.k1;

import com.bsnbase.sdk.util.common.Common;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.DERSequenceGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.fisco.bcos.web3j.abi.datatypes.Int;
import org.hyperledger.fabric.sdk.security.CryptoPrimitives;

import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class K1Util {
    static final Base64.Decoder DECODER = Base64.getDecoder();

    /**
     * k1签名
     * @param stringPrivateKey
     * @param signString
     * @return
     * @throws Exception
     */
    public static byte[] signData(String stringPrivateKey, byte[] signString) throws Exception {
        CryptoPrimitives cryptoPrimitives = new CryptoPrimitives();
        cryptoPrimitives.init();
        PrivateKey privateKey = cryptoPrimitives.bytesToPrivateKey(stringPrivateKey.getBytes());
        ECPrivateKey eck = (ECPrivateKey) privateKey;


        ECNamedCurveParameterSpec spec = ECNamedCurveTable.getParameterSpec("secp256k1");
        ECDomainParameters domain = new ECDomainParameters(spec.getCurve(), spec.getG(), spec.getN());
        ECDSASigner signer = new ECDSASigner();
        ECPrivateKeyParameters parameters = new ECPrivateKeyParameters(eck.getS(), domain);
        signer.init(true, parameters);
        byte[] hash = hash(signString);
        BigInteger[] signature = signer.generateSignature(hash);

        BigInteger r = signature[0];
        BigInteger s = signature[1];

        // BIP62: "S must be less than or equal to half of the Group Order N"

        BigInteger overTwo = domain.getN().divide(new BigInteger("2"));
        if (s.compareTo(overTwo) == 1) {
            s = domain.getN().subtract(s);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DERSequenceGenerator seq = new DERSequenceGenerator(bos);
        seq.addObject(new ASN1Integer(r));
        seq.addObject(new ASN1Integer(s));
        seq.close();
        return bos.toByteArray();
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
        byte[] asBytes = DECODER.decode(privateKeyPEM.replace("\r\n", ""));
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(asBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory.generatePrivate(spec);
    }

    public static PublicKey loadECPublicKey(String content, String algorithm) throws Exception {
        String strPublicKey = content.replaceAll("-----BEGIN PUBLIC KEY-----", "")
                .replaceAll("-----END PUBLIC KEY-----", "").replace("\r", "").replace("\n", "");
        byte[] asBytes = DECODER.decode(strPublicKey.replace("\r\n", ""));
        X509EncodedKeySpec spec = new X509EncodedKeySpec(asBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory.generatePublic(spec);
    }



    public static byte[] hash(byte[] input) {
        return hash(input, 0, input.length);
    }

    public static byte[] hash(byte[] input, int offset, int length) {
        MessageDigest digest = newDigest();
        digest.update(input, offset, length);
        return digest.digest();
    }

    public static MessageDigest newDigest() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);  // Can't happen.
        }
    }
}
