package com.bsnbase.sdk.util.pb;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * Xchain is the main interfaces
 * </pre>
 */
@javax.annotation.Generated(
        value = "by gRPC proto compiler (version 1.25.0)",
        comments = "Source: xchain.proto")
public final class XchainGrpc {

    public static final String SERVICE_NAME = "pb.Xchain";
    private static final int METHODID_POST_TX = 0;
    private static final int METHODID_QUERY_ACL = 1;
    private static final int METHODID_GET_ACCOUNT_CONTRACTS = 2;
    private static final int METHODID_QUERY_TX = 3;
    private static final int METHODID_GET_BALANCE = 4;
    private static final int METHODID_GET_BALANCE_DETAIL = 5;
    private static final int METHODID_GET_FROZEN_BALANCE = 6;
    private static final int METHODID_GET_BLOCK = 7;
    private static final int METHODID_GET_BLOCK_BY_HEIGHT = 8;
    private static final int METHODID_GET_BLOCK_CHAIN_STATUS = 9;
    private static final int METHODID_GET_BLOCK_CHAINS = 10;
    private static final int METHODID_GET_SYSTEM_STATUS = 11;
    private static final int METHODID_GET_NET_URL = 12;
    private static final int METHODID_SELECT_UTXO = 13;
    private static final int METHODID_PRE_EXEC_WITH_SELECT_UTXO = 14;
    private static final int METHODID_DEPLOY_NATIVE_CODE = 15;
    private static final int METHODID_NATIVE_CODE_STATUS = 16;
    private static final int METHODID_DPOS_CANDIDATES = 17;
    private static final int METHODID_DPOS_NOMINATE_RECORDS = 18;
    private static final int METHODID_DPOS_NOMINEE_RECORDS = 19;
    private static final int METHODID_DPOS_VOTE_RECORDS = 20;
    private static final int METHODID_DPOS_VOTED_RECORDS = 21;
    private static final int METHODID_DPOS_CHECK_RESULTS = 22;
    private static final int METHODID_DPOS_STATUS = 23;
    private static final int METHODID_GET_ACCOUNT_BY_AK = 24;
    private static final int METHODID_PRE_EXEC = 25;
    // Static method descriptors that strictly reflect the proto.
    private static volatile io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus,
            com.bsnbase.sdk.util.pb.XchainOuterClass.CommonReply> getPostTxMethod;
    private static volatile io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.AclStatus,
            com.bsnbase.sdk.util.pb.XchainOuterClass.AclStatus> getQueryACLMethod;
    private static volatile io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.GetAccountContractsRequest,
            com.bsnbase.sdk.util.pb.XchainOuterClass.GetAccountContractsResponse> getGetAccountContractsMethod;
    private static volatile io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus,
            com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus> getQueryTxMethod;
    private static volatile io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus,
            com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus> getGetBalanceMethod;
    private static volatile io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.AddressBalanceStatus,
            com.bsnbase.sdk.util.pb.XchainOuterClass.AddressBalanceStatus> getGetBalanceDetailMethod;
    private static volatile io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus,
            com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus> getGetFrozenBalanceMethod;
    private static volatile io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.BlockID,
            com.bsnbase.sdk.util.pb.XchainOuterClass.Block> getGetBlockMethod;
    private static volatile io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.BlockHeight,
            com.bsnbase.sdk.util.pb.XchainOuterClass.Block> getGetBlockByHeightMethod;
    private static volatile io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.BCStatus,
            com.bsnbase.sdk.util.pb.XchainOuterClass.BCStatus> getGetBlockChainStatusMethod;
    private static volatile io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn,
            com.bsnbase.sdk.util.pb.XchainOuterClass.BlockChains> getGetBlockChainsMethod;
    private static volatile io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn,
            com.bsnbase.sdk.util.pb.XchainOuterClass.SystemsStatusReply> getGetSystemStatusMethod;
    private static volatile io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn,
            com.bsnbase.sdk.util.pb.XchainOuterClass.RawUrl> getGetNetURLMethod;
    private static volatile io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.UtxoInput,
            com.bsnbase.sdk.util.pb.XchainOuterClass.UtxoOutput> getSelectUTXOMethod;
    private static volatile io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.PreExecWithSelectUTXORequest,
            com.bsnbase.sdk.util.pb.XchainOuterClass.PreExecWithSelectUTXOResponse> getPreExecWithSelectUTXOMethod;
    private static volatile io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.DeployNativeCodeRequest,
            com.bsnbase.sdk.util.pb.XchainOuterClass.DeployNativeCodeResponse> getDeployNativeCodeMethod;
    private static volatile io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.NativeCodeStatusRequest,
            com.bsnbase.sdk.util.pb.XchainOuterClass.NativeCodeStatusResponse> getNativeCodeStatusMethod;
    private static volatile io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.DposCandidatesRequest,
            com.bsnbase.sdk.util.pb.XchainOuterClass.DposCandidatesResponse> getDposCandidatesMethod;
    private static volatile io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.DposNominateRecordsRequest,
            com.bsnbase.sdk.util.pb.XchainOuterClass.DposNominateRecordsResponse> getDposNominateRecordsMethod;
    private static volatile io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.DposNomineeRecordsRequest,
            com.bsnbase.sdk.util.pb.XchainOuterClass.DposNomineeRecordsResponse> getDposNomineeRecordsMethod;
    private static volatile io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.DposVoteRecordsRequest,
            com.bsnbase.sdk.util.pb.XchainOuterClass.DposVoteRecordsResponse> getDposVoteRecordsMethod;
    private static volatile io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.DposVotedRecordsRequest,
            com.bsnbase.sdk.util.pb.XchainOuterClass.DposVotedRecordsResponse> getDposVotedRecordsMethod;
    private static volatile io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.DposCheckResultsRequest,
            com.bsnbase.sdk.util.pb.XchainOuterClass.DposCheckResultsResponse> getDposCheckResultsMethod;
    private static volatile io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.DposStatusRequest,
            com.bsnbase.sdk.util.pb.XchainOuterClass.DposStatusResponse> getDposStatusMethod;
    private static volatile io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.AK2AccountRequest,
            com.bsnbase.sdk.util.pb.XchainOuterClass.AK2AccountResponse> getGetAccountByAKMethod;
    private static volatile io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.InvokeRPCRequest,
            com.bsnbase.sdk.util.pb.XchainOuterClass.InvokeRPCResponse> getPreExecMethod;
    private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

    private XchainGrpc() {
    }

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "PostTx",
            requestType = com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus.class,
            responseType = com.bsnbase.sdk.util.pb.XchainOuterClass.CommonReply.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus,
            com.bsnbase.sdk.util.pb.XchainOuterClass.CommonReply> getPostTxMethod() {
        io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus, com.bsnbase.sdk.util.pb.XchainOuterClass.CommonReply> getPostTxMethod;
        if ((getPostTxMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getPostTxMethod) == null) {
            synchronized (com.bsnbase.sdk.util.pb.XchainGrpc.class) {
                if ((getPostTxMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getPostTxMethod) == null) {
                    com.bsnbase.sdk.util.pb.XchainGrpc.getPostTxMethod = getPostTxMethod =
                            io.grpc.MethodDescriptor.<com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus, com.bsnbase.sdk.util.pb.XchainOuterClass.CommonReply>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PostTx"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.CommonReply.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getPostTxMethod;
    }

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "QueryACL",
            requestType = com.bsnbase.sdk.util.pb.XchainOuterClass.AclStatus.class,
            responseType = com.bsnbase.sdk.util.pb.XchainOuterClass.AclStatus.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.AclStatus,
            com.bsnbase.sdk.util.pb.XchainOuterClass.AclStatus> getQueryACLMethod() {
        io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.AclStatus, com.bsnbase.sdk.util.pb.XchainOuterClass.AclStatus> getQueryACLMethod;
        if ((getQueryACLMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getQueryACLMethod) == null) {
            synchronized (com.bsnbase.sdk.util.pb.XchainGrpc.class) {
                if ((getQueryACLMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getQueryACLMethod) == null) {
                    com.bsnbase.sdk.util.pb.XchainGrpc.getQueryACLMethod = getQueryACLMethod =
                            io.grpc.MethodDescriptor.<com.bsnbase.sdk.util.pb.XchainOuterClass.AclStatus, com.bsnbase.sdk.util.pb.XchainOuterClass.AclStatus>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryACL"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.AclStatus.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.AclStatus.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getQueryACLMethod;
    }

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetAccountContracts",
            requestType = com.bsnbase.sdk.util.pb.XchainOuterClass.GetAccountContractsRequest.class,
            responseType = com.bsnbase.sdk.util.pb.XchainOuterClass.GetAccountContractsResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.GetAccountContractsRequest,
            com.bsnbase.sdk.util.pb.XchainOuterClass.GetAccountContractsResponse> getGetAccountContractsMethod() {
        io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.GetAccountContractsRequest, com.bsnbase.sdk.util.pb.XchainOuterClass.GetAccountContractsResponse> getGetAccountContractsMethod;
        if ((getGetAccountContractsMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getGetAccountContractsMethod) == null) {
            synchronized (com.bsnbase.sdk.util.pb.XchainGrpc.class) {
                if ((getGetAccountContractsMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getGetAccountContractsMethod) == null) {
                    com.bsnbase.sdk.util.pb.XchainGrpc.getGetAccountContractsMethod = getGetAccountContractsMethod =
                            io.grpc.MethodDescriptor.<com.bsnbase.sdk.util.pb.XchainOuterClass.GetAccountContractsRequest, com.bsnbase.sdk.util.pb.XchainOuterClass.GetAccountContractsResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAccountContracts"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.GetAccountContractsRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.GetAccountContractsResponse.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getGetAccountContractsMethod;
    }

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "QueryTx",
            requestType = com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus.class,
            responseType = com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus,
            com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus> getQueryTxMethod() {
        io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus, com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus> getQueryTxMethod;
        if ((getQueryTxMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getQueryTxMethod) == null) {
            synchronized (com.bsnbase.sdk.util.pb.XchainGrpc.class) {
                if ((getQueryTxMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getQueryTxMethod) == null) {
                    com.bsnbase.sdk.util.pb.XchainGrpc.getQueryTxMethod = getQueryTxMethod =
                            io.grpc.MethodDescriptor.<com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus, com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryTx"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getQueryTxMethod;
    }

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetBalance",
            requestType = com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus.class,
            responseType = com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus,
            com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus> getGetBalanceMethod() {
        io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus, com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus> getGetBalanceMethod;
        if ((getGetBalanceMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getGetBalanceMethod) == null) {
            synchronized (com.bsnbase.sdk.util.pb.XchainGrpc.class) {
                if ((getGetBalanceMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getGetBalanceMethod) == null) {
                    com.bsnbase.sdk.util.pb.XchainGrpc.getGetBalanceMethod = getGetBalanceMethod =
                            io.grpc.MethodDescriptor.<com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus, com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBalance"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getGetBalanceMethod;
    }

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetBalanceDetail",
            requestType = com.bsnbase.sdk.util.pb.XchainOuterClass.AddressBalanceStatus.class,
            responseType = com.bsnbase.sdk.util.pb.XchainOuterClass.AddressBalanceStatus.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.AddressBalanceStatus,
            com.bsnbase.sdk.util.pb.XchainOuterClass.AddressBalanceStatus> getGetBalanceDetailMethod() {
        io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.AddressBalanceStatus, com.bsnbase.sdk.util.pb.XchainOuterClass.AddressBalanceStatus> getGetBalanceDetailMethod;
        if ((getGetBalanceDetailMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getGetBalanceDetailMethod) == null) {
            synchronized (com.bsnbase.sdk.util.pb.XchainGrpc.class) {
                if ((getGetBalanceDetailMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getGetBalanceDetailMethod) == null) {
                    com.bsnbase.sdk.util.pb.XchainGrpc.getGetBalanceDetailMethod = getGetBalanceDetailMethod =
                            io.grpc.MethodDescriptor.<com.bsnbase.sdk.util.pb.XchainOuterClass.AddressBalanceStatus, com.bsnbase.sdk.util.pb.XchainOuterClass.AddressBalanceStatus>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBalanceDetail"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.AddressBalanceStatus.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.AddressBalanceStatus.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getGetBalanceDetailMethod;
    }

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetFrozenBalance",
            requestType = com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus.class,
            responseType = com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus,
            com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus> getGetFrozenBalanceMethod() {
        io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus, com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus> getGetFrozenBalanceMethod;
        if ((getGetFrozenBalanceMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getGetFrozenBalanceMethod) == null) {
            synchronized (com.bsnbase.sdk.util.pb.XchainGrpc.class) {
                if ((getGetFrozenBalanceMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getGetFrozenBalanceMethod) == null) {
                    com.bsnbase.sdk.util.pb.XchainGrpc.getGetFrozenBalanceMethod = getGetFrozenBalanceMethod =
                            io.grpc.MethodDescriptor.<com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus, com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetFrozenBalance"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getGetFrozenBalanceMethod;
    }

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetBlock",
            requestType = com.bsnbase.sdk.util.pb.XchainOuterClass.BlockID.class,
            responseType = com.bsnbase.sdk.util.pb.XchainOuterClass.Block.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.BlockID,
            com.bsnbase.sdk.util.pb.XchainOuterClass.Block> getGetBlockMethod() {
        io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.BlockID, com.bsnbase.sdk.util.pb.XchainOuterClass.Block> getGetBlockMethod;
        if ((getGetBlockMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getGetBlockMethod) == null) {
            synchronized (com.bsnbase.sdk.util.pb.XchainGrpc.class) {
                if ((getGetBlockMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getGetBlockMethod) == null) {
                    com.bsnbase.sdk.util.pb.XchainGrpc.getGetBlockMethod = getGetBlockMethod =
                            io.grpc.MethodDescriptor.<com.bsnbase.sdk.util.pb.XchainOuterClass.BlockID, com.bsnbase.sdk.util.pb.XchainOuterClass.Block>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBlock"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.BlockID.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.Block.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getGetBlockMethod;
    }

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetBlockByHeight",
            requestType = com.bsnbase.sdk.util.pb.XchainOuterClass.BlockHeight.class,
            responseType = com.bsnbase.sdk.util.pb.XchainOuterClass.Block.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.BlockHeight,
            com.bsnbase.sdk.util.pb.XchainOuterClass.Block> getGetBlockByHeightMethod() {
        io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.BlockHeight, com.bsnbase.sdk.util.pb.XchainOuterClass.Block> getGetBlockByHeightMethod;
        if ((getGetBlockByHeightMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getGetBlockByHeightMethod) == null) {
            synchronized (com.bsnbase.sdk.util.pb.XchainGrpc.class) {
                if ((getGetBlockByHeightMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getGetBlockByHeightMethod) == null) {
                    com.bsnbase.sdk.util.pb.XchainGrpc.getGetBlockByHeightMethod = getGetBlockByHeightMethod =
                            io.grpc.MethodDescriptor.<com.bsnbase.sdk.util.pb.XchainOuterClass.BlockHeight, com.bsnbase.sdk.util.pb.XchainOuterClass.Block>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBlockByHeight"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.BlockHeight.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.Block.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getGetBlockByHeightMethod;
    }

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetBlockChainStatus",
            requestType = com.bsnbase.sdk.util.pb.XchainOuterClass.BCStatus.class,
            responseType = com.bsnbase.sdk.util.pb.XchainOuterClass.BCStatus.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.BCStatus,
            com.bsnbase.sdk.util.pb.XchainOuterClass.BCStatus> getGetBlockChainStatusMethod() {
        io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.BCStatus, com.bsnbase.sdk.util.pb.XchainOuterClass.BCStatus> getGetBlockChainStatusMethod;
        if ((getGetBlockChainStatusMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getGetBlockChainStatusMethod) == null) {
            synchronized (com.bsnbase.sdk.util.pb.XchainGrpc.class) {
                if ((getGetBlockChainStatusMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getGetBlockChainStatusMethod) == null) {
                    com.bsnbase.sdk.util.pb.XchainGrpc.getGetBlockChainStatusMethod = getGetBlockChainStatusMethod =
                            io.grpc.MethodDescriptor.<com.bsnbase.sdk.util.pb.XchainOuterClass.BCStatus, com.bsnbase.sdk.util.pb.XchainOuterClass.BCStatus>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBlockChainStatus"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.BCStatus.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.BCStatus.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getGetBlockChainStatusMethod;
    }

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetBlockChains",
            requestType = com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn.class,
            responseType = com.bsnbase.sdk.util.pb.XchainOuterClass.BlockChains.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn,
            com.bsnbase.sdk.util.pb.XchainOuterClass.BlockChains> getGetBlockChainsMethod() {
        io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn, com.bsnbase.sdk.util.pb.XchainOuterClass.BlockChains> getGetBlockChainsMethod;
        if ((getGetBlockChainsMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getGetBlockChainsMethod) == null) {
            synchronized (com.bsnbase.sdk.util.pb.XchainGrpc.class) {
                if ((getGetBlockChainsMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getGetBlockChainsMethod) == null) {
                    com.bsnbase.sdk.util.pb.XchainGrpc.getGetBlockChainsMethod = getGetBlockChainsMethod =
                            io.grpc.MethodDescriptor.<com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn, com.bsnbase.sdk.util.pb.XchainOuterClass.BlockChains>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBlockChains"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.BlockChains.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getGetBlockChainsMethod;
    }

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetSystemStatus",
            requestType = com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn.class,
            responseType = com.bsnbase.sdk.util.pb.XchainOuterClass.SystemsStatusReply.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn,
            com.bsnbase.sdk.util.pb.XchainOuterClass.SystemsStatusReply> getGetSystemStatusMethod() {
        io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn, com.bsnbase.sdk.util.pb.XchainOuterClass.SystemsStatusReply> getGetSystemStatusMethod;
        if ((getGetSystemStatusMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getGetSystemStatusMethod) == null) {
            synchronized (com.bsnbase.sdk.util.pb.XchainGrpc.class) {
                if ((getGetSystemStatusMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getGetSystemStatusMethod) == null) {
                    com.bsnbase.sdk.util.pb.XchainGrpc.getGetSystemStatusMethod = getGetSystemStatusMethod =
                            io.grpc.MethodDescriptor.<com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn, com.bsnbase.sdk.util.pb.XchainOuterClass.SystemsStatusReply>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetSystemStatus"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.SystemsStatusReply.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getGetSystemStatusMethod;
    }

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetNetURL",
            requestType = com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn.class,
            responseType = com.bsnbase.sdk.util.pb.XchainOuterClass.RawUrl.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn,
            com.bsnbase.sdk.util.pb.XchainOuterClass.RawUrl> getGetNetURLMethod() {
        io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn, com.bsnbase.sdk.util.pb.XchainOuterClass.RawUrl> getGetNetURLMethod;
        if ((getGetNetURLMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getGetNetURLMethod) == null) {
            synchronized (com.bsnbase.sdk.util.pb.XchainGrpc.class) {
                if ((getGetNetURLMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getGetNetURLMethod) == null) {
                    com.bsnbase.sdk.util.pb.XchainGrpc.getGetNetURLMethod = getGetNetURLMethod =
                            io.grpc.MethodDescriptor.<com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn, com.bsnbase.sdk.util.pb.XchainOuterClass.RawUrl>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetNetURL"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.RawUrl.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getGetNetURLMethod;
    }

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "SelectUTXO",
            requestType = com.bsnbase.sdk.util.pb.XchainOuterClass.UtxoInput.class,
            responseType = com.bsnbase.sdk.util.pb.XchainOuterClass.UtxoOutput.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.UtxoInput,
            com.bsnbase.sdk.util.pb.XchainOuterClass.UtxoOutput> getSelectUTXOMethod() {
        io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.UtxoInput, com.bsnbase.sdk.util.pb.XchainOuterClass.UtxoOutput> getSelectUTXOMethod;
        if ((getSelectUTXOMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getSelectUTXOMethod) == null) {
            synchronized (com.bsnbase.sdk.util.pb.XchainGrpc.class) {
                if ((getSelectUTXOMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getSelectUTXOMethod) == null) {
                    com.bsnbase.sdk.util.pb.XchainGrpc.getSelectUTXOMethod = getSelectUTXOMethod =
                            io.grpc.MethodDescriptor.<com.bsnbase.sdk.util.pb.XchainOuterClass.UtxoInput, com.bsnbase.sdk.util.pb.XchainOuterClass.UtxoOutput>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SelectUTXO"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.UtxoInput.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.UtxoOutput.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getSelectUTXOMethod;
    }

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "PreExecWithSelectUTXO",
            requestType = com.bsnbase.sdk.util.pb.XchainOuterClass.PreExecWithSelectUTXORequest.class,
            responseType = com.bsnbase.sdk.util.pb.XchainOuterClass.PreExecWithSelectUTXOResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.PreExecWithSelectUTXORequest,
            com.bsnbase.sdk.util.pb.XchainOuterClass.PreExecWithSelectUTXOResponse> getPreExecWithSelectUTXOMethod() {
        io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.PreExecWithSelectUTXORequest, com.bsnbase.sdk.util.pb.XchainOuterClass.PreExecWithSelectUTXOResponse> getPreExecWithSelectUTXOMethod;
        if ((getPreExecWithSelectUTXOMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getPreExecWithSelectUTXOMethod) == null) {
            synchronized (com.bsnbase.sdk.util.pb.XchainGrpc.class) {
                if ((getPreExecWithSelectUTXOMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getPreExecWithSelectUTXOMethod) == null) {
                    com.bsnbase.sdk.util.pb.XchainGrpc.getPreExecWithSelectUTXOMethod = getPreExecWithSelectUTXOMethod =
                            io.grpc.MethodDescriptor.<com.bsnbase.sdk.util.pb.XchainOuterClass.PreExecWithSelectUTXORequest, com.bsnbase.sdk.util.pb.XchainOuterClass.PreExecWithSelectUTXOResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PreExecWithSelectUTXO"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.PreExecWithSelectUTXORequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.PreExecWithSelectUTXOResponse.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getPreExecWithSelectUTXOMethod;
    }

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "DeployNativeCode",
            requestType = com.bsnbase.sdk.util.pb.XchainOuterClass.DeployNativeCodeRequest.class,
            responseType = com.bsnbase.sdk.util.pb.XchainOuterClass.DeployNativeCodeResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.DeployNativeCodeRequest,
            com.bsnbase.sdk.util.pb.XchainOuterClass.DeployNativeCodeResponse> getDeployNativeCodeMethod() {
        io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.DeployNativeCodeRequest, com.bsnbase.sdk.util.pb.XchainOuterClass.DeployNativeCodeResponse> getDeployNativeCodeMethod;
        if ((getDeployNativeCodeMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getDeployNativeCodeMethod) == null) {
            synchronized (com.bsnbase.sdk.util.pb.XchainGrpc.class) {
                if ((getDeployNativeCodeMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getDeployNativeCodeMethod) == null) {
                    com.bsnbase.sdk.util.pb.XchainGrpc.getDeployNativeCodeMethod = getDeployNativeCodeMethod =
                            io.grpc.MethodDescriptor.<com.bsnbase.sdk.util.pb.XchainOuterClass.DeployNativeCodeRequest, com.bsnbase.sdk.util.pb.XchainOuterClass.DeployNativeCodeResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeployNativeCode"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DeployNativeCodeRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DeployNativeCodeResponse.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getDeployNativeCodeMethod;
    }

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "NativeCodeStatus",
            requestType = com.bsnbase.sdk.util.pb.XchainOuterClass.NativeCodeStatusRequest.class,
            responseType = com.bsnbase.sdk.util.pb.XchainOuterClass.NativeCodeStatusResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.NativeCodeStatusRequest,
            com.bsnbase.sdk.util.pb.XchainOuterClass.NativeCodeStatusResponse> getNativeCodeStatusMethod() {
        io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.NativeCodeStatusRequest, com.bsnbase.sdk.util.pb.XchainOuterClass.NativeCodeStatusResponse> getNativeCodeStatusMethod;
        if ((getNativeCodeStatusMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getNativeCodeStatusMethod) == null) {
            synchronized (com.bsnbase.sdk.util.pb.XchainGrpc.class) {
                if ((getNativeCodeStatusMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getNativeCodeStatusMethod) == null) {
                    com.bsnbase.sdk.util.pb.XchainGrpc.getNativeCodeStatusMethod = getNativeCodeStatusMethod =
                            io.grpc.MethodDescriptor.<com.bsnbase.sdk.util.pb.XchainOuterClass.NativeCodeStatusRequest, com.bsnbase.sdk.util.pb.XchainOuterClass.NativeCodeStatusResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "NativeCodeStatus"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.NativeCodeStatusRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.NativeCodeStatusResponse.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getNativeCodeStatusMethod;
    }

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "DposCandidates",
            requestType = com.bsnbase.sdk.util.pb.XchainOuterClass.DposCandidatesRequest.class,
            responseType = com.bsnbase.sdk.util.pb.XchainOuterClass.DposCandidatesResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.DposCandidatesRequest,
            com.bsnbase.sdk.util.pb.XchainOuterClass.DposCandidatesResponse> getDposCandidatesMethod() {
        io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.DposCandidatesRequest, com.bsnbase.sdk.util.pb.XchainOuterClass.DposCandidatesResponse> getDposCandidatesMethod;
        if ((getDposCandidatesMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getDposCandidatesMethod) == null) {
            synchronized (com.bsnbase.sdk.util.pb.XchainGrpc.class) {
                if ((getDposCandidatesMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getDposCandidatesMethod) == null) {
                    com.bsnbase.sdk.util.pb.XchainGrpc.getDposCandidatesMethod = getDposCandidatesMethod =
                            io.grpc.MethodDescriptor.<com.bsnbase.sdk.util.pb.XchainOuterClass.DposCandidatesRequest, com.bsnbase.sdk.util.pb.XchainOuterClass.DposCandidatesResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DposCandidates"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DposCandidatesRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DposCandidatesResponse.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getDposCandidatesMethod;
    }

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "DposNominateRecords",
            requestType = com.bsnbase.sdk.util.pb.XchainOuterClass.DposNominateRecordsRequest.class,
            responseType = com.bsnbase.sdk.util.pb.XchainOuterClass.DposNominateRecordsResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.DposNominateRecordsRequest,
            com.bsnbase.sdk.util.pb.XchainOuterClass.DposNominateRecordsResponse> getDposNominateRecordsMethod() {
        io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.DposNominateRecordsRequest, com.bsnbase.sdk.util.pb.XchainOuterClass.DposNominateRecordsResponse> getDposNominateRecordsMethod;
        if ((getDposNominateRecordsMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getDposNominateRecordsMethod) == null) {
            synchronized (com.bsnbase.sdk.util.pb.XchainGrpc.class) {
                if ((getDposNominateRecordsMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getDposNominateRecordsMethod) == null) {
                    com.bsnbase.sdk.util.pb.XchainGrpc.getDposNominateRecordsMethod = getDposNominateRecordsMethod =
                            io.grpc.MethodDescriptor.<com.bsnbase.sdk.util.pb.XchainOuterClass.DposNominateRecordsRequest, com.bsnbase.sdk.util.pb.XchainOuterClass.DposNominateRecordsResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DposNominateRecords"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DposNominateRecordsRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DposNominateRecordsResponse.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getDposNominateRecordsMethod;
    }

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "DposNomineeRecords",
            requestType = com.bsnbase.sdk.util.pb.XchainOuterClass.DposNomineeRecordsRequest.class,
            responseType = com.bsnbase.sdk.util.pb.XchainOuterClass.DposNomineeRecordsResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.DposNomineeRecordsRequest,
            com.bsnbase.sdk.util.pb.XchainOuterClass.DposNomineeRecordsResponse> getDposNomineeRecordsMethod() {
        io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.DposNomineeRecordsRequest, com.bsnbase.sdk.util.pb.XchainOuterClass.DposNomineeRecordsResponse> getDposNomineeRecordsMethod;
        if ((getDposNomineeRecordsMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getDposNomineeRecordsMethod) == null) {
            synchronized (com.bsnbase.sdk.util.pb.XchainGrpc.class) {
                if ((getDposNomineeRecordsMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getDposNomineeRecordsMethod) == null) {
                    com.bsnbase.sdk.util.pb.XchainGrpc.getDposNomineeRecordsMethod = getDposNomineeRecordsMethod =
                            io.grpc.MethodDescriptor.<com.bsnbase.sdk.util.pb.XchainOuterClass.DposNomineeRecordsRequest, com.bsnbase.sdk.util.pb.XchainOuterClass.DposNomineeRecordsResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DposNomineeRecords"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DposNomineeRecordsRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DposNomineeRecordsResponse.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getDposNomineeRecordsMethod;
    }

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "DposVoteRecords",
            requestType = com.bsnbase.sdk.util.pb.XchainOuterClass.DposVoteRecordsRequest.class,
            responseType = com.bsnbase.sdk.util.pb.XchainOuterClass.DposVoteRecordsResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.DposVoteRecordsRequest,
            com.bsnbase.sdk.util.pb.XchainOuterClass.DposVoteRecordsResponse> getDposVoteRecordsMethod() {
        io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.DposVoteRecordsRequest, com.bsnbase.sdk.util.pb.XchainOuterClass.DposVoteRecordsResponse> getDposVoteRecordsMethod;
        if ((getDposVoteRecordsMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getDposVoteRecordsMethod) == null) {
            synchronized (com.bsnbase.sdk.util.pb.XchainGrpc.class) {
                if ((getDposVoteRecordsMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getDposVoteRecordsMethod) == null) {
                    com.bsnbase.sdk.util.pb.XchainGrpc.getDposVoteRecordsMethod = getDposVoteRecordsMethod =
                            io.grpc.MethodDescriptor.<com.bsnbase.sdk.util.pb.XchainOuterClass.DposVoteRecordsRequest, com.bsnbase.sdk.util.pb.XchainOuterClass.DposVoteRecordsResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DposVoteRecords"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DposVoteRecordsRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DposVoteRecordsResponse.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getDposVoteRecordsMethod;
    }

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "DposVotedRecords",
            requestType = com.bsnbase.sdk.util.pb.XchainOuterClass.DposVotedRecordsRequest.class,
            responseType = com.bsnbase.sdk.util.pb.XchainOuterClass.DposVotedRecordsResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.DposVotedRecordsRequest,
            com.bsnbase.sdk.util.pb.XchainOuterClass.DposVotedRecordsResponse> getDposVotedRecordsMethod() {
        io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.DposVotedRecordsRequest, com.bsnbase.sdk.util.pb.XchainOuterClass.DposVotedRecordsResponse> getDposVotedRecordsMethod;
        if ((getDposVotedRecordsMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getDposVotedRecordsMethod) == null) {
            synchronized (com.bsnbase.sdk.util.pb.XchainGrpc.class) {
                if ((getDposVotedRecordsMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getDposVotedRecordsMethod) == null) {
                    com.bsnbase.sdk.util.pb.XchainGrpc.getDposVotedRecordsMethod = getDposVotedRecordsMethod =
                            io.grpc.MethodDescriptor.<com.bsnbase.sdk.util.pb.XchainOuterClass.DposVotedRecordsRequest, com.bsnbase.sdk.util.pb.XchainOuterClass.DposVotedRecordsResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DposVotedRecords"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DposVotedRecordsRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DposVotedRecordsResponse.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getDposVotedRecordsMethod;
    }

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "DposCheckResults",
            requestType = com.bsnbase.sdk.util.pb.XchainOuterClass.DposCheckResultsRequest.class,
            responseType = com.bsnbase.sdk.util.pb.XchainOuterClass.DposCheckResultsResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.DposCheckResultsRequest,
            com.bsnbase.sdk.util.pb.XchainOuterClass.DposCheckResultsResponse> getDposCheckResultsMethod() {
        io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.DposCheckResultsRequest, com.bsnbase.sdk.util.pb.XchainOuterClass.DposCheckResultsResponse> getDposCheckResultsMethod;
        if ((getDposCheckResultsMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getDposCheckResultsMethod) == null) {
            synchronized (com.bsnbase.sdk.util.pb.XchainGrpc.class) {
                if ((getDposCheckResultsMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getDposCheckResultsMethod) == null) {
                    com.bsnbase.sdk.util.pb.XchainGrpc.getDposCheckResultsMethod = getDposCheckResultsMethod =
                            io.grpc.MethodDescriptor.<com.bsnbase.sdk.util.pb.XchainOuterClass.DposCheckResultsRequest, com.bsnbase.sdk.util.pb.XchainOuterClass.DposCheckResultsResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DposCheckResults"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DposCheckResultsRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DposCheckResultsResponse.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getDposCheckResultsMethod;
    }

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "DposStatus",
            requestType = com.bsnbase.sdk.util.pb.XchainOuterClass.DposStatusRequest.class,
            responseType = com.bsnbase.sdk.util.pb.XchainOuterClass.DposStatusResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.DposStatusRequest,
            com.bsnbase.sdk.util.pb.XchainOuterClass.DposStatusResponse> getDposStatusMethod() {
        io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.DposStatusRequest, com.bsnbase.sdk.util.pb.XchainOuterClass.DposStatusResponse> getDposStatusMethod;
        if ((getDposStatusMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getDposStatusMethod) == null) {
            synchronized (com.bsnbase.sdk.util.pb.XchainGrpc.class) {
                if ((getDposStatusMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getDposStatusMethod) == null) {
                    com.bsnbase.sdk.util.pb.XchainGrpc.getDposStatusMethod = getDposStatusMethod =
                            io.grpc.MethodDescriptor.<com.bsnbase.sdk.util.pb.XchainOuterClass.DposStatusRequest, com.bsnbase.sdk.util.pb.XchainOuterClass.DposStatusResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DposStatus"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DposStatusRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DposStatusResponse.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getDposStatusMethod;
    }

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetAccountByAK",
            requestType = com.bsnbase.sdk.util.pb.XchainOuterClass.AK2AccountRequest.class,
            responseType = com.bsnbase.sdk.util.pb.XchainOuterClass.AK2AccountResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.AK2AccountRequest,
            com.bsnbase.sdk.util.pb.XchainOuterClass.AK2AccountResponse> getGetAccountByAKMethod() {
        io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.AK2AccountRequest, com.bsnbase.sdk.util.pb.XchainOuterClass.AK2AccountResponse> getGetAccountByAKMethod;
        if ((getGetAccountByAKMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getGetAccountByAKMethod) == null) {
            synchronized (com.bsnbase.sdk.util.pb.XchainGrpc.class) {
                if ((getGetAccountByAKMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getGetAccountByAKMethod) == null) {
                    com.bsnbase.sdk.util.pb.XchainGrpc.getGetAccountByAKMethod = getGetAccountByAKMethod =
                            io.grpc.MethodDescriptor.<com.bsnbase.sdk.util.pb.XchainOuterClass.AK2AccountRequest, com.bsnbase.sdk.util.pb.XchainOuterClass.AK2AccountResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAccountByAK"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.AK2AccountRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.AK2AccountResponse.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getGetAccountByAKMethod;
    }

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "PreExec",
            requestType = com.bsnbase.sdk.util.pb.XchainOuterClass.InvokeRPCRequest.class,
            responseType = com.bsnbase.sdk.util.pb.XchainOuterClass.InvokeRPCResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.InvokeRPCRequest,
            com.bsnbase.sdk.util.pb.XchainOuterClass.InvokeRPCResponse> getPreExecMethod() {
        io.grpc.MethodDescriptor<com.bsnbase.sdk.util.pb.XchainOuterClass.InvokeRPCRequest, com.bsnbase.sdk.util.pb.XchainOuterClass.InvokeRPCResponse> getPreExecMethod;
        if ((getPreExecMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getPreExecMethod) == null) {
            synchronized (com.bsnbase.sdk.util.pb.XchainGrpc.class) {
                if ((getPreExecMethod = com.bsnbase.sdk.util.pb.XchainGrpc.getPreExecMethod) == null) {
                    com.bsnbase.sdk.util.pb.XchainGrpc.getPreExecMethod = getPreExecMethod =
                            io.grpc.MethodDescriptor.<com.bsnbase.sdk.util.pb.XchainOuterClass.InvokeRPCRequest, com.bsnbase.sdk.util.pb.XchainOuterClass.InvokeRPCResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PreExec"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.InvokeRPCRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.InvokeRPCResponse.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getPreExecMethod;
    }

    /**
     * Creates a new async stub that supports all call types for the service
     */
    public static XchainStub newStub(io.grpc.Channel channel) {
        return new XchainStub(channel);
    }

    /**
     * Creates a new blocking-style stub that supports unary and streaming output calls on the service
     */
    public static XchainBlockingStub newBlockingStub(
            io.grpc.Channel channel) {
        return new XchainBlockingStub(channel);
    }

    /**
     * Creates a new ListenableFuture-style stub that supports unary calls on the service
     */
    public static XchainFutureStub newFutureStub(
            io.grpc.Channel channel) {
        return new XchainFutureStub(channel);
    }

    public static io.grpc.ServiceDescriptor getServiceDescriptor() {
        io.grpc.ServiceDescriptor result = serviceDescriptor;
        if (result == null) {
            synchronized (com.bsnbase.sdk.util.pb.XchainGrpc.class) {
                result = serviceDescriptor;
                if (result == null) {
                    serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                            .addMethod(getPostTxMethod())
                            .addMethod(getQueryACLMethod())
                            .addMethod(getGetAccountContractsMethod())
                            .addMethod(getQueryTxMethod())
                            .addMethod(getGetBalanceMethod())
                            .addMethod(getGetBalanceDetailMethod())
                            .addMethod(getGetFrozenBalanceMethod())
                            .addMethod(getGetBlockMethod())
                            .addMethod(getGetBlockByHeightMethod())
                            .addMethod(getGetBlockChainStatusMethod())
                            .addMethod(getGetBlockChainsMethod())
                            .addMethod(getGetSystemStatusMethod())
                            .addMethod(getGetNetURLMethod())
                            .addMethod(getSelectUTXOMethod())
                            .addMethod(getPreExecWithSelectUTXOMethod())
                            .addMethod(getDeployNativeCodeMethod())
                            .addMethod(getNativeCodeStatusMethod())
                            .addMethod(getDposCandidatesMethod())
                            .addMethod(getDposNominateRecordsMethod())
                            .addMethod(getDposNomineeRecordsMethod())
                            .addMethod(getDposVoteRecordsMethod())
                            .addMethod(getDposVotedRecordsMethod())
                            .addMethod(getDposCheckResultsMethod())
                            .addMethod(getDposStatusMethod())
                            .addMethod(getGetAccountByAKMethod())
                            .addMethod(getPreExecMethod())
                            .build();
                }
            }
        }
        return result;
    }

    /**
     * <pre>
     * Xchain is the main interfaces
     * </pre>
     */
    public static abstract class XchainImplBase implements io.grpc.BindableService {

        /**
         * <pre>
         * PostTx post Transaction to a node
         * </pre>
         */
        public void postTx(com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus request,
                           io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.CommonReply> responseObserver) {
            asyncUnimplementedUnaryCall(getPostTxMethod(), responseObserver);
        }

        /**
         *
         */
        public void queryACL(com.bsnbase.sdk.util.pb.XchainOuterClass.AclStatus request,
                             io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.AclStatus> responseObserver) {
            asyncUnimplementedUnaryCall(getQueryACLMethod(), responseObserver);
        }

        /**
         *
         */
        public void getAccountContracts(com.bsnbase.sdk.util.pb.XchainOuterClass.GetAccountContractsRequest request,
                                        io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.GetAccountContractsResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getGetAccountContractsMethod(), responseObserver);
        }

        /**
         * <pre>
         * QueryTx query Transaction by TxStatus,
         * Bcname and Txid are required for this
         * </pre>
         */
        public void queryTx(com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus request,
                            io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus> responseObserver) {
            asyncUnimplementedUnaryCall(getQueryTxMethod(), responseObserver);
        }

        /**
         * <pre>
         * GetBalance get balance of an address,
         * Address is required for this
         * </pre>
         */
        public void getBalance(com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus request,
                               io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus> responseObserver) {
            asyncUnimplementedUnaryCall(getGetBalanceMethod(), responseObserver);
        }

        /**
         * <pre>
         * GetFrozenBalance get two kinds of balance
         * 1. Still be frozen of an address
         * 2. Available now of an address
         * Address is required for this
         * </pre>
         */
        public void getBalanceDetail(com.bsnbase.sdk.util.pb.XchainOuterClass.AddressBalanceStatus request,
                                     io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.AddressBalanceStatus> responseObserver) {
            asyncUnimplementedUnaryCall(getGetBalanceDetailMethod(), responseObserver);
        }

        /**
         * <pre>
         * GetFrozenBalance get balance that still be frozen of an address,
         * Address is required for this
         * </pre>
         */
        public void getFrozenBalance(com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus request,
                                     io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus> responseObserver) {
            asyncUnimplementedUnaryCall(getGetFrozenBalanceMethod(), responseObserver);
        }

        /**
         * <pre>
         * GetBlock get block by blockid and return if the block in trunk or in branch
         * </pre>
         */
        public void getBlock(com.bsnbase.sdk.util.pb.XchainOuterClass.BlockID request,
                             io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.Block> responseObserver) {
            asyncUnimplementedUnaryCall(getGetBlockMethod(), responseObserver);
        }

        /**
         * <pre>
         * GetBlockByHeight get block by height and return if the block in trunk or in
         * branch
         * </pre>
         */
        public void getBlockByHeight(com.bsnbase.sdk.util.pb.XchainOuterClass.BlockHeight request,
                                     io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.Block> responseObserver) {
            asyncUnimplementedUnaryCall(getGetBlockByHeightMethod(), responseObserver);
        }

        /**
         *
         */
        public void getBlockChainStatus(com.bsnbase.sdk.util.pb.XchainOuterClass.BCStatus request,
                                        io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.BCStatus> responseObserver) {
            asyncUnimplementedUnaryCall(getGetBlockChainStatusMethod(), responseObserver);
        }

        /**
         * <pre>
         * Get blockchains query blockchains
         * </pre>
         */
        public void getBlockChains(com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn request,
                                   io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.BlockChains> responseObserver) {
            asyncUnimplementedUnaryCall(getGetBlockChainsMethod(), responseObserver);
        }

        /**
         * <pre>
         * GetSystemStatus query system status
         * </pre>
         */
        public void getSystemStatus(com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn request,
                                    io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.SystemsStatusReply> responseObserver) {
            asyncUnimplementedUnaryCall(getGetSystemStatusMethod(), responseObserver);
        }

        /**
         * <pre>
         * GetNetURL return net url
         * </pre>
         */
        public void getNetURL(com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn request,
                              io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.RawUrl> responseObserver) {
            asyncUnimplementedUnaryCall(getGetNetURLMethod(), responseObserver);
        }

        /**
         * <pre>
         * The new Select utxos interface, which does not require a signature, can support utxo for select accounts
         * </pre>
         */
        public void selectUTXO(com.bsnbase.sdk.util.pb.XchainOuterClass.UtxoInput request,
                               io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.UtxoOutput> responseObserver) {
            asyncUnimplementedUnaryCall(getSelectUTXOMethod(), responseObserver);
        }

        /**
         * <pre>
         * PreExecWithSelectUTXO preExec &amp; selectUtxo
         * </pre>
         */
        public void preExecWithSelectUTXO(com.bsnbase.sdk.util.pb.XchainOuterClass.PreExecWithSelectUTXORequest request,
                                          io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.PreExecWithSelectUTXOResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getPreExecWithSelectUTXOMethod(), responseObserver);
        }

        /**
         * <pre>
         * Native code deploy interface
         * </pre>
         */
        public void deployNativeCode(com.bsnbase.sdk.util.pb.XchainOuterClass.DeployNativeCodeRequest request,
                                     io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.DeployNativeCodeResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getDeployNativeCodeMethod(), responseObserver);
        }

        /**
         * <pre>
         * Native code status
         * </pre>
         */
        public void nativeCodeStatus(com.bsnbase.sdk.util.pb.XchainOuterClass.NativeCodeStatusRequest request,
                                     io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.NativeCodeStatusResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getNativeCodeStatusMethod(), responseObserver);
        }

        /**
         * <pre>
         *  DposCandidates get all candidates of the tdpos consensus
         * </pre>
         */
        public void dposCandidates(com.bsnbase.sdk.util.pb.XchainOuterClass.DposCandidatesRequest request,
                                   io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.DposCandidatesResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getDposCandidatesMethod(), responseObserver);
        }

        /**
         * <pre>
         *  DposNominateRecords get all records nominated by an user
         * </pre>
         */
        public void dposNominateRecords(com.bsnbase.sdk.util.pb.XchainOuterClass.DposNominateRecordsRequest request,
                                        io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.DposNominateRecordsResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getDposNominateRecordsMethod(), responseObserver);
        }

        /**
         * <pre>
         *  DposNomineeRecords get nominated record of a candidate
         * </pre>
         */
        public void dposNomineeRecords(com.bsnbase.sdk.util.pb.XchainOuterClass.DposNomineeRecordsRequest request,
                                       io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.DposNomineeRecordsResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getDposNomineeRecordsMethod(), responseObserver);
        }

        /**
         * <pre>
         *  DposVoteRecords get all vote records voted by an user
         * </pre>
         */
        public void dposVoteRecords(com.bsnbase.sdk.util.pb.XchainOuterClass.DposVoteRecordsRequest request,
                                    io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.DposVoteRecordsResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getDposVoteRecordsMethod(), responseObserver);
        }

        /**
         * <pre>
         *  DposVotedRecords get all vote records of a candidate
         * </pre>
         */
        public void dposVotedRecords(com.bsnbase.sdk.util.pb.XchainOuterClass.DposVotedRecordsRequest request,
                                     io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.DposVotedRecordsResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getDposVotedRecordsMethod(), responseObserver);
        }

        /**
         * <pre>
         *  DposCheckResults get check results of a specific term
         * </pre>
         */
        public void dposCheckResults(com.bsnbase.sdk.util.pb.XchainOuterClass.DposCheckResultsRequest request,
                                     io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.DposCheckResultsResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getDposCheckResultsMethod(), responseObserver);
        }

        /**
         * <pre>
         * DposStatus get dpos status
         * </pre>
         */
        public void dposStatus(com.bsnbase.sdk.util.pb.XchainOuterClass.DposStatusRequest request,
                               io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.DposStatusResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getDposStatusMethod(), responseObserver);
        }

        /**
         * <pre>
         * GetAccountByAK get account sets contain a specific address
         * </pre>
         */
        public void getAccountByAK(com.bsnbase.sdk.util.pb.XchainOuterClass.AK2AccountRequest request,
                                   io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.AK2AccountResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getGetAccountByAKMethod(), responseObserver);
        }

        /**
         * <pre>
         * Pre-execution Contracts
         * </pre>
         */
        public void preExec(com.bsnbase.sdk.util.pb.XchainOuterClass.InvokeRPCRequest request,
                            io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.InvokeRPCResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getPreExecMethod(), responseObserver);
        }

        @Override
        public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            getPostTxMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus,
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.CommonReply>(
                                            this, METHODID_POST_TX)))
                    .addMethod(
                            getQueryACLMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.AclStatus,
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.AclStatus>(
                                            this, METHODID_QUERY_ACL)))
                    .addMethod(
                            getGetAccountContractsMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.GetAccountContractsRequest,
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.GetAccountContractsResponse>(
                                            this, METHODID_GET_ACCOUNT_CONTRACTS)))
                    .addMethod(
                            getQueryTxMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus,
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus>(
                                            this, METHODID_QUERY_TX)))
                    .addMethod(
                            getGetBalanceMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus,
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus>(
                                            this, METHODID_GET_BALANCE)))
                    .addMethod(
                            getGetBalanceDetailMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.AddressBalanceStatus,
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.AddressBalanceStatus>(
                                            this, METHODID_GET_BALANCE_DETAIL)))
                    .addMethod(
                            getGetFrozenBalanceMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus,
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus>(
                                            this, METHODID_GET_FROZEN_BALANCE)))
                    .addMethod(
                            getGetBlockMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.BlockID,
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.Block>(
                                            this, METHODID_GET_BLOCK)))
                    .addMethod(
                            getGetBlockByHeightMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.BlockHeight,
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.Block>(
                                            this, METHODID_GET_BLOCK_BY_HEIGHT)))
                    .addMethod(
                            getGetBlockChainStatusMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.BCStatus,
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.BCStatus>(
                                            this, METHODID_GET_BLOCK_CHAIN_STATUS)))
                    .addMethod(
                            getGetBlockChainsMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn,
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.BlockChains>(
                                            this, METHODID_GET_BLOCK_CHAINS)))
                    .addMethod(
                            getGetSystemStatusMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn,
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.SystemsStatusReply>(
                                            this, METHODID_GET_SYSTEM_STATUS)))
                    .addMethod(
                            getGetNetURLMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn,
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.RawUrl>(
                                            this, METHODID_GET_NET_URL)))
                    .addMethod(
                            getSelectUTXOMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.UtxoInput,
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.UtxoOutput>(
                                            this, METHODID_SELECT_UTXO)))
                    .addMethod(
                            getPreExecWithSelectUTXOMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.PreExecWithSelectUTXORequest,
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.PreExecWithSelectUTXOResponse>(
                                            this, METHODID_PRE_EXEC_WITH_SELECT_UTXO)))
                    .addMethod(
                            getDeployNativeCodeMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DeployNativeCodeRequest,
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DeployNativeCodeResponse>(
                                            this, METHODID_DEPLOY_NATIVE_CODE)))
                    .addMethod(
                            getNativeCodeStatusMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.NativeCodeStatusRequest,
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.NativeCodeStatusResponse>(
                                            this, METHODID_NATIVE_CODE_STATUS)))
                    .addMethod(
                            getDposCandidatesMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DposCandidatesRequest,
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DposCandidatesResponse>(
                                            this, METHODID_DPOS_CANDIDATES)))
                    .addMethod(
                            getDposNominateRecordsMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DposNominateRecordsRequest,
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DposNominateRecordsResponse>(
                                            this, METHODID_DPOS_NOMINATE_RECORDS)))
                    .addMethod(
                            getDposNomineeRecordsMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DposNomineeRecordsRequest,
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DposNomineeRecordsResponse>(
                                            this, METHODID_DPOS_NOMINEE_RECORDS)))
                    .addMethod(
                            getDposVoteRecordsMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DposVoteRecordsRequest,
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DposVoteRecordsResponse>(
                                            this, METHODID_DPOS_VOTE_RECORDS)))
                    .addMethod(
                            getDposVotedRecordsMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DposVotedRecordsRequest,
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DposVotedRecordsResponse>(
                                            this, METHODID_DPOS_VOTED_RECORDS)))
                    .addMethod(
                            getDposCheckResultsMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DposCheckResultsRequest,
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DposCheckResultsResponse>(
                                            this, METHODID_DPOS_CHECK_RESULTS)))
                    .addMethod(
                            getDposStatusMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DposStatusRequest,
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.DposStatusResponse>(
                                            this, METHODID_DPOS_STATUS)))
                    .addMethod(
                            getGetAccountByAKMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.AK2AccountRequest,
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.AK2AccountResponse>(
                                            this, METHODID_GET_ACCOUNT_BY_AK)))
                    .addMethod(
                            getPreExecMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.InvokeRPCRequest,
                                            com.bsnbase.sdk.util.pb.XchainOuterClass.InvokeRPCResponse>(
                                            this, METHODID_PRE_EXEC)))
                    .build();
        }
    }

    /**
     * <pre>
     * Xchain is the main interfaces
     * </pre>
     */
    public static final class XchainStub extends io.grpc.stub.AbstractStub<XchainStub> {
        private XchainStub(io.grpc.Channel channel) {
            super(channel);
        }

        private XchainStub(io.grpc.Channel channel,
                           io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        protected XchainStub build(io.grpc.Channel channel,
                                   io.grpc.CallOptions callOptions) {
            return new XchainStub(channel, callOptions);
        }

        /**
         * <pre>
         * PostTx post Transaction to a node
         * </pre>
         */
        public void postTx(com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus request,
                           io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.CommonReply> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getPostTxMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void queryACL(com.bsnbase.sdk.util.pb.XchainOuterClass.AclStatus request,
                             io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.AclStatus> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getQueryACLMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void getAccountContracts(com.bsnbase.sdk.util.pb.XchainOuterClass.GetAccountContractsRequest request,
                                        io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.GetAccountContractsResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetAccountContractsMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * QueryTx query Transaction by TxStatus,
         * Bcname and Txid are required for this
         * </pre>
         */
        public void queryTx(com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus request,
                            io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getQueryTxMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * GetBalance get balance of an address,
         * Address is required for this
         * </pre>
         */
        public void getBalance(com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus request,
                               io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * GetFrozenBalance get two kinds of balance
         * 1. Still be frozen of an address
         * 2. Available now of an address
         * Address is required for this
         * </pre>
         */
        public void getBalanceDetail(com.bsnbase.sdk.util.pb.XchainOuterClass.AddressBalanceStatus request,
                                     io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.AddressBalanceStatus> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetBalanceDetailMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * GetFrozenBalance get balance that still be frozen of an address,
         * Address is required for this
         * </pre>
         */
        public void getFrozenBalance(com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus request,
                                     io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetFrozenBalanceMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * GetBlock get block by blockid and return if the block in trunk or in branch
         * </pre>
         */
        public void getBlock(com.bsnbase.sdk.util.pb.XchainOuterClass.BlockID request,
                             io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.Block> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetBlockMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * GetBlockByHeight get block by height and return if the block in trunk or in
         * branch
         * </pre>
         */
        public void getBlockByHeight(com.bsnbase.sdk.util.pb.XchainOuterClass.BlockHeight request,
                                     io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.Block> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetBlockByHeightMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void getBlockChainStatus(com.bsnbase.sdk.util.pb.XchainOuterClass.BCStatus request,
                                        io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.BCStatus> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetBlockChainStatusMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * Get blockchains query blockchains
         * </pre>
         */
        public void getBlockChains(com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn request,
                                   io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.BlockChains> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetBlockChainsMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * GetSystemStatus query system status
         * </pre>
         */
        public void getSystemStatus(com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn request,
                                    io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.SystemsStatusReply> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetSystemStatusMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * GetNetURL return net url
         * </pre>
         */
        public void getNetURL(com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn request,
                              io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.RawUrl> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetNetURLMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * The new Select utxos interface, which does not require a signature, can support utxo for select accounts
         * </pre>
         */
        public void selectUTXO(com.bsnbase.sdk.util.pb.XchainOuterClass.UtxoInput request,
                               io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.UtxoOutput> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getSelectUTXOMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * PreExecWithSelectUTXO preExec &amp; selectUtxo
         * </pre>
         */
        public void preExecWithSelectUTXO(com.bsnbase.sdk.util.pb.XchainOuterClass.PreExecWithSelectUTXORequest request,
                                          io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.PreExecWithSelectUTXOResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getPreExecWithSelectUTXOMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * Native code deploy interface
         * </pre>
         */
        public void deployNativeCode(com.bsnbase.sdk.util.pb.XchainOuterClass.DeployNativeCodeRequest request,
                                     io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.DeployNativeCodeResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getDeployNativeCodeMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * Native code status
         * </pre>
         */
        public void nativeCodeStatus(com.bsnbase.sdk.util.pb.XchainOuterClass.NativeCodeStatusRequest request,
                                     io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.NativeCodeStatusResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getNativeCodeStatusMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         *  DposCandidates get all candidates of the tdpos consensus
         * </pre>
         */
        public void dposCandidates(com.bsnbase.sdk.util.pb.XchainOuterClass.DposCandidatesRequest request,
                                   io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.DposCandidatesResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getDposCandidatesMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         *  DposNominateRecords get all records nominated by an user
         * </pre>
         */
        public void dposNominateRecords(com.bsnbase.sdk.util.pb.XchainOuterClass.DposNominateRecordsRequest request,
                                        io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.DposNominateRecordsResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getDposNominateRecordsMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         *  DposNomineeRecords get nominated record of a candidate
         * </pre>
         */
        public void dposNomineeRecords(com.bsnbase.sdk.util.pb.XchainOuterClass.DposNomineeRecordsRequest request,
                                       io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.DposNomineeRecordsResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getDposNomineeRecordsMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         *  DposVoteRecords get all vote records voted by an user
         * </pre>
         */
        public void dposVoteRecords(com.bsnbase.sdk.util.pb.XchainOuterClass.DposVoteRecordsRequest request,
                                    io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.DposVoteRecordsResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getDposVoteRecordsMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         *  DposVotedRecords get all vote records of a candidate
         * </pre>
         */
        public void dposVotedRecords(com.bsnbase.sdk.util.pb.XchainOuterClass.DposVotedRecordsRequest request,
                                     io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.DposVotedRecordsResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getDposVotedRecordsMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         *  DposCheckResults get check results of a specific term
         * </pre>
         */
        public void dposCheckResults(com.bsnbase.sdk.util.pb.XchainOuterClass.DposCheckResultsRequest request,
                                     io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.DposCheckResultsResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getDposCheckResultsMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * DposStatus get dpos status
         * </pre>
         */
        public void dposStatus(com.bsnbase.sdk.util.pb.XchainOuterClass.DposStatusRequest request,
                               io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.DposStatusResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getDposStatusMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * GetAccountByAK get account sets contain a specific address
         * </pre>
         */
        public void getAccountByAK(com.bsnbase.sdk.util.pb.XchainOuterClass.AK2AccountRequest request,
                                   io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.AK2AccountResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetAccountByAKMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * Pre-execution Contracts
         * </pre>
         */
        public void preExec(com.bsnbase.sdk.util.pb.XchainOuterClass.InvokeRPCRequest request,
                            io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.InvokeRPCResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getPreExecMethod(), getCallOptions()), request, responseObserver);
        }
    }

    /**
     * <pre>
     * Xchain is the main interfaces
     * </pre>
     */
    public static final class XchainBlockingStub extends io.grpc.stub.AbstractStub<XchainBlockingStub> {
        private XchainBlockingStub(io.grpc.Channel channel) {
            super(channel);
        }

        private XchainBlockingStub(io.grpc.Channel channel,
                                   io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        protected XchainBlockingStub build(io.grpc.Channel channel,
                                           io.grpc.CallOptions callOptions) {
            return new XchainBlockingStub(channel, callOptions);
        }

        /**
         * <pre>
         * PostTx post Transaction to a node
         * </pre>
         */
        public com.bsnbase.sdk.util.pb.XchainOuterClass.CommonReply postTx(com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus request) {
            return blockingUnaryCall(
                    getChannel(), getPostTxMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public com.bsnbase.sdk.util.pb.XchainOuterClass.AclStatus queryACL(com.bsnbase.sdk.util.pb.XchainOuterClass.AclStatus request) {
            return blockingUnaryCall(
                    getChannel(), getQueryACLMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public com.bsnbase.sdk.util.pb.XchainOuterClass.GetAccountContractsResponse getAccountContracts(com.bsnbase.sdk.util.pb.XchainOuterClass.GetAccountContractsRequest request) {
            return blockingUnaryCall(
                    getChannel(), getGetAccountContractsMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * QueryTx query Transaction by TxStatus,
         * Bcname and Txid are required for this
         * </pre>
         */
        public com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus queryTx(com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus request) {
            return blockingUnaryCall(
                    getChannel(), getQueryTxMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * GetBalance get balance of an address,
         * Address is required for this
         * </pre>
         */
        public com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus getBalance(com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus request) {
            return blockingUnaryCall(
                    getChannel(), getGetBalanceMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * GetFrozenBalance get two kinds of balance
         * 1. Still be frozen of an address
         * 2. Available now of an address
         * Address is required for this
         * </pre>
         */
        public com.bsnbase.sdk.util.pb.XchainOuterClass.AddressBalanceStatus getBalanceDetail(com.bsnbase.sdk.util.pb.XchainOuterClass.AddressBalanceStatus request) {
            return blockingUnaryCall(
                    getChannel(), getGetBalanceDetailMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * GetFrozenBalance get balance that still be frozen of an address,
         * Address is required for this
         * </pre>
         */
        public com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus getFrozenBalance(com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus request) {
            return blockingUnaryCall(
                    getChannel(), getGetFrozenBalanceMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * GetBlock get block by blockid and return if the block in trunk or in branch
         * </pre>
         */
        public com.bsnbase.sdk.util.pb.XchainOuterClass.Block getBlock(com.bsnbase.sdk.util.pb.XchainOuterClass.BlockID request) {
            return blockingUnaryCall(
                    getChannel(), getGetBlockMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * GetBlockByHeight get block by height and return if the block in trunk or in
         * branch
         * </pre>
         */
        public com.bsnbase.sdk.util.pb.XchainOuterClass.Block getBlockByHeight(com.bsnbase.sdk.util.pb.XchainOuterClass.BlockHeight request) {
            return blockingUnaryCall(
                    getChannel(), getGetBlockByHeightMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public com.bsnbase.sdk.util.pb.XchainOuterClass.BCStatus getBlockChainStatus(com.bsnbase.sdk.util.pb.XchainOuterClass.BCStatus request) {
            return blockingUnaryCall(
                    getChannel(), getGetBlockChainStatusMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * Get blockchains query blockchains
         * </pre>
         */
        public com.bsnbase.sdk.util.pb.XchainOuterClass.BlockChains getBlockChains(com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn request) {
            return blockingUnaryCall(
                    getChannel(), getGetBlockChainsMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * GetSystemStatus query system status
         * </pre>
         */
        public com.bsnbase.sdk.util.pb.XchainOuterClass.SystemsStatusReply getSystemStatus(com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn request) {
            return blockingUnaryCall(
                    getChannel(), getGetSystemStatusMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * GetNetURL return net url
         * </pre>
         */
        public com.bsnbase.sdk.util.pb.XchainOuterClass.RawUrl getNetURL(com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn request) {
            return blockingUnaryCall(
                    getChannel(), getGetNetURLMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * The new Select utxos interface, which does not require a signature, can support utxo for select accounts
         * </pre>
         */
        public com.bsnbase.sdk.util.pb.XchainOuterClass.UtxoOutput selectUTXO(com.bsnbase.sdk.util.pb.XchainOuterClass.UtxoInput request) {
            return blockingUnaryCall(
                    getChannel(), getSelectUTXOMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * PreExecWithSelectUTXO preExec &amp; selectUtxo
         * </pre>
         */
        public com.bsnbase.sdk.util.pb.XchainOuterClass.PreExecWithSelectUTXOResponse preExecWithSelectUTXO(com.bsnbase.sdk.util.pb.XchainOuterClass.PreExecWithSelectUTXORequest request) {
            return blockingUnaryCall(
                    getChannel(), getPreExecWithSelectUTXOMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * Native code deploy interface
         * </pre>
         */
        public com.bsnbase.sdk.util.pb.XchainOuterClass.DeployNativeCodeResponse deployNativeCode(com.bsnbase.sdk.util.pb.XchainOuterClass.DeployNativeCodeRequest request) {
            return blockingUnaryCall(
                    getChannel(), getDeployNativeCodeMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * Native code status
         * </pre>
         */
        public com.bsnbase.sdk.util.pb.XchainOuterClass.NativeCodeStatusResponse nativeCodeStatus(com.bsnbase.sdk.util.pb.XchainOuterClass.NativeCodeStatusRequest request) {
            return blockingUnaryCall(
                    getChannel(), getNativeCodeStatusMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         *  DposCandidates get all candidates of the tdpos consensus
         * </pre>
         */
        public com.bsnbase.sdk.util.pb.XchainOuterClass.DposCandidatesResponse dposCandidates(com.bsnbase.sdk.util.pb.XchainOuterClass.DposCandidatesRequest request) {
            return blockingUnaryCall(
                    getChannel(), getDposCandidatesMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         *  DposNominateRecords get all records nominated by an user
         * </pre>
         */
        public com.bsnbase.sdk.util.pb.XchainOuterClass.DposNominateRecordsResponse dposNominateRecords(com.bsnbase.sdk.util.pb.XchainOuterClass.DposNominateRecordsRequest request) {
            return blockingUnaryCall(
                    getChannel(), getDposNominateRecordsMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         *  DposNomineeRecords get nominated record of a candidate
         * </pre>
         */
        public com.bsnbase.sdk.util.pb.XchainOuterClass.DposNomineeRecordsResponse dposNomineeRecords(com.bsnbase.sdk.util.pb.XchainOuterClass.DposNomineeRecordsRequest request) {
            return blockingUnaryCall(
                    getChannel(), getDposNomineeRecordsMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         *  DposVoteRecords get all vote records voted by an user
         * </pre>
         */
        public com.bsnbase.sdk.util.pb.XchainOuterClass.DposVoteRecordsResponse dposVoteRecords(com.bsnbase.sdk.util.pb.XchainOuterClass.DposVoteRecordsRequest request) {
            return blockingUnaryCall(
                    getChannel(), getDposVoteRecordsMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         *  DposVotedRecords get all vote records of a candidate
         * </pre>
         */
        public com.bsnbase.sdk.util.pb.XchainOuterClass.DposVotedRecordsResponse dposVotedRecords(com.bsnbase.sdk.util.pb.XchainOuterClass.DposVotedRecordsRequest request) {
            return blockingUnaryCall(
                    getChannel(), getDposVotedRecordsMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         *  DposCheckResults get check results of a specific term
         * </pre>
         */
        public com.bsnbase.sdk.util.pb.XchainOuterClass.DposCheckResultsResponse dposCheckResults(com.bsnbase.sdk.util.pb.XchainOuterClass.DposCheckResultsRequest request) {
            return blockingUnaryCall(
                    getChannel(), getDposCheckResultsMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * DposStatus get dpos status
         * </pre>
         */
        public com.bsnbase.sdk.util.pb.XchainOuterClass.DposStatusResponse dposStatus(com.bsnbase.sdk.util.pb.XchainOuterClass.DposStatusRequest request) {
            return blockingUnaryCall(
                    getChannel(), getDposStatusMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * GetAccountByAK get account sets contain a specific address
         * </pre>
         */
        public com.bsnbase.sdk.util.pb.XchainOuterClass.AK2AccountResponse getAccountByAK(com.bsnbase.sdk.util.pb.XchainOuterClass.AK2AccountRequest request) {
            return blockingUnaryCall(
                    getChannel(), getGetAccountByAKMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * Pre-execution Contracts
         * </pre>
         */
        public com.bsnbase.sdk.util.pb.XchainOuterClass.InvokeRPCResponse preExec(com.bsnbase.sdk.util.pb.XchainOuterClass.InvokeRPCRequest request) {
            return blockingUnaryCall(
                    getChannel(), getPreExecMethod(), getCallOptions(), request);
        }
    }

    /**
     * <pre>
     * Xchain is the main interfaces
     * </pre>
     */
    public static final class XchainFutureStub extends io.grpc.stub.AbstractStub<XchainFutureStub> {
        private XchainFutureStub(io.grpc.Channel channel) {
            super(channel);
        }

        private XchainFutureStub(io.grpc.Channel channel,
                                 io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        protected XchainFutureStub build(io.grpc.Channel channel,
                                         io.grpc.CallOptions callOptions) {
            return new XchainFutureStub(channel, callOptions);
        }

        /**
         * <pre>
         * PostTx post Transaction to a node
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.bsnbase.sdk.util.pb.XchainOuterClass.CommonReply> postTx(
                com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus request) {
            return futureUnaryCall(
                    getChannel().newCall(getPostTxMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<com.bsnbase.sdk.util.pb.XchainOuterClass.AclStatus> queryACL(
                com.bsnbase.sdk.util.pb.XchainOuterClass.AclStatus request) {
            return futureUnaryCall(
                    getChannel().newCall(getQueryACLMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<com.bsnbase.sdk.util.pb.XchainOuterClass.GetAccountContractsResponse> getAccountContracts(
                com.bsnbase.sdk.util.pb.XchainOuterClass.GetAccountContractsRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetAccountContractsMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * QueryTx query Transaction by TxStatus,
         * Bcname and Txid are required for this
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus> queryTx(
                com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus request) {
            return futureUnaryCall(
                    getChannel().newCall(getQueryTxMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * GetBalance get balance of an address,
         * Address is required for this
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus> getBalance(
                com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * GetFrozenBalance get two kinds of balance
         * 1. Still be frozen of an address
         * 2. Available now of an address
         * Address is required for this
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.bsnbase.sdk.util.pb.XchainOuterClass.AddressBalanceStatus> getBalanceDetail(
                com.bsnbase.sdk.util.pb.XchainOuterClass.AddressBalanceStatus request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetBalanceDetailMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * GetFrozenBalance get balance that still be frozen of an address,
         * Address is required for this
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus> getFrozenBalance(
                com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetFrozenBalanceMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * GetBlock get block by blockid and return if the block in trunk or in branch
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.bsnbase.sdk.util.pb.XchainOuterClass.Block> getBlock(
                com.bsnbase.sdk.util.pb.XchainOuterClass.BlockID request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetBlockMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * GetBlockByHeight get block by height and return if the block in trunk or in
         * branch
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.bsnbase.sdk.util.pb.XchainOuterClass.Block> getBlockByHeight(
                com.bsnbase.sdk.util.pb.XchainOuterClass.BlockHeight request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetBlockByHeightMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<com.bsnbase.sdk.util.pb.XchainOuterClass.BCStatus> getBlockChainStatus(
                com.bsnbase.sdk.util.pb.XchainOuterClass.BCStatus request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetBlockChainStatusMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * Get blockchains query blockchains
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.bsnbase.sdk.util.pb.XchainOuterClass.BlockChains> getBlockChains(
                com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetBlockChainsMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * GetSystemStatus query system status
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.bsnbase.sdk.util.pb.XchainOuterClass.SystemsStatusReply> getSystemStatus(
                com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetSystemStatusMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * GetNetURL return net url
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.bsnbase.sdk.util.pb.XchainOuterClass.RawUrl> getNetURL(
                com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetNetURLMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * The new Select utxos interface, which does not require a signature, can support utxo for select accounts
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.bsnbase.sdk.util.pb.XchainOuterClass.UtxoOutput> selectUTXO(
                com.bsnbase.sdk.util.pb.XchainOuterClass.UtxoInput request) {
            return futureUnaryCall(
                    getChannel().newCall(getSelectUTXOMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * PreExecWithSelectUTXO preExec &amp; selectUtxo
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.bsnbase.sdk.util.pb.XchainOuterClass.PreExecWithSelectUTXOResponse> preExecWithSelectUTXO(
                com.bsnbase.sdk.util.pb.XchainOuterClass.PreExecWithSelectUTXORequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getPreExecWithSelectUTXOMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * Native code deploy interface
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.bsnbase.sdk.util.pb.XchainOuterClass.DeployNativeCodeResponse> deployNativeCode(
                com.bsnbase.sdk.util.pb.XchainOuterClass.DeployNativeCodeRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getDeployNativeCodeMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * Native code status
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.bsnbase.sdk.util.pb.XchainOuterClass.NativeCodeStatusResponse> nativeCodeStatus(
                com.bsnbase.sdk.util.pb.XchainOuterClass.NativeCodeStatusRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getNativeCodeStatusMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         *  DposCandidates get all candidates of the tdpos consensus
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.bsnbase.sdk.util.pb.XchainOuterClass.DposCandidatesResponse> dposCandidates(
                com.bsnbase.sdk.util.pb.XchainOuterClass.DposCandidatesRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getDposCandidatesMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         *  DposNominateRecords get all records nominated by an user
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.bsnbase.sdk.util.pb.XchainOuterClass.DposNominateRecordsResponse> dposNominateRecords(
                com.bsnbase.sdk.util.pb.XchainOuterClass.DposNominateRecordsRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getDposNominateRecordsMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         *  DposNomineeRecords get nominated record of a candidate
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.bsnbase.sdk.util.pb.XchainOuterClass.DposNomineeRecordsResponse> dposNomineeRecords(
                com.bsnbase.sdk.util.pb.XchainOuterClass.DposNomineeRecordsRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getDposNomineeRecordsMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         *  DposVoteRecords get all vote records voted by an user
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.bsnbase.sdk.util.pb.XchainOuterClass.DposVoteRecordsResponse> dposVoteRecords(
                com.bsnbase.sdk.util.pb.XchainOuterClass.DposVoteRecordsRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getDposVoteRecordsMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         *  DposVotedRecords get all vote records of a candidate
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.bsnbase.sdk.util.pb.XchainOuterClass.DposVotedRecordsResponse> dposVotedRecords(
                com.bsnbase.sdk.util.pb.XchainOuterClass.DposVotedRecordsRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getDposVotedRecordsMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         *  DposCheckResults get check results of a specific term
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.bsnbase.sdk.util.pb.XchainOuterClass.DposCheckResultsResponse> dposCheckResults(
                com.bsnbase.sdk.util.pb.XchainOuterClass.DposCheckResultsRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getDposCheckResultsMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * DposStatus get dpos status
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.bsnbase.sdk.util.pb.XchainOuterClass.DposStatusResponse> dposStatus(
                com.bsnbase.sdk.util.pb.XchainOuterClass.DposStatusRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getDposStatusMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * GetAccountByAK get account sets contain a specific address
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.bsnbase.sdk.util.pb.XchainOuterClass.AK2AccountResponse> getAccountByAK(
                com.bsnbase.sdk.util.pb.XchainOuterClass.AK2AccountRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetAccountByAKMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * Pre-execution Contracts
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.bsnbase.sdk.util.pb.XchainOuterClass.InvokeRPCResponse> preExec(
                com.bsnbase.sdk.util.pb.XchainOuterClass.InvokeRPCRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getPreExecMethod(), getCallOptions()), request);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final XchainImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(XchainImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_POST_TX:
                    serviceImpl.postTx((com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus) request,
                            (io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.CommonReply>) responseObserver);
                    break;
                case METHODID_QUERY_ACL:
                    serviceImpl.queryACL((com.bsnbase.sdk.util.pb.XchainOuterClass.AclStatus) request,
                            (io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.AclStatus>) responseObserver);
                    break;
                case METHODID_GET_ACCOUNT_CONTRACTS:
                    serviceImpl.getAccountContracts((com.bsnbase.sdk.util.pb.XchainOuterClass.GetAccountContractsRequest) request,
                            (io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.GetAccountContractsResponse>) responseObserver);
                    break;
                case METHODID_QUERY_TX:
                    serviceImpl.queryTx((com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus) request,
                            (io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.TxStatus>) responseObserver);
                    break;
                case METHODID_GET_BALANCE:
                    serviceImpl.getBalance((com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus) request,
                            (io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus>) responseObserver);
                    break;
                case METHODID_GET_BALANCE_DETAIL:
                    serviceImpl.getBalanceDetail((com.bsnbase.sdk.util.pb.XchainOuterClass.AddressBalanceStatus) request,
                            (io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.AddressBalanceStatus>) responseObserver);
                    break;
                case METHODID_GET_FROZEN_BALANCE:
                    serviceImpl.getFrozenBalance((com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus) request,
                            (io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.AddressStatus>) responseObserver);
                    break;
                case METHODID_GET_BLOCK:
                    serviceImpl.getBlock((com.bsnbase.sdk.util.pb.XchainOuterClass.BlockID) request,
                            (io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.Block>) responseObserver);
                    break;
                case METHODID_GET_BLOCK_BY_HEIGHT:
                    serviceImpl.getBlockByHeight((com.bsnbase.sdk.util.pb.XchainOuterClass.BlockHeight) request,
                            (io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.Block>) responseObserver);
                    break;
                case METHODID_GET_BLOCK_CHAIN_STATUS:
                    serviceImpl.getBlockChainStatus((com.bsnbase.sdk.util.pb.XchainOuterClass.BCStatus) request,
                            (io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.BCStatus>) responseObserver);
                    break;
                case METHODID_GET_BLOCK_CHAINS:
                    serviceImpl.getBlockChains((com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn) request,
                            (io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.BlockChains>) responseObserver);
                    break;
                case METHODID_GET_SYSTEM_STATUS:
                    serviceImpl.getSystemStatus((com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn) request,
                            (io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.SystemsStatusReply>) responseObserver);
                    break;
                case METHODID_GET_NET_URL:
                    serviceImpl.getNetURL((com.bsnbase.sdk.util.pb.XchainOuterClass.CommonIn) request,
                            (io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.RawUrl>) responseObserver);
                    break;
                case METHODID_SELECT_UTXO:
                    serviceImpl.selectUTXO((com.bsnbase.sdk.util.pb.XchainOuterClass.UtxoInput) request,
                            (io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.UtxoOutput>) responseObserver);
                    break;
                case METHODID_PRE_EXEC_WITH_SELECT_UTXO:
                    serviceImpl.preExecWithSelectUTXO((com.bsnbase.sdk.util.pb.XchainOuterClass.PreExecWithSelectUTXORequest) request,
                            (io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.PreExecWithSelectUTXOResponse>) responseObserver);
                    break;
                case METHODID_DEPLOY_NATIVE_CODE:
                    serviceImpl.deployNativeCode((com.bsnbase.sdk.util.pb.XchainOuterClass.DeployNativeCodeRequest) request,
                            (io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.DeployNativeCodeResponse>) responseObserver);
                    break;
                case METHODID_NATIVE_CODE_STATUS:
                    serviceImpl.nativeCodeStatus((com.bsnbase.sdk.util.pb.XchainOuterClass.NativeCodeStatusRequest) request,
                            (io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.NativeCodeStatusResponse>) responseObserver);
                    break;
                case METHODID_DPOS_CANDIDATES:
                    serviceImpl.dposCandidates((com.bsnbase.sdk.util.pb.XchainOuterClass.DposCandidatesRequest) request,
                            (io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.DposCandidatesResponse>) responseObserver);
                    break;
                case METHODID_DPOS_NOMINATE_RECORDS:
                    serviceImpl.dposNominateRecords((com.bsnbase.sdk.util.pb.XchainOuterClass.DposNominateRecordsRequest) request,
                            (io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.DposNominateRecordsResponse>) responseObserver);
                    break;
                case METHODID_DPOS_NOMINEE_RECORDS:
                    serviceImpl.dposNomineeRecords((com.bsnbase.sdk.util.pb.XchainOuterClass.DposNomineeRecordsRequest) request,
                            (io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.DposNomineeRecordsResponse>) responseObserver);
                    break;
                case METHODID_DPOS_VOTE_RECORDS:
                    serviceImpl.dposVoteRecords((com.bsnbase.sdk.util.pb.XchainOuterClass.DposVoteRecordsRequest) request,
                            (io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.DposVoteRecordsResponse>) responseObserver);
                    break;
                case METHODID_DPOS_VOTED_RECORDS:
                    serviceImpl.dposVotedRecords((com.bsnbase.sdk.util.pb.XchainOuterClass.DposVotedRecordsRequest) request,
                            (io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.DposVotedRecordsResponse>) responseObserver);
                    break;
                case METHODID_DPOS_CHECK_RESULTS:
                    serviceImpl.dposCheckResults((com.bsnbase.sdk.util.pb.XchainOuterClass.DposCheckResultsRequest) request,
                            (io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.DposCheckResultsResponse>) responseObserver);
                    break;
                case METHODID_DPOS_STATUS:
                    serviceImpl.dposStatus((com.bsnbase.sdk.util.pb.XchainOuterClass.DposStatusRequest) request,
                            (io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.DposStatusResponse>) responseObserver);
                    break;
                case METHODID_GET_ACCOUNT_BY_AK:
                    serviceImpl.getAccountByAK((com.bsnbase.sdk.util.pb.XchainOuterClass.AK2AccountRequest) request,
                            (io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.AK2AccountResponse>) responseObserver);
                    break;
                case METHODID_PRE_EXEC:
                    serviceImpl.preExec((com.bsnbase.sdk.util.pb.XchainOuterClass.InvokeRPCRequest) request,
                            (io.grpc.stub.StreamObserver<com.bsnbase.sdk.util.pb.XchainOuterClass.InvokeRPCResponse>) responseObserver);
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(
                io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                default:
                    throw new AssertionError();
            }
        }
    }
}
