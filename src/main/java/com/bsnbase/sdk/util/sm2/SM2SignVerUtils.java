package com.bsnbase.sdk.util.sm2;

import org.bouncycastle.asn1.*;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.math.ec.ECPoint;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.util.Enumeration;

/**
 * signature of state encryption, verify the signature 
 */
public class SM2SignVerUtils {
    /**
     * Default USERID
     */
    public static String USER_ID = "1234567812345678";
    /**
     * sign the private key
     * Use SM3 to compute a digest value for the plaintext data
     * @param privatekey private key
     * @param sourceData plaintext data
     * @return the value after the signature
     * @throws Exception
     */
    public static SM2SignVO Sign2SM2(byte[] privatekey,byte[] sourceData) throws Exception{
        SM2SignVO sm2SignVO = new SM2SignVO();
        sm2SignVO.setSm2_type("sign");
        SM2Factory factory = SM2Factory.getInstance();
        BigInteger userD = new  BigInteger(privatekey);
        //System.out.println("userD:"+userD.toString(16));
        sm2SignVO.setSm2_userd(userD.toString(16));

        ECPoint userKey = factory.ecc_point_g.multiply(userD);
        //System.out.println("Elliptic curve pointX: "+ userKey.getXCoord().toBigInteger().toString(16));
        //System.out.println("Elliptic curve pointY: "+ userKey.getYCoord().toBigInteger().toString(16));

        SM3Digest sm3Digest = new SM3Digest();
        byte [] z = factory.sm2GetZ(USER_ID.getBytes(), userKey);
        //System.out.println("SM3 summary Z: " + Util.getHexString(z));
        //System.out.println("hexadecimal encrypted data: " + Util.getHexString(sourceData));
        sm2SignVO.setSm3_z(Util.getHexString(z));
        sm2SignVO.setSign_express(Util.getHexString(sourceData));

        sm3Digest.update(z, 0, z.length);
        sm3Digest.update(sourceData,0,sourceData.length);
        byte [] md = new byte[32];
        sm3Digest.doFinal(md, 0);
        //System.out.println("SM3 summary value: " + Util.getHexString(md));
        sm2SignVO.setSm3_digest(Util.getHexString(md));

        SM2Result sm2Result = new SM2Result();
        factory.sm2Sign(md, userD, userKey, sm2Result);
        //System.out.println("r: " + sm2Result.r.toString(16));
        //System.out.println("s: " + sm2Result.s.toString(16));
        sm2SignVO.setSign_r(sm2Result.r.toString(16));
        sm2SignVO.setSign_s(sm2Result.s.toString(16));

        ASN1Integer d_r = new ASN1Integer(sm2Result.r);
        ASN1Integer d_s = new ASN1Integer(sm2Result.s);
        ASN1EncodableVector v2 = new ASN1EncodableVector();
        v2.add(d_r);
        v2.add(d_s);
        DERSequence sign = new DERSequence(v2);
        String result = Util.byteToHex(sign.getEncoded());
        sm2SignVO.setSm2_sign(result);
        return sm2SignVO;
    }
    /**
     * verify the signature
     * @param publicKey  public key info 
     * @param sourceData cipher info
     * @param signData signature data
     * @return object of check-sign contains related parameters and check-sign results
     */
    @SuppressWarnings("unchecked")
    public static SM2SignVO VerifySignSM2(byte[] publicKey,byte[] sourceData,byte[] signData){
        try {
            byte[] formatedPubKey;
            SM2SignVO verifyVo = new SM2SignVO();
            verifyVo.setSm2_type("verify");
            if (publicKey.length == 64) {
                // add a byte identifier for ECPoint parsing 
                formatedPubKey = new byte[65];
                formatedPubKey[0] = 0x04;
                System.arraycopy(publicKey, 0, formatedPubKey, 1, publicKey.length);
            } else{
                formatedPubKey = publicKey;
            }
            SM2Factory factory = SM2Factory.getInstance();
            ECPoint userKey = factory.ecc_curve.decodePoint(formatedPubKey);

            SM3Digest sm3Digest = new SM3Digest();
            byte [] z = factory.sm2GetZ(USER_ID.getBytes(), userKey);
            //System.out.println("SM3 abstract Z: " + Util.getHexString(z));
            verifyVo.setSm3_z(Util.getHexString(z));
            sm3Digest.update(z,0,z.length);
            sm3Digest.update(sourceData,0,sourceData.length);
            byte [] md = new byte[32];
            sm3Digest.doFinal(md, 0);
            //System.out.println("SM3 abstract value: " + Util.getHexString(md));
            verifyVo.setSm3_digest(Util.getHexString(md));
            ByteArrayInputStream bis = new ByteArrayInputStream(signData);
            ASN1InputStream dis = new ASN1InputStream(bis);
            SM2Result sm2Result = null;
            ASN1Primitive derObj = dis.readObject();
            Enumeration<ASN1Integer> e = ((ASN1Sequence)derObj).getObjects();
            BigInteger r = ((ASN1Integer) e.nextElement()).getValue();
            BigInteger s = ((ASN1Integer) e.nextElement()).getValue();
            sm2Result = new SM2Result();
            sm2Result.r = r;
            sm2Result.s = s;
            //System.out.println("vr: " + sm2Result.r.toString(16));
            //System.out.println("vs: " + sm2Result.s.toString(16));
            verifyVo.setVerify_r(sm2Result.r.toString(16));
            verifyVo.setVerify_s(sm2Result.s.toString(16));
            factory.sm2Verify(md, userKey, sm2Result.r, sm2Result.s, sm2Result);
            boolean verifyFlag = sm2Result.r.equals(sm2Result.R);
            verifyVo.setVerify(verifyFlag);
            return  verifyVo;
        } catch (IllegalArgumentException e) {
            return null;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) throws Exception {
        String text = "This is a plaintext";
        byte [] sourceData = text.getBytes();
        //String publicKey ="FA05C51AD1162133DFDF862ECA5E4A481B52FB37FF83E53D45FD18BBD6F32668A92C4692EEB305684E3B9D4ACE767F91D5D108234A9F07936020A92210BA9447";
        //String privatekey = "5EB4DF17021CC719B678D970C620690A11B29C8357D71FA4FF9BF7FB6D89767A";
        String publicKey ="04BB34D657EE7E8490E66EF577E6B3CEA28B739511E787FB4F71B7F38F241D87F18A5A93DF74E90FF94F4EB907F271A36B295B851F971DA5418F4915E2C1A23D6E";
        String privatekey = "0B1CE43098BC21B8E82B5C065EDB534CB86532B1900A49D49F3C53762D2997FA";
        SM2SignVO sign = SM2SignVerUtils.Sign2SM2(Util.hexStringToBytes(privatekey), sourceData);
        SM2SignVO verify = SM2SignVerUtils.VerifySignSM2(Util.hexStringToBytes(publicKey), sourceData, Util.hexStringToBytes(sign.getSm2_signForSoft()));
        System.out.println("The r value of the signature:"+sign.getSign_r()+"\n signature value "+sign.getSm2_signForSoft());
        System.out.println("R value of verified signature:"+verify.getVerify_r());
        System.err.println("\n result of verified signature" +verify.isVerify());
    }
}
