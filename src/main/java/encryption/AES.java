package encryption;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class AES {
    private final static int AES_KEYLENGTH = 128;
    private static SecretKeySpec secretKey;
    private static byte[] key;
    private static String ALGORITHM_NAME = "AES" ;
    private static String MODE_OF_OPERATION = "ECB"; /* ECB/CBC/CTR/GCM/CCM */
    private static String PADDING_SCHEME = "PKCS5Padding" ;

    public static void setKey(String secret)
    {
        try {
            key = secret.getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] digestOfPassword = sha.digest(key);
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 32);
            secretKey = new SecretKeySpec(keyBytes, ALGORITHM_NAME);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    // Needed for CBC or CTR operation mode and pass to aesChipher.init()
    public static byte[] generateInitializationVector(){
        byte[] iv = new byte[AES_KEYLENGTH / 8];
        SecureRandom prng = new SecureRandom();
        prng.nextBytes(iv);
        return iv;
    }

    public static String aesEncrypt(String secret, String strDataToEncrypt) {
        try
        {
            setKey(secret);
            Cipher aesCipher = Cipher.getInstance(ALGORITHM_NAME + "/" + MODE_OF_OPERATION + "/" + PADDING_SCHEME);
            aesCipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] byteDataToEncrypt = strDataToEncrypt.getBytes();
            byte[] byteCipherText = aesCipher.doFinal(byteDataToEncrypt);
            String strCipherText = new BASE64Encoder().encode(byteCipherText);
            return strCipherText;
        }
        catch (Exception ex)
        {
            System.out.println("Error while encrypting: " + ex.toString());
        }
        return null;
    }

    public static String aesDecrypt(String secret, String cipherText) {
        try {
            setKey(secret);
            Cipher aesCipher = Cipher.getInstance(ALGORITHM_NAME + "/" + MODE_OF_OPERATION + "/" + PADDING_SCHEME);
            aesCipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] byteCipherText = new BASE64Decoder().decodeBuffer(cipherText);
            byte[] byteDecryptedText = aesCipher.doFinal(byteCipherText);
            return new String(byteDecryptedText);
        }
        catch (Exception ex)
        {
            System.out.println("Error while decrypting: " + ex.toString());
        }
        return null;
    }
}
