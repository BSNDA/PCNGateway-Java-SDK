package com.bsnbase.sdk.util.path;

public class PathUtil {

    /**
     * Default get user information interface
     */
    public static final String DEFAULT_GET_APPINFO = "/api/app/getAppInfo";
    /**
     * Default API prefix
     */
    public static final String DEFAULT_API_PREFIX = "/api";


    //***************************** FABRIC HTTP Context *****************************\\


    public static final String FABRIC_API_VERSION = "/fabric/v1";

    //-----------------------------  Chaincode information operation interface -----------------------------\\

    public static final String FABRIC_CHAIN_CONTEXT = DEFAULT_API_PREFIX + FABRIC_API_VERSION + "/chainCode/event";

    /**
     * Chaincode event registration
     * /api/fabric/v1/chainCode/event/register
     */
    public static final String FABRIC_CHAIN_CODE_REGISTER = FABRIC_CHAIN_CONTEXT + "/register";
    /**
     * Query chaincode event
     * /api/fabric/v1/chainCode/event/query
     */
    public static final String FABRIC_CHAIN_CODE_EVENT_QUERY = FABRIC_CHAIN_CONTEXT + "/query";
    /**
     * Remove chaincode event
     * /api/fabric/v1/chainCode/event/remove
     */
    public static final String FABRIC_CHAIN_CODE_EVENT_REMOVE = FABRIC_CHAIN_CONTEXT + "/remove";
    /**
     * block information registration
     * /api/fabric/v1/chainCode/event/blockRegister
     */
    public static final String FABRIC_CHAIN_CODE_EVENT_BLOCK_REGISTER = FABRIC_CHAIN_CONTEXT + "/blockRegister";

    //-----------------------------  Node information operation interface -----------------------------\\

    public static final String FABRIC_NODE_CONTEXT = DEFAULT_API_PREFIX + FABRIC_API_VERSION + "/node";

    /**
     * Invoke chaincode in Key Trust Mode
     * /api/fabric/v1/node/reqChainCode
     */
    public static final String FABRIC_NODE_REQ_CHAIN_CODE = FABRIC_NODE_CONTEXT + "/reqChainCode";
    /**
     * Invoke chaincode in Public Key Upload Mode
     * /api/fabric/v1/node/trans
     */
    public static final String FABRIC_NODE_TRANS = FABRIC_NODE_CONTEXT + "/trans";
    /**
     * Get transaction information
     * /api/fabric/v1/node/getTransaction
     */
    public static final String FABRIC_NODE_GET_TRANSACTION = FABRIC_NODE_CONTEXT + "/getTransaction";
    /**
     * Get block information
     * /api/fabric/v1/node/getBlockInfo
     */
    public static final String FABRIC_NODE_GET_BLOCKINFO = FABRIC_NODE_CONTEXT + "/getBlockInfo";
    /**
     * Get the latest ledger information
     * /api/fabric/v1/node/getLedgerInfo
     */
    public static final String FABRIC_NODE_GET_LEDGER_INFO = FABRIC_NODE_CONTEXT + "/getLedgerInfo";
    /**
     * Get transaction data
     * /api/fabric/v1/node/getTransdata
     */
    public static final String FABRIC_NODE_GET_TRANS_DATA = FABRIC_NODE_CONTEXT + "/getTransdata";
    /**
     * Get block data
     * /api/fabric/v1/node/getBlockData
     */
    public static final String FABRIC_NODE_GET_BLOCK_DATA = FABRIC_NODE_CONTEXT + "/getBlockData";

    //-----------------------------   User information operation interface -----------------------------\\

    public static final String FABRIC_USER_CONTEXT = DEFAULT_API_PREFIX + FABRIC_API_VERSION + "/user";

    /**
     * User registration
     * /api/fabric/v1/user/register
     */
    public static final String FABRIC_USER_REGISTER = FABRIC_USER_CONTEXT + "/register";
    /**
     * User certificate registration in Public Key Upload Mode
     * /api/fabric/v1/user/enroll
     */
    public static final String FABRIC_USER_ENROLL = FABRIC_USER_CONTEXT + "/enroll";


    //***************************** FISCO-BCOS HTTP Context *****************************\\


    public static final String FISCOBCOS_API_VERSION = "/fiscobcos/v1";

    //-----------------------------   Chaincode information operation interface -----------------------------\\

    public static final String FISCOBCOS_EVENT_CONTEXT = DEFAULT_API_PREFIX + FISCOBCOS_API_VERSION + "/event";

    /**
     * Chaincode event registration
     * /api/fiscobcos/v1/event/register
     */
    public static final String FISCOBCOS_EVENT_REGISTER = FISCOBCOS_EVENT_CONTEXT + "/register";
    /**
     * Query chaincode event
     * /api/fiscobcos/v1/event/query
     */
    public static final String FISCOBCOS_EVENT_QUERY = FISCOBCOS_EVENT_CONTEXT + "/query";
    /**
     * Remove chaincode event
     * /api/fiscobcos/v1/event/remove
     */
    public static final String FISCOBCOS_EVENT_REMOVE = FISCOBCOS_EVENT_CONTEXT + "/remove";

    //-----------------------------  Node information operation interface -----------------------------\\

    public static final String FISCOBCOS_NODE_CONTEXT = DEFAULT_API_PREFIX + FISCOBCOS_API_VERSION + "/node";

    /**
     * Invoke the smart contract in Key Trust Mode
     * /api/fiscobcos/v1/node/reqChainCode
     */
    public static final String FISCOBCOS_NODE_REQ_CHAINCODE = FISCOBCOS_NODE_CONTEXT + "/reqChainCode";
    /**
     * Invoke the smart contract in Public Key Upload Mode
     * /api/fiscobcos/v1/node/trans
     */
    public static final String FISCOBCOS_NODE_TRANS = FISCOBCOS_NODE_CONTEXT + "/trans";
    /**
     * Get transaction information
     * /api/fiscobcos/v1/node/getTxInfoByTxHash
     */
    public static final String FISCOBCOS_NODE_GET_TXINFO_BY_TXHASH = FISCOBCOS_NODE_CONTEXT + "/getTxInfoByTxHash";
    /**
     * Get block information
     * /api/fiscobcos/v1/node/getBlockInfo
     */
    public static final String FISCOBCOS_NODE_GET_BLOCK_INFO = FISCOBCOS_NODE_CONTEXT + "/getBlockInfo";
    /**
     * Get the block height
     * /api/fiscobcos/v1/node/getBlockHeight
     */
    public static final String FISCOBCOS_NODE_GET_BLOCK_HEIGHT = FISCOBCOS_NODE_CONTEXT + "/getBlockHeight";
    /**
     * Get the total number of transactions
     * /api/fiscobcos/v1/node/getTxCount
     */
    public static final String FISCOBCOS_NODE_GET_TXCOUNT = FISCOBCOS_NODE_CONTEXT + "/getTxCount";
    /**
     * Get the total number of transactions in the block
     * /api/fiscobcos/v1/node/getTxCountByBlockNumber
     */
    public static final String FISCOBCOS_NODE_GET_TXCOUNT_BY_BLOCK_NUMBER = FISCOBCOS_NODE_CONTEXT + "/getTxCountByBlockNumber";
    /**
     * Get transaction receipt
     * /api/fiscobcos/v1/node/getTxReceiptByTxHash
     */
    public static final String FISCOBCOS_NODE_GET_TXRECEIPT_BY_TXHASH = FISCOBCOS_NODE_CONTEXT + "/getTxReceiptByTxHash";

    //----------------------------- User information operation interface -----------------------------\\

    public static final String FISCOBCOS_USER_CONTEXT = DEFAULT_API_PREFIX + FISCOBCOS_API_VERSION + "/user";

    /**
     * User registration
     * /api/fiscobcos/v1/user/register
     */
    public static final String FISCOBCOS_USER_REGISTER = FISCOBCOS_USER_CONTEXT + "/register";


    //***************************** XUPERCHAIN HTTP Context *****************************\\


    public static final String XUPERCHAIN_API_VERSION = "/xuperchain/v1";

    //-----------------------------   Chaincode information operation interface -----------------------------\\

    public static final String XUPERCHAIN_EVENT_CONTEXT = DEFAULT_API_PREFIX + XUPERCHAIN_API_VERSION + "/event";

    /**
     * Chaincode event registration
     * /api/xuperchain/v1/event/register
     */
    public static final String XUPERCHAIN_EVENT_REGISTER = XUPERCHAIN_EVENT_CONTEXT + "/register";
    /**
     * Query chaincode event
     * /api/xuperchain/v1/event/query
     */
    public static final String XUPERCHAIN_EVENT_QUERY = XUPERCHAIN_EVENT_CONTEXT + "/query";
    /**
     * Remove chaincode event
     * /api/xuperchain/v1/event/remove
     */
    public static final String XUPERCHAIN_EVENT_REMOVE = XUPERCHAIN_EVENT_CONTEXT + "/remove";

    //-----------------------------  Node information operation interface -----------------------------\\

    public static final String XUPERCHAIN_NODE_CONTEXT = DEFAULT_API_PREFIX + XUPERCHAIN_API_VERSION + "/node";

    /**
     * Invoke the smart contract in Key Trust Mode
     * /api/xuperchain/v1/node/reqChainCode
     */
    public static final String XUPERCHAIN_NODE_REQ_CHAIN_CODE = XUPERCHAIN_NODE_CONTEXT + "/reqChainCode";
    /**
     * Invoke the smart contract in Public Key Upload Mode
     * /api/xuperchain/v1/node/trans
     */
    public static final String XUPERCHAIN_NODE_TRANS = XUPERCHAIN_NODE_CONTEXT + "/trans";
    /**
     * Get transaction information
     * /api/xuperchain/v1/node/getTxInfoByTxHash
     */
    public static final String XUPERCHAIN_NODE_GET_TXINFO_BY_TXHASH = XUPERCHAIN_NODE_CONTEXT + "/getTxInfoByTxHash";
    /**
     * Get block information
     * /api/xuperchain/v1/node/getBlockInfo
     */
    public static final String XUPERCHAIN_NODE_GET_BLOCK_INFO = XUPERCHAIN_NODE_CONTEXT + "/getBlockInfo";


    //-----------------------------  User information operation interface -----------------------------\\

    public static final String XUPERCHAIN_USER_CONTEXT = DEFAULT_API_PREFIX + XUPERCHAIN_API_VERSION + "/user";

    /**
     * User registration
     * /api/xuperchain/v1/user/register
     */
    public static final String XUPERCHAIN_USER_REGISTER = XUPERCHAIN_USER_CONTEXT + "/register";


    //***************************** CITA HTTP Context *****************************\\


    public static final String CITA_API_VERSION = "/cita/v1";

    //-----------------------------   Chaincode information operation interface -----------------------------\\

    public static final String CITA_EVENT_CONTEXT = DEFAULT_API_PREFIX + CITA_API_VERSION + "/event";

    /**
     * Chaincode event registration
     * /api/cita/v1/event/register
     */
    public static final String CITA_EVENT_REGISTER = CITA_EVENT_CONTEXT + "/register";
    /**
     * Query chaincode event
     * /api/cita/v1/event/query
     */
    public static final String CITA_EVENT_QUERY = CITA_EVENT_CONTEXT + "/query";
    /**
     * Remove chaincode event
     * /api/cita/v1/event/remove
     */
    public static final String CITA_EVENT_REMOVE = CITA_EVENT_CONTEXT + "/remove";

    //-----------------------------   Node information operation interface -----------------------------\\

    public static final String CITA_NODE_CONTEXT = DEFAULT_API_PREFIX + CITA_API_VERSION + "/node";

    /**
     * Invoke the smart contract in Key Trust Mode
     * /api/cita/v1/node/reqChainCode
     */
    public static final String CITA_NODE_REQ_CHAIN_CODE = CITA_NODE_CONTEXT + "/reqChainCode";
    /**
     * Invoke the smart contract in Public Key Upload Mode
     * /api/cita/v1/node/trans
     */
    public static final String CITA_NODE_TRANS = CITA_NODE_CONTEXT + "/trans";
    /**
     * Get Transaction Receipt
     * /api/cita/v1/node/getTxReceiptByTxHash
     */
    public static final String CITA_NODE_GET_TXRECEIPT_BY_TXHASH = CITA_NODE_CONTEXT + "/getTxReceiptByTxHash";
    /**
     * Get Transaction Information
     * /api/cita/v1/node/getTxInfoByTxHash
     */
    public static final String CITA_NODE_GET_TXINFO_BY_TXHASH = CITA_NODE_CONTEXT + "/getTxInfoByTxHash";
    /**
     * Get block information
     * /api/cita/v1/node/getBlockInfo
     */
    public static final String CITA_NODE_GET_BLOCK_INFO = CITA_NODE_CONTEXT + "/getBlockInfo";
    /**
     * Get the block height
     * /api/cita/v1/node/getBlockHeight
     */
    public static final String CITA_NODE_GET_BLOCK_HEIGHT = CITA_NODE_CONTEXT + "/getBlockHeight";

    //-----------------------------   User information operation interface -----------------------------\\

    public static final String CITA_USER_CONTEXT = DEFAULT_API_PREFIX + CITA_API_VERSION + "/user";

    /**
     * User register interface
     * /api/cita/v1/user/register
     */
    public static final String CITA_USER_REGISTER = CITA_USER_CONTEXT + "/register";


}
