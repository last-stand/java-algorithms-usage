package compression;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class DeflaterInflater {

    private static int compressedDataLength;
    private static final int MAX_BYTE_SIZE = 8192;

    public static String compress(String inputString) throws Exception {
        byte[] inputBytes = inputString.getBytes("UTF-8");

        byte[] compressedData = new byte[MAX_BYTE_SIZE];
        Deflater compresser = new Deflater();
        compresser.setInput(inputBytes);
        compresser.finish();
        compressedDataLength = compresser.deflate(compressedData);
        compresser.end();
        return new BASE64Encoder().encode(compressedData);
    }

    public static String decompress(String compressedDataString) throws Exception {
        byte[] compressedStrBytes = new BASE64Decoder().decodeBuffer(compressedDataString);
        byte[] result = new byte[MAX_BYTE_SIZE];
        Inflater decompresser = new Inflater();
        decompresser.setInput(compressedStrBytes, 0, compressedDataLength);
        int resultLength = decompresser.inflate(result);
        decompresser.end();


        String outputString = new String(result, 0, resultLength, "UTF-8");
        return outputString;

    }

    private static String getFile(String filename) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return classLoader.getResource(filename).getPath();
    }
}
