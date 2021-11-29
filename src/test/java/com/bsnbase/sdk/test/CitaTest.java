package com.bsnbase.sdk.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bsnbase.sdk.client.cita.CitaClient;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.cita.*;
import com.bsnbase.sdk.entity.resp.cita.*;
import com.bsnbase.sdk.util.common.Common;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CitaTest {

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
     * Instantiate the Config object：
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
     * Initialize by reading a json file：
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
     * After a user participates in a CITA application service successfully, he/she needs to call this interface through the off-chain business system to generate the user account and account address for on-chain transaction processing.
     */
    @Test
    public void userRegister() {
        try {
            initConfig();
            ReqUserRegister register = new ReqUserRegister();
            register.setUserId("userId");
            ResUserRegister resUserRegister = CitaClient.userRegister(register);
            System.out.println(JSONObject.toJSONString(resUserRegister, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * Invoke the smart contract in Key Trust Mode
     * When the off-chain business system connects to the BSN gateway, it needs to add the corresponding parameters in the request message according to the interface description. After invoking the gateway, the gateway will return the execution result of the smart contract.
     * Example of default contract method call
     * Save data/Update data.
     * FuncName: insert/update
     * FuncParam: The first element is a byte32 string (key), the second element is a byte16 string (value), which can be obtained by Common.getByte32() and Common.getByte16().
     * Delete data/Get data:.
     * FuncName: remove/retrieve
     * FuncParam: the first element is a byte32 string (key)
     */
    @Test
    public void reqChainCode() {
        try {
            initConfig();
            ReqKeyEscrow reqkey = new ReqKeyEscrow();
            reqkey.setContractName("contractName");
            reqkey.setFuncName("funcName");
            List<Object> list = new ArrayList<>();
            list.add(Common.getByte32("param".getBytes()));
            reqkey.setFuncParam(JSON.toJSONString(list));
            System.out.println(JSON.toJSONString(list));
            reqkey.setUserId("userId");
            ResKeyEscrow resKeyEscrow = CitaClient.reqChainCode(reqkey);
            System.out.println(JSONObject.toJSONString(resKeyEscrow, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * Invoke the smart contract in Public Key Upload Mode
     * When the off-chain business system connects to the BSN gateway, it needs to add the corresponding parameters in the request message according to the interface description. After invoking the gateway, the gateway will return the execution result of the smart contract.
     * In the transaction of public key upload mode, the private key of the on-chain transaction is generated and saved by the user, and then the client will assemble and sign the on-chain data locally, and upload the signed data to the node gateway.
     * The gateway forwards the data to the corresponding blockchain node to initiate the transaction request. In this mode, the ABI of the contract and the contract address are required to assemble the data.
     * The ABI of the contract is obtained by compiling the contract when developing the contract, and the contract address can be obtained from the details page of the participated service.
     * The method to assemble the on-chain data has been implemented in the SDK of the gateway, and it can be called directly.
     * -----------------------------------------------------------------------------------------------------------------
     * Example of default contract method parameters
     * FuncParam: the parameter type of the preset chaincode package is already handled in Service, just pass String.
     * <p>
     * Save data
     * FuncName:insert
     * FuncParam:{"insert1219", "123456"}
     * <p>
     * Remove data
     * FuncName:remove
     * FuncParam:{"insert1219"}
     * <p>
     * Update data
     * FuncName:update
     * FuncParam:{"insert1219", "1234567"}
     * Get data
     * FuncName:retrieve
     * FuncParam:{"insert1219"}
     * <p>
     * Get key by index
     * FuncName:keyAtIndex
     * FuncParam:{"1"}
     * -----------------------------------------------------------------------------------------------------------------
     */

    @Test
    public void nodeTrans() {
        try {
            initConfig();
            String[] strArray = {"param1", "param2"};
            ReqKeyUpload reqKeyUpload = new ReqKeyUpload();
            reqKeyUpload.setContractAddr("contractAddr");
            reqKeyUpload.setFuncName("funcName");
            reqKeyUpload.setContractName("contractName");
            reqKeyUpload.setArgs(strArray);
            ResKeyEscrow resKeyEscrow = CitaClient.nodeTrans(reqKeyUpload);
            System.out.println(JSONObject.toJSONString(resKeyEscrow, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.getMessage();
            System.out.println(e.getMessage());
        }
    }

    /**
     * Get Transaction Receipt
     * After a smart contract executes a transaction, you can use this interface to get the transaction's receipt information based on the transaction's hash value.
     */
    @Test
    public void getTxReceiptByTxHash() {
        try {
            initConfig();
            ReqGetTxReceiptByTxHash reqGetTxReceiptByTxHash = new ReqGetTxReceiptByTxHash();
            reqGetTxReceiptByTxHash.setTxHash("txHash");
            ResGetTxReceiptByTxHash resGetTxReceiptByTxHash = CitaClient.getTxReceiptByTxHash(reqGetTxReceiptByTxHash);
            System.out.println(JSONObject.toJSONString(resGetTxReceiptByTxHash, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Get Transaction Information
     * After a smart contract executes a transaction, you can use this interface to get the transaction's detailed information based on the transaction's hash value.
     */
    @Test
    public void getTransaction() {
        try {
            initConfig();
            ReqGetTransaction reqGetTransaction = new ReqGetTransaction();
            reqGetTransaction.setTxHash("txHash");
            ResGetTransaction transaction = CitaClient.getTransaction(reqGetTransaction);
            System.out.println(JSONObject.toJSONString(transaction, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Get block information
     * When the data is uploaded and a block is generated, the information of the responding block can be queried according to the block number or the block hash, where the block number and the block hash cannot be empty at the same time.
     * When both are not empty, the information of the block corresponding to the block number is queried in priority.
     */
    @Test
    public void getBlockInfo() {
        try {
            initConfig();
            ReqGetBlockInformation rbf = new ReqGetBlockInformation();
            rbf.setBlockHash("txHash");
            rbf.setBlockNumber("blockNumber");
            ResGetBlockInformation blockInfo = CitaClient.getBlockInfo(rbf);
            System.out.println(JSONObject.toJSONString(blockInfo, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Get the block height
     * Get the block height based on this interface.
     */
    @Test
    public void getBlockHeight() {
        try {
            initConfig();
            ResGetBlockHeight resGetBlockHeight = CitaClient.getBlockHeight();
            System.out.println(JSONObject.toJSONString(resGetBlockHeight, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Chaincode event registration
     * You can register cita's block event or contract event through this interface, and when the event is triggered, the system will send the event content to the registered notification address.
     */
    @Test
    public void eventRegister() {
        try {
            initConfig();
            ReqChainCodeRegister reqChainCodeRegister = new ReqChainCodeRegister();
            reqChainCodeRegister.setAttachArgs("attachArgs");
            reqChainCodeRegister.setContractAddress("contractAddress");
            reqChainCodeRegister.setContractName("contractName");
            reqChainCodeRegister.setEventType(1);
            reqChainCodeRegister.setNotifyUrl("notifyUrl");
            ResChainCodeRegister resChainCodeRegister = CitaClient.eventRegister(reqChainCodeRegister);
            System.out.println(JSONObject.toJSONString(resChainCodeRegister, SerializerFeature.PrettyFormat));
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
            List<ResChainCodeQuery> resChainCodeQuery = CitaClient.eventQuery();
            System.out.println(JSONObject.toJSONString(resChainCodeQuery, SerializerFeature.PrettyFormat));
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
            ReqChainCodeCancel reqChainCodeCancel = new ReqChainCodeCancel();
            reqChainCodeCancel.setEventId("eventId");
            ResChainCodeCancel resChainCodeCancel = CitaClient.eventRemove(reqChainCodeCancel);
            System.out.println(JSONObject.toJSONString(resChainCodeCancel, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
