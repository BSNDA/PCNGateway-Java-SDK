package com.bsnbase.sdk.client.xuperChain.test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bsnbase.sdk.client.xuperChain.XuperClient;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.xuperChain.*;
import com.bsnbase.sdk.entity.res.xuperChain.*;
import com.bsnbase.sdk.util.common.Common;
import org.junit.Test;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XuperChainTest {

    /**
     * 初始化config
     *
     * 应用私钥、应用公钥为pem中具体内容，
     * com.bsnbase.sdk.util.common.Common提供根据路径获取内容方法，
     * Common.readLocalFile参数为pem存储目录的绝对路径，
     * 例如:Common.readFile("cert/private_key.pem")
     * Common.readFile参数为pem存储目录的相对路径，
     * 例如:Common.readLocalFile("D:/test/private_key.pem")
     * 或者直接填入pem内容。
     *
     * puk字段和prk字段为用户公钥和私钥不能为空
     */

    public void initConfig() throws IOException {
        Config config = new Config();
        config.setAppCode("app0001202010221038364886804");
        config.setUserCode("USER0001202010201539390086090");
        config.setApi("http://192.168.1.43:17502");
        config.setPrk(Common.readFile("cert/private_key.pem"));
        config.setPuk(Common.readFile("cert/public_key.pem"));
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
            reqUserRegister.setUserId  ("123test");
            ResUserRegister resUserRegister=XuperClient.register(reqUserRegister);
            System.out.println(JSONObject.toJSONString(resUserRegister, SerializerFeature.PrettyFormat));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 密钥托管模式下调用智能合约接口
     * 默认合约方法参数示例：
     * 增加：
     * FuncName：insert_data
     * FuncParam:{"base_key":"dev_0001","base_value":"test"}
     *删除：
     * FuncName：remove_data
     * FuncParam:{"base_key":"dev_0001"}
     *更新：
     * FuncName：update_data
     * FuncParam:{"base_key":"dev_0001","base_value":"test2"}
     *查询：
     * FuncName：select_data
     * FuncParam:{"base_key":"dev_0001"}
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
            paramMap.put("base_value","dev_001122");
            reqKeyEscrow.setFuncParam(JSONObject.toJSONString(paramMap));
            ResKeyEscrow resKeyEscrow= XuperClient.reqChainCode(reqKeyEscrow);
            System.out.println(JSONObject.toJSONString(resKeyEscrow, SerializerFeature.PrettyFormat));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     *  上传公钥模式获取账户地址
     */
    @Test
    public void getTransInitiator() {
        try {
            initConfig();
            String initiator=XuperClient.getUserAddress();
            System.out.println("address:"+initiator);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 公钥上传模式下调用智能合约Query接口
     * initiator:代表xupchain的账户地址；可调用方法getTransInitiator生成address信息
     */
    @Test
    public void transQuery() {
        try {
            initConfig();
            ReqTransData reqTransData=new ReqTransData();
            reqTransData.setContractName("cc_appxc_01");
            reqTransData.setMethodName("get");
            reqTransData.setInitiator("rJHdYSyCWcnhZ9bjhfbhoN5mfhMVCWGhX");
            Map<String,String> args = new HashMap<>();
            args.put("key", "dev_002");
            reqTransData.setArgs(args);
            ResKeyEscrowNo resKeyEscrowNo= XuperClient.reqChainCode(reqTransData);
            System.out.println(JSONObject.toJSONString(resKeyEscrowNo, SerializerFeature.PrettyFormat));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 公钥上传模式下调用智能合约invoke接口
     * initiator:代表xupchain的账户地址；可调用方法getTransInitiator生成address信息
     */
    @Test
    public void transInvoke() {
        try {
            initConfig();
            ReqTransData reqTransData=new ReqTransData();
            reqTransData.setContractName("cc_appxc_01");
            reqTransData.setMethodName("increase");
            reqTransData.setInitiator("rJHdYSyCWcnhZ9bjhfbhoN5mfhMVCWGhX");
            Map<String, String> args = new HashMap<>();
            args.put("key", "123123123");
            reqTransData.setArgs(args);
            ResKeyEscrowNo resKeyEscrowNo= XuperClient.reqInvokeChainCode(reqTransData);
            System.out.println(JSONObject.toJSONString(resKeyEscrowNo, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
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
        reqGetTransaction.setTxHash("ffad46c6b89f5a770fe7fcd236a553c5c0842d25ca046286cbbc0b0196c1ff9e");
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
//            reqGetBlockInformation.setBlockHash("0c38bd52a7a4cc15117bbadba8602c39154d20e6ac306d120942033a98df2954");
            reqGetBlockInformation.setBlockHeight(135374);
            ResGetBlockInformation resGetBlockInformation= XuperClient.getBlockInfo(reqGetBlockInformation);
            System.out.println(JSONObject.toJSONString(resGetBlockInformation, SerializerFeature.PrettyFormat));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 链码事件注册接口
     */
    @Test
    public void testChainCodeRegister() {
        try {
            initConfig();
            ReqChainCodeRegister reqChainCodeRegister=new ReqChainCodeRegister();
            reqChainCodeRegister.setContractName("cc_appxc_01");
            reqChainCodeRegister.setEventKey("123");
            reqChainCodeRegister.setNotifyUrl("123");
            reqChainCodeRegister.setAttachArgs("123");
            ResChainCodeRegister resChainCodeRegister= XuperClient.reqChainCodeRegister(reqChainCodeRegister);
            System.out.println(JSONObject.toJSONString(resChainCodeRegister, SerializerFeature.PrettyFormat));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
    * 链码事件查询接口
    */
    @Test
    public void testChainCodeQuery() {
        try {
            initConfig();
            List<ResChainCodeQuery> resChainCodeQuery= XuperClient.reqChainCodeQuery();
            System.out.println(JSONObject.toJSONString(resChainCodeQuery, SerializerFeature.PrettyFormat));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 链码事件注销接口
     */
    @Test
    public void testChainCodeCancel() {
        try {
            initConfig();
            ReqChainCodeCancel reqChainCodeCancel=new ReqChainCodeCancel();
            reqChainCodeCancel.setEventId("");
            ResChainCodeCancel resChainCodeCancel= XuperClient.reqChainCodeCancel(reqChainCodeCancel);
            System.out.println(JSONObject.toJSONString(resChainCodeCancel, SerializerFeature.PrettyFormat));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}