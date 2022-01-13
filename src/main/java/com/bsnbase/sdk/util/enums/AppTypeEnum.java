package com.bsnbase.sdk.util.enums;


public enum AppTypeEnum {
    FABIRC("fabric");

    private String type;


    AppTypeEnum(String type) {
        this.type = type;
    }
    public String getValue() {
        return this.type;
    }


}
