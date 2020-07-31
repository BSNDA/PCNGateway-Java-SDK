package com.bsnbase.sdk.client.xuperChain.test;

import com.alibaba.fastjson.JSONObject;
import com.bsnbase.sdk.client.xuperChain.service.NodeService;
import com.bsnbase.sdk.client.xuperChain.service.UserService;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.xuperChain.ReqGetBlockInformation;
import com.bsnbase.sdk.entity.req.xuperChain.ReqGetTransaction;
import com.bsnbase.sdk.entity.req.xuperChain.ReqKeyEscrow;
import com.bsnbase.sdk.entity.req.xuperChain.ReqUserRegister;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class XuperChainTest {


    //初始化config    Sm2
    public void initConfig() throws IOException {
        Config config = new Config();
        config.setAppCode("app0006202007171545196904721");
        config.setUserCode("USER0006202007171549487497611");
        config.setApi("http://192.168.1.43:17502");
        config.setCert("cert/bsn_gateway_https.crt");
        config.setPrk("cert/sm2/private_key.pem");
        config.setPuk("cert/sm2/public_key.pem");
        config.setMspDir("D:/test");
        config.initConfig(config);
    }

    @Test
    /**
     *"userId": "xuperchain",
     *"AccAddr": "2BwTWrZbscUpY8tfvLMxnaek51CgxGuH5G"
     * 注册用户
     */
    public void testRegister() {
        try {
            initConfig();
            ReqUserRegister reqUserRegister = new ReqUserRegister();
            reqUserRegister.setUserId("xuperchain");
            UserService.userRegister(reqUserRegister);
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
            reqKeyEscrow.setUserId("xuperchain");
            reqKeyEscrow.setUserAddr("2BwTWrZbscUpY8tfvLMxnaek51CgxGuH5G");
            reqKeyEscrow.setContractName("cc_appxc_01");
            reqKeyEscrow.setFuncName("insert_data");
            Map<String,Object> paramMap=new HashMap();
            paramMap.put("base_key","dev_001");
            paramMap.put("base_value","aaron1");
            reqKeyEscrow.setFuncParam(JSONObject.toJSONString(paramMap));
            NodeService.reqChainCode(reqKeyEscrow);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void testGetTxInfoByTxHash() {
        try {
        initConfig();
        ReqGetTransaction reqGetTransaction=new ReqGetTransaction();
        reqGetTransaction.setTxHash("134d69f1a4d98cc6f52590cb338a5bc4316cacf5a6ad5c858de224a0ef1288b6");
        NodeService.getTxInfoByTxHash(reqGetTransaction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testGetBlockInfo() {
        try {
            initConfig();
            ReqGetBlockInformation reqGetBlockInformation=new ReqGetBlockInformation();
            reqGetBlockInformation.setBlockHash("123");
            reqGetBlockInformation.setBlockHeight(1);
            NodeService.getBlockInfo(reqGetBlockInformation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}