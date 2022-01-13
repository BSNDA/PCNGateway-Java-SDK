package com.bsnbase.sdk.util.aes;

import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;


/**
 * Symmetric encryption Aes
 */
public class AES {
    public static void main(String[] args) {
        String encodeRules = "test";
        String content = "test";

        System.out.println("The encrypted ciphertext is：" + AESEncode(encodeRules, content));
        System.out.println("The decrypted plaintext is：" + AESDncode(encodeRules, AESEncode(encodeRules, content)));
    }

    /*
     * Encryption
     * 1. Construct the key generator KeyGenerator
     * 2. Initialize the key generator according to the ecnodeRules rules
     * 3. Generate the key
     * 4. Create and initialize ciphers
     * 5. Encrypt the content
     * 6. Return the string
     */
    public static String AESEncode(String encodeRules, String content) {
        try {
            //1. Construct the key generator, specifying AES algorithm, case-insensitive
	        	/*javax.crypto
								class KeyGenerator
	        	 * This class provides the functionality of a (symmetric) key generator.
	        	 */
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            // 2. Initialize the key generator according to the ecnodeRules rules
            //Generate a 128-bit random source, based on the incoming byte array
            keygen.init(128, new SecureRandom(encodeRules.getBytes()));
            //3. Generate the original symmetric key
	            /*javax.crypto
                Interface SecretKey
                All super interfaces:
                Key, Serializable
	             * SecretKey
	             * public interface SecretKey extends Key
	             * Secret (symmetric) key.
	             * This interface does not contain methods or constants. Its sole purpose is to group secret keys (and provide type safety for them).
The provider implementation of this interface must override the equals and hashCode methods inherited from java.lang.Object,
*** in order to perform secret key comparisons based on the underlying key material rather than by reference***.
Keys that implement this interface return the string RAW in its encoded format (see getFormat) and return the original key bytes as the result of the getEncoded method call.
(The getFormat and getEncoded methods inherit from the java.security.Key parent interface.)
					Commonly used methods:
	             * byte[] getEncoded()
          			Returns the key in the basic encoding format, or null if the key does not support encoding.
	             *
	             */
            SecretKey original_key = keygen.generateKey();
            //4. Get the byte array of the original symmetric key
            byte[] raw = original_key.getEncoded();
            //5. Generate AES key from the byte array
	            /*
	             * SecretKeySpec
	             * public class SecretKeySpec extends Object implements KeySpec, SecretKey
	             * This class specifies a key in a provider-independent manner.
This class is only useful for raw keys that can be represented as a byte array and do not have any key parameters associated with them, e.g., DES or Triple DES keys.
	             */
            SecretKey key = new SecretKeySpec(raw, "AES");
            //6. Self-forming ciphers based on the specified algorithm AES
	            /*
	             * Cipher
	             * public class Cipher extends Object
	             * This class provides cryptographic functions for encryption and decryption. It forms the core of the Java Cryptographic Extension (JCE) framework.
	             * To create a Cipher object, the application calls the getInstance method of the Cipher and passes it the name of the requested transformation.
 The name of the provider can also be specified (optional).
    Commonly used methods
  byte[] doFinal()
          Ends a multipart encryption or decryption operation (depending on how this Cipher is initialized).

	             */
            Cipher cipher = Cipher.getInstance("AES");

            //7. Initialize the cipher, the first parameter is the Encrypt_mode or Decrypt_mode operation, the second parameter is the KEY used
            cipher.init(Cipher.ENCRYPT_MODE, key);

            //8. Get the byte array of encrypted content (set it to utf-8 here), otherwise the content will be decrypted into garbled code if there is Chinese and English mixed with Chinese
            byte[] byte_encode = content.getBytes("utf-8");
            //9. According to the initialization method of the cryptograph - encryption: encrypt the data
            byte[] byte_AES = cipher.doFinal(byte_encode);
            //10. Convert the encrypted data to a string
            // Here the package will not be found in Base64Encoder
            //Solution:
            // Remove the JRE System Library in the Build path of the project first, then add the library JRE System Library, recompile and everything will be fine.
            String AES_encode = new String(Base64.encode(byte_AES));
            //11. Return the string
            return AES_encode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return nulll if there is an error
        return null;
    }

    /*
     * decryption
     * Decryption process:
     * 1. Same as encryption steps 1-4
     * 2. Back-spin the encrypted string into a byte[] array
     * 3. Decrypt the encrypted content
     */
    public static String AESDncode(String encodeRules, String content) {
        try {
            // 1. Construct the key generator, specify the AES algorithm, case-insensitive
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            //2. Initialize the key generator according to the ecnodeRules rules
            //generate a 128-bit random source, based on the incoming byte array
            keygen.init(128, new SecureRandom(encodeRules.getBytes()));
            //3. Generate the original symmetric key
            SecretKey original_key = keygen.generateKey();
            //4. Get the byte array of the original symmetric key
            byte[] raw = original_key.getEncoded();
            //5. Generate AES key according to the byte array
            SecretKey key = new SecretKeySpec(raw, "AES");
            // 6. According to the specified algorithm AES self-forming cipher
            Cipher cipher = Cipher.getInstance("AES");

            //7. Initialize the cipher, the first parameter is the Encrypt_mode or Decrypt_mode operation, the second parameter is the KEY used
            cipher.init(Cipher.DECRYPT_MODE, key);
            //8. Decode the encrypted and encoded content into a byte array
            byte[] byte_content = Base64.decode(content);
            /*
             * Decrypt
             */
            byte[] byte_decode = cipher.doFinal(byte_content);
            String AES_decode = new String(byte_decode, "utf-8");
            return AES_decode;
        } catch (Exception e) {
            e.printStackTrace();
        }

        //return nulll if there is an error
        return null;
    }

}
