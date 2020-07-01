package com.bsnbase.sdk.util.common;

import com.bsnbase.sdk.entity.config.Config;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;

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
     * string converted to byte arrays 
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
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
        String line = null;
        StringBuilder sb =new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line+"\r\n");
        }

        return sb.toString();
    }

}
