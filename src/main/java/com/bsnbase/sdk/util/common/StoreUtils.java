package com.bsnbase.sdk.util.common;

import com.bsnbase.sdk.util.enums.ResultInfoEnum;
import com.bsnbase.sdk.util.exception.GlobalException;
import com.bsnbase.sdk.util.sign.SignUtil;
import org.apache.commons.net.util.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;

public class StoreUtils {
    private final static Logger logger = LoggerFactory.getLogger(StoreUtils.class);
    public static String privateCertPath="cert/private_Key.pem";

    /**
     * new csr
     * @param name
     * @param appCode
     * @param fileName
     * @return
     */
    public static UserCertInfo generateCSR(String name,String appCode, String fileName) {
        Security.addProvider(new BouncyCastleProvider());
        String sigAlg = "SHA256withECDSA";//"SHA256withECDSA";
        try {
            int algSize = 256;
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("ECDSA");
            kpg.initialize(algSize, new SecureRandom());
            KeyPair kp = kpg.generateKeyPair();
            PublicKey publicKey = kp.getPublic();
            PrivateKey privateKey = kp.getPrivate();

            System.out.println("publicKey========" + kp.getPublic());
            System.out.println("privateKey=======" + kp.getPrivate());
            sun.security.pkcs10.PKCS10 pkcs10 = new sun.security.pkcs10.PKCS10(publicKey);

            Signature signature = Signature.getInstance(sigAlg);
            signature.initSign(privateKey);
            String DN = "CN=" + Common.getCNName(name,appCode);
            @SuppressWarnings("restriction")
            sun.security.x509.X500Name x500Name = new sun.security.x509.X500Name(DN);
            pkcs10.encodeAndSign(x500Name, signature);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            pkcs10.print(ps);
            String strPEMCSR = baos.toString();

            UserCertInfo user = new UserCertInfo();
            user.setCSRPem(strPEMCSR);
            user.setKey(privateKey);

            return user;
        } catch (Exception e) {
            System.out.println("Message :"+e.getMessage());
            System.out.println("StackTrace :"+e.getStackTrace());
            throw new GlobalException("get CSR exceptions");
        }
    }

    public static byte[] signData(String algorithm, byte[] data, PrivateKey key) throws Exception {
        Signature signer = Signature.getInstance(algorithm);
        signer.initSign(key);
        signer.update(data);
        return (signer.sign());
    }

    /**
     * get sign
     * @param signStr
     * @return
     */
    public  static String signMac(String signStr) {
        try {
            Resource keystoreResource = new ClassPathResource(privateCertPath);
            File keystoreFile = keystoreResource.getFile();
            byte[] by= SignUtil.signByPKPath(keystoreFile.getAbsolutePath(), signStr.getBytes());
            String sign = java.util.Base64.getEncoder().encodeToString(by);
            return sign;
        } catch (Exception e) {
            logger.error("failed to sign：", e);
            e.printStackTrace();
            throw new GlobalException(ResultInfoEnum.SYSTEM_ERROR);
        }
    }

    /**
     *
     * @param str
     * @param privatePath
     * @return
     */
    public  static byte[] signMac(byte[] str,String privatePath) {
        try {
           // Resource keystoreResource = new ClassPathResource(privatePath);
            //File keystoreFile = readFile(privatePath);
            return SignUtil.signByPKPath(privatePath, str);
        } catch (Exception e) {
            logger.error("failed to sign：", e);
            e.printStackTrace();
            throw new GlobalException(ResultInfoEnum.SYSTEM_ERROR);
        }
    }


    /**
     * Sign
     *
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String writeSignVerify(PrivateKey privateKey) throws Exception {
        byte[] data = new byte[1000];
        for (int i = 0; i < data.length; i++)
            data[i] = 0xa;

        byte[] sign = signData("SHA256withECDSA", data, privateKey);
        String s = new String(sign);
        System.out.println(s);
        return s;
    }
    /**
     *
     *
     * @param privateKey
     * @param name
     * @throws Exception
     */
    public static void savePrivateKeyAsPEM(PrivateKey privateKey, String name) throws IOException {
        String content = Base64.encodeBase64String(privateKey.getEncoded());
        File file = new File(name);
        //byte[] sign = writeSignVerify(privateKey);
        if (file.isFile() && file.exists())
            throw new IOException("file already exists");
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
            randomAccessFile.write("-----BEGIN PRIVATE KEY-----\n".getBytes());
            int i = 0;
            for (; i < (content.length() - (content.length() % 64)); i += 64) {
                randomAccessFile.write(content.substring(i, i + 64).getBytes());
                randomAccessFile.write('\n');
            }
            randomAccessFile.write(content.substring(i, content.length()).getBytes());
            randomAccessFile.write('\n');
            randomAccessFile.write("-----END PRIVATE KEY-----".getBytes());
        }
    }


    /**
     *
     *
     * @param content
     * @param name
     * @throws Exception
     */
    public static void saveCSR(String content, String name) throws IOException {
        File file = new File(name);
        if (file.isFile() && file.exists())
            throw new IOException("file already exists");
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
            randomAccessFile.write(content.getBytes());
        }
    }

    /**
     *
     *
     * @param fileName
     * @return
     */
    public static String readFileContent(String fileName) {
        System.out.println(fileName);
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }

    /**
     *
     * @param fileName
     * @return
     */
    public static File readFile(String fileName) {
        System.out.println(fileName);
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return file;
    }

    /**
     *
     *
     * @param content
     * @return
     * @throws Exception
     */
    public static PrivateKey loadECPrivateKey(String content) throws Exception {
        String algorithm = "EC";
        String privateKeyPEM = content.replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "").replace("\n", "");
        byte[] decode = Base64.decodeBase64(privateKeyPEM);
        System.out.println(privateKeyPEM);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decode);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory.generatePrivate(spec);
    }

}
