package com.bsnbase.sdk.util.sign;

import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.gm.GMObjectIdentifiers;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;

import java.security.*;

public class Sm2Util {
    public static Signature signature;
    public static KeyPair keyPair;

    public static void createKeyPair() {
        // Get the recommended parameters for SM2 elliptic curves
        X9ECParameters ecParameters = GMNamedCurves.getByName("sm2p256v1");
        // Construct the EC algorithm parameters
        ECNamedCurveParameterSpec sm2Spec = new ECNamedCurveParameterSpec(
                // Set the OID of the SM2 algorithm
                GMObjectIdentifiers.sm2p256v1.toString()
                // Set the curve equation
                , ecParameters.getCurve()
                // elliptic curve G-point
                , ecParameters.getG()
                // large integer N
                , ecParameters.getN());
        try {
            // create the key pair generator
            KeyPairGenerator gen = KeyPairGenerator.getInstance("EC", new BouncyCastleProvider());
            // initialize the key generator using the algorithm region of SM2
            gen.initialize(sm2Spec, new SecureRandom());
            // Get the key pair
            keyPair = gen.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Signature
     *
     * @param privateKey Private key for signing
     * @param plainText  Plaintext
     * @return
     */
    public static byte[] encrypt(PrivateKey privateKey, byte[] plainText) {

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
     * Verify signature
     *
     * @param publicKey  Public key for signing
     * @param signResult Signing result
     * @param plainText  Plaintext
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
     * Hex string convert to byte
     *
     * @param inHex Hex string to be converted
     * @return Converted byte
     */
    public static byte hexToByte(String inHex) {
        return (byte) Integer.parseInt(inHex, 16);
    }


    public static byte[] hexToByteArray(String inHex) {
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1) {
            //Odd number
            hexlen++;
            result = new byte[(hexlen / 2)];
            inHex = "0" + inHex;
        } else {
            //Even number
            result = new byte[(hexlen / 2)];
        }
        int j = 0;
        for (int i = 0; i < hexlen; i += 2) {
            result[j] = hexToByte(inHex.substring(i, i + 2));
            j++;
        }
        return result;
    }
}
