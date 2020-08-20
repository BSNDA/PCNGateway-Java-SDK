package com.bsnbase.sdk.util.common;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Common {
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

    public static String getClassPathResource(String path) throws Exception {
        Resource keystoreResource = new ClassPathResource(path);
        File keystoreFile = keystoreResource.getFile();
        return keystoreFile.getAbsolutePath();
    }

    public static String readFile(String path) throws IOException {
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

}
