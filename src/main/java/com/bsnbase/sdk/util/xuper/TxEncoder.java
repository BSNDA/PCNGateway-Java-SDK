package com.bsnbase.sdk.util.xuper;

import com.bsnbase.sdk.util.common.Common;
import com.bsnbase.sdk.util.pb.XchainOuterClass;
import com.google.gson.*;
import com.google.protobuf.ByteString;
import org.bouncycastle.util.encoders.Base64;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

public class TxEncoder {
    private static final Gson gson = new GsonBuilder()
            .registerTypeHierarchyAdapter(ByteString.class, new PbByteStringAdapter())
            .disableHtmlEscaping()
            .create();
    private StringBuilder buffer;

    TxEncoder() {
        buffer = new StringBuilder();
    }

    public  static byte[] makeTxDigest(XchainOuterClass.Transaction tx) {
        TxEncoder enc = new TxEncoder();
        return Common.doubleSha256(enc.encodeTx(tx, false));
    }

    public   static byte[] makeTxID(XchainOuterClass.Transaction tx) {
        TxEncoder enc = new TxEncoder();
        return Common.doubleSha256(enc.encodeTx(tx, true));
    }

    private void encode(Object obj) {
        String s = gson.toJson(obj);
        buffer.append(s);
        buffer.append("\n");
    }

    private void encode(ByteString bs) {
        if (!bs.isEmpty()) {
            encode(Base64.toBase64String(bs.toByteArray()));
        }
    }

    byte[] encodeTx(XchainOuterClass.Transaction tx, boolean needSign) {
            String a=null;
            encode(a);

        for (XchainOuterClass.TxInput input : tx.getTxInputsList()) {
            encode(input.getRefTxid());
            encode(input.getRefOffset());
            encode(input.getFromAddr());
            encode(input.getAmount());
            encode(input.getFrozenHeight());
        }
        encode(tx.getDesc());
        encode(tx.getNonce());
        encode(tx.getTimestamp());
        encode(tx.getVersion());
        for (XchainOuterClass.TxInputExt input : tx.getTxInputsExtList()) {
            encode(input.getBucket());
            encode(input.getKey());
            encode(input.getRefTxid());
            encode(input.getRefOffset());
        }
        for (XchainOuterClass.TxOutputExt output : tx.getTxOutputsExtList()) {
            encode(output.getBucket());
            encode(output.getKey());
            encode(output.getValue());
        }
        Object[] invokes = null;
        if (tx.getContractRequestsCount() != 0) {
            invokes = new Object[tx.getContractRequestsCount()];
            for (int i = 0; i < tx.getContractRequestsCount(); i++) {
                invokes[i] = InvokeRequestBean.create(tx.getContractRequests(i));
            }
        }
        encode(invokes);

        encode(tx.getInitiator());
        List<String> list=null;

        if(tx.getAuthRequireList().size()>0){
            encode(tx.getAuthRequireList());
        }
        encode(list);

        if (needSign) {
            Object[] sigs = null;
            if (tx.getInitiatorSignsCount() != 0) {
                sigs = new Object[tx.getInitiatorSignsCount()];
                for (int i = 0; i < tx.getInitiatorSignsCount(); i++) {
                    sigs[i] = SignatureInfoBean.create(tx.getInitiatorSigns(i));
                }
                encode(sigs);
            }

            sigs=null;
            if (tx.getAuthRequireSignsCount() != 0) {
                sigs = new Object[tx.getAuthRequireSignsCount()];

            }
            for (int i = 0; i < tx.getAuthRequireSignsCount(); i++) {
                sigs[i] = SignatureInfoBean.create(tx.getAuthRequireSigns(i));
            }
            encode(sigs);
            sigs=null;
            if (tx.hasXuperSign()) {
                encode(sigs);
            }
        }
        encode(tx.getCoinbase());
        encode(tx.getAutogen());
        return buffer.toString().getBytes();
    }

    private static class PbByteStringAdapter implements JsonSerializer<ByteString> {
        public JsonElement serialize(ByteString src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(Base64.toBase64String(src.toByteArray()));
        }
    }

    private static class InvokeRequestBean {
        static Object create(XchainOuterClass.InvokeRequest pb) {
            LinkedHashMap<String, Object> m = new LinkedHashMap<>();
            if (!pb.getMethodName().isEmpty()) {
                m.put("module_name", pb.getModuleName());
            }
            if (!pb.getContractName().isEmpty()) {
                m.put("contract_name", pb.getContractName());
            }
            if (!pb.getMethodName().isEmpty()) {
                m.put("method_name", pb.getMethodName());
            }
            if (pb.getArgsCount() != 0) {
                TreeMap<String, ByteString> margs = new TreeMap<>();
                margs.putAll(pb.getArgsMap());
                m.put("args", margs);
            }
            if (pb.getResourceLimitsCount() != 0) {
                Object[] resource_limits = new Object[pb.getResourceLimitsCount()];
                for (int i = 0; i < pb.getResourceLimitsCount(); i++) {
                    resource_limits[i] = ResourceLimitBean.create(pb.getResourceLimits(i));
                }
                m.put("resource_limits", resource_limits);
            }
            if (!pb.getAmount().isEmpty()) {
                m.put("amount", pb.getAmount());
            }
            return m;
        }
    }

    private static class ResourceLimitBean {
        static Object create(XchainOuterClass.ResourceLimit pb) {
            LinkedHashMap<String, Object> m = new LinkedHashMap<>();
            if (pb.getType().getNumber() != 0) {
                m.put("type", pb.getType().getNumber());
            }
            if (pb.getLimit() != 0) {
                m.put("limit", pb.getLimit());
            }
            return m;
        }
    }

    private static class SignatureInfoBean {
        static Object create(XchainOuterClass.SignatureInfo pb) {
            LinkedHashMap<String, Object> m = new LinkedHashMap<>();
            if (!pb.getPublicKey().isEmpty()) {
                m.put("PublicKey", pb.getPublicKey());
            }
            if (!pb.getSign().isEmpty()) {
                m.put("Sign", pb.getSign());
            }
            return m;
        }
    }
}
