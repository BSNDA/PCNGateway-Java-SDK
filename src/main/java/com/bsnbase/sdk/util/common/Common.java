package com.bsnbase.sdk.util.common;


import com.bsnbase.sdk.util.exception.GlobalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLDecoder;

public class Common {

    private final static Logger logger = LoggerFactory.getLogger(Common.class);

    public static String getCNName(String name,String appCode){
        return name+"@"+appCode;
    }

    public static String getUserPrivateKeyPath(String fileName,String name,String appCode){
        String cn = getCNName(name,appCode);

        return fileName +"/"+cn+"PrivateKey.pem";
    }


    public static String getUserCertPath(String fileName,String name,String appCode){
        String cn = getCNName(name,appCode);

        return fileName +"/"+cn+"Cert.pem";
    }


    /**
     * string与 byte数组转换
     *
     * @param s
     */
    public static byte[][] StringBytesConvert(String[] s) {

        byte[][] bytess = new byte[s.length][0];

        for (int i=0;i<s.length;i++){
            bytess[i] = s[i].getBytes();
        }
        return bytess;
    }

    public static String readLocalFile(String path) throws IOException {
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
                    result.append(lineTxt).append("\n");;
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
            logger.error("Path:{},Exception:{}",path, e);
        }
        return null;
    }

}
