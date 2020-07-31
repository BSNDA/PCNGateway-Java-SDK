package com.bsnbase.sdk.client.fiscobcos.test;

import com.bsnbase.sdk.client.fiscobcos.service.ChainCodeService;
import com.bsnbase.sdk.client.fiscobcos.service.NodeService;
import com.bsnbase.sdk.client.fiscobcos.service.UserService;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.fiscobcos.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class FiscobcosTest {


    //    //初始化config   R1
//    public void initConfig() throws IOException {
//        Config config = new Config();
//        config.setAppCode("app0001202004161020152918451");
//        config.setUserCode("USER0001202004151958010871292");
//        config.setApi("http://192.168.1.43:17502");
//        config.setCert("cert/bsn_gateway_https.crt");
//        config.setPrk("cert/private_key.pem");
//        config.setPuk("cert/public_key.pem");
//        config.setMspDir("D:/test");
//        config.initConfig(config);
//    }
    //初始化config    Sm2
    public void initConfig() throws IOException {
            Config config = new Config();
        config.setAppCode("app0001202006221045063821068");
        config.setUserCode("USER0001202005281426464614357");
        config.setApi("http://192.168.1.43:17502");
        config.setCert("cert/bsn_gateway_https.crt");
        config.setPrk("cert/sm2/private_key.pem");
        config.setPuk("cert/sm2/public_key.pem");
        config.setMspDir("D:/test");
        config.initConfig(config);
    }
    //k1
//    public void initConfig() throws IOException {
//        Config config = new Config();
//        config.setAppCode("app0001202006042323057101002");
//        config.setUserCode("USER0001202006042321579692440");
//        config.setApi("http://192.168.1.43:17502");
//        config.setCert("cert/bsn_gateway_https.crt");
//        config.setPrk("cert/k1/private_key.pem");
//        config.setPuk("cert/k1/public_key.pem");
//        config.setMspDir("D:/test");
//        config.initConfig(config);
//    }

    @Test
    /**
     *"userId": "abc",
     *"userAddress": "0x63011363406b75c62fea55f6a826f25fafc9c580"
     * 注册用户
     */
    public void testRegister() {
        try {
            initConfig();
            ReqUserRegister reqUserRegister = new ReqUserRegister();
            reqUserRegister.setUserId("aaa");
            UserService.userRegister(reqUserRegister);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //7.3.2密钥托管模式下调用智能合约接口
    @Test
    public void reqChainCode() throws IOException {
        initConfig();
        ReqUserRegister reqUserRegister = new ReqUserRegister();
        reqUserRegister.setUserId("abc");
        ReqKeyEscrow resKeyEscrow = new ReqKeyEscrow();
        resKeyEscrow.setContractName("111");
        resKeyEscrow.setFuncName("funcName");
        resKeyEscrow.setFuncParam("funcParam");
        NodeService.reqChainCode(resKeyEscrow);
    }

    /**
     * 7.3.4获取交易回执接口
     */
    @Test
    public void getTxReceiptByTxHash() throws IOException, NoSuchAlgorithmException {
        initConfig();
        ReqGetTxReceiptByTxHash reqGetTxReceiptByTxHash = new ReqGetTxReceiptByTxHash();
        reqGetTxReceiptByTxHash.setTxHash("0x90e8df4398468bec0cda353a5868a957b0afb0930e73b6c2ac407455765ccc1f");
        NodeService.getTxReceiptByTxHash(reqGetTxReceiptByTxHash);
    }


    /**
     * 7.3.5获取交易信息接口
     */
    @Test
    public void getTxInfoByTxHash() throws IOException, NoSuchAlgorithmException {
        initConfig();
        ReqGetTransaction reqGetTxReceiptByTxHash = new ReqGetTransaction();
        reqGetTxReceiptByTxHash.setTxHash("0x90e8df4398468bec0cda353a5868a957b0afb0930e73b6c2ac407455765ccc1f");
        NodeService.getTxInfoByTxHash(reqGetTxReceiptByTxHash);
    }

    /**
     * 7.3.6获取块信息接口
     *
     */
    @Test
    public void getBlockInfo() throws IOException, NoSuchAlgorithmException {
        initConfig();
        ReqGetBlockInformation reqGetBlockInformation = new ReqGetBlockInformation();
        reqGetBlockInformation.setBlockHash("0x755f3e7833778f674e1b025f513f05722ba7248be43a3c9168b880847814021a");
        reqGetBlockInformation.setBlockNumber("1");
        NodeService.getBlockInfo(reqGetBlockInformation);
    }

    /**
     * 7.3.7获取应用内的块高接口
     *
     */
    @Test
    public void getBlockHeight() throws IOException, NoSuchAlgorithmException {
        initConfig();
        NodeService.getBlockHeight();
    }

    /**
     * 7.3.8获取应用内的交易总数接口
     *
     */
    @Test
    public void getTxCount() throws IOException, NoSuchAlgorithmException {
        initConfig();
        NodeService.getTxCount();
    }

    /**
     * 7.3.9获取块内的交易总数接口
     *
     * @throws IOException
     */
    @Test
    public void getTxCountByBlockNumber() throws IOException, NoSuchAlgorithmException {
        initConfig();
        ReqGetTxCountByBlockNumber reqGetTxCountByBlockNumber = new ReqGetTxCountByBlockNumber();
        reqGetTxCountByBlockNumber.setBlockNumber("22");
        NodeService.getTxCountByBlockNumber(reqGetTxCountByBlockNumber);
    }

    /**
     * 7.3.10链码事件注册接口
     *
     */
    @Test
    public void eventRegister() throws IOException, NoSuchAlgorithmException {
        initConfig();
        ReqChainCodeRegister reqChainCodeRegister=new ReqChainCodeRegister();
        reqChainCodeRegister.setEventType(1);
        reqChainCodeRegister.setContractAddress("0x866aefc204b8f8fdc3e45b908fd43d76667d7f76");
        reqChainCodeRegister.setContractName("BsnBaseContractk1");
        reqChainCodeRegister.setNotifyUrl("http://127.0.0.1:18080");
        reqChainCodeRegister.setAttachArgs("abc=123");
        ChainCodeService.eventRegister(reqChainCodeRegister);
    }

    /**
     * 7.3.11链码事件查询接口
     */
    @Test
    public void eventQuery() throws Exception {
        initConfig();
        ChainCodeService.eventQuery();
    }


    /**
     *
     * 7.3.12链码事件取消接口
     *"msg": "删除出现异常"
     */
    @Test
    public void eventRemove() throws Exception {
        initConfig();
        ReqChainCodeCancel reqChainCodeCancel=new ReqChainCodeCancel();
        reqChainCodeCancel.setEventId("123");
        ChainCodeService.eventRemove(reqChainCodeCancel);
    }


    /**
     *
     * 7.3.12链码事件取消接口
     */
    @Test
    public void trans() throws Exception {
        initConfig();
        String abi ="[{\"constant\":false,\"inputs\":[{\"name\":\"base_id\",\"type\":\"string\"},{\"name\":\"base_key\",\"type\":\"int256\"},{\"name\":\"base_value\",\"type\":\"string\"}],\"name\":\"update\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"base_id\",\"type\":\"string\"},{\"name\":\"base_key\",\"type\":\"int256\"}],\"name\":\"remove\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"base_id\",\"type\":\"string\"},{\"name\":\"base_key\",\"type\":\"int256\"},{\"name\":\"base_value\",\"type\":\"string\"}],\"name\":\"insert\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"base_id\",\"type\":\"string\"}],\"name\":\"select\",\"outputs\":[{\"name\":\"\",\"type\":\"string[]\"},{\"name\":\"\",\"type\":\"int256[]\"},{\"name\":\"\",\"type\":\"string[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]";
        String funcName ="insert";
        String contactName="BsnBaseContract";
        String contractAddress ="0xc206db9e77e547b015e2cb39d23ff8b0314746a4";
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
        NodeService.trans(reqTransData);
    }
}