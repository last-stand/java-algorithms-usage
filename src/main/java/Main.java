import encryption.AES;

import java.security.KeyPair;

import static encryption.RSA.generateKeyPair;
import static encryption.RSA.rsaDecrypt;
import static encryption.RSA.rsaEncrypt;

public class Main {
    public static void main(String[] args) throws Exception{
        // AES encryption
        final String secretKey = "abcd1234";
        String originalString = "Hello World!";
        byte[] iv = AES.generateInitializationVector();
        String encryptedText = AES.encrypt(secretKey, originalString, iv);
        System.out.println("EncryptedText: " + encryptedText);
        String decryptedText = AES.decrypt(secretKey, encryptedText, iv);
        System.out.println("DecryptedText: " + decryptedText);

        // RSA encryption
        KeyPair rsaKeyPair = generateKeyPair() ;
        String shortMessage = "Bye Bye..";
        String encryptedRsaText = rsaEncrypt(shortMessage, rsaKeyPair.getPublic());
        String decryptedRsaText = rsaDecrypt(encryptedRsaText, rsaKeyPair.getPrivate()) ;
        System.out.println("Encrypted text = " + encryptedRsaText.length()) ;
        System.out.println("Decrypted text = " + decryptedRsaText) ;
    }
}
