package com.bsnbase.sdk.util.common;

import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.util.enums.AlgorithmTypeEnum;
import com.bsnbase.sdk.util.enums.ResultInfoEnum;
import com.bsnbase.sdk.util.exception.GlobalException;
import com.bsnbase.sdk.util.algorithm.AlgorithmTypeContext;
import com.bsnbase.sdk.util.sm2.Sm2Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Objects;

public class StoreUtils {
    private final static Logger logger = LoggerFactory.getLogger(StoreUtils.class);

    /**
     * csr生成
     * @param name
     * @param appCode
     * @param fileName
     * @return
     */
    public static UserCertInfo generateCSR(String name,String appCode, String fileName) {
        try {
            String DN = "CN=" + Common.getCNName(name, appCode)+",OU=client";
            AlgorithmTypeEnum algorithmTypeEnum = AlgorithmTypeEnum.fromAlgorithmTypeEnum(Config.config.getAppInfo().getAlgorithmType());
            AlgorithmTypeContext algorithmTypeContext = new AlgorithmTypeContext(algorithmTypeEnum);
            UserCertInfo certInfo=algorithmTypeContext.getAlgorithmTypeHandle().getUserCertInfo(DN);
            if(Objects.isNull(certInfo)){
                throw new GlobalException(ResultInfoEnum.ALGORITHM_TYPE_ERROR);
            }
            return certInfo;
        } catch (Exception e) {
            System.out.println("Message :"+e.getMessage());
            System.out.println("StackTrace :"+e.getStackTrace());
            throw new GlobalException("获取CSR异常");
        }
    }

}
