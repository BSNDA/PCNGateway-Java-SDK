package com.bsnbase.sdk.util.algorithm;

import com.bsnbase.sdk.util.common.UserCertInfo;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.DERSequenceGenerator;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.hyperledger.fabric.sdk.helper.Config;
import org.hyperledger.fabric.sdk.security.CryptoPrimitives;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.Certificate;
import java.security.interfaces.ECPrivateKey;
import java.util.Base64;

public class R1Algorithm implements AlgorithmTypeHandle {
    /**
     * @param stringPrivateKey
     * @param signString
     * @return
     */
    @Override
    public String sign(String stringPrivateKey, String signString) throws Exception {
        CryptoPrimitives cryptoPrimitives = new CryptoPrimitives();
        cryptoPrimitives.init();
        PrivateKey privateKey = cryptoPrimitives.bytesToPrivateKey(stringPrivateKey.getBytes());
        ECPrivateKey eck = (ECPrivateKey) privateKey;


        ECNamedCurveParameterSpec spec = ECNamedCurveTable.getParameterSpec("secp256r1");
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

    /**
     * @param pemCertificateString
     * @param signatureString
     * @param unsignedString
     * @return
     */
    @Override
    public boolean verify(String pemCertificateString, String signatureString, String unsignedString) throws Exception {

        CryptoPrimitives cryptoPrimitives = new CryptoPrimitives();
        cryptoPrimitives.init();
        Certificate publicKey = cryptoPrimitives.bytesToCertificate(pemCertificateString.getBytes());
        // 执行签名
        Signature signature = Signature.getInstance(Config.getConfig().getSignatureAlgorithm());
        // 验证签名
        signature.initVerify(publicKey);
        signature.update(unsignedString.getBytes());

        byte[] signByte = Base64.getDecoder().decode(signatureString);
        return signature.verify(signByte);
    }

    /**
     * 获取证书CSR
     *
     * @param DN
     * @return
     */
    @Override
    public UserCertInfo getUserCertInfo(String DN) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        int algSize = 256;
        String sigAlg = "SHA256withECDSA";
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("ECDSA");
        kpg.initialize(algSize, new SecureRandom());
        KeyPair kp = kpg.generateKeyPair();
        PrivateKey privateKey = kp.getPrivate();
        Signature signature = Signature.getInstance(sigAlg);
        signature.initSign(privateKey);

        X500Name x500Name=new X500Name(DN);
        SubjectPublicKeyInfo subjectPublicKeyInfo= SubjectPublicKeyInfo.getInstance(kp.getPublic().getEncoded());

        PKCS10CertificationRequestBuilder builder = new PKCS10CertificationRequestBuilder(x500Name, subjectPublicKeyInfo);
        JcaContentSignerBuilder jcaContentSignerBuilder = new JcaContentSignerBuilder(sigAlg);
        Provider BC = new BouncyCastleProvider();
        jcaContentSignerBuilder.setProvider(BC);

        ContentSigner contentSigner = jcaContentSignerBuilder.build(kp.getPrivate());
        PKCS10CertificationRequest csr = builder.build(contentSigner);

        byte[] der = csr.getEncoded();
        String strPEMCSR = "-----BEGIN CERTIFICATE REQUEST-----\n";
        strPEMCSR += new String(org.bouncycastle.util.encoders.Base64.encode(der));
        strPEMCSR += "\n-----END CERTIFICATE REQUEST-----\n";

        UserCertInfo user = new UserCertInfo();
        user.setCSRPem(strPEMCSR);
        user.setKey(privateKey);
        return user;
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