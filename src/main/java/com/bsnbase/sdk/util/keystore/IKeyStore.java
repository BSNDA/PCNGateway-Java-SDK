package com.bsnbase.sdk.util.keystore;

import com.bsnbase.sdk.entity.transactionHeader.TransactionUser;

import java.io.IOException;
import java.security.PrivateKey;

public interface IKeyStore {

    void storeUserPrivateKey(String name, String appCode, PrivateKey privateKey) throws IOException;
    void storeUserCert(String name,String appCode,String cert) throws IOException;
    TransactionUser loadUser(String name, String appCode) throws IOException;
}
