package com.bsnbase.sdk.util.algorithm;

import com.bsnbase.sdk.util.enums.AlgorithmTypeEnum;

public class AlgorithmTypeContext {

    private AlgorithmTypeEnum algorithmTypeEnum;

    public AlgorithmTypeContext(AlgorithmTypeEnum algorithmTypeEnum) {
        this.algorithmTypeEnum = algorithmTypeEnum;
    }

    public AlgorithmTypeHandle getAlgorithmTypeHandle() {
        AlgorithmTypeHandle algorithmTypeHandle = null;
        switch (algorithmTypeEnum) {
            case AppAlgorithmType_SM2:
                algorithmTypeHandle = new SM2Algorithm();
                break;
            case AppAlgorithmType_R1:
                algorithmTypeHandle = new R1Algorithm();
                break;
            case AppAlgorithmType_K1:
                algorithmTypeHandle = new K1Algorithm();
                break;
            default:
        }
        return algorithmTypeHandle;

    }
}
