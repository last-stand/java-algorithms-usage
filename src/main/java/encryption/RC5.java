package encryption;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Arrays;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class RC5 {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static String ALGORITHM_NAME = "RC5";
    private static String MODE_OF_OPERATION = "ECB";
    private static String PADDING_SCHEME = "PKCS5Padding" ;
    private static SecretKey secretKey;
    private final static int RC5_KEYLENGTH = 128;

    public static void setKey(String secret)
    {
        try{
            byte[] key = secret.getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] digestOfPassword = sha.digest(key);
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, RC5_KEYLENGTH);
            secretKey = new SecretKeySpec(keyBytes, ALGORITHM_NAME);
            System.out.println(secretKey.getEncoded());
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String rc5Encrypt(String toEncrypt, String key) throws Exception {
        setKey(key);
        Cipher cipher = Cipher.getInstance(ALGORITHM_NAME + "/" + MODE_OF_OPERATION + "/" + PADDING_SCHEME);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] byteCipherText = cipher.doFinal(toEncrypt.getBytes());
        String strCipherText = new BASE64Encoder().encode(byteCipherText);
        return strCipherText;
    }

    public static String rc5Decrypt(String ecryptedText, String key) throws Exception {
        setKey(key);
        Cipher cipher = Cipher.getInstance(ALGORITHM_NAME + "/" + MODE_OF_OPERATION + "/" + PADDING_SCHEME);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] byteEncryptedText = new BASE64Decoder().decodeBuffer(ecryptedText);
        byte[] decrypted = cipher.doFinal(byteEncryptedText);
        return new String(decrypted);
    }
}
