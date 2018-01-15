import encryption.AES;

import javax.crypto.SecretKey;

public class Main {
    public static void main(String[] args) throws Exception{

        // AES Encryption Algorithm
        SecretKey secretKey = AES.getSecretEncryptionKey();
        System.out.println("SecretKey: "+ secretKey);
        byte[] iv = AES.generateInitializationVector();
        String encryptedText = AES.encryptText(secretKey, "Hello world", iv);
        System.out.println("EncryptedText: " + encryptedText);
        String decryptedText = AES.decryptText(secretKey, encryptedText, iv);
        System.out.println("DecryptedText: " + decryptedText);
    }
}
