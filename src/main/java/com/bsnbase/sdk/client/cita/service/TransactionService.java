package com.bsnbase.sdk.client.cita.service;


import com.alibaba.fastjson.JSON;
import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.cita.ReqKeyEscrow;
import com.bsnbase.sdk.entity.res.cita.ResKeyEscrow;
import com.bsnbase.sdk.util.common.HttpService;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransactionService {

    /**
     * 密钥托管模式交易处理
     *
     * @param kes
     * @return
     * @throws IOException
     */
    public static ResKeyEscrow reqChainCode(@NotNull ReqKeyEscrow kes) throws IOException {
        String api = Config.config.getApi() + "/api/cita/v1/node/reqChainCode";
        fixReqKeyEscrowParam(kes);
        BaseReqModel<ReqKeyEscrow> req = new BaseReqModel<ReqKeyEscrow>();
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        req.setBody(kes);

        HttpService<ReqKeyEscrow, ResKeyEscrow> httpService = new HttpService<ReqKeyEscrow, ResKeyEscrow>();
        BaseResModel<ResKeyEscrow> res = httpService.post(req, api,  ResKeyEscrow.class);
        return res.getBody();
    }

    /**
     * 修改交易请求参数中方法参数的值
     * key:bytes32，bytes32为byte转16进制，取64位，不足64位前面补0
     * value:bytes16进制
     * @param kes
     */
    private static void fixReqKeyEscrowParam(ReqKeyEscrow kes) {
        String param= kes.getFuncParam();
        List<Object> list= JSON.parseArray(param);
        String key=list.get(0).toString();
        String value=list.get(1).toString();

        byte[] bytes=key.getBytes();
        String keyHex = DatatypeConverter.printHexBinary(bytes);
        String fixKey = String.format("%0" + 64 + "d", Long.parseLong(keyHex));

        byte[] valueBytes=value.getBytes();
        String fixValue=DatatypeConverter.printHexBinary(valueBytes);
        List<String> fixParamList=new ArrayList<>();
        fixParamList.add(fixKey);
        fixParamList.add(fixValue);
        kes.setFuncParam(JSON.toJSONString(fixParamList));
    }

}
