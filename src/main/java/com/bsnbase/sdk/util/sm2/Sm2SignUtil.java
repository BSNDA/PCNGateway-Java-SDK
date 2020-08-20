package com.bsnbase.sdk.util.sm2;

import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithID;
import org.bouncycastle.crypto.signers.SM2Signer;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

/**
 * @author kuan
 * Created on 2020/4/13.
 * @description 国密签名与验签
 */
public class Sm2SignUtil {

    private static final byte[] SM2_ID = {
            (byte) 0x31, (byte) 0x32, (byte) 0x33, (byte) 0x34, (byte) 0x35, (byte) 0x36, (byte) 0x37, (byte) 0x38,
            (byte) 0x31, (byte) 0x32, (byte) 0x33, (byte) 0x34, (byte) 0x35, (byte) 0x36, (byte) 0x37, (byte) 0x38
    };

    private static final String BC = BouncyCastleProvider.PROVIDER_NAME;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * @return java.lang.String
     * @Description 通过SM2私钥进行签名
     * @Param [privateKeyPath, signDigest]
     **/
    public static String signSM2ByPKStr(String privateKey, byte[] signDigest) throws Exception {
        byte[] signBytes = SM2Sign(signDigest, getPrivateKey(privateKey.getBytes()));
        return Base64.getEncoder().encodeToString(signBytes);
    }

    /**
     * @return boolean
     * @Description 通过SM2公钥证书进行验签
     * @Param [pubKeyPath, mac, encryptionValueBytes]
     **/
    public static boolean verifySM2ByCertPath(String certPath, String mac, String encryptionValue) throws Exception {
        return SM2VerifySign(encryptionValue.getBytes(), Base64.getDecoder().decode(mac), getPublicKeyFromPem(certPath));
    }

    /**
     * @return boolean
     * @Description 通过SM2公钥进行验签
     * @Param [pubKeyPath, mac, encryptionValueBytes]
     **/
    public static boolean verifySM2ByPubKeyStr(String publicKey, String mac, byte[] encryptionValueBytes) throws Exception {
        return SM2VerifySign(encryptionValueBytes, Base64.getDecoder().decode(mac), getPublicKey(publicKey.getBytes()));
    }

    /**
     * @return byte[]
     * @Description SM2签名
     * @Param [inData, privateKey]
     **/
    public static byte[] SM2Sign(byte[] inData, PrivateKey privateKey) throws Exception {
        AsymmetricKeyParameter ecParam = PrivateKeyFactory.createKey(privateKey.getEncoded());
        SM2Signer sm2Signer = new SM2Signer();
        sm2Signer.init(true, new ParametersWithID(ecParam, SM2_ID));
        sm2Signer.update(inData, 0, inData.length);
        return sm2Signer.generateSignature();
    }

    /**
     * @return boolean
     * @Description SM2验证签名
     * @Param [inData, signature, publicKey]
     **/
    private static boolean SM2VerifySign(byte[] inData, byte[] signature, PublicKey publicKey) throws Exception {
        AsymmetricKeyParameter ecParam = ECUtil.generatePublicKeyParameter(publicKey);
        SM2Signer sm2Signer = new SM2Signer();
        sm2Signer.init(false, new ParametersWithID(ecParam, SM2_ID));
        sm2Signer.update(inData, 0, inData.length);
        return sm2Signer.verifySignature(signature);
    }

    /**
     * @return byte[]
     * @Description 计算SM3 摘要hash
     * @Param [message]
     **/
    public static byte[] SM3Digest(byte[] srcBytes) {
        SM3Digest sm3 = new SM3Digest();
        sm3.update(srcBytes, 0, srcBytes.length);
        byte[] out = new byte[32];
        sm3.doFinal(out, 0);
        return out;
    }

    /**
     * @return boolean
     * @Description SM3摘要验证
     * @Param [srcStr, sm3Base64String]
     **/
    private static boolean SM3Verify(String srcStr, String sm3Base64String) throws UnsupportedEncodingException {
        boolean flag = false;
        byte[] sm3Hash = Base64.getDecoder().decode(sm3Base64String);
        byte[] newHash = SM3Digest(srcStr.getBytes(StandardCharsets.UTF_8));
        if (Arrays.equals(newHash, sm3Hash)) {
            flag = true;
        }
        return flag;
    }

    /**
     * @return java.security.PrivateKey
     * @Description 通过key获取 PrivateKey
     * @Param [pemKey]
     **/
    public static PrivateKey getPrivateKey(byte[] pemKey) throws CryptoException {
        PrivateKey privateKey;
        try {
            PemReader pr = new PemReader(new StringReader(new String(pemKey)));
            PEMParser pem = new PEMParser(new StringReader(new String(pemKey)));
            PemObject po = pr.readPemObject();
            Object obj = pem.readObject();
            JcaPEMKeyConverter jcaPEMKeyConverter = new JcaPEMKeyConverter();
            if ("PRIVATE KEY".equals(po.getType())) {
                privateKey = jcaPEMKeyConverter.getPrivateKey((PrivateKeyInfo) obj);
            } else {
                privateKey = jcaPEMKeyConverter.getPrivateKey(((PEMKeyPair) obj).getPrivateKeyInfo());
            }
        } catch (Exception e) {
            throw new CryptoException("转换私钥失败：%s", e);
        }
        return privateKey;
    }

    /**
     * @return java.security.PublicKey
     * @Description 通过key获取PublicKey
     * @Param [pemKey]
     **/
    public static PublicKey getPublicKey(byte[] pemKey) throws CryptoException, NoSuchAlgorithmException, InvalidKeySpecException {
        PublicKey publicKey;
        try {
            PemReader pr = new PemReader(new StringReader(new String(pemKey)));
            PEMParser pem = new PEMParser(new StringReader(new String(pemKey)));
            PemObject po = pr.readPemObject();
            Object obj = pem.readObject();
            JcaPEMKeyConverter jcaPEMKeyConverter = new JcaPEMKeyConverter();
            if ("PUBLIC KEY".equals(po.getType())) {
                publicKey = jcaPEMKeyConverter.getPublicKey((SubjectPublicKeyInfo) obj);
            } else {
                publicKey = jcaPEMKeyConverter.getPublicKey(((PEMKeyPair) obj).getPublicKeyInfo());
            }
        } catch (Exception e) {
            throw new CryptoException("转换公钥失败：%s", e);
        }
        return publicKey;
    }

    /**
     * @return java.security.PublicKey
     * @Description 通过pem证书获取公钥
     * @Param [pemPath]
     **/
    private static PublicKey getPublicKeyFromPem(String pemPath) throws IOException, CertificateException, NoSuchProviderException {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509", BC);
        FileInputStream fileInputStream = new FileInputStream(pemPath);
        X509Certificate x509Certificate = (X509Certificate) certificateFactory.generateCertificate(fileInputStream);
        fileInputStream.close();
        return x509Certificate.getPublicKey();
    }

    /**
     * @param filePath 文件路径
     * @return java.lang.String 文件信息
     * @description：读文件
     */
    private static String readFile(String filePath) {
        StringBuilder info = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String tempString;
            while ((tempString = br.readLine()) != null) {
                info.append(tempString).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return info.toString();
        }
    }


    public static void main(String[] args) throws Exception {

        //================SM2签名校验========================

//        String privateKeyPath = "D:\\private_Key.pem";
//        String certPath = "D:\\public_Key.pem";
//
//        String unSignString = "USER0001202004151958010871292app0001202004172002586111406ccDown14";
//        byte[] sm3DigestBytes = SM3Digest(unSignString.getBytes(StandardCharsets.UTF_8));
//        String signResult = signSM2ByPKStr(privateKeyPath, sm3DigestBytes);
//        System.out.println(signResult);
//
////        signResult = "MEQCIHic0uXEVcNPwCwLnQ6Dwxnyo4PLH+5QTRUBm+qxVuATAiBgvrh43lwrgdyI3/HC0xCkzsODLNn7zdmcLX9k/G405w==";
//
//        boolean isVerified = verifySM2ByPubKeyStr(certPath, signResult, unSignString.getBytes());
//        System.out.println(isVerified);

        //======================sm3校验========================

//        System.out.println("-1用户已存在 UTF_8>>>"+Base64.getEncoder().encodeToString(unSignString.getBytes(StandardCharsets.UTF_8)));
//        System.out.println("-1用户已存在 gbk>>>"+Base64.getEncoder().encodeToString(unSignString.getBytes("gbk")));
//        System.out.println("-1用户已存在 gb2312>>>"+Base64.getEncoder().encodeToString(unSignString.getBytes("gb2312")));

//        byte[] sm3DigestBytes = SM3Digest(unSignString.getBytes(StandardCharsets.UTF_8));
//        System.out.println("sm3DigestBytes>>>>>>>"+Base64.getEncoder().encodeToString(sm3DigestBytes));

//        boolean sm3Verify = SM3Verify(unSignString, Base64.getEncoder().encodeToString(sm3DigestBytes));
//        System.out.println("sm3Verify>>>"+sm3Verify);

//        PublicKey publicKey=getPublicKeyFromPem("D:\\test\\cert\\test23@app0001202007271538152051987_cert.pem");
//        System.out.println(publicKey.getEncoded());


//        Sm2Util.createKeyPair();
//        KeyPair kp = Sm2Util.keyPair;
//
//        JcaPKCS8Generator gen1 = new JcaPKCS8Generator(kp.getPrivate(), null);
//        PemObject obj1 = gen1.generate();
//        StringWriter sw1 = new StringWriter();
//        try (JcaPEMWriter pw = new JcaPEMWriter(sw1)) {
//            pw.writeObject(obj1);
//        }
//        String content = sw1.toString();
//        System.out.println(content);
//
//        byte[] sm3DigestBytes = Sm2SignUtil.SM3Digest("123".getBytes(StandardCharsets.UTF_8));
//        String a= Sm2SignUtil.signSM2ByPKStr(content, sm3DigestBytes);
//
//
//        byte[] sm3DigestBytesVer = Sm2SignUtil.SM3Digest("123".getBytes(StandardCharsets.UTF_8));
//
//       boolean verify= SM2VerifySign(sm3DigestBytesVer, Base64.getDecoder().decode(a), kp.getPublic());
//        System.out.println(verify);

        String privateKey="-----BEGIN PRIVATE KEY-----\n" +
                "MIGTAgEAMBMGByqGSM49AgEGCCqBHM9VAYItBHkwdwIBAQQg/7RMFXO8U9LyrTJW\n" +
                "EZ3gtdUI5A5K+yPAEb3iiPe7bKegCgYIKoEcz1UBgi2hRANCAASvJdHvty4qiZ2r\n" +
                "xcDYrMrgskyr6vthAy/Tgz/3S6SR/9ERuYVLh+Hzb6ptpIWHo0ek5j05ERh5vSzC\n" +
                "PIXILYkE\n" +
                "-----END PRIVATE KEY-----";
        byte[] sm3DigestBytes = Sm2SignUtil.SM3Digest("123".getBytes(StandardCharsets.UTF_8));
        String a= Sm2SignUtil.signSM2ByPKStr(privateKey, sm3DigestBytes);


        PublicKey publicKey=getPublicKeyFromPem("D:\\test\\cert\\test23@app0001202007271538152051987_cert.pem");

       byte[] sm3DigestBytesVer = Sm2SignUtil.SM3Digest("123".getBytes(StandardCharsets.UTF_8));

      boolean verify= SM2VerifySign(sm3DigestBytesVer, Base64.getDecoder().decode(a),publicKey);
        System.out.println(verify);

//        boolean result= SM2VerifySign("123".getBytes(), Base64.getDecoder().decode(a),publicKey);
//        System.out.println(result);

    }
}
