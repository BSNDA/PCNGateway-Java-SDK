package com.bsnbase.sdk.util.common;


import com.citahub.cita.protobuf.ConvertStrByte;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import static com.citahub.cita.utils.Numeric.cleanHexPrefix;

public class Common {

    private final static Logger logger = LoggerFactory.getLogger(Common.class);

    public static String getCNName(String name, String appCode) {
        return name + "@" + appCode;
    }

    /**
     * Convert string to byte array
     *
     * @param s
     */
    public static byte[][] StringBytesConvert(String[] s) {

        byte[][] bytess = new byte[s.length][0];

        for (int i = 0; i < s.length; i++) {
            bytess[i] = s[i].getBytes();
        }
        return bytess;
    }

    /**
     * Read local file
     *
     * @param path
     * @return
     */
    public static String readLocalFile(String path) {
        StringBuilder info = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path));
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

    /**
     * Read files in resource folder
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static String readFile(String path) throws IOException {
        StringBuffer result = new StringBuffer();
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        try {
            InputStream stream = Common.class.getResourceAsStream(path);
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
                String lineTxt = null;
                while ((lineTxt = br.readLine()) != null) {
                    result.append(lineTxt).append("\n");
                    ;
                }
                br.close();
            } catch (FileNotFoundException e) {
                logger.error("FileNotFoundException:" + e);
            } catch (IOException e) {
                logger.error("IOException:" + e);
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        logger.error("close br error:" + e);
                    }
                }
            }
            return result.toString();
        } catch (Exception e) {
            logger.error("Path:{},Exception:{}", path, e);
        }
        return null;
    }

    /**
     * Load private key in pem file
     *
     * @param content
     * @param algorithm
     * @return
     * @throws Exception
     */
    public static PrivateKey loadPrivateKey(String content, String algorithm) throws Exception {
        String privateKeyPEM = content.replaceAll("-----BEGIN PRIVATE KEY-----", "")
                .replaceAll("-----END PRIVATE KEY-----", "").replace("\r", "").replace("\n", "");
        byte[] asBytes = Base64.getDecoder().decode(privateKeyPEM.replace("\r\n", ""));
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(asBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory.generatePrivate(spec);
    }

    /**
     * Load public key in pem file
     *
     * @param content
     * @param algorithm
     * @return
     * @throws Exception
     */
    public static PublicKey loadPublicKey(String content, String algorithm) throws Exception {
        String strPublicKey = content.replaceAll("-----BEGIN PUBLIC KEY-----", "")
                .replaceAll("-----END PUBLIC KEY-----", "").replace("\r", "").replace("\n", "");
        byte[] asBytes = Base64.getDecoder().decode(strPublicKey.replace("\r\n", ""));
        X509EncodedKeySpec spec = new X509EncodedKeySpec(asBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory.generatePublic(spec);
    }

    /**
     * Creates a MessageDigest instance object with the specified algorithm name.
     *
     * @param algo
     * @return
     */

    public static MessageDigest newDigest(String algo) {
        try {
            return MessageDigest.getInstance(algo);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);  // Can't happen.
        }
    }

    public static byte[] sha256(byte[] msg) {
        final MessageDigest md = newDigest("SHA-256");
        return md.digest(msg);
    }

    public static byte[] doubleSha256(byte[] msg) {
        return doubleSha256(msg, 0, msg.length);
    }

    public static byte[] doubleSha256(byte[] msg, int offset, int length) {
        final MessageDigest md = newDigest("SHA-256");
        md.update(msg, offset, length);
        return md.digest(md.digest());
    }


    /**
     * Get byte32 value
     *
     * @param value
     * @return
     */
    public static String getByte32(byte[] value) {
        String keyHex = DatatypeConverter.printHexBinary(value);
        String fixKey = addZeroForNum(keyHex, 64);
        return fixKey;
    }

    /**
     * Get byte
     *
     * @param par
     * @return
     */
    public static byte[] getByte32String(String par) {
        String keyHex = DatatypeConverter.printHexBinary(par.getBytes());
        String fixKey = addZeroForNum(keyHex,64);
        byte[] value = ConvertStrByte.hexStringToBytes(cleanHexPrefix(fixKey));
        int length = value.length;
        int mod = length % 32;
        byte[] dest;
        if (mod != 0) {
            int padding = 32 - mod;
            dest = new byte[length + padding];
            System.arraycopy(value, 0, dest, 0, length);
        } else {
            dest = value;
        }
        return dest;
    }

    public static String addZeroForNum(String str, int strLength) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                sb.append("0").append(str);
                str = sb.toString();
                strLen = str.length();
            }
        }

        return str;
    }
}
