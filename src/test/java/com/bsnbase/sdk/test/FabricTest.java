package com.bsnbase.sdk.test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bsnbase.sdk.client.fabric.FabricClient;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.fabric.*;
import com.bsnbase.sdk.entity.resp.fabric.*;
import com.bsnbase.sdk.util.common.Common;
import com.google.protobuf.util.JsonFormat;
import org.junit.Test;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

public class FabricTest {


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

    //Initialize config
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
     * <p>
     * In both key trust mode and public key upload mode, when the user is participating in the Fabric application and needs to create a separate user transaction certificate for the sub-user of the off-chain system, it is necessary to call this interface first to register the user in this city node, and the user's username is name@appCode in the call parameter.
     */
    @Test
    public void userRegister() {
        try {
            initConfig();
            ReqUserRegister register = new ReqUserRegister();
            register.setName("test54");
            register.setSecret("123456");
            ResUserRegister resUserRegister = FabricClient.userRegister(register);
            System.out.println(JSONObject.toJSONString(resUserRegister, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * Invoke chaincode in Key Trust Mode
     * When the off-chain business system connects to the BSN gateway, it needs to add the corresponding parameters in the request message according to the interface description.
     * After invoking the gateway, the gateway will return the execution result of the chaincode.
     * This interface will directly response the result and does not wait for the transaction to block. User can call "Get Transaction Information API" to query the block based on the transaction ID.
     */
    @Test
    public void reqChainCode() {
        try {
            initConfig();
            ReqKeyEscrow reqkey = new ReqKeyEscrow();
            String[] args = {"{\"basekey\":\"161\",\"basevalue\":\"123\"}"};
            reqkey.setArgs(args);
            reqkey.setFuncName("set");
            reqkey.setChainCode("cc_app0001202111111132373045541_01");
            reqkey.setUserName("test54");
            ResKeyEscrow resKeyEscrow = FabricClient.reqChainCode(reqkey);
            System.out.println(JSONObject.toJSONString(resKeyEscrow, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * User certificate registration in Public Key Upload Mode
     * When a user participated in the application in the public key upload mode needs to register a sub-user, after completing the user registration interface, he/she can call this interface to upload a public key certificate application file and obtain a sub-user certificate issued by the city node.
     * An exception will be returned when this interface is called in key trust mode.
     */
    @Test
    public void userEnroll() {
        try {
            initConfig();
            ReqKeyEscrowEnroll r = new ReqKeyEscrowEnroll();
            r.setName("test54");
            r.setSecret("123456");
            ResKeyEscrowEnroll resKeyEscrowEnroll = FabricClient.userEnroll(r);
            System.out.println(JSONObject.toJSONString(resKeyEscrowEnroll, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Invoke chaincode in Public Key Upload Mode
     * <p>
     * When the user of the public key upload mode application needs to initiate a transaction from the off-chain system to the chaincode on the chain, he/she needs to assemble the transaction message locally and call this interface to initiate the transaction.
     */
    @Test
    public void nodeTrans() {
        try {
            initConfig();
            ReqKeyEscrow reqkey = new ReqKeyEscrow();
            String[] args = {"{\"baseKey\":\"test20200652\",\"baseValue\":\"this is string \"}"};
            reqkey.setArgs(args);
            reqkey.setFuncName("set");
            reqkey.setChainCode("cc_app0001202111121647396153631_01");
            reqkey.setUserName("test54");
            ResKeyEscrowNo resKeyEscrowNo = FabricClient.nodeTrans(reqkey);
            System.out.println(JSONObject.toJSONString(resKeyEscrowNo, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get transaction information
     * The off-chain system can use this interface to get the transaction's detailed information based on the transaction ID.
     */
    @Test
    public void getTransaction() {
        try {
            initConfig();
            ReqGetTransaction reqData = new ReqGetTransaction();
            reqData.setTxId("aa2b7510d67e5317cb3d290c3c88d1216715d2b0ba9c7d8ce5a65801c50ca967");
            ResGetTransaction transaction = FabricClient.getTransaction(reqData);
            System.out.println(JSONObject.toJSONString(transaction, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Get transaction data
     * This interface can be used by the off-chain system to obtain transaction data based on the transaction ID, and the transaction information is returned in the form of base64 string.
     */
    @Test
    public void reqTransData() {
        try {
            initConfig();
            ReqTransData reqTransData = new ReqTransData();
            reqTransData.setTxId("b033975bcf1e3a20190a5d5a7de6eb461aa8ae98cbc7ebfcf56beb2ee9775930");
            ResTransData resChainCodeCancel = FabricClient.getTransData(reqTransData);
            System.out.println(JSONObject.toJSONString(resChainCodeCancel, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Get block information
     * After the data is uploaded, the off-chain business system will call the node gateway to get the block information (body.blockInfo), status value (body.blockInfo.status) and transaction ID (body.blockInfo.txId) of the current transaction.
     * If the status value is 0, it means the transaction is submitted successfully and the block is generated, and the user can query the block information based on the transaction ID.
     */
    @Test
    public void getBlockInfo() {
        try {
            initConfig();
            ReqGetBlockInformation rbf = new ReqGetBlockInformation();
            rbf.setTxId("aa2b7510d67e5317cb3d290c3c88d1216715d2b0ba9c7d8ce5a65801c50ca967");
            ResGetBlockInformation blockInfo = FabricClient.getBlockInfo(rbf);
            System.out.println(JSONObject.toJSONString(blockInfo, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Get block data
     * After the data is uploaded, the off-chain business system calls this interface through the node gateway to get the block information of the current transaction
     */
    @Test
    public void getBlockData() {
        try {
            initConfig();
            ReqGetBlockData reqGetBlockData = new ReqGetBlockData();
            reqGetBlockData.setTxId("e0cffa0b857bae8c00bf63e49a72e0f09384983fb97eb2ef19088048630b2c5b");
            ResGetBlockData resGetBlockData = FabricClient.getBlockData(reqGetBlockData);
            System.out.println(JSONObject.toJSONString(resGetBlockData, SerializerFeature.PrettyFormat));

            org.hyperledger.fabric.protos.common.Common.Block block = org.hyperledger.fabric.protos.common.Common.Block.parseFrom(Base64.getDecoder().decode(resGetBlockData.getBlockData()));
            System.out.println("------------------blockData------------------:\n" + JsonFormat.printer().includingDefaultValueFields().print(block));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the latest ledger information
     * Get the latest ledger information of the application, including block hash, previous block hash, the current block height, and other information.
     */
    @Test
    public void getLedgerInfo() {
        try {
            initConfig();
            ResGetLedgerInfo ledgerInfo = FabricClient.getLedgerInfo();
            System.out.println(JSONObject.toJSONString(ledgerInfo, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Chaincode event registration
     * If the user who has participated in the application needs to trigger the off-chain business system for subsequent business processing by the chaincode event, he/she can call this interface to register the chaincode event to be listened to.
     */
    @Test
    public void eventRegister() {
        try {
            initConfig();
            ReqChainCodeRegister data = new ReqChainCodeRegister();
            data.setAttachArgs("name=张三&age=20");
            data.setChainCode("cc_app0001202008181653099751659_01");
            data.setEventKey("test");
            data.setNotifyUrl("http://192.168.6.128:8080/api/event/notifyUrl");
            ResChainCodeRegister resChainCodeRegister = FabricClient.eventRegister(data);
            System.out.println(JSONObject.toJSONString(resChainCodeRegister, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Block event registration
     * If the user who has participated in the application needs to trigger the off-chain business system for subsequent business processing by the block event, he/she can call this interface to register the block event to be listened to.
     */
    @Test
    public void eventBlockRegister() {
        try {
            initConfig();
            ReqBlockRegister data = new ReqBlockRegister();
            data.setAttachArgs("name=张三&age=20");
            data.setNotifyUrl("http://192.168.6.128:8080/api/event/notifyUrl");
            ResBlockRegister resBlockRegister = FabricClient.eventBlockRegister(data);
            System.out.println(JSONObject.toJSONString(resBlockRegister, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Query chaincode event
     * This interface can query the list of registered events.
     */
    @Test
    public void eventQuery() {
        try {
            initConfig();
            List<ResChainCodeQuery> resChainCodeQueries = FabricClient.eventQuery();
            System.out.println(JSONObject.toJSONString(resChainCodeQueries, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove chaincode event
     * This interface can remove the registered events.
     */
    @Test
    public void eventRemove() {
        try {
            initConfig();
            ReqChainCodeRemove reqChainCodeRemove = new ReqChainCodeRemove();
            reqChainCodeRemove.setEventId("ad59c71e3dbc418e8aef8b101addd0cb");
            ResChainCodeRemove resChainCodeRemove = FabricClient.eventRemove(reqChainCodeRemove);
            System.out.println(JSONObject.toJSONString(resChainCodeRemove, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
