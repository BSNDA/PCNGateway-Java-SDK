package com.bsnbase.sdk.client.cita.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bsnbase.sdk.client.cita.CitaClient;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.cita.*;
import com.bsnbase.sdk.entity.res.cita.*;
import com.bsnbase.sdk.util.common.Common;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CitaTest {

    /**
     * 初始化config
     * <p>
     * 应用私钥、应用公钥为pem中具体内容，
     * com.bsnbase.sdk.util.common.Common提供根据路径获取内容方法，
     * Common.readLocalFile参数为pem存储目录的绝对路径，
     * 例如:Common.readFile("cert/private_key.pem")
     * Common.readFile参数为pem存储目录的相对路径，
     * 例如:Common.readLocalFile("D:/test/private_key.pem")
     * 或者直接填入pem内容。
     * <p>
     * puk字段和prk字段为用户公钥和私钥不能为空
     * <p>
     * testServerIdn 测试网服务需要配置为true,其他服务不用配置
     */

    //初始化config
    public void initConfig() throws IOException {
        Config config = new Config();
        config.setAppCode("app0003202008100054119967051");
        config.setUserCode("USER0003202005291706487822713");
        config.setApi("http://192.168.1.43:17502");
        config.setPrk(Common.readFile("cert/private_key.pem"));
        config.setPuk(Common.readFile("cert/public_key.pem"));
        config.setMspDir("D:/test");
        config.initConfig(config);
    }

    /**
     * 用户注册
     */
    @Test
    public void userRegister() {
        try {
            initConfig();
            ReqUserRegister register = new ReqUserRegister();
            register.setUserId("test565");
            ResUserRegister resUserRegister = CitaClient.userRegister(register);
            System.out.println(JSONObject.toJSONString(resUserRegister, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * 密钥托管模式交易处理
     * <p>
     * 默认合约方法调用示例
     * 保存数据/更新数据：
     * FuncName：insert/update
     * FuncParam:第一个元素为byte32字符串(key),第二个元素为Byte16字符串(value)可通过Common.getByte32()和Common.getByte16()获取
     * 删除数据/获取数据：：
     * FuncName：remove/retrieve
     * FuncParam:第一个元素为byte32字符串(key)
     */
    @Test
    public void reqChainCode() {
        try {
            initConfig();
            ReqKeyEscrow reqkey = new ReqKeyEscrow();
            reqkey.setContractName("CitaBsnBaseContract");
            reqkey.setFuncName("retrieve");
            List<Object> list = new ArrayList<>();
            list.add(Common.getByte32("test1".getBytes()));
            reqkey.setFuncParam(JSON.toJSONString(list));
            System.out.println(JSON.toJSONString(list));
            reqkey.setUserId("test56");
            ResKeyEscrow resKeyEscrow = CitaClient.reqChainCode(reqkey);
            System.out.println(JSONObject.toJSONString(resKeyEscrow, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 公钥上传调用智能合约接口，默认合约示例参数
     * FuncParam:基础链码包的参数类型Service中已经处理，传String就行。
     * <p>
     * 保存数据
     * FuncName:insert
     * FuncParam:{"insert1219", "123456"}
     * <p>
     * 删除数据
     * FuncName:remove
     * FuncParam:{"insert1219"}
     * <p>
     * 更新数据
     * FuncName:update
     * FuncParam:{"insert1219", "1234567"}
     * 获取数据
     * FuncName:retrieve
     * FuncParam:{"insert1219"}
     * <p>
     * 通过index获取key
     * FuncName:keyAtIndex
     * FuncParam:{"1"}
     *
     * @throws Exception
     */
    @Test
    public void nodeTrans() {
        try {
            initConfig();
            String[] strArray = {"123456", "w12345627"};
            ReqKeyUpload reqKeyUpload = new ReqKeyUpload();
            reqKeyUpload.setContractAddr("0x98c1bb31a119b96d7f9fb4ec8e7aa8c6917be4dd");
            reqKeyUpload.setFuncName("retrieve");
            reqKeyUpload.setContractName("CitaBsnBaseContract");
            reqKeyUpload.setArgs(strArray);
            CitaClient.nodeTrans(reqKeyUpload);

        } catch (Exception e) {
            e.getMessage();
            System.out.println(e.getMessage());
        }
    }

    /**
     * 获取交易回执接口
     */
    @Test
    public void getTxReceiptByTxHash() {
        try {
            initConfig();
            ReqGetTxReceiptByTxHash reqGetTxReceiptByTxHash = new ReqGetTxReceiptByTxHash();
            reqGetTxReceiptByTxHash.setTxHash("0x901dca70c3939922a56fafa783cda4c96b06633a47ac13f187998a3b68c6017a");
            ResGetTxReceiptByTxHash resGetTxReceiptByTxHash = CitaClient.getTxReceiptByTxHash(reqGetTxReceiptByTxHash);
            System.out.println(JSONObject.toJSONString(resGetTxReceiptByTxHash, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取交易信息
     */
    @Test
    public void getTransaction() {
        try {
            initConfig();
            ReqGetTransaction reqGetTransaction = new ReqGetTransaction();
            reqGetTransaction.setTxHash("0xe71c2966e576aab79f4f3a570c532d81daa38b3c38c05f2a281b5e59e3ecf13d");
            ResGetTransaction transaction = CitaClient.getTransaction(reqGetTransaction);
            System.out.println(JSONObject.toJSONString(transaction, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取块信息
     *
     * @return
     */
    @Test
    public void getBlockInfo() {
        try {
            initConfig();
            ReqGetBlockInformation rbf = new ReqGetBlockInformation();
            rbf.setBlockHash("0x2d784e55d38c4a466d540be976596aacc9fee0d7e9a345c4a852696353417ce2");
            rbf.setBlockNumber("10");
            ResGetBlockInformation blockInfo = CitaClient.getBlockInfo(rbf);
            System.out.println(JSONObject.toJSONString(blockInfo, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取应用内的块高接口
     *
     * @return
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
     * 链码事件注册
     *
     * @return
     */
    @Test
    public void eventRegister() {
        try {
            initConfig();
            ReqChainCodeRegister reqChainCodeRegister = new ReqChainCodeRegister();
            reqChainCodeRegister.setAttachArgs("");
            reqChainCodeRegister.setContractAddress("0x2b93131c6008d3a1c7d9a42ea1482d2b51e0cc2c");
            reqChainCodeRegister.setContractName("CitaBsnBaseContract");
            reqChainCodeRegister.setEventType(1);
            reqChainCodeRegister.setNotifyUrl("http://127.0.0.1:18080");
            ResChainCodeRegister resChainCodeRegister = CitaClient.eventRegister(reqChainCodeRegister);
            System.out.println(JSONObject.toJSONString(resChainCodeRegister, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 链码事件查询
     *
     * @return
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
     * 链码事件取消接口
     *
     * @return
     */
    @Test
    public void eventRemove() {
        try {
            initConfig();
            ReqChainCodeCancel reqChainCodeCancel = new ReqChainCodeCancel();
            reqChainCodeCancel.setEventId("2e1da82881e7b519147c1612d261f19ce4d44cdee19bf7c2ff239a2a8efa7bdd");
            ResChainCodeCancel resChainCodeCancel = CitaClient.eventRemove(reqChainCodeCancel);
            System.out.println(JSONObject.toJSONString(resChainCodeCancel, SerializerFeature.PrettyFormat));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
