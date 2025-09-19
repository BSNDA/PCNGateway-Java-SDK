package com.bsnbase.sdk.util.trans;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bsnbase.sdk.util.exception.GlobalException;
import org.fisco.bcos.web3j.abi.FunctionEncoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.*;
import org.fisco.bcos.web3j.abi.datatypes.generated.*;
import org.fisco.bcos.web3j.crypto.*;
import org.fisco.bcos.web3j.crypto.gm.sm2.SM2Sign;
import org.fisco.bcos.web3j.protocol.core.methods.response.AbiDefinition;
import org.fisco.bcos.web3j.rlp.RlpDecoder;
import org.fisco.bcos.web3j.rlp.RlpList;
import org.fisco.bcos.web3j.rlp.RlpString;
import org.fisco.bcos.web3j.tx.gas.DefaultGasProvider;
import org.fisco.bcos.web3j.tx.txdecode.BaseException;
import org.fisco.bcos.web3j.tx.txdecode.ConstantCode;
import org.fisco.bcos.web3j.tx.txdecode.ContractAbiUtil;
import org.fisco.bcos.web3j.utils.ByteUtil;
import org.fisco.bcos.web3j.utils.Numeric;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;

public class AbiUtil {

    public static List<String> getFuncOutputType(final AbiDefinition abiDefinition) {
        final List<String> outputList = new ArrayList<String>();
        final List<AbiDefinition.NamedType> outputs = abiDefinition.getOutputs();
        for (final AbiDefinition.NamedType output : outputs) {
            outputList.add(output.getType());
        }
        return outputList;
    }

    public static List<Type> inputFormat(final List<String> funcInputTypes, final List<Object> params) throws BaseException {
        final List<Type> finalInputs = new ArrayList<Type>();
        for (int i = 0; i < funcInputTypes.size(); ++i) {
            Class<? extends Type> inputType = null;
            Object input = null;
            if (funcInputTypes.get(i).indexOf("[") != -1 && funcInputTypes.get(i).indexOf("]") != -1) {
                final List<Object> arrList = new ArrayList<Object>(Arrays.asList(params.get(i).toString().split(",")));
                final List<Type> arrParams = new ArrayList<Type>();
                for (int j = 0; j < arrList.size(); ++j) {
                    inputType = (Class<? extends Type>) AbiTypes.getType(funcInputTypes.get(i).substring(0, funcInputTypes.get(i).indexOf("[")));
                    input = parseByType(funcInputTypes.get(i).substring(0, funcInputTypes.get(i).indexOf("[")), arrList.get(j).toString());
                    arrParams.add(generateClassFromInput(input.toString(), inputType));
                }
                finalInputs.add(new DynamicArray(arrParams));
            } else {
                inputType = (Class<? extends Type>) AbiTypes.getType(funcInputTypes.get(i));
                input = parseByType(funcInputTypes.get(i), params.get(i).toString());
                finalInputs.add(generateClassFromInput(input.toString(), inputType));
            }
        }
        return finalInputs;
    }


    public static <T extends Type> T generateClassFromInput(final String input, final Class<T> type) throws BaseException {
        try {
            if (Address.class.isAssignableFrom(type)) {
                return (T) new Address(input);
            }
            if (NumericType.class.isAssignableFrom(type)) {
                return encodeNumeric(input, type);
            }
            if (Bool.class.isAssignableFrom(type)) {
                return (T) new Bool(Boolean.valueOf(input));
            }
            if (Utf8String.class.isAssignableFrom(type)) {
                return (T) new Utf8String(input);
            }
            if (Bytes.class.isAssignableFrom(type)) {
                return encodeBytes(input, type);
            }
            if (DynamicBytes.class.isAssignableFrom(type)) {
                return (T) new DynamicBytes(input.getBytes());
            }
            throw new BaseException(201201, String.format("type:%s unsupported encoding", type.getName()));
        } catch (Exception e) {
            throw new BaseException(21200, "contract funcParam is error");
        }
    }

    public static <T extends Type> T encodeNumeric(final String input, final Class<T> type) throws BaseException {
        try {
            final BigInteger numericValue = new BigInteger(input);
            return type.getConstructor(BigInteger.class).newInstance(numericValue);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex2) {
            throw new BaseException(201203, String.format("unable to create instance of type:%s", type.getName()));
        }
    }

    public static <T extends Type> T encodeBytes(final String input, final Class<T> type) throws BaseException {
        try {
            final byte[] bytes = Numeric.hexStringToByteArray(input);
            return type.getConstructor(byte[].class).newInstance(bytes);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex2) {
            throw new BaseException(201203, String.format("unable to create instance of type:%s", type.getName()));
        }
    }

    public static String transactionAssembleForMethodInvoke(String contractAbi, int groupId, BigInteger blockNumber, String contractAddress, String funcName, List<Object> funcParam) throws IOException, BaseException {
        AbiDefinition abiDefinition = getFunctionAbiDefinition(funcName, contractAbi);
        if (Objects.isNull(abiDefinition)) {
            throw new GlobalException("contract funcName is error");
        } else {
            List<String> funcInputTypes = ContractAbiUtil.getFuncInputType(abiDefinition);
            if (funcParam == null) {
                funcParam = new ArrayList();
            }

            if (funcInputTypes.size() != ((List) funcParam).size()) {
                throw new GlobalException("contract funcParam size is error");
            } else {
                List<Type> finalInputs = AbiUtil.inputFormat(funcInputTypes, (List) funcParam);
                List<String> funOutputTypes = getFuncOutputType(abiDefinition);
                List<TypeReference<?>> finalOutputs = outputFormat(funOutputTypes);
                boolean isConstant = abiDefinition.isConstant();
                Function function = new Function(funcName, finalInputs, finalOutputs);
                String encodedFunction = FunctionEncoder.encode(function);
                if (isConstant) {
                    return encodedFunction;
                } else {
                    SecureRandom random = new SecureRandom();
                    BigInteger randomid = new BigInteger(250, random);
                    BigInteger blockNumberLimit = blockNumber.add(new BigInteger(String.valueOf(600L)));
                    ExtendedRawTransaction extendedRawTransaction = ExtendedRawTransaction.createTransaction(randomid, DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT, blockNumberLimit, contractAddress, BigInteger.ZERO, encodedFunction, new BigInteger("1"), BigInteger.valueOf((long) groupId), "");
                    byte[] encodedTransaction = ExtendedTransactionEncoder.encode(extendedRawTransaction);
                    String encodedDataStr = Numeric.toHexString(encodedTransaction);
                    return encodedDataStr;
                }
            }
        }
    }

    public static AbiDefinition getFunctionAbiDefinition(String functionName, String contractAbi) {
        JSONArray abiArr = JSONArray.parseArray(contractAbi);
        AbiDefinition result = null;
        Iterator var4 = abiArr.iterator();

        while (var4.hasNext()) {
            Object object = var4.next();
            AbiDefinition abiDefinition = (AbiDefinition) JSON.parseObject(object.toString(), AbiDefinition.class);
            if ("function".equals(abiDefinition.getType()) && functionName.equals(abiDefinition.getName())) {
                result = abiDefinition;
                break;
            }
        }

        return result;
    }


    public static List<TypeReference<?>> outputFormat(List<String> funOutputTypes) throws BaseException {
        List<TypeReference<?>> finalOutputs = new ArrayList();

        for (int i = 0; i < funOutputTypes.size(); ++i) {
            Class<? extends Type> outputType = null;
            TypeReference<?> typeReference = null;
            if ((funOutputTypes.get(i)).indexOf("[") != -1 && (funOutputTypes.get(i)).indexOf("]") != -1) {
                typeReference = AbiUtil.getArrayType((funOutputTypes.get(i)).substring(0, (funOutputTypes.get(i)).indexOf("[")));
            } else {
                outputType = (Class<? extends Type>) AbiTypes.getType(funOutputTypes.get(i));
                typeReference = TypeReference.create(outputType);
            }

            finalOutputs.add(typeReference);
        }

        return finalOutputs;
    }

    public static String signMessageByEncryptType(String hexMessage, ECKeyPair keyPair, int encryptType) {
        byte[] messageByte = ByteUtil.hexStringToBytes(hexMessage);
        Sign.SignatureData signatureData;
        if (encryptType == 1) {
            signatureData = SM2Sign.sign(messageByte, keyPair);
        } else {
            ECDSASign ecdsaSign = new ECDSASign();
            signatureData = ecdsaSign.signMessage(messageByte, keyPair);
        }

        ExtendedRawTransaction extendedRawTransaction = decode(hexMessage);
        byte[] signedMessage = ExtendedTransactionEncoder.encode(extendedRawTransaction, signatureData);
        String signMsg = Numeric.toHexString(signedMessage);
        return signMsg;
    }

    public static ExtendedRawTransaction decode(String hexTransaction) {
        byte[] transaction = Numeric.hexStringToByteArray(hexTransaction);
        RlpList rlpList = RlpDecoder.decode(transaction);
        RlpList values = (RlpList) rlpList.getValues().get(0);
        BigInteger randomid = ((RlpString) values.getValues().get(0)).asPositiveBigInteger();
        BigInteger gasPrice = ((RlpString) values.getValues().get(1)).asPositiveBigInteger();
        BigInteger gasLimit = ((RlpString) values.getValues().get(2)).asPositiveBigInteger();
        BigInteger blockLimit = ((RlpString) values.getValues().get(3)).asPositiveBigInteger();
        String to = ((RlpString) values.getValues().get(4)).asString();
        BigInteger value = ((RlpString) values.getValues().get(5)).asPositiveBigInteger();
        String data = ((RlpString) values.getValues().get(6)).asString();
        BigInteger chainId = ((RlpString) values.getValues().get(7)).asPositiveBigInteger();
        BigInteger groupId = ((RlpString) values.getValues().get(8)).asPositiveBigInteger();
        String extraData = ((RlpString) values.getValues().get(9)).asString();
        if (values.getValues().size() > 10) {
            byte v = ((RlpString) values.getValues().get(10)).getBytes()[0];
            byte[] r = Numeric.toBytesPadded(Numeric.toBigInt(((RlpString) values.getValues().get(11)).getBytes()), 32);
            byte[] s = Numeric.toBytesPadded(Numeric.toBigInt(((RlpString) values.getValues().get(12)).getBytes()), 32);
            Sign.SignatureData signatureData = new Sign.SignatureData(v, r, s);
            return new SignedExtendedRawTransaction(randomid, gasPrice, gasLimit, blockLimit, to, value, data, chainId, groupId, extraData, signatureData);
        } else {
            return ExtendedRawTransaction.createTransaction(randomid, gasPrice, gasLimit, blockLimit, to, value, data, chainId, groupId, extraData);
        }
    }

    public static Object parseByType(String type, String value) throws BaseException, BaseException {
        try {
            byte var3 = -1;
            switch (type.hashCode()) {
                case -1374008026:
                    if ("bytes1".equals(type)) {
                        var3 = 67;
                    }
                    break;
                case -1374008025:
                    if ("bytes2".equals(type)) {
                        var3 = 68;
                    }
                    break;
                case -1374008024:
                    if ("bytes3".equals(type)) {
                        var3 = 69;
                    }
                    break;
                case -1374008023:
                    if ("bytes4".equals(type)) {
                        var3 = 70;
                    }
                    break;
                case -1374008022:
                    if ("bytes5".equals(type)) {
                        var3 = 71;
                    }
                    break;
                case -1374008021:
                    if ("bytes6".equals(type)) {
                        var3 = 72;
                    }
                    break;
                case -1374008020:
                    if ("bytes7".equals(type)) {
                        var3 = 73;
                    }
                    break;
                case -1374008019:
                    if ("bytes8".equals(type)) {
                        var3 = 74;
                    }
                    break;
                case -1374008018:
                    if ("bytes9".equals(type)) {
                        var3 = 75;
                    }
                    break;
                case -1183814746:
                    if ("int104".equals(type)) {
                        var3 = 28;
                    }
                    break;
                case -1183814717:
                    if ("int112".equals(type)) {
                        var3 = 30;
                    }
                    break;
                case -1183814688:
                    if ("int120".equals(type)) {
                        var3 = 32;
                    }
                    break;
                case -1183814680:
                    if ("int128".equals(type)) {
                        var3 = 34;
                    }
                    break;
                case -1183814651:
                    if ("int136".equals(type)) {
                        var3 = 36;
                    }
                    break;
                case -1183814622:
                    if ("int144".equals(type)) {
                        var3 = 38;
                    }
                    break;
                case -1183814593:
                    if ("int152".equals(type)) {
                        var3 = 40;
                    }
                    break;
                case -1183814564:
                    if ("int160".equals(type)) {
                        var3 = 42;
                    }
                    break;
                case -1183814556:
                    if ("int168".equals(type)) {
                        var3 = 44;
                    }
                    break;
                case -1183814527:
                    if ("int176".equals(type)) {
                        var3 = 46;
                    }
                    break;
                case -1183814498:
                    if ("int184".equals(type)) {
                        var3 = 48;
                    }
                    break;
                case -1183814469:
                    if ("int192".equals(type)) {
                        var3 = 50;
                    }
                    break;
                case -1183813789:
                    if ("int200".equals(type)) {
                        var3 = 52;
                    }
                    break;
                case -1183813781:
                    if ("int208".equals(type)) {
                        var3 = 54;
                    }
                    break;
                case -1183813752:
                    if ("int216".equals(type)) {
                        var3 = 56;
                    }
                    break;
                case -1183813723:
                    if ("int224".equals(type)) {
                        var3 = 58;
                    }
                    break;
                case -1183813694:
                    if ("int232".equals(type)) {
                        var3 = 60;
                    }
                    break;
                case -1183813665:
                    if ("int240".equals(type)) {
                        var3 = 62;
                    }
                    break;
                case -1183813657:
                    if ("int248".equals(type)) {
                        var3 = 64;
                    }
                    break;
                case -1183813628:
                    if ("int256".equals(type)) {
                        var3 = 66;
                    }
                    break;
                case -1147692044:
                    if ("address".equals(type)) {
                        var3 = 0;
                    }
                    break;
                case -891985903:
                    if ("string".equals(type)) {
                        var3 = 2;
                    }
                    break;
                case -844996865:
                    if ("uint16".equals(type)) {
                        var3 = 5;
                    }
                    break;
                case -844996836:
                    if ("uint24".equals(type)) {
                        var3 = 7;
                    }
                    break;
                case -844996807:
                    if ("uint32".equals(type)) {
                        var3 = 9;
                    }
                    break;
                case -844996778:
                    if ("uint40".equals(type)) {
                        var3 = 11;
                    }
                    break;
                case -844996770:
                    if ("uint48".equals(type)) {
                        var3 = 13;
                    }
                    break;
                case -844996741:
                    if ("uint56".equals(type)) {
                        var3 = 15;
                    }
                    break;
                case -844996712:
                    if ("uint64".equals(type)) {
                        var3 = 17;
                    }
                    break;
                case -844996683:
                    if ("uint72".equals(type)) {
                        var3 = 19;
                    }
                    break;
                case -844996654:
                    if ("uint80".equals(type)) {
                        var3 = 21;
                    }
                    break;
                case -844996646:
                    if ("uint88".equals(type)) {
                        var3 = 23;
                    }
                    break;
                case -844996617:
                    if ("uint96".equals(type)) {
                        var3 = 25;
                    }
                    break;
                case -425099173:
                    if ("uint104".equals(type)) {
                        var3 = 27;
                    }
                    break;
                case -425099144:
                    if ("uint112".equals(type)) {
                        var3 = 29;
                    }
                    break;
                case -425099115:
                    if ("uint120".equals(type)) {
                        var3 = 31;
                    }
                    break;
                case -425099107:
                    if ("uint128".equals(type)) {
                        var3 = 33;
                    }
                    break;
                case -425099078:
                    if ("uint136".equals(type)) {
                        var3 = 35;
                    }
                    break;
                case -425099049:
                    if ("uint144".equals(type)) {
                        var3 = 37;
                    }
                    break;
                case -425099020:
                    if ("uint152".equals(type)) {
                        var3 = 39;
                    }
                    break;
                case -425098991:
                    if ("uint160".equals(type)) {
                        var3 = 41;
                    }
                    break;
                case -425098983:
                    if ("uint168".equals(type)) {
                        var3 = 43;
                    }
                    break;
                case -425098954:
                    if ("uint176".equals(type)) {
                        var3 = 45;
                    }
                    break;
                case -425098925:
                    if ("uint184".equals(type)) {
                        var3 = 47;
                    }
                    break;
                case -425098896:
                    if ("uint192".equals(type)) {
                        var3 = 49;
                    }
                    break;
                case -425098216:
                    if ("uint200".equals(type)) {
                        var3 = 51;
                    }
                    break;
                case -425098208:
                    if ("uint208".equals(type)) {
                        var3 = 53;
                    }
                    break;
                case -425098179:
                    if ("uint216".equals(type)) {
                        var3 = 55;
                    }
                    break;
                case -425098150:
                    if ("uint224".equals(type)) {
                        var3 = 57;
                    }
                    break;
                case -425098121:
                    if ("uint232".equals(type)) {
                        var3 = 59;
                    }
                    break;
                case -425098092:
                    if ("uint240".equals(type)) {
                        var3 = 61;
                    }
                    break;
                case -425098084:
                    if ("uint248".equals(type)) {
                        var3 = 63;
                    }
                    break;
                case -425098055:
                    if ("uint256".equals(type)) {
                        var3 = 65;
                    }
                    break;
                case 3029738:
                    if ("bool".equals(type)) {
                        var3 = 1;
                    }
                    break;
                case 3237417:
                    if ("int8".equals(type)) {
                        var3 = 4;
                    }
                    break;
                case 94224491:
                    if ("bytes".equals(type)) {
                        var3 = 99;
                    }
                    break;
                case 100359764:
                    if ("int16".equals(type)) {
                        var3 = 6;
                    }
                    break;
                case 100359793:
                    if ("int24".equals(type)) {
                        var3 = 8;
                    }
                    break;
                case 100359822:
                    if ("int32".equals(type)) {
                        var3 = 10;
                    }
                    break;
                case 100359851:
                    if ("int40".equals(type)) {
                        var3 = 12;
                    }
                    break;
                case 100359859:
                    if ("int48".equals(type)) {
                        var3 = 14;
                    }
                    break;
                case 100359888:
                    if ("int56".equals(type)) {
                        var3 = 16;
                    }
                    break;
                case 100359917:
                    if ("int64".equals(type)) {
                        var3 = 18;
                    }
                    break;
                case 100359946:
                    if ("int72".equals(type)) {
                        var3 = 20;
                    }
                    break;
                case 100359975:
                    if ("int80".equals(type)) {
                        var3 = 22;
                    }
                    break;
                case 100359983:
                    if ("int88".equals(type)) {
                        var3 = 24;
                    }
                    break;
                case 100360012:
                    if ("int96".equals(type)) {
                        var3 = 26;
                    }
                    break;
                case 111289374:
                    if ("uint8".equals(type)) {
                        var3 = 3;
                    }
                    break;
                case 355424202:
                    if ("bytes10".equals(type)) {
                        var3 = 76;
                    }
                    break;
                case 355424203:
                    if ("bytes11".equals(type)) {
                        var3 = 77;
                    }
                    break;
                case 355424204:
                    if ("bytes12".equals(type)) {
                        var3 = 78;
                    }
                    break;
                case 355424205:
                    if ("bytes13".equals(type)) {
                        var3 = 79;
                    }
                    break;
                case 355424206:
                    if ("bytes14".equals(type)) {
                        var3 = 80;
                    }
                    break;
                case 355424207:
                    if ("bytes15".equals(type)) {
                        var3 = 81;
                    }
                    break;
                case 355424208:
                    if ("bytes16".equals(type)) {
                        var3 = 82;
                    }
                    break;
                case 355424209:
                    if ("bytes17".equals(type)) {
                        var3 = 83;
                    }
                    break;
                case 355424210:
                    if ("bytes18".equals(type)) {
                        var3 = 84;
                    }
                    break;
                case 355424211:
                    if ("bytes19".equals(type)) {
                        var3 = 85;
                    }
                    break;
                case 355424233:
                    if ("bytes20".equals(type)) {
                        var3 = 86;
                    }
                    break;
                case 355424234:
                    if ("bytes21".equals(type)) {
                        var3 = 87;
                    }
                    break;
                case 355424235:
                    if ("bytes22".equals(type)) {
                        var3 = 88;
                    }
                    break;
                case 355424236:
                    if ("bytes23".equals(type)) {
                        var3 = 89;
                    }
                    break;
                case 355424237:
                    if ("bytes24".equals(type)) {
                        var3 = 90;
                    }
                    break;
                case 355424238:
                    if ("bytes25".equals(type)) {
                        var3 = 91;
                    }
                    break;
                case 355424239:
                    if ("bytes26".equals(type)) {
                        var3 = 92;
                    }
                    break;
                case 355424240:
                    if ("bytes27".equals(type)) {
                        var3 = 93;
                    }
                    break;
                case 355424241:
                    if ("bytes28".equals(type)) {
                        var3 = 94;
                    }
                    break;
                case 355424242:
                    if ("bytes29".equals(type)) {
                        var3 = 95;
                    }
                    break;
                case 355424264:
                    if ("bytes30".equals(type)) {
                        var3 = 96;
                    }
                    break;
                case 355424265:
                    if ("bytes31".equals(type)) {
                        var3 = 97;
                    }
                    break;
                case 355424266:
                    if ("bytes32".equals(type)) {
                        var3 = 98;
                    }
            }

            switch (var3) {
                case 0:
                    return value;
                case 1:
                    return Boolean.valueOf(value);
                case 2:
                    return value;
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                    return new BigInteger(value);
                case 67:
                case 68:
                case 69:
                case 70:
                case 71:
                case 72:
                case 73:
                case 74:
                case 75:
                case 76:
                case 77:
                case 78:
                case 79:
                case 80:
                case 81:
                case 82:
                case 83:
                case 84:
                case 85:
                case 86:
                case 87:
                case 88:
                case 89:
                case 90:
                case 91:
                case 92:
                case 93:
                case 94:
                case 95:
                case 96:
                case 97:
                case 98:
                case 99:
                    return value;
                default:
                    throw new BaseException(201201, String.format("type:%s unsupported encoding", type));
            }
        } catch (Exception var4) {
            throw new BaseException(ConstantCode.IN_FUNCPARAM_ERROR);
        }
    }


    public static TypeReference<?> getArrayType(String type) throws BaseException {
        byte var2 = -1;
        switch (type.hashCode()) {
            case -1374008026:
                if ("bytes1".equals(type)) {
                    var2 = 67;
                }
                break;
            case -1374008025:
                if ("bytes2".equals(type)) {
                    var2 = 68;
                }
                break;
            case -1374008024:
                if ("bytes3".equals(type)) {
                    var2 = 69;
                }
                break;
            case -1374008023:
                if ("bytes4".equals(type)) {
                    var2 = 70;
                }
                break;
            case -1374008022:
                if ("bytes5".equals(type)) {
                    var2 = 71;
                }
                break;
            case -1374008021:
                if ("bytes6".equals(type)) {
                    var2 = 72;
                }
                break;
            case -1374008020:
                if ("bytes7".equals(type)) {
                    var2 = 73;
                }
                break;
            case -1374008019:
                if ("bytes8".equals(type)) {
                    var2 = 74;
                }
                break;
            case -1374008018:
                if ("bytes9".equals(type)) {
                    var2 = 75;
                }
                break;
            case -1183814746:
                if ("int104".equals(type)) {
                    var2 = 29;
                }
                break;
            case -1183814717:
                if ("int112".equals(type)) {
                    var2 = 31;
                }
                break;
            case -1183814688:
                if ("int120".equals(type)) {
                    var2 = 33;
                }
                break;
            case -1183814680:
                if ("int128".equals(type)) {
                    var2 = 35;
                }
                break;
            case -1183814651:
                if ("int136".equals(type)) {
                    var2 = 37;
                }
                break;
            case -1183814622:
                if ("int144".equals(type)) {
                    var2 = 39;
                }
                break;
            case -1183814564:
                if ("int160".equals(type)) {
                    var2 = 42;
                }
                break;
            case -1183814556:
                if ("int168".equals(type)) {
                    var2 = 44;
                }
                break;
            case -1183814527:
                if ("int176".equals(type)) {
                    var2 = 46;
                }
                break;
            case -1183814498:
                if ("int184".equals(type)) {
                    var2 = 48;
                }
                break;
            case -1183814469:
                if ("int192".equals(type)) {
                    var2 = 50;
                }
                break;
            case -1183813789:
                if ("int200".equals(type)) {
                    var2 = 52;
                }
                break;
            case -1183813781:
                if ("int208".equals(type)) {
                    var2 = 54;
                }
                break;
            case -1183813752:
                if ("int216".equals(type)) {
                    var2 = 56;
                }
                break;
            case -1183813723:
                if ("int224".equals(type)) {
                    var2 = 58;
                }
                break;
            case -1183813694:
                if ("int232".equals(type)) {
                    var2 = 60;
                }
                break;
            case -1183813665:
                if ("int240".equals(type)) {
                    var2 = 62;
                }
                break;
            case -1183813657:
                if ("int248".equals(type)) {
                    var2 = 64;
                }
                break;
            case -1183813628:
                if ("int256".equals(type)) {
                    var2 = 66;
                }
                break;
            case -1147692044:
                if ("address".equals(type)) {
                    var2 = 0;
                }
                break;
            case -891985903:
                if ("string".equals(type)) {
                    var2 = 2;
                }
                break;
            case -844996865:
                if ("uint16".equals(type)) {
                    var2 = 6;
                }
                break;
            case -844996836:
                if ("uint24".equals(type)) {
                    var2 = 8;
                }
                break;
            case -844996807:
                if ("uint32".equals(type)) {
                    var2 = 10;
                }
                break;
            case -844996778:
                if ("uint40".equals(type)) {
                    var2 = 12;
                }
                break;
            case -844996770:
                if ("uint48".equals(type)) {
                    var2 = 14;
                }
                break;
            case -844996741:
                if ("uint56".equals(type)) {
                    var2 = 16;
                }
                break;
            case -844996712:
                if ("uint64".equals(type)) {
                    var2 = 18;
                }
                break;
            case -844996683:
                if ("uint72".equals(type)) {
                    var2 = 20;
                }
                break;
            case -844996654:
                if ("uint80".equals(type)) {
                    var2 = 22;
                }
                break;
            case -844996646:
                if ("uint88".equals(type)) {
                    var2 = 24;
                }
                break;
            case -844996617:
                if ("uint96".equals(type)) {
                    var2 = 26;
                }
                break;
            case -425099173:
                if ("uint104".equals(type)) {
                    var2 = 28;
                }
                break;
            case -425099144:
                if ("uint112".equals(type)) {
                    var2 = 30;
                }
                break;
            case -425099115:
                if ("uint120".equals(type)) {
                    var2 = 32;
                }
                break;
            case -425099107:
                if ("uint128".equals(type)) {
                    var2 = 34;
                }
                break;
            case -425099078:
                if ("uint136".equals(type)) {
                    var2 = 36;
                }
                break;
            case -425099049:
                if ("uint144".equals(type)) {
                    var2 = 38;
                }
                break;
            case -425099020:
                if ("uint152".equals(type)) {
                    var2 = 40;
                }
                break;
            case -425098991:
                if ("uint160".equals(type)) {
                    var2 = 41;
                }
                break;
            case -425098983:
                if ("uint168".equals(type)) {
                    var2 = 43;
                }
                break;
            case -425098954:
                if ("uint176".equals(type)) {
                    var2 = 45;
                }
                break;
            case -425098925:
                if ("uint184".equals(type)) {
                    var2 = 47;
                }
                break;
            case -425098896:
                if ("uint192".equals(type)) {
                    var2 = 49;
                }
                break;
            case -425098216:
                if ("uint200".equals(type)) {
                    var2 = 51;
                }
                break;
            case -425098208:
                if ("uint208".equals(type)) {
                    var2 = 53;
                }
                break;
            case -425098179:
                if ("uint216".equals(type)) {
                    var2 = 55;
                }
                break;
            case -425098150:
                if ("uint224".equals(type)) {
                    var2 = 57;
                }
                break;
            case -425098121:
                if ("uint232".equals(type)) {
                    var2 = 59;
                }
                break;
            case -425098092:
                if ("uint240".equals(type)) {
                    var2 = 61;
                }
                break;
            case -425098084:
                if ("uint248".equals(type)) {
                    var2 = 63;
                }
                break;
            case -425098055:
                if ("uint256".equals(type)) {
                    var2 = 65;
                }
                break;
            case 3029738:
                if ("bool".equals(type)) {
                    var2 = 1;
                }
                break;
            case 3237417:
                if ("int8".equals(type)) {
                    var2 = 5;
                }
                break;
            case 94224491:
                if ("bytes".equals(type)) {
                    var2 = 3;
                }
                break;
            case 100359764:
                if ("int16".equals(type)) {
                    var2 = 7;
                }
                break;
            case 100359793:
                if ("int24".equals(type)) {
                    var2 = 9;
                }
                break;
            case 100359822:
                if ("int32".equals(type)) {
                    var2 = 11;
                }
                break;
            case 100359851:
                if ("int40".equals(type)) {
                    var2 = 13;
                }
                break;
            case 100359859:
                if ("int48".equals(type)) {
                    var2 = 15;
                }
                break;
            case 100359888:
                if ("int56".equals(type)) {
                    var2 = 17;
                }
                break;
            case 100359917:
                if ("int64".equals(type)) {
                    var2 = 19;
                }
                break;
            case 100359946:
                if ("int72".equals(type)) {
                    var2 = 21;
                }
                break;
            case 100359975:
                if ("int80".equals(type)) {
                    var2 = 23;
                }
                break;
            case 100359983:
                if ("int88".equals(type)) {
                    var2 = 25;
                }
                break;
            case 100360012:
                if ("int96".equals(type)) {
                    var2 = 27;
                }
                break;
            case 111289374:
                if ("uint8".equals(type)) {
                    var2 = 4;
                }
                break;
            case 355424202:
                if ("bytes10".equals(type)) {
                    var2 = 76;
                }
                break;
            case 355424203:
                if ("bytes11".equals(type)) {
                    var2 = 77;
                }
                break;
            case 355424204:
                if ("bytes12".equals(type)) {
                    var2 = 78;
                }
                break;
            case 355424205:
                if ("bytes13".equals(type)) {
                    var2 = 79;
                }
                break;
            case 355424206:
                if ("bytes14".equals(type)) {
                    var2 = 80;
                }
                break;
            case 355424207:
                if ("bytes15".equals(type)) {
                    var2 = 81;
                }
                break;
            case 355424208:
                if ("bytes16".equals(type)) {
                    var2 = 82;
                }
                break;
            case 355424209:
                if ("bytes17".equals(type)) {
                    var2 = 83;
                }
                break;
            case 355424210:
                if ("bytes18".equals(type)) {
                    var2 = 84;
                }
                break;
            case 355424211:
                if ("bytes19".equals(type)) {
                    var2 = 85;
                }
                break;
            case 355424233:
                if ("bytes20".equals(type)) {
                    var2 = 86;
                }
                break;
            case 355424234:
                if ("bytes21".equals(type)) {
                    var2 = 87;
                }
                break;
            case 355424235:
                if ("bytes22".equals(type)) {
                    var2 = 88;
                }
                break;
            case 355424236:
                if ("bytes23".equals(type)) {
                    var2 = 89;
                }
                break;
            case 355424237:
                if ("bytes24".equals(type)) {
                    var2 = 90;
                }
                break;
            case 355424238:
                if ("bytes25".equals(type)) {
                    var2 = 91;
                }
                break;
            case 355424239:
                if ("bytes26".equals(type)) {
                    var2 = 92;
                }
                break;
            case 355424240:
                if ("bytes27".equals(type)) {
                    var2 = 93;
                }
                break;
            case 355424241:
                if ("bytes28".equals(type)) {
                    var2 = 94;
                }
                break;
            case 355424242:
                if ("bytes29".equals(type)) {
                    var2 = 95;
                }
                break;
            case 355424264:
                if ("bytes30".equals(type)) {
                    var2 = 96;
                }
                break;
            case 355424265:
                if ("bytes31".equals(type)) {
                    var2 = 97;
                }
                break;
            case 355424266:
                if ("bytes32".equals(type)) {
                    var2 = 98;
                }
        }

        switch (var2) {
            case 0:
                return new TypeReference<DynamicArray<Address>>() {
                };
            case 1:
                return new TypeReference<DynamicArray<Bool>>() {
                };
            case 2:
                return new TypeReference<DynamicArray<Utf8String>>() {
                };
            case 3:
                return new TypeReference<DynamicArray<DynamicBytes>>() {
                };
            case 4:
                return new TypeReference<DynamicArray<Uint8>>() {
                };
            case 5:
                return new TypeReference<DynamicArray<Int8>>() {
                };
            case 6:
                return new TypeReference<DynamicArray<Uint16>>() {
                };
            case 7:
                return new TypeReference<DynamicArray<Int16>>() {
                };
            case 8:
                return new TypeReference<DynamicArray<Uint24>>() {
                };
            case 9:
                return new TypeReference<DynamicArray<Int24>>() {
                };
            case 10:
                return new TypeReference<DynamicArray<Uint32>>() {
                };
            case 11:
                return new TypeReference<DynamicArray<Int32>>() {
                };
            case 12:
                return new TypeReference<DynamicArray<Uint40>>() {
                };
            case 13:
                return new TypeReference<DynamicArray<Int40>>() {
                };
            case 14:
                return new TypeReference<DynamicArray<Uint48>>() {
                };
            case 15:
                return new TypeReference<DynamicArray<Int48>>() {
                };
            case 16:
                return new TypeReference<DynamicArray<Uint56>>() {
                };
            case 17:
                return new TypeReference<DynamicArray<Int56>>() {
                };
            case 18:
                return new TypeReference<DynamicArray<Uint64>>() {
                };
            case 19:
                return new TypeReference<DynamicArray<Int64>>() {
                };
            case 20:
                return new TypeReference<DynamicArray<Uint72>>() {
                };
            case 21:
                return new TypeReference<DynamicArray<Int72>>() {
                };
            case 22:
                return new TypeReference<DynamicArray<Uint80>>() {
                };
            case 23:
                return new TypeReference<DynamicArray<Int80>>() {
                };
            case 24:
                return new TypeReference<DynamicArray<Uint88>>() {
                };
            case 25:
                return new TypeReference<DynamicArray<Int88>>() {
                };
            case 26:
                return new TypeReference<DynamicArray<Uint96>>() {
                };
            case 27:
                return new TypeReference<DynamicArray<Int96>>() {
                };
            case 28:
                return new TypeReference<DynamicArray<Uint104>>() {
                };
            case 29:
                return new TypeReference<DynamicArray<Int104>>() {
                };
            case 30:
                return new TypeReference<DynamicArray<Uint112>>() {
                };
            case 31:
                return new TypeReference<DynamicArray<Int112>>() {
                };
            case 32:
                return new TypeReference<DynamicArray<Uint120>>() {
                };
            case 33:
                return new TypeReference<DynamicArray<Int120>>() {
                };
            case 34:
                return new TypeReference<DynamicArray<Uint128>>() {
                };
            case 35:
                return new TypeReference<DynamicArray<Int128>>() {
                };
            case 36:
                return new TypeReference<DynamicArray<Uint136>>() {
                };
            case 37:
                return new TypeReference<DynamicArray<Int136>>() {
                };
            case 38:
                return new TypeReference<DynamicArray<Uint144>>() {
                };
            case 39:
                return new TypeReference<DynamicArray<Int144>>() {
                };
            case 40:
                return new TypeReference<DynamicArray<Uint152>>() {
                };
            case 41:
                return new TypeReference<DynamicArray<Uint160>>() {
                };
            case 42:
                return new TypeReference<DynamicArray<Int160>>() {
                };
            case 43:
                return new TypeReference<DynamicArray<Uint168>>() {
                };
            case 44:
                return new TypeReference<DynamicArray<Int168>>() {
                };
            case 45:
                return new TypeReference<DynamicArray<Uint176>>() {
                };
            case 46:
                return new TypeReference<DynamicArray<Int176>>() {
                };
            case 47:
                return new TypeReference<DynamicArray<Uint184>>() {
                };
            case 48:
                return new TypeReference<DynamicArray<Int184>>() {
                };
            case 49:
                return new TypeReference<DynamicArray<Uint192>>() {
                };
            case 50:
                return new TypeReference<DynamicArray<Int192>>() {
                };
            case 51:
                return new TypeReference<DynamicArray<Uint200>>() {
                };
            case 52:
                return new TypeReference<DynamicArray<Int200>>() {
                };
            case 53:
                return new TypeReference<DynamicArray<Uint208>>() {
                };
            case 54:
                return new TypeReference<DynamicArray<Int208>>() {
                };
            case 55:
                return new TypeReference<DynamicArray<Uint216>>() {
                };
            case 56:
                return new TypeReference<DynamicArray<Int216>>() {
                };
            case 57:
                return new TypeReference<DynamicArray<Uint224>>() {
                };
            case 58:
                return new TypeReference<DynamicArray<Int224>>() {
                };
            case 59:
                return new TypeReference<DynamicArray<Uint232>>() {
                };
            case 60:
                return new TypeReference<DynamicArray<Int232>>() {
                };
            case 61:
                return new TypeReference<DynamicArray<Uint240>>() {
                };
            case 62:
                return new TypeReference<DynamicArray<Int240>>() {
                };
            case 63:
                return new TypeReference<DynamicArray<Uint248>>() {
                };
            case 64:
                return new TypeReference<DynamicArray<Int248>>() {
                };
            case 65:
                return new TypeReference<DynamicArray<Uint256>>() {
                };
            case 66:
                return new TypeReference<DynamicArray<Int256>>() {
                };
            case 67:
                return new TypeReference<DynamicArray<Bytes1>>() {
                };
            case 68:
                return new TypeReference<DynamicArray<Bytes2>>() {
                };
            case 69:
                return new TypeReference<DynamicArray<Bytes3>>() {
                };
            case 70:
                return new TypeReference<DynamicArray<Bytes4>>() {
                };
            case 71:
                return new TypeReference<DynamicArray<Bytes5>>() {
                };
            case 72:
                return new TypeReference<DynamicArray<Bytes6>>() {
                };
            case 73:
                return new TypeReference<DynamicArray<Bytes7>>() {
                };
            case 74:
                return new TypeReference<DynamicArray<Bytes8>>() {
                };
            case 75:
                return new TypeReference<DynamicArray<Bytes9>>() {
                };
            case 76:
                return new TypeReference<DynamicArray<Bytes10>>() {
                };
            case 77:
                return new TypeReference<DynamicArray<Bytes11>>() {
                };
            case 78:
                return new TypeReference<DynamicArray<Bytes12>>() {
                };
            case 79:
                return new TypeReference<DynamicArray<Bytes13>>() {
                };
            case 80:
                return new TypeReference<DynamicArray<Bytes14>>() {
                };
            case 81:
                return new TypeReference<DynamicArray<Bytes15>>() {
                };
            case 82:
                return new TypeReference<DynamicArray<Bytes16>>() {
                };
            case 83:
                return new TypeReference<DynamicArray<Bytes17>>() {
                };
            case 84:
                return new TypeReference<DynamicArray<Bytes18>>() {
                };
            case 85:
                return new TypeReference<DynamicArray<Bytes19>>() {
                };
            case 86:
                return new TypeReference<DynamicArray<Bytes20>>() {
                };
            case 87:
                return new TypeReference<DynamicArray<Bytes21>>() {
                };
            case 88:
                return new TypeReference<DynamicArray<Bytes22>>() {
                };
            case 89:
                return new TypeReference<DynamicArray<Bytes23>>() {
                };
            case 90:
                return new TypeReference<DynamicArray<Bytes24>>() {
                };
            case 91:
                return new TypeReference<DynamicArray<Bytes25>>() {
                };
            case 92:
                return new TypeReference<DynamicArray<Bytes26>>() {
                };
            case 93:
                return new TypeReference<DynamicArray<Bytes27>>() {
                };
            case 94:
                return new TypeReference<DynamicArray<Bytes28>>() {
                };
            case 95:
                return new TypeReference<DynamicArray<Bytes29>>() {
                };
            case 96:
                return new TypeReference<DynamicArray<Bytes30>>() {
                };
            case 97:
                return new TypeReference<DynamicArray<Bytes31>>() {
                };
            case 98:
                return new TypeReference<DynamicArray<Bytes32>>() {
                };
            default:
                throw new BaseException(201201, String.format("type:%s array unsupported encoding", type));
        }
    }
}
