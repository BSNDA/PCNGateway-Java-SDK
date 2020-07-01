package com.bsnbase.sdk.client.fabric.test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

import com.bsnbase.sdk.client.fabric.service.ChainCodeService;
import com.bsnbase.sdk.client.fabric.service.NodeService;
import com.bsnbase.sdk.client.fabric.service.TransactionService;
import com.bsnbase.sdk.client.fabric.service.UserService;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.ReqChainCodeRegister;
import com.bsnbase.sdk.entity.req.ReqChainCodeRemove;
import com.bsnbase.sdk.entity.req.ReqGetBlockInformation;
import com.bsnbase.sdk.entity.req.ReqGetTransaction;
import com.bsnbase.sdk.entity.req.ReqKeyEscrow;
import com.bsnbase.sdk.entity.req.ReqKeyEscrowEnroll;
import com.bsnbase.sdk.entity.req.ReqUserRegister;
import com.bsnbase.sdk.util.exception.GlobalException;

public class FabricTest {
	
	
	//Initialzie config
	public void initConfig() throws IOException {
    	Config config = new Config();
        config.setAppCode("app0001202004161020152918451");
        config.setUserCode("USER0001202004151958010871292");
        config.setApi("http://192.168.1.43:17502");
        config.setCert("cert/bsn_gateway_https.crt");
        config.setPrk("cert/private_Key.pem");
        config.setPuk("cert/public_Key.pem");
        config.setMspDir("D:/test");
        config.initConfig(config);
	}
	
	 /**
     * user registration
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
    	register.setName("test19");
    	register.setSecret("123456");
    	//user registration
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
     * transaction processing under key-trust mode 
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
        reqkey.setUserName("test18");
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
     * user cert registration under Public-Key-Upload Mode 
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
  	  	r.setName("test19");
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
     * transaction under Public-Key-Upload Mode 
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
        reqkey.setChainCode("cc_app0001202004161020152918451_00");
        reqkey.setUserName("test19");
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
     * get the latest ledger data 
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
		rbf.setBlockNumber(22);

		try {
			NodeService.getBlockInfo(rbf);
		} catch(GlobalException g) {
			g.printStackTrace();
			return ;
		}

    }
    
    /**
     * get block data 
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
     * register an event chaincode
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
    	data.setAttachArgs("name=example&age=20");
    	data.setChainCode("cc_bsn_test_00");
    	data.setEventKey("test01");
    	data.setNotifyUrl("http://192.168.6.128:8080/api/event/notifyUrl");
    	try {
			ChainCodeService.eventRegister(data);
		} catch (GlobalException e) {
			e.printStackTrace();
		}
    }

    /**
     * event chaincode query 
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
     * event chaincode logout
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
         reqChainCodeRemove.setEventId("bd3391deedbe44a7ad5b7f80ce59abfa");
         try {
			ChainCodeService.eventRemove(reqChainCodeRemove);
		} catch (GlobalException g) {
			g.printStackTrace();
		}
    }
}
