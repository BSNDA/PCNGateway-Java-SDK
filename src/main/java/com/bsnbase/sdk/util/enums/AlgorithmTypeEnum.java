package com.bsnbase.sdk.util.enums;


import com.bsnbase.sdk.util.exception.GlobalException;

public enum AlgorithmTypeEnum {
    /*
     sm2
     */
    AppAlgorithmType_SM2(1),
    /*
     r1
     */
    AppAlgorithmType_R1(2),
    /*
    k1
     */
    AppAlgorithmType_K1(3);


    public final int nCode;


    AlgorithmTypeEnum(int nCode) {

        this.nCode = nCode;

    }

    public static AlgorithmTypeEnum fromAlgorithmTypeEnum(int algorithmType) {
        for (AlgorithmTypeEnum algorithmTypeEnum : AlgorithmTypeEnum.values()) {
            if (algorithmTypeEnum.getValue() == algorithmType) {
                return algorithmTypeEnum;
            }
        }
        throw new GlobalException(ResultInfoEnum.ALGORITHM_TYPE_ERROR);
    }

    public int getValue() {
        return this.nCode;
    }
}
