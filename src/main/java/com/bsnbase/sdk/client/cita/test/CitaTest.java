package com.bsnbase.sdk.client.cita.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bsnbase.sdk.client.cita.CitaClient;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.cita.*;
import com.bsnbase.sdk.entity.res.cita.*;
import com.bsnbase.sdk.util.common.Common;
import org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32;
import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CitaTest {


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

	//初始化config
	public void initConfig() throws IOException {
		Config config = new Config();
		config.setAppCode("app0001202010290953308225341");
		config.setUserCode("USER0001202007091921188284916");
		config.setApi("http://192.168.1.43:17502");
		config.setPrk(Common.readFile("cert/private_key.pem"));
		config.setPuk(Common.readFile("cert/public_key.pem"));
		config.setMspDir("D:/test");
      //config.setTestServerIdn(true);
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
			register.setUserId("test56");
			ResUserRegister resUserRegister = CitaClient.userRegister(register);
			System.out.println(JSONObject.toJSONString(resUserRegister, SerializerFeature.PrettyFormat));
		} catch(Exception e) {
			e.printStackTrace();
			return;
		}
    }
    
    /**
     * 密钥托管模式交易处理
     */
    @Test
    public void reqChainCode() {
    	try {
			initConfig();
			ReqKeyEscrow reqkey = new ReqKeyEscrow();
			reqkey.setContractName("CitaBsnBaseContract");
			reqkey.setFuncName("insert");
			List<Object> list=new ArrayList<>();
			list.add("test");
			list.add("1111");
			reqkey.setFuncParam(JSON.toJSONString(list));
			reqkey.setUserId("test55");
			ResKeyEscrow resKeyEscrow = CitaClient.reqChainCode(reqkey);
			System.out.println(JSONObject.toJSONString(resKeyEscrow, SerializerFeature.PrettyFormat));
		} catch (Exception e) {
			e.printStackTrace();
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
			reqGetTxReceiptByTxHash.setTxHash("0xe71c2966e576aab79f4f3a570c532d81daa38b3c38c05f2a281b5e59e3ecf13d");
			ResGetTxReceiptByTxHash resGetTxReceiptByTxHash = CitaClient.getTxReceiptByTxHash(reqGetTxReceiptByTxHash);
			System.out.println(JSONObject.toJSONString(resGetTxReceiptByTxHash, SerializerFeature.PrettyFormat));
		} catch(Exception e) {
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
		} catch(Exception e) {
			e.printStackTrace();
		}
        
    }
    
    /**
     * 获取块信息
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
		} catch(Exception e) {
			e.printStackTrace();
		}

    }

	/**
	 * 获取应用内的块高接口
	 * @return
	 */
	@Test
	public void getBlockHeight() {
		try {
			initConfig();
			ResGetBlockHeight resGetBlockHeight = CitaClient.getBlockHeight();
			System.out.println(JSONObject.toJSONString(resGetBlockHeight, SerializerFeature.PrettyFormat));
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
