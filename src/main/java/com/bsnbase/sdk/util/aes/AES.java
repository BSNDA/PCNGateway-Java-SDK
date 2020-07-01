package com.bsnbase.sdk.util.aes;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * symmetric encryption Aes
 */
public class AES {
    public static void main(String[] args) {
        String encodeRules="Hello world";
        String content="Hello world";

        System.out.println("encrypted ciphertext："+AESEncode(encodeRules,content));
        System.out.println("decrypted plaintext："+AESDncode(encodeRules,AESEncode(encodeRules,content)));
    }

    /*
     * encryption
     * 1.build KeyGenerator
     * 2.initialize key generator according to ecnodeRules
     * 3.generate a key
     * 4.create and initialzie the password 
     * 5.encrypt the content
     * 6.return string
     */
    public static String AESEncode(String encodeRules,String content){
        try {
            //1. construct a key generator, specified as the AES algorithm, case insensitive
	        	/*javax.crypto
								class KeyGenerator
	        	 * This class provides the functionality of a (symmetric) key generator.
	        	 */
            KeyGenerator keygen=KeyGenerator.getInstance("AES");
            //2. Initialize the key generator according to the ecnodeRules rule
            //Generate a 128-bit random source based on input array of bytes 
            keygen.init(128, new SecureRandom(encodeRules.getBytes()));
            //3.Generate the original symmetric key
	        
            SecretKey original_key=keygen.generateKey();
            //4.get the byte array of the original symmetric key 
            byte [] raw=original_key.getEncoded();
            //5.The AES key is generated from the byte array 
	            /*
	             * SecretKeySpec
	             * public class SecretKeySpec extends Object implements KeySpec, SecretKey
	             * This calss specifies a key in a way independent of the provider. 
This class is only useful for eaw keys represented as a byte array and do not have any key parameters associated with it such as DES or Triple DES keys. 
	             */
            SecretKey key=new SecretKeySpec(raw, "AES");
            //6.AES independent ciphers based on the specified algorithm 
	            /*
	             * Cipher
	             * public class Cipher extends Object
	             * This class facilitates password encryption and decryption. It constitutes the core of the Java Cryptographic Extension (JCE) framework. 
	             * To create a Cipher object, the DApp calls Cipher's getInstance function and passes the name of the requested transformation. 
 Specify the name of the provider (optional). 
    Common method
  byte[] doFinal()
          End a multi-part encryption or decryption operation  (depending on how this Cipher is initialized).

	             */
            Cipher cipher=Cipher.getInstance("AES");
            //7.Initialize the cipher with the first parameter being either the encryption or Decrypt_mode operation, and the second parameter being the KEY used 
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //8.Get the byte array of encrypted content (set to utf-8 in this case), otherwise the content will be decrypted to garble if there is a mix of Chinese and English in the content 
            byte [] byte_encode=content.getBytes("utf-8");
            //9.Encrypt: encrypt the data according to the way the cipher is initialized 
            byte [] byte_AES=cipher.doFinal(byte_encode);
            //10.Convert the encrypted data into a string
            //Package may not be found using Base64Encoder
            //Solution：
            //Remove the JRE System Library from the project's Build path, add JRE System Library, and everything will be fine after recompiling.
            String AES_encode=new String(new BASE64Encoder().encode(byte_AES));
            //11. Return the string
            return AES_encode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return null if error
        return null;
    }

    /*
     * Decryption
     * Steps：
     * 1.Same as Step 1-4 of encryption 
     * 2.The encrypted string is converted to a byte[]array
     * 3.Decrypt the encrypted content
     */
    public static String AESDncode(String encodeRules,String content){
        try {
            //1.build a key generator, specified as the AES algorithm, case insensitive
            KeyGenerator keygen=KeyGenerator.getInstance("AES");
            //2.initialize the key generator according to ecnodeRules
            //generate a 128-bit random source based on input array of bytes
            keygen.init(128, new SecureRandom(encodeRules.getBytes()));
            //3.generate the original symmetric key
            SecretKey original_key=keygen.generateKey();
            //4.get the byte array of the original symmetric key
            byte [] raw=original_key.getEncoded();
            //5.the AES key is generated from the byte array
            SecretKey key=new SecretKeySpec(raw, "AES");
            //6.create a cipher maker based on specified AES algorithm 
            Cipher cipher=Cipher.getInstance("AES");
            //7.initialize the cipher maker, the first parameter is either Encrypt_mode or Decrypt_mode, the second is the KEY used
            cipher.init(Cipher.DECRYPT_MODE, key);
            //8.Decode the encrypted and encoded content into a byte array 
            byte [] byte_content= new BASE64Decoder().decodeBuffer(content);
            /*
             * Decryption
             */
            byte [] byte_decode=cipher.doFinal(byte_content);
            String AES_decode=new String(byte_decode,"utf-8");
            return AES_decode;
        } catch (Exception e) {
            e.printStackTrace();
        }

        // return nulll if error
        return null;
    }

}
