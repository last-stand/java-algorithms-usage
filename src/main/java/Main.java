import java.security.KeyPair;

import encryption.AES;
import encryption.TripleDES;

import static encryption.RSA.*;

public class Main {
    public static void main(String[] args) throws Exception{
        System.out.println("AES encryption...");
        final String aesSecretKey = "abcd1234";
        String originalString = "Hello World!";
        byte[] iv = AES.generateInitializationVector(); // Needed for CBC or CTR operation mode
        String encryptedAesText = AES.aesEncrypt(aesSecretKey, originalString);
        System.out.println("EncryptedAESText: " + encryptedAesText);
        String decryptedAesText = AES.aesDecrypt(aesSecretKey, encryptedAesText);
        System.out.println("DecryptedAESText: " + decryptedAesText);

        System.out.println("\n3DES encryption...");
        final String desSecretKey = "abcd1234";
        String someString = "Hello World Again!";
        String encryptedDesText = TripleDES.tripleDesEncrypt(desSecretKey, someString);
        System.out.println("EncryptedDESText: " + encryptedDesText);
        String decryptedDesText = TripleDES.tripleDesDecrypt(desSecretKey, encryptedDesText);
        System.out.println("DecryptedDESText: " + decryptedDesText);

        System.out.println("\nRSA encryption...");
        KeyPair rsaKeyPair = generateKeyPair() ;
        String shortMessage = "Bye Bye..";
        String encryptedRsaText = rsaEncrypt(shortMessage, rsaKeyPair.getPublic());
        String decryptedRsaText = rsaDecrypt(encryptedRsaText, rsaKeyPair.getPrivate()) ;
        System.out.println("Encrypted text = " + encryptedRsaText.length()) ;
        System.out.println("Decrypted text = " + decryptedRsaText) ;
    }
}
