package com.bsnbase.sdk.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bsnbase.sdk.client.fiscobcos.FiscobcosClient;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.fiscobcos.*;
import com.bsnbase.sdk.entity.resp.fiscobcos.*;
import com.bsnbase.sdk.util.common.Common;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FiscobcosTest {

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
     * In both key trust mode and public key upload mode, when the user participated in the FISCO BCOS application needs to create a separate user transaction certificate for the sub-user of the off-chain system, it is necessary to call this interface first to register the sub-user in this city node, and the sub-user's username is name@appCode in the call parameter.
     */
    @Test
    public void testRegister() {
        try {
            initConfig();
            ReqUserRegister reqUserRegister = new ReqUserRegister();
            reqUserRegister.setUserId("userId");
            ResUserRegister resUserRegister = FiscobcosClient.register(reqUserRegister);
            System.out.println(JSONObject.toJSONString(resUserRegister, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Invoke the smart contract in Key Trust Mode
     * When the off-chain business system connects to the BSN gateway, it needs to add the corresponding parameters in the request message according to the interface description.
     * After invoking the gateway, the gateway will return the execution result of the smart contract.
     */
    @Test
    public void reqChainCode() {
        try {
            initConfig();
            ReqKeyEscrow reqKeyEscrow = new ReqKeyEscrow();
            reqKeyEscrow.setUserId("userId");
            reqKeyEscrow.setContractName("contractName");
            reqKeyEscrow.setFuncName("funcName");
            List<Object> list = new ArrayList<>();
            list.add("param");
            reqKeyEscrow.setFuncParam(JSON.toJSONString(list));
            ResKeyEscrow resKeyEscrow = FiscobcosClient.reqChainCode(reqKeyEscrow);
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
     */
    @Test
    public void trans() {
        try {
            initConfig();
            String abi = "abi";
            String funcName = "funcName";
            String contactName = "contactName";
            String contractAddress = "contractAddress";
            List<Object> funcParam = new ArrayList<>();
            funcParam.add("param");
            String name = "userName";
            ReqTransData reqTransData = new ReqTransData();
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
     * Get transaction receipt
     * After a smart contract executes a transaction, you can use this interface to get the transaction's receipt information based on the transaction's hash value.
     */
    @Test
    public void getTxReceiptByTxHash() {
        try {
            initConfig();
            ReqGetTxReceiptByTxHash reqGetTxReceiptByTxHash = new ReqGetTxReceiptByTxHash();
            reqGetTxReceiptByTxHash.setTxHash("txHash");
            ResGetTxReceiptByTxHash txReceiptByTxHash = FiscobcosClient.getTxReceiptByTxHash(reqGetTxReceiptByTxHash);
            System.out.println(JSONObject.toJSONString(txReceiptByTxHash, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Get transaction information
     * After a smart contract executes a transaction, you can use this interface to get the transaction's detailed information based on the transaction's hash value.
     */
    @Test
    public void getTxInfoByTxHash() {
        try {
            initConfig();
            ReqGetTransaction reqGetTxReceiptByTxHash = new ReqGetTransaction();
            reqGetTxReceiptByTxHash.setTxHash("txHash");
            ResGetTransaction txInfoByTxHash = FiscobcosClient.getTxInfoByTxHash(reqGetTxReceiptByTxHash);
            System.out.println(JSONObject.toJSONString(txInfoByTxHash, SerializerFeature.PrettyFormat));
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
            ReqGetBlockInformation reqGetBlockInformation = new ReqGetBlockInformation();
            reqGetBlockInformation.setBlockHash("txHash");
            reqGetBlockInformation.setBlockNumber("1");
            ResGetBlockInformation blockInfo = FiscobcosClient.getBlockInfo(reqGetBlockInformation);
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
            ResGetBlockHeight blockHeight = FiscobcosClient.getBlockHeight();
            System.out.println(JSONObject.toJSONString(blockHeight, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the total number of transactions
     * The total number of transactions can be getd by calling this interface
     */
    @Test
    public void getTxCount() {
        try {
            initConfig();
            ResGetTxCount txCount = FiscobcosClient.getTxCount();
            System.out.println(JSONObject.toJSONString(txCount, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the total number of transactions in the block
     * The total number of transactions in a block can be queried within the FISCO-BCOS application based on the block number, where the block number cannot be empty.
     */
    @Test
    public void getTxCountByBlockNumber() {
        try {
            initConfig();
            ReqGetTxCountByBlockNumber reqGetTxCountByBlockNumber = new ReqGetTxCountByBlockNumber();
            reqGetTxCountByBlockNumber.setBlockNumber("1");
            ResGetTxCountByBlockNumber txCountByBlockNumber = FiscobcosClient.getTxCountByBlockNumber(reqGetTxCountByBlockNumber);
            System.out.println(JSONObject.toJSONString(txCountByBlockNumber, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Chaincode event registration
     * You can register FISCO-BCOS's block event or contract event through this interface, and when the event is triggered, the system will send the event content to the registered notification address.
     */
    @Test
    public void eventRegister() {
        try {
            initConfig();
            ReqChainCodeRegister reqChainCodeRegister = new ReqChainCodeRegister();
            reqChainCodeRegister.setEventType(1);
            reqChainCodeRegister.setContractAddress("contractAddress");
            reqChainCodeRegister.setContractName("contractName");
            reqChainCodeRegister.setNotifyUrl("notifyUrl");
            reqChainCodeRegister.setAttachArgs("attachArgs");
            ResChainCodeRegister resChainCodeRegister = FiscobcosClient.eventRegister(reqChainCodeRegister);
            System.out.println(JSONObject.toJSONString(resChainCodeRegister, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Query chaincode event
     * This interface can query the list of registered chaincode events and block events.
     */
    @Test
    public void eventQuery() {
        try {
            initConfig();
            List<ResChainCodeQuery> resChainCodeQueries = FiscobcosClient.eventQuery();
            System.out.println(JSONObject.toJSONString(resChainCodeQueries, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Remove chaincode event
     * This interface can remove the registered chaincode events and block events.
     */
    @Test
    public void eventRemove() {
        try {
            initConfig();
            ReqChainCodeCancel reqChainCodeCancel = new ReqChainCodeCancel();
            reqChainCodeCancel.setEventId("eventId");
            ResChainCodeCancel resChainCodeCancel = FiscobcosClient.eventRemove(reqChainCodeCancel);
            System.out.println(JSONObject.toJSONString(resChainCodeCancel, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}