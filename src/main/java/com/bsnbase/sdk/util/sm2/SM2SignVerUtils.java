package com.bsnbase.sdk.util.sm2;

import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.util.common.Common;
import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithID;
import org.bouncycastle.crypto.signers.SM2Signer;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Enumeration;

/**
 * 国密算法的签名、验签
 */
public class SM2SignVerUtils {
    /**
     * 默认USERID
     */
    public static String USER_ID = "1234567812345678";

    /**
     * 私钥签名
     * 使用SM3进行对明文数据计算一个摘要值
     *
     * @param privatekey 私钥
     * @param sourceData 明文数据
     * @return 签名后的值
     * @throws Exception
     */
    public static SM2SignVO Sign2SM2(byte[] privatekey, byte[] sourceData) throws Exception {
        SM2SignVO sm2SignVO = new SM2SignVO();
        sm2SignVO.setSm2_type("sign");
        SM2Factory factory = SM2Factory.getInstance();
        BigInteger userD = new BigInteger(privatekey);
        //System.out.println("userD:"+userD.toString(16));
        sm2SignVO.setSm2_userd(userD.toString(16));

        ECPoint userKey = factory.ecc_point_g.multiply(userD);
        //System.out.println("椭圆曲线点X: "+ userKey.getXCoord().toBigInteger().toString(16));
        //System.out.println("椭圆曲线点Y: "+ userKey.getYCoord().toBigInteger().toString(16));

        SM3Digest sm3Digest = new SM3Digest();
        byte[] z = factory.sm2GetZ(USER_ID.getBytes(), userKey);
        //System.out.println("SM3摘要Z: " + Util.getHexString(z));
        //System.out.println("被加密数据的16进制: " + Util.getHexString(sourceData));
        sm2SignVO.setSm3_z(Util.getHexString(z));
        sm2SignVO.setSign_express(Util.getHexString(sourceData));

        sm3Digest.update(z, 0, z.length);
        sm3Digest.update(sourceData, 0, sourceData.length);
        byte[] md = new byte[32];
        sm3Digest.doFinal(md, 0);
        //System.out.println("SM3摘要值: " + Util.getHexString(md));
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
     * 验证签名
     *
     * @param publicKey  公钥信息
     * @param sourceData 密文信息
     * @param signData   签名信息
     * @return 验签的对象 包含了相关参数和验签结果
     */
    @SuppressWarnings("unchecked")
    public static SM2SignVO VerifySignSM2(byte[] publicKey, byte[] sourceData, byte[] signData) {
        try {
            byte[] formatedPubKey;
            SM2SignVO verifyVo = new SM2SignVO();
            verifyVo.setSm2_type("verify");
            if (publicKey.length == 64) {
                // 添加一字节标识，用于ECPoint解析
                formatedPubKey = new byte[65];
                formatedPubKey[0] = 0x04;
                System.arraycopy(publicKey, 0, formatedPubKey, 1, publicKey.length);
            } else {
                formatedPubKey = publicKey;
            }
            SM2Factory factory = SM2Factory.getInstance();
            ECPoint userKey = factory.ecc_curve.decodePoint(formatedPubKey);

            SM3Digest sm3Digest = new SM3Digest();
            byte[] z = factory.sm2GetZ(USER_ID.getBytes(), userKey);
            //System.out.println("SM3摘要Z: " + Util.getHexString(z));
            verifyVo.setSm3_z(Util.getHexString(z));
            sm3Digest.update(z, 0, z.length);
            sm3Digest.update(sourceData, 0, sourceData.length);
            byte[] md = new byte[32];
            sm3Digest.doFinal(md, 0);
            //System.out.println("SM3摘要值: " + Util.getHexString(md));
            verifyVo.setSm3_digest(Util.getHexString(md));
            ByteArrayInputStream bis = new ByteArrayInputStream(signData);
            ASN1InputStream dis = new ASN1InputStream(bis);
            SM2Result sm2Result = null;
            ASN1Primitive derObj = dis.readObject();
            Enumeration<ASN1Integer> e = ((ASN1Sequence) derObj).getObjects();
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
            return verifyVo;
        } catch (IllegalArgumentException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws Exception {

            Security.addProvider(new BouncyCastleProvider());
        String base64String =
                "MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEIlh1C0iWAdcKnM/yAaZZT/42NVzT" +
                        "Vyr31H9MDhHbPkp+/B3gsp5iZOr6OTAGO9jpN10/YMIrxt2IMg5auIEvMA==";
        byte[] publicBytes = new BASE64Decoder().decodeBuffer(base64String);
        X509EncodedKeySpec eks = new X509EncodedKeySpec(publicBytes);
        KeyFactory kf = KeyFactory.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);
        ECPublicKey bce2 = (BCECPublicKey) kf.generatePublic(eks);
        System.out.println(java.util.Base64.getEncoder().encodeToString(bce2.getEncoded()));
        System.out.println(Util.byteToHex(publicBytes));





        Security.addProvider(new BouncyCastleProvider());
        String prvString =
                "MIGTAgEAMBMGByqGSM49AgEGCCqBHM9VAYItBHkwdwIBAQQgRbYy4qWCVTNkWHsk" +
                        "rf7BzHAb45HIL5rtATHDIJlSkz6gCgYIKoEcz1UBgi2hRANCAATA7r9nim0dOMFs" +
                        "tz1EiBRZOg7Riv3Lr36vjn3l3iR5Ln5NGfbaYvbrtcId4bMEpAFWJErQErFj0CU6" +
                        "M8E8TCxW";
        byte[] prvBytes22 = new BASE64Decoder().decodeBuffer(prvString);
        PKCS8EncodedKeySpec eks2 = new PKCS8EncodedKeySpec(prvBytes22);
        KeyFactory kf22= KeyFactory.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);
        PrivateKey pvk= kf22.generatePrivate(eks2);
        BCECPrivateKey bpve = (BCECPrivateKey) pvk;
        System.out.println("私钥："+ bpve.getS()+":::16hex:::"+bpve.getS().toString(16));
        System.out.println(Util.byteToHex((prvBytes22)));
        bpve.getEncoded();
        System.out.println(Util.byteToHex((bpve.getEncoded())));
        System.out.println(pvk);




        String text = "aaaa";
        byte[] sourceData = text.getBytes();
//        String publicKey = "FA05C51AD1162133DFDF862ECA5E4A481B52FB37FF83E53D45FD18BBD6F32668A92C4692EEB305684E3B9D4ACE767F91D5D108234A9F07936020A92210BA9447";
//        String privatekey = "5EB4DF17021CC719B678D970C620690A11B29C8357D71FA4FF9BF7FB6D89767A";
        String publicKey = "3059301306072a8648ce3d020106082a811ccf5501822d034200042258750b489601d70a9ccff201a6594ffe36355cd3572af7d47f4c0e11db3e4a7efc1de0b29e6264eafa3930063bd8e9375d3f60c22bc6dd88320e5ab8812f30";
        String privatekey = "45b632e2a582553364587b24adfec1cc701be391c82f9aed0131c3209952933e";

        String prikS = new String(Util.byteToHex(publicKey.getBytes()));

        System.out.println(Base64.getDecoder().decode(publicKey.getBytes()).toString());

        SM2SignVO sign = SM2SignVerUtils.Sign2SM2(Util.hexStringToBytes(privatekey), sourceData);


        SM2SignVO verify = SM2SignVerUtils.VerifySignSM2(Util.hexStringToBytes(publicKey), sourceData, Util.hexStringToBytes(sign.getSm2_signForSoft()));
        System.out.println("签名得到的r值:" + sign.getSign_r() + "\n签名值 " + sign.getSm2_signForSoft());
        System.out.println("验签得到的R值:" + verify.getVerify_r());
        System.err.println("\n验签结果" + verify.isVerify());
        System.out.println(Util.hexStringToBytes("sm2_sign"));
    }

}
