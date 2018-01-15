package encryption;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

;

public class RSA {
    static int RSA_KEY_LENGTH = 2048;
    static String ALGORITHM_NAME = "RSA" ;
    static String PADDING_SCHEME = "OAEPWITHSHA-512ANDMGF1PADDING" ;
    static String MODE_OF_OPERATION = "ECB" ;

    public static KeyPair generateKeyPair () throws NoSuchAlgorithmException {
        KeyPairGenerator rsaKeyGen = KeyPairGenerator.getInstance(ALGORITHM_NAME) ;
        rsaKeyGen.initialize(RSA_KEY_LENGTH) ;
        KeyPair rsaKeyPair = rsaKeyGen.generateKeyPair() ;
        return rsaKeyPair;
    }

    public static String rsaEncrypt(String message, Key publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM_NAME + "/" + MODE_OF_OPERATION + "/" + PADDING_SCHEME) ;
        cipher.init(Cipher.ENCRYPT_MODE, publicKey) ;
        byte[] cipherTextArray = cipher.doFinal(message.getBytes()) ;
        return Base64.getEncoder().encodeToString(cipherTextArray) ;
    }


    public static String rsaDecrypt(String encryptedMessage, Key privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM_NAME + "/" + MODE_OF_OPERATION + "/" + PADDING_SCHEME) ;
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] encryptedMessageBytes = Base64.getDecoder().decode(encryptedMessage);
        byte[] plainText = cipher.doFinal(encryptedMessageBytes);
        return new String(plainText) ;

    }
}
