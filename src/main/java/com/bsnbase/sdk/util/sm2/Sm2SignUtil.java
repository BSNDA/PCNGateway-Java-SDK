package com.bsnbase.sdk.util.sm2;

import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.util.common.Common;
import lombok.var;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.DERSequenceGenerator;
import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.gm.GMObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTNamedCurves;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.digests.ShortenedDigest;
import org.bouncycastle.crypto.generators.KDF1BytesGenerator;
import org.bouncycastle.crypto.params.*;
import org.bouncycastle.crypto.signers.*;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveSpec;
import org.bouncycastle.math.ec.ECMultiplier;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.hyperledger.fabric.sdk.security.CryptoPrimitives;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

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
     * @return java.lang.String
     * @Description 通过SM2私钥进行签名
     * @Param [privateKeyPath, signDigest]
     **/
    public static  byte[] signSM2Byte(String privateKey, byte[] signDigest) throws Exception {
        byte[] sm3DigestBytes = Sm2SignUtil.SM3Digest(signDigest);
        byte[] signBytes = SM2Sign(sm3DigestBytes, getPrivateKey(privateKey.getBytes()));
        return signBytes;
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
    public static boolean SM2VerifySign(byte[] inData, byte[] signature, PublicKey publicKey) throws Exception {
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
    /* access modifiers changed from: protected */
    public static ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }

    public static byte[] xuperSignature(String privateKeyStr,byte[] message) throws Exception {
        PrivateKey privateKey = Sm2SignUtil.getPrivateKey(privateKeyStr.getBytes());
        BCECPrivateKey ecPrivateKey = (BCECPrivateKey) privateKey;
        X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
        ECDomainParameters ec = new ECDomainParameters(sm2ECParameters.getCurve(),
                sm2ECParameters.getG(), sm2ECParameters.getN());
        BigInteger n = ec.getN(); //阶n
        ECPoint G = ec.getG();  //基点G
        BigInteger r, s;
        // 获取私钥d
        BigInteger d = ecPrivateKey.getD();

        ECMultiplier basePointMultiplier = createBasePointMultiplier();
        DSAKCalculator kCalculator = new RandomDSAKCalculator();
        // 初始化随机数生成器
//        if (kCalculator.isDeterministic()) {
//            kCalculator.init(n, d, message);
//        } else {
        kCalculator.init(n, new SecureRandom());
//        }

        do { // 计算s
            BigInteger k;
            BigInteger e;
            BigInteger tmp;
            BigInteger tmp2;
            do { // 计算r，参照GM/T 0003.2-2012 6.1
                k = kCalculator.nextK();

                ECPoint p = basePointMultiplier.multiply(G, k).normalize();

                e = org.bouncycastle.util.BigIntegers.fromUnsignedByteArray(message);
                // r = (e + x) mod n
                r = p.getAffineXCoord().toBigInteger().add(e).mod(n);

            } while (r.equals(ZERO) || r.add(k).equals(n));

            // tmp = (1+d).inverse
            tmp = d.add(ONE).modInverse(n);
            // tmp2 = k - r*d
            tmp2 = k.subtract(r.multiply(d));
            s = tmp.multiply(tmp2).mod(n);

        } while (s.equals(ZERO));

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DERSequenceGenerator seq = new DERSequenceGenerator(bos);
        seq.addObject(new ASN1Integer(r));
        seq.addObject(new ASN1Integer(s));
        seq.close();
        return bos.toByteArray();

    }
}
