package com.bsnbase.sdk.util.trans;

import com.bsnbase.sdk.client.fiscobcos.service.NodeService;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.fiscobcos.ReqTrans;
import com.bsnbase.sdk.entity.req.fiscobcos.ReqTransData;
import com.bsnbase.sdk.entity.res.fiscobcos.ResGetBlockHeight;
import com.bsnbase.sdk.util.common.Common;
import com.bsnbase.sdk.util.enums.AlgorithmTypeEnum;
import com.bsnbase.sdk.util.enums.ResultInfoEnum;
import com.bsnbase.sdk.util.exception.GlobalException;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.ECKeyPair;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.tx.TransactionAssembleManager;

import java.io.File;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

public class FiscoTransUtil {

    public static ReqTrans buildTrans(ReqTransData reqTransData) throws Exception {
        //0:k1 1:sm2
        EncryptType encryptType = getEncryptTypeByAlgorithmType(Config.config.getAppInfo().getAlgorithmType());
        String abi = reqTransData.getContractAbi();
        String funcName = reqTransData.getFuncName();
        int groupId = Integer.parseInt(Config.config.getAppInfo().getChannelId());
        String contractAddress =reqTransData.getContractAddress();
        List<Object> funcParam = reqTransData.getFuncParam();
        ResGetBlockHeight resGetBlockHeight= NodeService.getBlockHeight();
        if(Objects.isNull(resGetBlockHeight)){
            throw new GlobalException(ResultInfoEnum.BLOCK_HEIGHT_ERROR);
        }
        Integer blockHeight= Integer.valueOf(resGetBlockHeight.getData());
        BigInteger blockLimit = BigInteger.valueOf(blockHeight+100);
        String signedStr="";
        String encodeTransaction = TransactionAssembleManager.transactionAssembleForMethodInvoke(abi,groupId, blockLimit, contractAddress, funcName, funcParam);
        if(("select".equals(funcName.toLowerCase()))){
            signedStr=encodeTransaction;
        }else{
            ECKeyPair ecKeyPair=loadKeyPair(reqTransData.getUserName(),Config.config.getAppCode(),Config.config.getMspDir());
            signedStr = TransactionAssembleManager.signMessageByEncryptType(encodeTransaction, ecKeyPair, encryptType.getEncryptType());
        }
        ReqTrans reqTrans = new ReqTrans();
        reqTrans.setTransData(signedStr);
        reqTrans.setContractName(reqTransData.getContractName());
        return reqTrans;

    }

    private static ECKeyPair loadKeyPair(String userName, String appCode,String path ) throws Exception {
        ECKeyPair keyPair;
        String priFilePath = path +"/"+ userName + "@" + appCode+"_prk.pem";
        String pubFilePath = path +"/"+ userName + "@" + appCode+"_puk.pem";
        Boolean isExists = findFile(priFilePath)&&findFile(pubFilePath);
        if (isExists) {
            String privateKey = Common.readLocalFile(priFilePath).replaceAll("\n","");
            String publicKey = Common.readLocalFile(pubFilePath).replaceAll("\n","");
            keyPair=new ECKeyPair(new BigInteger(privateKey),new BigInteger(publicKey));
        } else {
            Credentials genCredentials = GenCredential.create();
            keyPair=genCredentials.getEcKeyPair();
            saveKey(keyPair.getPrivateKey(), priFilePath);
            saveKey(keyPair.getPublicKey(), pubFilePath);
        }
        return keyPair;

    }

    private static void saveKey(BigInteger key, String pubFilePath) throws Exception {
        File file = new File( pubFilePath);
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
            randomAccessFile.write(key.toString().getBytes());
        }
    }

    private static EncryptType getEncryptTypeByAlgorithmType(Integer algorithmType) {
        EncryptType encryptType = null;
        AlgorithmTypeEnum algorithmTypeEnum = AlgorithmTypeEnum.fromAlgorithmTypeEnum(algorithmType);
        switch (algorithmTypeEnum) {
            case AppAlgorithmType_SM2:
                encryptType = new EncryptType(1);
                break;
            case AppAlgorithmType_K1:
                encryptType = new EncryptType(0);
                break;
            default:
        }
        if (Objects.isNull(encryptType)) {
            throw new GlobalException(ResultInfoEnum.ALGORITHM_TYPE_ERROR);
        }
        return encryptType;
    }


    public static boolean findFile(String pathUrl) throws Exception {
        File file = new File( pathUrl);
        if (file.isFile() && file.exists()) {
            return true;
        }
        return false;
    }
}
