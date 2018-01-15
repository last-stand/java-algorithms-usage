package encryption;

import com.sun.tools.javac.util.Convert;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;

public class AES {
    final static int AES_KEYLENGTH = 128;

    public static SecretKey getSecretEncryptionKey() throws Exception{
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(AES_KEYLENGTH);
        SecretKey secKey = generator.generateKey();
        return secKey;
    }

    public static byte[] generateInitializationVector(){
        byte[] iv = new byte[AES_KEYLENGTH / 8];
        SecureRandom prng = new SecureRandom();
        prng.nextBytes(iv);
        return iv;
    }

    public static String encryptText(SecretKey secretKey, String strDataToEncrypt, byte[] iv) throws Exception{
        Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        aesCipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
        byte[] byteDataToEncrypt = strDataToEncrypt.getBytes();
        byte[] byteCipherText = aesCipher.doFinal(byteDataToEncrypt);
        String strCipherText = new BASE64Encoder().encode(byteCipherText);
        return strCipherText;
    }

    public static String decryptText(SecretKey secretKey, String cipherText, byte[] iv) throws Exception{
        Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        aesCipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
        byte[] byteCipherText = new BASE64Decoder().decodeBuffer(cipherText);
        byte[] byteDecryptedText = aesCipher.doFinal(byteCipherText);
        return new String(byteDecryptedText);
    }
}
