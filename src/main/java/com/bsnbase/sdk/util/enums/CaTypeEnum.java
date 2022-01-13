package com.bsnbase.sdk.util.enums;


import com.bsnbase.sdk.util.exception.GlobalException;

public enum CaTypeEnum {
    /*
     hosted
     */
    hosted(1),
    /*
     NON_HOSTED
     */
    NON_HOSTED(2);
    /*
    k1
     */

    public final int nCode;


    CaTypeEnum(int nCode) {
        this.nCode = nCode;

    }

}
