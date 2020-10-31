package com.bsnbase.sdk.client.fabric.test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bsnbase.sdk.client.fabric.FabricClient;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.fabric.*;
import com.bsnbase.sdk.entity.res.fabric.*;
import com.bsnbase.sdk.util.common.Common;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class FabricTest {


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
		config.setAppCode("app0003202008100054119967051");
		config.setUserCode("USER0003202005291706487822713");
		config.setApi("http://192.168.1.43:17502");
		config.setPrk(Common.readLocalFile("D:/private_key.pem"));
		config.setPuk(Common.readLocalFile("D:/public_key.pem"));
      //config.setTestServerIdn(true);
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
			register.setName("test54");
			register.setSecret("123456");
			ResUserRegister resUserRegister = FabricClient.userRegister(register);
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
			String[] args = {"{\"baseKey\":\"test2020050\",\"baseValue\":\"this is string \"}"};
			reqkey.setArgs(args);
			reqkey.setFuncName("set");
			reqkey.setChainCode("cc_app0001202007311431220086431_01");
			reqkey.setUserName("test28");
			ResKeyEscrow resKeyEscrow = FabricClient.reqChainCode(reqkey);
			System.out.println(JSONObject.toJSONString(resKeyEscrow, SerializerFeature.PrettyFormat));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 公钥上传模式用户证书登记
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
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 公钥上传模式交易
     */
    @Test
    public void nodeTrans() {
    	try {
			initConfig();
			ReqKeyEscrow reqkey = new ReqKeyEscrow();
			String[] args = {"{\"baseKey\":\"test20200650\",\"baseValue\":\"this is string \"}"};
			reqkey.setArgs(args);
			reqkey.setFuncName("set");
			reqkey.setChainCode("cc_app0001202007311431220086431_01");
			reqkey.setUserName("test54");
			ResKeyEscrowNo resKeyEscrowNo = FabricClient.nodeTrans(reqkey);
			System.out.println(JSONObject.toJSONString(resKeyEscrowNo, SerializerFeature.PrettyFormat));
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
			ReqGetTransaction reqData = new ReqGetTransaction();
			reqData.setTxId("aa2b7510d67e5317cb3d290c3c88d1216715d2b0ba9c7d8ce5a65801c50ca967");
			ResGetTransaction transaction = FabricClient.getTransaction(reqData);
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
			rbf.setTxId("aa2b7510d67e5317cb3d290c3c88d1216715d2b0ba9c7d8ce5a65801c50ca967");
			ResGetBlockInformation blockInfo = FabricClient.getBlockInfo(rbf);
			System.out.println(JSONObject.toJSONString(blockInfo, SerializerFeature.PrettyFormat));
		} catch(Exception e) {
			e.printStackTrace();
		}

    }
    
    /**
     * 获取最新账本信息
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
     * 链码事件注册
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
     * 链码事件查询
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
     * 链码事件注销
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
