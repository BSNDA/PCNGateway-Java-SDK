package com.bsnbase.sdk.entity.req.fiscobcos;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
public class ReqTransData {
    String contractAbi;
    String contractAddress;
    String ContractName;
    String funcName;
    List<Object> funcParam;
    String userName;
}
