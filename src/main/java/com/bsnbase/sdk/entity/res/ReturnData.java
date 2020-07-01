package com.bsnbase.sdk.entity.res;

import lombok.Data;

import java.util.Map;

@Data
public class ReturnData {
    Map header;
    String mac;
    Map body;

}
