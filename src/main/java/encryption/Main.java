package encryption;

import java.security.KeyPair;

import static encryption.RC5.rc5Decrypt;
import static encryption.RC5.rc5Encrypt;
import static encryption.RC6.rc6Decrypt;
import static encryption.RC6.rc6Encrypt;
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
        System.out.println("Encrypted text = " + encryptedRsaText) ;
        System.out.println("Decrypted text = " + decryptedRsaText) ;

        System.out.println("\nRC5 encryption...");
        String toEncrypt = "The shorter you live, the longer you're dead!";
        String rc5EncryptedText = rc5Encrypt(toEncrypt, "password");
        System.out.println("Encrypted RC5 text = " + rc5EncryptedText);
        String rc5DecryptedText = rc5Decrypt(rc5EncryptedText, "password");
        System.out.println("Decrypted RC5 text: " + rc5DecryptedText);

        System.out.println("\nRC6 encryption...");
        String textToEncrypt = "Oh my god, you're alive!";
        String rc6EncryptedText = rc6Encrypt(textToEncrypt, "password123");
        System.out.println("Encrypted text: " + rc6EncryptedText);
        String rc6DecryptedText = rc6Decrypt(rc6EncryptedText, "password123");
        System.out.println("Decrypted text: " + rc6DecryptedText);
    }
}
