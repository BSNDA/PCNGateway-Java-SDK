package com.bsnbase.sdk.client.fabric.test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

import com.bsnbase.sdk.client.fabric.service.ChainCodeService;
import com.bsnbase.sdk.client.fabric.service.NodeService;
import com.bsnbase.sdk.client.fabric.service.TransactionService;
import com.bsnbase.sdk.client.fabric.service.UserService;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.fabric.ReqChainCodeRegister;
import com.bsnbase.sdk.entity.req.fabric.ReqChainCodeRemove;
import com.bsnbase.sdk.entity.req.fabric.ReqGetBlockInformation;
import com.bsnbase.sdk.entity.req.fabric.ReqGetTransaction;
import com.bsnbase.sdk.entity.req.fabric.ReqKeyEscrow;
import com.bsnbase.sdk.entity.req.fabric.ReqKeyEscrowEnroll;
import com.bsnbase.sdk.entity.req.fabric.ReqUserRegister;
import com.bsnbase.sdk.util.exception.GlobalException;

public class FabricTest {
	
	
	//初始化config    R1
//	public void initConfig() throws IOException {
//    	Config config = new Config();
//        config.setAppCode("app0001202004161020152918451");
//        config.setUserCode("USER0001202004151958010871292");
//        config.setApi("http://192.168.1.43:17502");
//        config.setCert("cert/bsn_gateway_https.crt");
//        config.setPrk("cert/private_Key.pem");
//        config.setPuk("cert/public_key.pem");
//        config.setMspDir("D:/test");
//        config.initConfig(config);
//	}

	//初始化config    SM2
	public void initConfig() throws IOException {
		Config config = new Config();
		config.setAppCode("app0001202004161020152918451");
		config.setUserCode("USER0001202004151958010871292");
		config.setApi("http://192.168.1.41:17502");
		config.setCert("cert/bsn_gateway_https.crt");
		config.setPrk("cert/private_SM2_Key.pem");
		config.setPuk("cert/public_SM2_Key.pem");
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
		} catch (IOException e) {
			e.printStackTrace();
			return ;
		}
    	ReqUserRegister register = new ReqUserRegister();
    	register.setName("test25");
    	register.setSecret("123456");
    	//用户注册
    	try {
			UserService.userRegister(register);
		} catch(GlobalException g) {
			g.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
			return;
		}
    }
    
    /**
     * 密钥托管模式交易处理
     * @return
     * @throws IOException
     */
    @Test
    public void reqChainCode() {
    	try {
			initConfig();
		} catch (IOException e) {
			e.printStackTrace();
			return ;
		}
    	ReqKeyEscrow reqkey = new ReqKeyEscrow();
        String[] args = {"{\"baseKey\":\"test2020041\",\"baseValue\":\"this is string \"}"};
        reqkey.setArgs(args);
        reqkey.setFuncName("set");
        reqkey.setChainCode("cc_app0001202004161020152918451_00");
        reqkey.setUserName("test21");
        reqkey.setTransientData(null);
        try {
			TransactionService.reqChainCode(reqkey);
		} catch(GlobalException g) {
			g.printStackTrace();
			return ;
		} catch (IOException e) {
			e.printStackTrace();
			return ;
		}
    }
    
    /**
     * 用户非托管模式用户证书登记
     */
    @Test
    public void userEnroll() {
    	try {
			initConfig();
		} catch(GlobalException g) {
			g.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			return ;
		}
    	ReqKeyEscrowEnroll r = new ReqKeyEscrowEnroll(); 
  	  	r.setName("test23");
  	  	r.setSecret("123456");
  	  try {
			UserService.userEnroll(r);
		} catch(GlobalException g) {
			g.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			return ;
		}
    }
    
    /**
     * 密钥非托管模式交易
     */
    @Test
    public void nodeTrans() {
    	try {
			initConfig();
		} catch (IOException e) {
			e.printStackTrace();
			return ;
		}
    	ReqKeyEscrow reqkey = new ReqKeyEscrow();
        String[] args = {"{\"baseKey\":\"test2020068\",\"baseValue\":\"this is string \"}"};
        reqkey.setArgs(args);
        reqkey.setFuncName("set");
        reqkey.setChainCode("cc_app0001202007271538152051987_01");
        reqkey.setUserName("test23");
        //reqkey.setTransientData(null);
    	try {
			TransactionService.nodeTrans(reqkey);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch(GlobalException g) {
			g.printStackTrace();
		}
    }

    @Test
    public void getTransaction() {
		try {
			initConfig();
		} catch (IOException e) {
			e.printStackTrace();
			return ;
		}
    	ReqGetTransaction reqData = new ReqGetTransaction();
        reqData.setTxId("ff316d99147cb3a5db617ad6a794c322d43ffc2f9afbadc50ba2c67f6dafeda0");
        try {
        	NodeService.getTransaction(reqData);
		} catch(GlobalException g) {
			g.printStackTrace();
			return ;
		}
        
    }
    
    /**
     * 获取最新账本信息
     * @return
     */
    @Test
    public void getBlockInfo() {
		try {
			initConfig();
		} catch (IOException e) {
			e.printStackTrace();
			return ;
		}
		ReqGetBlockInformation rbf = new ReqGetBlockInformation();
		rbf.setTxId("ff316d99147cb3a5db617ad6a794c322d43ffc2f9afbadc50ba2c67f6dafeda0");

		try {
			NodeService.getBlockInfo(rbf);
		} catch(GlobalException g) {
			g.printStackTrace();
			return ;
		}

    }
    
    /**
     * 获取块信息
     */
    @Test
    public void getLedgerInfo() {
		try {
			initConfig();
		} catch (IOException e) {
			e.printStackTrace();
			return ;
		}
		NodeService.getLedgerInfo();
    }
    
    /**
     * 链码事件注册
     */
    @Test
    public void eventRegister() {
		try {
			initConfig();
		} catch (IOException e) {
			e.printStackTrace();
			return ;
		}
    	ReqChainCodeRegister data = new ReqChainCodeRegister();
    	data.setAttachArgs("name=张三&age=20");
    	data.setChainCode("cc_app0001202007291443281737652_01");
    	data.setEventKey("test");
    	data.setNotifyUrl("http://192.168.6.128:8080/api/event/notifyUrl");
    	try {
			ChainCodeService.eventRegister(data);
		} catch (GlobalException e) {
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
		} catch (IOException e) {
			e.printStackTrace();
			return ;
		}
    	try {
			ChainCodeService.eventQuery();
		} catch (GlobalException e) {
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
		} catch (IOException e) {
			e.printStackTrace();
			return ;
		}
    	 ReqChainCodeRemove reqChainCodeRemove = new ReqChainCodeRemove();
         reqChainCodeRemove.setEventId("ad59c71e3dbc418e8aef8b101addd0cb");
         try {
			ChainCodeService.eventRemove(reqChainCodeRemove);
		} catch (GlobalException g) {
			g.printStackTrace();
		}
    }

	public static void main(String[] args) {
		String a="-----BEGIN CERTIFICATE-----\nMIIC3jCCAoOgAwIBAgIIa8i+nmc9TbswDAYIKoEcz1UBg3UFADCBjDELMAkGA1UE\\nBhMCQ04xEDAOBgNVBAgMB0JlaWppbmcxEDAOBgNVBAcMB0JlaWppbmcxDDAKBgNV\\nBAoMA0JzbjETMBEGA1UECwwKZ21vcmdibm9kZTEQMA4GA1UECwwHYnNuYmFzZTEM\\nMAoGA1UECwwDY29tMRYwFAYDVQQDDA1nbW9yZ2Jub2RlaWNhMCAXDTIwMDcyODA3\\nMTg1N1oYDzIxMDAwNzI4MDcxODU3WjAuMSwwKgYDVQQDDCN0ZXN0MjFAYXBwMDAw\\nMTIwMjAwNzI3MTUzODE1MjA1MTk4NzBZMBMGByqGSM49AgEGCCqBHM9VAYItA0IA\\nBIMNNxPd6X748oWIVEtPIGxiwmU0oU1EPDd52L/N+MGrjuNP9t09VuBltp8gcY/O\\nOmSTO4qPerK964GrY2aTm86jggEmMIIBIjAMBgNVHRMBAf8EAjAAMA4GA1UdDwEB\\n/wQEAwIHgDAdBgNVHQ4EFgQUMNHqfL4UFzQ7LeGndK1SMEz6qRowHwYDVR0jBBgw\\nFoAUrwoMZProCnyMQHRuW71NpImazPQwJAYDVR0RBB0wG4IZY2EuZ21vcmdibm9k\\nZS5ic25iYXNlLmNvbTCBmwYIKgMEBQYHCAEEgY57ImF0dHJzIjp7ImhmLkFmZmls\\naWF0aW9uIjoiZ21vcmdibm9kZS5ic25iYXNlLmNvbSIsImhmLkVucm9sbG1lbnRJ\\nRCI6InRlc3QyMUBhcHAwMDAxMjAyMDA3MjcxNTM4MTUyMDUxOTg3IiwiaGYuVHlw\\nZSI6ImNsaWVudCIsInJvbGUiOiJ1c2VyIn19MAwGCCqBHM9VAYN1BQADRwAwRAIg\\nJ0qerNYbY3ZP0ED6RBOtFHOkqSqiHa+VZQVllcmNE8oCID0PAGwCKFusyV2kpIZ3\\n0YyVyXSxvx8rW6+qerHjxNK0\\n-----END CERTIFICATE-----\\n";
		System.out.println(a);
	}
}
