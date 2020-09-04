package com.bsnbase.sdk.client.xuperChain.test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bsnbase.sdk.client.xuperChain.XuperClient;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.xuperChain.ReqGetBlockInformation;
import com.bsnbase.sdk.entity.req.xuperChain.ReqGetTransaction;
import com.bsnbase.sdk.entity.req.xuperChain.ReqKeyEscrow;
import com.bsnbase.sdk.entity.req.xuperChain.ReqUserRegister;
import com.bsnbase.sdk.entity.res.xuperChain.ResGetBlockInformation;
import com.bsnbase.sdk.entity.res.xuperChain.ResGetTransaction;
import com.bsnbase.sdk.entity.res.xuperChain.ResKeyEscrow;
import com.bsnbase.sdk.entity.res.xuperChain.ResUserRegister;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class XuperChainTest {

    /**
     *
     * 初始化config
     *
     * config中puk字段为网关公钥，在证书下载压缩包gatewayCert目录下，可为空
     * puk字段为空时系统使用默认网关公钥请求
     */

    public void initConfig() throws IOException {
        Config config = new Config();
        config.setAppCode("app0001202008121628000612841");
        config.setUserCode("ceshi1002");
        config.setApi("https://suzhounode.bsngate.com:17602");
        config.setPrk("cert/private_key.pem");
        config.setPuk("cert/public_key.pem");
        config.setMspDir("D:/test");
        config.initConfig(config);
    }


    /**
     * 注册用户
     */
    @Test
    public void testRegister() {
        try {
            initConfig();
            ReqUserRegister reqUserRegister=new ReqUserRegister();
            reqUserRegister.setUserId("123test");
            ResUserRegister resUserRegister=XuperClient.register(reqUserRegister);
            System.out.println(JSONObject.toJSONString(resUserRegister, SerializerFeature.PrettyFormat));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 密钥托管模式下调用智能合约接口
     */

    @Test
    public void testReqChainCode() {
        try {
            initConfig();
            ReqKeyEscrow reqKeyEscrow=new ReqKeyEscrow();
            reqKeyEscrow.setUserId("123test");
            reqKeyEscrow.setUserAddr("roe4JkYpujeaeDJL8LVbwsMBG8CUbtLtv");
            reqKeyEscrow.setContractName("cc_appxc_01");
            reqKeyEscrow.setFuncName("select_data");
            Map<String,Object> paramMap=new HashMap();
            paramMap.put("base_key","dev_001122");
            reqKeyEscrow.setFuncParam(JSONObject.toJSONString(paramMap));
            ResKeyEscrow resKeyEscrow= XuperClient.reqChainCode(reqKeyEscrow);
            System.out.println(JSONObject.toJSONString(resKeyEscrow, SerializerFeature.PrettyFormat));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 获取交易信息接口
     */
    @Test
    public void testGetTxInfoByTxHash() {
        try {
        initConfig();
        ReqGetTransaction reqGetTransaction=new ReqGetTransaction();
        reqGetTransaction.setTxHash("c30ece5db2774023b2a1ada3bd67d0c66e9576f4889cdcc9e2b260336dd01aa0");
        ResGetTransaction resGetTransaction= XuperClient.getTxInfoByTxHash(reqGetTransaction);
            System.out.println(JSONObject.toJSONString(resGetTransaction, SerializerFeature.PrettyFormat));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取块信息接口
     */
    @Test
    public void testGetBlockInfo() {
        try {
            initConfig();
            ReqGetBlockInformation reqGetBlockInformation=new ReqGetBlockInformation();
            reqGetBlockInformation.setBlockHash("123");
            reqGetBlockInformation.setBlockHeight(1);
            ResGetBlockInformation resGetBlockInformation= XuperClient.getBlockInfo(reqGetBlockInformation);
            System.out.println(JSONObject.toJSONString(resGetBlockInformation, SerializerFeature.PrettyFormat));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}