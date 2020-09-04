package com.bsnbase.sdk.util.keystore;

import com.bsnbase.sdk.entity.transactionHeader.TransactionUser;
import com.bsnbase.sdk.util.common.Common;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.openssl.jcajce.JcaPKCS8Generator;
import org.bouncycastle.util.io.pem.PemObject;

import java.io.*;
import java.security.PrivateKey;

public class KeyStore implements IKeyStore{
    String baseDir;

    public KeyStore(String path){

        this.baseDir = path;
        checkPath(getPrivateKeyDirPath());
        checkPath(getCertDirPath());

    }

    private void checkPath(String dirPath){
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private String getPrivateKeyDirPath(){
        return this.baseDir+"/pk/";
    }

    private String getCertDirPath(){
        return this.baseDir+"/cert/";
    }

    private String getPrivateKeyPath(String name,String appCode){
        return getPrivateKeyDirPath()+name+"@"+appCode+"_pk.pem";
    }

    private String getCertPath(String name,String appCode){
        return getCertDirPath()+name+"@"+appCode+"_cert.pem";
    }

    @Override
    public void storeUserPrivateKey(String name, String appCode, PrivateKey privateKey) throws IOException {

        JcaPKCS8Generator gen1 = new JcaPKCS8Generator(privateKey, null);
        PemObject obj1 = gen1.generate();
        StringWriter sw1 = new StringWriter();
        try (JcaPEMWriter pw = new JcaPEMWriter(sw1)) {
            pw.writeObject(obj1);
        }

        String path = getPrivateKeyPath(name,appCode);
        String content = sw1.toString();

        FileOutputStream fos = new FileOutputStream(path);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        osw.write(content);
        osw.flush();
    }

    @Override
    public void storeUserCert(String name, String appCode, String cert) throws IOException {
        FileOutputStream fos = new FileOutputStream(getCertPath(name,appCode));
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        osw.write(cert);
        osw.flush();
    }



    @Override
    public TransactionUser loadUser(String name, String appCode) throws IOException {
        TransactionUser user = new TransactionUser();

        String key = Common.readLocalFile(this.getPrivateKeyPath(name,appCode));
        user.setPrivateKey(key);

        String cert = Common.readLocalFile(this.getCertPath(name,appCode));
        user.setCert(cert.getBytes());

        return user;
    }
}


