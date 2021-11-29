package com.bsnbase.sdk.util.sign;

import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.DERSequenceGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.hyperledger.fabric.sdk.security.CryptoPrimitives;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.ECPrivateKey;

public class EcdsaSign {

    public static String sign(String algorithm, String stringPrivateKey, String signString) throws Exception {
        CryptoPrimitives cryptoPrimitives = new CryptoPrimitives();
        cryptoPrimitives.init();
        PrivateKey privateKey = cryptoPrimitives.bytesToPrivateKey(stringPrivateKey.getBytes());
        ECPrivateKey eck = (ECPrivateKey) privateKey;


        ECNamedCurveParameterSpec spec = ECNamedCurveTable.getParameterSpec(algorithm);
        ECDomainParameters domain = new ECDomainParameters(spec.getCurve(), spec.getG(), spec.getN());
        ECDSASigner signer = new ECDSASigner();
        ECPrivateKeyParameters parameters = new ECPrivateKeyParameters(eck.getS(), domain);
        signer.init(true, parameters);
        byte[] hash = hash(signString.getBytes());
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
        return java.util.Base64.getEncoder().encodeToString(bos.toByteArray());
    }


    private static byte[] hash(byte[] input) {
        return hash(input, 0, input.length);
    }

    private static byte[] hash(byte[] input, int offset, int length) {
        MessageDigest digest = newDigest();
        digest.update(input, offset, length);
        return digest.digest();
    }

    private static MessageDigest newDigest() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);  // Can't happen.
        }
    }
}
