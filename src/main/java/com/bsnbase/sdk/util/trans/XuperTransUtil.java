package com.bsnbase.sdk.util.trans;

import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.xuperChain.ReqTrans;
import com.bsnbase.sdk.entity.req.xuperChain.ReqTransData;
import com.bsnbase.sdk.entity.resp.xuperChain.ResKeyEscrowNo;
import com.bsnbase.sdk.util.common.Common;
import com.bsnbase.sdk.util.sign.Sm2SignUtil;
import com.bsnbase.sdk.util.xuper.TxEncoder;
import com.google.gson.Gson;
import com.google.protobuf.ByteString;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;
import java.security.PublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class XuperTransUtil {
    static public final int txVersion = 1;

    static public String newNonce() {
        return String.valueOf(System.nanoTime()) + (int) (Math.random() * 100000000);
    }


    public static ReqTrans buildRequest(ReqTransData reqTransData) {
        com.baidu.xuper.pb.XchainOuterClass.InvokeRequest.Builder invokeRequestBuilder = com.baidu.xuper.pb.XchainOuterClass.InvokeRequest.newBuilder();
        if (reqTransData.getContractName() != null && reqTransData.getMethodName() != null && reqTransData.getArgs() != null) {
            invokeRequestBuilder.setModuleName("wasm")
                    .setMethodName(reqTransData.getMethodName())
                    .setContractName(reqTransData.getContractName());
            Map<String, ByteString> paramArgs = new HashMap<>();
            for (Map.Entry<String, byte[]> entry : reqTransData.getArgs().entrySet()) {
                paramArgs.put(entry.getKey(), ByteString.copyFrom(entry.getValue()));
            }
            invokeRequestBuilder.putAllArgs(paramArgs);
        }

        com.baidu.xuper.pb.XchainOuterClass.InvokeRequest invokeRequest = invokeRequestBuilder.build();


        ReqTrans reqTrans = new ReqTrans();
        reqTrans.setFlag(0);
        reqTrans.setInitiator(reqTransData.getInitiator());
        reqTrans.setTransData(Base64.getEncoder().encodeToString(invokeRequest.toByteArray()));
        return reqTrans;
    }

    public static ReqTrans buildTransaction(ReqTransData reqTransData, ResKeyEscrowNo resKeyEscrowNo) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(resKeyEscrowNo.getPreExecRes());

        com.baidu.xuper.pb.XchainOuterClass.InvokeResponse invokeResponse = com.baidu.xuper.pb.XchainOuterClass.InvokeResponse.parseFrom(bytes);


        com.baidu.xuper.pb.XchainOuterClass.Transaction.Builder txBuilder = com.baidu.xuper.pb.XchainOuterClass.Transaction.newBuilder()
                .setDesc(ByteString.copyFromUtf8("sdk contract transaction"))
                .setVersion(txVersion)
                .setCoinbase(false)
                .setTimestamp(System.nanoTime())
                .setInitiator(reqTransData.getInitiator())
                .setNonce(newNonce());

        // add utxos generated from contract
        txBuilder.addAllTxInputs(invokeResponse.getUtxoInputsList());
        txBuilder.addAllTxOutputs(invokeResponse.getUtxoOutputsList());

        // add xmodel inputs and outputs
        txBuilder.addAllTxInputsExt(invokeResponse.getInputsList());
        txBuilder.addAllTxOutputsExt(invokeResponse.getOutputsList());
        txBuilder.addAllContractRequests(invokeResponse.getRequestsList());
        byte[] hash = TxEncoder.makeTxDigest(txBuilder.build());


        byte[] sig = Sm2SignUtil.xuperSignature(Config.config.getPrk(), hash);
        com.baidu.xuper.pb.XchainOuterClass.SignatureInfo siginfo = com.baidu.xuper.pb.XchainOuterClass.SignatureInfo.newBuilder()
                .setPublicKey(createJSONPublicKey(Config.config.getPuk()))
                .setSign(ByteString.copyFrom(sig))
                .build();


        txBuilder.addInitiatorSigns(siginfo);

        byte[] txid = TxEncoder.makeTxID(txBuilder.build());

        txBuilder.setTxid(ByteString.copyFrom(txid));

        com.baidu.xuper.pb.XchainOuterClass.Transaction transaction = txBuilder.build();

        ReqTrans reqTrans = new ReqTrans();
        reqTrans.setFlag(1);
        reqTrans.setInitiator(reqTransData.getInitiator());
        reqTrans.setTransData(Base64.getEncoder().encodeToString(transaction.toByteArray()));

        return reqTrans;

    }


    static private String createJSONPublicKey(String pemCertificateString) throws Exception {
        PublicKey publicKey = Common.loadPublicKey(pemCertificateString, "EC");
        ECPublicKey ecPublicKey = (ECPublicKey) publicKey;
        ECPoint ecPoint1 = ecPublicKey.getQ();
        BigInteger x = ecPoint1.getAffineXCoord().toBigInteger();
        BigInteger y = ecPoint1.getAffineYCoord().toBigInteger();
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("Curvname", "SM2-P-256");
        m.put("X", x);
        m.put("Y", y);
        Gson gson = new Gson();
        return gson.toJson(m);
    }


}