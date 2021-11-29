package com.bsnbase.sdk.test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bsnbase.sdk.client.xuperChain.XuperClient;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.xuperChain.*;
import com.bsnbase.sdk.entity.resp.xuperChain.*;
import com.bsnbase.sdk.util.common.Common;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XuperChainTest {

    /**
     * Initialize the config
     * The private and public keys of the application are filled in the .pem files.
     * com.bsnbase.sdk.util.common.Common provides the method to get the content according to the path.
     * Common.readLocalFile parameter is the absolute path to the directory of pem.
     * For example: Common.readLocalFile("D:/test/private_key.pem")
     * The Common.readFile parameter is the relative path to the pem storage directory.
     * For example: Common.readFile("cert/private_key.pem")
     * Or fill in the pem content directly.
     * puk field and prk field are user's public and private keys respectively, which are required to fill in.
     * testServerIdn needs to be configured as true for Testnet services, but not for other services.
     * config can be initialized by instantiating a Config object or by reading a json file, the current example is instantiating a config object
     * ------------------------------------------------------------------------------------------
     * Instantiate the Config object:
     * Config config = new Config();
     * config.setAppCode("app0001202010221038364886804");
     * config.setUserCode("USER0001202010201539390086090");
     * config.setApi("http://192.168.1.43:17502");
     * config.setPrk(Common.readFile("cert/private_key.pem"));
     * config.setPuk(Common.readFile("cert/public_key.pem"));
     * config.setMspDir("D:/test");
     * config.setTestServerIdn(true);
     * config.initConfig(config);
     * ------------------------------------------------------------------------------------------
     * Initialize by reading a json file:
     * String filePath="config/config.json";
     * Config config=Config.buildByConfigJson(filePath);
     * config.initConfig(config);
     * ------------------------------------------------------------------------------------------
     */

    public void initConfig() throws IOException {
        Config config = new Config();
        config.setAppCode("appCode");
        config.setUserCode("userCode");
        config.setApi("api");
        config.setPrk(Common.readFile("cert/private_key.pem"));
        config.setPuk(Common.readFile("cert/public_key.pem"));
        config.setMspDir("mspDir");
        config.initConfig(config);
    }


    /**
     * User registration
     * After a user participates in a XuperChain-based application, he/she needs to call this interface through the off-chain business system to generate the user account and account address for on-chain transaction processing.
     */
    @Test
    public void testRegister() {
        try {
            initConfig();
            ReqUserRegister reqUserRegister = new ReqUserRegister();
            reqUserRegister.setUserId("userId");
            ResUserRegister resUserRegister = XuperClient.register(reqUserRegister);
            System.out.println(JSONObject.toJSONString(resUserRegister, SerializerFeature.PrettyFormat));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Invoke the smart contract in Key Trust Mode
     * When the off-chain business system connects to the BSN gateway, it needs to add the corresponding parameters in the request message according to the interface description. After invoking the gateway, the gateway will return the execution result of the smart contract.
     * -----------------------------------------------------------------------------------------------------------
     * Example of default contract method parameters:
     * Save data:
     * FuncName: insert_data
     * FuncParam:{"base_key":"dev_0001","base_value":"test"}
     * Remove data:
     * FuncName: remove_data
     * FuncParam:{"base_key":"dev_0001"}
     * Update data:
     * FuncName: update_data
     * FuncParam:{"base_key":"dev_0001","base_value":"test2"}
     * Get data:
     * FuncName: select_data
     * FuncParam:{"base_key":"dev_0001"}
     * -----------------------------------------------------------------------------------------------------------
     */

    @Test
    public void testReqChainCode() {
        try {
            initConfig();
            ReqKeyEscrow reqKeyEscrow = new ReqKeyEscrow();
            reqKeyEscrow.setUserId("userId");
            reqKeyEscrow.setUserAddr("userAddr");
            reqKeyEscrow.setContractName("contractName");
            reqKeyEscrow.setFuncName("funcName");
            Map<String, Object> paramMap = new HashMap();
            paramMap.put("base_key", "key");
            paramMap.put("base_value", "value");
            reqKeyEscrow.setFuncParam(JSONObject.toJSONString(paramMap));
            ResKeyEscrow resKeyEscrow = XuperClient.reqChainCode(reqKeyEscrow);
            System.out.println(JSONObject.toJSONString(resKeyEscrow, SerializerFeature.PrettyFormat));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Get account address in Public Key Upload Mode
     */
    @Test
    public void getTransInitiator() {
        try {
            initConfig();
            String initiator = XuperClient.getUserAddress();
            System.out.println("address:" + initiator);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * Query the smart contract interface in Public Key Upload Mode
     * When the off-chain business system connects to the BSN gateway, it needs to add the corresponding parameters in the request message according to the interface description. After invoking the gateway, the gateway will return the execution result of the smart contract.
     * In the transaction of public key upload mode, the private key of the on-chain transaction is generated and saved by the user, and then the client will assemble and sign the on-chain data locally, and upload the signed data to the node gateway.
     * The method to assemble the on-chain data has been implemented in the SDK of the gateway, and it can be called directly.
     * ---------------------------------------------------------------------------------------------------------
     * initiator: represents the account address of xupchain; the method getTransInitiator can be called to generate address information
     * ---------------------------------------------------------------------------------------------------------
     */
    @Test
    public void transQuery() {
        try {
            initConfig();
            ReqTransData reqTransData = new ReqTransData();
            reqTransData.setContractName("contractName");
            reqTransData.setMethodName("methodName");
            reqTransData.setInitiator("initiator");
            Map<String, byte[]> args = new HashMap<>();
            args.put("base_key", "key".getBytes());
            args.put("base_value", "value".getBytes());
            reqTransData.setArgs(args);
            ResKeyEscrowNo resKeyEscrowNo = XuperClient.reqChainCode(reqTransData);
            System.out.println(JSONObject.toJSONString(resKeyEscrowNo, SerializerFeature.PrettyFormat));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Invoke the smart contract in Public Key Upload Mode
     * When the off-chain business system connects to the BSN gateway, it needs to add the corresponding parameters in the request message according to the interface description. After invoking the gateway, the gateway will return the execution result of the smart contract.
     * In the transaction of public key upload mode, the private key of the on-chain transaction is generated and saved by the user, and then the client will assemble and sign the on-chain data locally, and upload the signed data to the node gateway.
     * The method to assemble the on-chain data has been implemented in the SDK of the gateway, and it can be called directly.
     * ---------------------------------------------------------------------------------------------------------
     * initiator: represents the account address of xupchain; the method getTransInitiator can be called to generate address information
     * ---------------------------------------------------------------------------------------------------------
     */
    @Test
    public void transInvoke() {
        try {
            initConfig();
            ReqTransData reqTransData = new ReqTransData();
            reqTransData.setContractName("contractName");
            reqTransData.setMethodName("methodName");
            reqTransData.setInitiator("initiator");
            Map<String, byte[]> args = new HashMap<>();
            args.put("base_key", "key".getBytes());
            args.put("base_value", "value".getBytes());
            reqTransData.setArgs(args);
            ResKeyEscrowNo resKeyEscrowNo = XuperClient.reqInvokeChainCode(reqTransData);
            System.out.println(JSONObject.toJSONString(resKeyEscrowNo, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Get transaction information
     * After a smart contract executes a transaction, you can use this interface to get the transaction's detailed information based on the transaction's hash value.
     */
    @Test
    public void testGetTxInfoByTxHash() {
        try {
            initConfig();
            ReqGetTransaction reqGetTransaction = new ReqGetTransaction();
            reqGetTransaction.setTxHash("txHash");
            ResGetTransaction resGetTransaction = XuperClient.getTxInfoByTxHash(reqGetTransaction);
            System.out.println(JSONObject.toJSONString(resGetTransaction, SerializerFeature.PrettyFormat));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get block information
     * When the data is uploaded and a block is generated, the information of the responding block can be queried according to the block number or the block hash, where the block number and the block hash cannot be empty at the same time.
     * When both are not empty, the information of the block corresponding to the block number is queried in priority.
     */
    @Test
    public void testGetBlockInfo() {
        try {
            initConfig();
            ReqGetBlockInformation reqGetBlockInformation = new ReqGetBlockInformation();
            reqGetBlockInformation.setBlockHash("blockHash");
            reqGetBlockInformation.setBlockHeight(1);
            ResGetBlockInformation resGetBlockInformation = XuperClient.getBlockInfo(reqGetBlockInformation);
            System.out.println(JSONObject.toJSONString(resGetBlockInformation, SerializerFeature.PrettyFormat));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Chaincode event registration
     * If the user who has participated in the application needs to trigger the off-chain business system for subsequent business processing by the chaincode event, he/she can call this interface to register the chaincode event to be listened to.
     */
    @Test
    public void testChainCodeRegister() {
        try {
            initConfig();
            ReqChainCodeRegister reqChainCodeRegister = new ReqChainCodeRegister();
            reqChainCodeRegister.setContractName("contractName");
            reqChainCodeRegister.setEventKey("eventKey");
            reqChainCodeRegister.setNotifyUrl("notifyUrl");
            reqChainCodeRegister.setAttachArgs("attachArgs");
            ResChainCodeRegister resChainCodeRegister = XuperClient.reqChainCodeRegister(reqChainCodeRegister);
            System.out.println(JSONObject.toJSONString(resChainCodeRegister, SerializerFeature.PrettyFormat));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Query chaincode event
     * This interface can query the list of registered events.
     */
    @Test
    public void testChainCodeQuery() {
        try {
            initConfig();
            List<ResChainCodeQuery> resChainCodeQuery = XuperClient.reqChainCodeQuery();
            System.out.println(JSONObject.toJSONString(resChainCodeQuery, SerializerFeature.PrettyFormat));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove chaincode event
     * This interface can remove the registered events.
     */
    @Test
    public void testChainCodeCancel() {
        try {
            initConfig();
            ReqChainCodeCancel reqChainCodeCancel = new ReqChainCodeCancel();
            reqChainCodeCancel.setEventId("eventId");
            ResChainCodeCancel resChainCodeCancel = XuperClient.reqChainCodeCancel(reqChainCodeCancel);
            System.out.println(JSONObject.toJSONString(resChainCodeCancel, SerializerFeature.PrettyFormat));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}