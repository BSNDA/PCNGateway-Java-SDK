package com.bsnbase.sdk.util.aes;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * 对称加密Aes
 */
public class AES {
    public static void main(String[] args) {
        String encodeRules="舍非yy";
        String content="舍非yy";

        System.out.println("加密后的密文为："+AESEncode(encodeRules,content));
        System.out.println("解密后的明文为："+AESDncode(encodeRules,AESEncode(encodeRules,content)));
    }

    /*
     * 加密
     * 1.构造密钥生成器KeyGenerator
     * 2.根据ecnodeRules规则初始化密钥生成器
     * 3.产生密钥
     * 4.创建和初始化密码器
     * 5.内容加密
     * 6.返回字符串
     */
    public static String AESEncode(String encodeRules,String content){
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
	        	/*javax.crypto
								类 KeyGenerator
	        	 * 此类提供（对称）密钥生成器的功能。
	        	 */
            KeyGenerator keygen=KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            keygen.init(128, new SecureRandom(encodeRules.getBytes()));
            //3.产生原始对称密钥
	            /*javax.crypto
								接口 SecretKey
								所有超级接口：
								Key, Serializable
	             * SecretKey
	             * public interface SecretKey extends Key
	             * 秘密（对称）密钥。
	             * 此接口不包含方法或常量。其唯一目的是分组秘密密钥（并为其提供类型安全）。
此接口的提供者实现必须改写继承自 java.lang.Object 的 equals 和 hashCode 方法，
***以便根据底层密钥材料而不是根据引用进行秘密密钥比较***。
实现此接口的密钥以其编码格式（请参阅 getFormat）返回字符串 RAW，并返回作为 getEncoded 方法调用结果的原始密钥字节。
（getFormat 和 getEncoded 方法继承自 java.security.Key 父接口。）
					常用方法：
	             * byte[] getEncoded()
          			返回基本编码格式的密钥，如果此密钥不支持编码，则返回 null。
	             *
	             */
            SecretKey original_key=keygen.generateKey();
            //4.获得原始 对称密钥 的字节数组
            byte [] raw=original_key.getEncoded();
            //5.根据字节数组生成AES密钥
	            /*
	             * SecretKeySpec
	             * public class SecretKeySpec extends Object implements KeySpec, SecretKey
	             * 此类以与 provider 无关的方式指定一个密钥。
此类仅对能表示为   一个字节数组  并且没有任何与之相关联的钥参数的  原始密钥   有用，如，DES 或者 Triple DES 密钥。
	             */
            SecretKey key=new SecretKeySpec(raw, "AES");
            //6.根据指定算法AES自成密码器
	            /*
	             * Cipher
	             * public class Cipher extends Object
	             * 此类为加密和解密提供密码功能。它构成了 Java Cryptographic Extension (JCE) 框架的核心。
	             * 为创建 Cipher 对象，应用程序调用 Cipher 的 getInstance 方法并将所请求转换 的名称传递给它。
 还可以指定提供者的名称（可选）。
    常用方法
  byte[] doFinal()
          结束多部分加密或解密操作（具体取决于此 Cipher 的初始化方式）。

	             */
            Cipher cipher=Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte [] byte_encode=content.getBytes("utf-8");
            //9.根据密码器的初始化方式--加密：将数据加密
            byte [] byte_AES=cipher.doFinal(byte_encode);
            //10.将加密后的数据转换为字符串
            //这里用Base64Encoder中会找不到包
            //解决办法：
            //在项目的Build path中先移除JRE System Library，再添加库JRE System Library，重新编译后就一切正常了。
            String AES_encode=new String(new BASE64Encoder().encode(byte_AES));
            //11.将字符串返回
            return AES_encode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果有错就返加nulll
        return null;
    }

    /*
     * 解密
     * 解密过程：
     * 1.同加密1-4步
     * 2.将加密后的字符串反纺成byte[]数组
     * 3.将加密内容解密
     */
    public static String AESDncode(String encodeRules,String content){
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen=KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            keygen.init(128, new SecureRandom(encodeRules.getBytes()));
            //3.产生原始对称密钥
            SecretKey original_key=keygen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte [] raw=original_key.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key=new SecretKeySpec(raw, "AES");
            //6.根据指定算法AES自成密码器
            Cipher cipher=Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, key);
            //8.将加密并编码后的内容解码成字节数组
            byte [] byte_content= new BASE64Decoder().decodeBuffer(content);
            /*
             * 解密
             */
            byte [] byte_decode=cipher.doFinal(byte_content);
            String AES_decode=new String(byte_decode,"utf-8");
            return AES_decode;
        } catch (Exception e) {
            e.printStackTrace();
        }

        //如果有错就返加nulll
        return null;
    }

}
