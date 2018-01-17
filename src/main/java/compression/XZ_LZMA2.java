package compression;

import org.tukaani.xz.LZMA2Options;
import org.tukaani.xz.XZInputStream;
import org.tukaani.xz.XZOutputStream;

import java.io.*;

public class XZ_LZMA2 {

    public static  byte[] compress(String inputString) throws IOException {
        if ((inputString == null) || (inputString.length() == 0)) {
            return null;
        }
        ByteArrayOutputStream xzOutput = new ByteArrayOutputStream();
        XZOutputStream xzStream = new XZOutputStream(xzOutput, new LZMA2Options(LZMA2Options.PRESET_MAX));
        xzStream.write(inputString.getBytes());
        xzStream.close();
        return xzOutput.toByteArray();
    }

    public static  String decompress(byte[] compressedData) throws IOException {
        if ((compressedData == null) || (compressedData.length == 0)) {
            return "";
        }
        XZInputStream xzInputStream = new XZInputStream(new ByteArrayInputStream(compressedData));
        byte firstByte = (byte) xzInputStream.read();
        byte[] buffer = new byte[xzInputStream.available()];
        buffer[0] = firstByte;
        xzInputStream.read(buffer, 1, buffer.length - 2);
        xzInputStream.close();
        return new String(buffer);
    }
}
