package com.bsnbase.sdk.util.sm2;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.util.encoders.Hex;

import java.io.IOException;
import java.util.UUID;

public class SecurityTestAll {
    //SM2 Public key encoding format
    //HardPubKey:3059301306072A8648CE3D020106082A811CCF5501822D03420004+X+Y
    //SoftPubKey:04+X+Y
    public static final String SM2PubHardKeyHead = "3059301306072A8648CE3D020106082A811CCF5501822D034200";
    //SM2 Encryption ciphertext difference: soft encryption adds 04
    //SM2 Encryption auto-signature encoding format
    //HardSign:R+S
    //public static final String SM2PubHardKeyHead="3059301306072A8648CE3D020106082A811CCF5501822D034200";
    //private final String SM4_CBC_IV="";
    //private final String SM2="";


    public static void main(String[] args) throws Exception {
        System.out.println("--Generates the SM2 secret key--:");
        SM2KeyVO sm2KeyVO = generateSM2Key();
        System.out.println("public key:" + sm2KeyVO.getPubHexInSoft());
        System.out.println("private key:" + sm2KeyVO.getPriHexInSoft());
        //data encryption
        System.out.println("--Test encryption start--");
        String src = "This is string";
        System.out.println("Original utf-8 hex:" + Util.byteToHex(src.getBytes()));
        String SM2Enc = SM2Enc(sm2KeyVO.getPubHexInSoft(), src);
        System.out.println("encryption:");
        System.out.println("chiper:" + SM2Enc);
        String SM2Dec = SM2Dec(sm2KeyVO.getPriHexInSoft(), SM2Enc);//private key decode
        System.out.println("decryption:" + SM2Dec);
        System.out.println("--End of test encryption--");

        System.out.println("--Test SM2 signatures--");
        System.out.println("Original hex:" + Util.byteToHex(src.getBytes()));
        String s5 = Util.byteToHex(src.getBytes());

        System.out.println("Signature test begins:");
        SM2SignVO sign = genSM2Signature(sm2KeyVO.getPriHexInSoft(), s5);
        System.out.println("Soft-encrypt signature results:" + sign.getSm2_signForSoft());
        System.out.println("Encrypt machine signature results:" + sign.getSm2_signForHard());
        //System.out.println("Transfer signature test:"+SM2SignHardToSoft(sign.getSm2_signForHard()));
        System.out.println("Verify signature 1, software encryption:");
        boolean b = verifySM2Signature(sm2KeyVO.getPubHexInSoft(), s5, sign.getSm2_signForSoft());
        System.out.println("verify the signature via software encryption:" + b);
        System.out.println("verify the signature2, hardware encryption:");
        String sm2_signForHard = sign.getSm2_signForHard();
        System.out.println("signature R:"+sign.sign_r);
        System.out.println("signature S:"+sign.sign_s);
        //System.out.println("hard:"+sm2_signForHard);
        b = verifySM2Signature(sm2KeyVO.getPubHexInSoft(), s5, SM2SignHardToSoft(sign.getSm2_signForHard()));
        System.out.println("verify the signature via hardware encryption:" + b);
        if (!b) {
            throw new RuntimeException();
        }
        System.out.println("--end of signature test--");

    }

    //SM2 public key soft switch to Hard
    public static String SM2PubKeySoftToHard(String softKey) {
        return SM2PubHardKeyHead + softKey;
    }

    //SM2 public key Hard switch to soft
    public static String SM2PubKeyHardToSoft(String hardKey) {
        return hardKey.replaceFirst(SM2PubHardKeyHead, "");
    }

    //generate asymmetric keys
    public static SM2KeyVO generateSM2Key() throws IOException {
        SM2KeyVO sm2KeyVO = SM2EncDecUtils.generateKeyPair();
        return sm2KeyVO;
    }

    //Public key encryption
    public static String SM2Enc(String pubKey, String src) throws IOException {
        String encrypt = SM2EncDecUtils.encrypt(Util.hexStringToBytes(pubKey), src.getBytes());
        //delete 04
        encrypt=encrypt.substring(2,encrypt.length());
        return encrypt;
    }

    //private key decode
    public static String SM2Dec(String priKey, String encryptedData) throws IOException {
        // add 04
        encryptedData="04"+encryptedData;
        byte[] decrypt = SM2EncDecUtils.decrypt(Util.hexStringToBytes(priKey), Util.hexStringToBytes(encryptedData));
        return new String(decrypt);
    }

    //private key signature, parameter 2: the original string must be hex!!!! Because it is directly used to calculate the signature, it may be an SM3 string or a normal string Hex
    public static SM2SignVO genSM2Signature(String priKey, String sourceData) throws Exception {
        SM2SignVO sign = SM2SignVerUtils.Sign2SM2(Util.hexToByte(priKey), Util.hexToByte(sourceData));
        return sign;
    }

    //verify the public key parameter two:  the original string must be hex!! Because it is directly used to calculate the signature, it may be an SM3 string or a normal string Hex
    public static boolean verifySM2Signature(String pubKey, String sourceData, String hardSign) {
        SM2SignVO verify = SM2SignVerUtils.VerifySignSM2(Util.hexStringToBytes(pubKey), Util.hexToByte(sourceData), Util.hexToByte(hardSign));
        return verify.isVerify();
    }

    //SM2 signature Hard to soft
    public static String SM2SignHardToSoft(String hardSign) {
        byte[] bytes = Util.hexToByte(hardSign);
        byte[] r = new byte[bytes.length / 2];
        byte[] s = new byte[bytes.length / 2];
        System.arraycopy(bytes, 0, r, 0, bytes.length / 2);
        System.arraycopy(bytes, bytes.length / 2, s, 0, bytes.length / 2);
        ASN1Integer d_r = new ASN1Integer(Util.byteConvertInteger(r));
        ASN1Integer d_s = new ASN1Integer(Util.byteConvertInteger(s));
        ASN1EncodableVector v2 = new ASN1EncodableVector();
        v2.add(d_r);
        v2.add(d_s);
        DERSequence sign = new DERSequence(v2);

        String result = null;
        try {
            result = Util.byteToHex(sign.getEncoded());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        // SM2 encryption machine to soft encryption encoding format
        //return SM2SignHardKeyHead+hardSign.substring(0, hardSign.length()/2)+SM2SignHardKeyMid+hardSign.substring(hardSign.length()/2);
        return result;
    }

    //abstract calculation
    public static String generateSM3HASH(String src) {
        byte[] md = new byte[32];
        byte[] msg1 = src.getBytes();
        //System.out.println(Util.byteToHex(msg1));
        SM3Digest sm3 = new SM3Digest();
        sm3.update(msg1, 0, msg1.length);
        sm3.doFinal(md, 0);
        String s = new String(Hex.encode(md));
        return s.toUpperCase();
    }

    //Generate a symmetric key
    public static String generateSM4Key() {
        return UUID.randomUUID().toString().replace("-", "");
    }




}
