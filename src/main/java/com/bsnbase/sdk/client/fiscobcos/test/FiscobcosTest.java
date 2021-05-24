package com.bsnbase.sdk.client.fiscobcos.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bsnbase.sdk.client.fiscobcos.FiscobcosClient;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.fiscobcos.*;
import com.bsnbase.sdk.entity.res.fiscobcos.*;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FiscobcosTest {

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
     *
     * testServerIdn 测试网服务需要配置为true,其他服务不用配置
     */


    public void initConfig() throws IOException {
//        Config config = new Config();
//        config.setAppCode("app0001202010221038364886804");
//        config.setUserCode("USER0001202010201539390086090");
//        config.setApi("http://192.168.1.43:17502");
//        config.setPrk(Common.readFile("cert/private_key.pem"));
//        config.setPuk(Common.readFile("cert/public_key.pem"));
//        config.setMspDir("D:/test");
//        config.setTestServerIdn(true);
//        config.initConfig(config);
        String filePath="config/config.json";
        Config config=Config.buildByConfigJson(filePath);
        config.initConfig(config);
    }

    @Test
    /**
     * 注册用户
     */
    public void testRegister() {
        try {
            initConfig();
            ReqUserRegister reqUserRegister = new ReqUserRegister();
            reqUserRegister.setUserId("task852321");
            ResUserRegister resUserRegister = FiscobcosClient.register(reqUserRegister);
            System.out.println(JSONObject.toJSONString(resUserRegister, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *  密钥托管模式下调用智能合约接口
     */
    @Test
    public void reqChainCode(){
        try {
            initConfig();
            ReqKeyEscrow reqKeyEscrow = new ReqKeyEscrow();
            reqKeyEscrow.setUserId("task852321");
            reqKeyEscrow.setContractName("BsnBaseGlobalContract");
            reqKeyEscrow.setFuncName("insert");
            List<Object> list=new ArrayList<>();
            list.add("test1");
            list.add(1);
            list.add("test2");
            reqKeyEscrow.setFuncParam(JSON.toJSONString(list));
            ResKeyEscrow resKeyEscrow = FiscobcosClient.reqChainCode(reqKeyEscrow);
            System.out.println(JSONObject.toJSONString(resKeyEscrow, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 公钥上传模式下调用智能合约接口
     */
    @Test
    public void trans() {
        try {
            initConfig();
            String abi ="[{\"constant\":false,\"inputs\":[{\"name\":\"base_id\",\"type\":\"string\"},{\"name\":\"base_key\",\"type\":\"int256\"},{\"name\":\"base_value\",\"type\":\"string\"}],\"name\":\"update\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"base_id\",\"type\":\"string\"},{\"name\":\"base_key\",\"type\":\"int256\"}],\"name\":\"remove\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"base_id\",\"type\":\"string\"},{\"name\":\"base_key\",\"type\":\"int256\"},{\"name\":\"base_value\",\"type\":\"string\"}],\"name\":\"insert\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"base_id\",\"type\":\"string\"}],\"name\":\"select\",\"outputs\":[{\"name\":\"\",\"type\":\"string[]\"},{\"name\":\"\",\"type\":\"int256[]\"},{\"name\":\"\",\"type\":\"string[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]";
            String funcName ="insert";
            String contactName="BsnBaseContract";
            String contractAddress ="0x9217f004c88e7cfea9b35d99b95dcdcc003e606b";
            List<Object> funcParam = new ArrayList<>();
            funcParam.add("s0604");
            funcParam.add(5);
            funcParam.add("aa");
            String name="test0611";
            ReqTransData reqTransData=new ReqTransData();
            reqTransData.setContractAbi(abi);
            reqTransData.setContractAddress(contractAddress);
            reqTransData.setFuncName(funcName);
            reqTransData.setFuncParam(funcParam);
            reqTransData.setContractName(contactName);
            reqTransData.setUserName(name);
            ResTrans trans = FiscobcosClient.trans(reqTransData);
            System.out.println(JSONObject.toJSONString(trans, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取交易回执接口
     */
    @Test
    public void getTxReceiptByTxHash(){
        try {
            initConfig();
            ReqGetTxReceiptByTxHash reqGetTxReceiptByTxHash = new ReqGetTxReceiptByTxHash();
            reqGetTxReceiptByTxHash.setTxHash("0x90e8df4398468bec0cda353a5868a957b0afb0930e73b6c2ac407455765ccc1f");
            ResGetTxReceiptByTxHash txReceiptByTxHash = FiscobcosClient.getTxReceiptByTxHash(reqGetTxReceiptByTxHash);
            System.out.println(JSONObject.toJSONString(txReceiptByTxHash, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取交易信息接口
     */
    @Test
    public void getTxInfoByTxHash() {
        try {
            initConfig();
            ReqGetTransaction reqGetTxReceiptByTxHash = new ReqGetTransaction();
            reqGetTxReceiptByTxHash.setTxHash("0x90e8df4398468bec0cda353a5868a957b0afb0930e73b6c2ac407455765ccc1f");
            ResGetTransaction txInfoByTxHash = FiscobcosClient.getTxInfoByTxHash(reqGetTxReceiptByTxHash);
            System.out.println(JSONObject.toJSONString(txInfoByTxHash, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取块信息接口
     *
     */
    @Test
    public void getBlockInfo(){
        try {
            initConfig();
            ReqGetBlockInformation reqGetBlockInformation = new ReqGetBlockInformation();
            reqGetBlockInformation.setBlockHash("0x755f3e7833778f674e1b025f513f05722ba7248be43a3c9168b880847814021a");
            reqGetBlockInformation.setBlockNumber("1");
            ResGetBlockInformation blockInfo = FiscobcosClient.getBlockInfo(reqGetBlockInformation);
            System.out.println(JSONObject.toJSONString(blockInfo, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取应用内的块高接口
     *
     */
    @Test
    public void getBlockHeight() {
        try {
            initConfig();
            ResGetBlockHeight blockHeight = FiscobcosClient.getBlockHeight();
            System.out.println(JSONObject.toJSONString(blockHeight, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取应用内的交易总数接口
     *
     */
    @Test
    public void getTxCount(){
        try {
            initConfig();
            ResGetTxCount txCount = FiscobcosClient.getTxCount();
            System.out.println(JSONObject.toJSONString(txCount, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *获取块内的交易总数接口
     *
     * @throws IOException
     */
    @Test
    public void getTxCountByBlockNumber(){
        try {
            initConfig();
            ReqGetTxCountByBlockNumber reqGetTxCountByBlockNumber = new ReqGetTxCountByBlockNumber();
            reqGetTxCountByBlockNumber.setBlockNumber("22");
            ResGetTxCountByBlockNumber txCountByBlockNumber = FiscobcosClient.getTxCountByBlockNumber(reqGetTxCountByBlockNumber);
            System.out.println(JSONObject.toJSONString(txCountByBlockNumber, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *链码事件注册接口
     */
    @Test
    public void eventRegister() {
        try {
            initConfig();
            ReqChainCodeRegister reqChainCodeRegister=new ReqChainCodeRegister();
            reqChainCodeRegister.setEventType(1);
            reqChainCodeRegister.setContractAddress("0x9217f004c88e7cfea9b35d99b95dcdcc003e606b");
            reqChainCodeRegister.setContractName("BsnBaseContractk1");
            reqChainCodeRegister.setNotifyUrl("http://127.0.0.1:18080");
            reqChainCodeRegister.setAttachArgs("abc=123");
            ResChainCodeRegister resChainCodeRegister = FiscobcosClient.eventRegister(reqChainCodeRegister);
            System.out.println(JSONObject.toJSONString(resChainCodeRegister, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 链码事件查询接口
     */
    @Test
    public void eventQuery(){
        try {
            initConfig();
            List<ResChainCodeQuery> resChainCodeQueries = FiscobcosClient.eventQuery();
            System.out.println(JSONObject.toJSONString(resChainCodeQueries, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * 链码事件取消接口
     */
    @Test
    public void eventRemove(){
        try {
            initConfig();
            ReqChainCodeCancel reqChainCodeCancel=new ReqChainCodeCancel();
            reqChainCodeCancel.setEventId("992b172844174d0795e06826a9d8f6da");
            ResChainCodeCancel resChainCodeCancel = FiscobcosClient.eventRemove(reqChainCodeCancel);
            System.out.println(JSONObject.toJSONString(resChainCodeCancel, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}