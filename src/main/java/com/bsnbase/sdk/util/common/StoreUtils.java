package com.bsnbase.sdk.util.common;

import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.util.algorithm.AlgorithmTypeContext;
import com.bsnbase.sdk.util.enums.AlgorithmTypeEnum;
import com.bsnbase.sdk.util.enums.ResultInfoEnum;
import com.bsnbase.sdk.util.exception.GlobalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class StoreUtils {
    private final static Logger logger = LoggerFactory.getLogger(StoreUtils.class);

    /**
     * csr generation
     *
     * @param name
     * @param appCode
     * @return
     */
    public static UserCertInfo generateCSR(String name, String appCode) {
        try {
            String DN = "CN=" + Common.getCNName(name, appCode) + ",OU=client";
            AlgorithmTypeEnum algorithmTypeEnum = AlgorithmTypeEnum.fromAlgorithmTypeEnum(Config.config.getAppInfo().getAlgorithmType());
            AlgorithmTypeContext algorithmTypeContext = new AlgorithmTypeContext(algorithmTypeEnum);
            UserCertInfo certInfo = algorithmTypeContext.getAlgorithmTypeHandle().getUserCertInfo(DN);
            if (Objects.isNull(certInfo)) {
                throw new GlobalException(ResultInfoEnum.ALGORITHM_TYPE_ERROR);
            }
            return certInfo;
        } catch (Exception e) {
            logger.error("Get CSR Exception", e);
            throw new GlobalException("Get CSR Exception");
        }
    }

}
