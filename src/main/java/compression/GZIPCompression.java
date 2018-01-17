package compression;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPCompression {

    public static String compress(final String str) throws IOException {
        if ((str == null) || (str.length() == 0)) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(byteArrayOutputStream);
        gzip.write(str.getBytes());
        gzip.flush();
        gzip.close();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return new BASE64Encoder().encode(bytes);
    }

    public static String decompress(final String zippedBase64Str) throws IOException {
        byte[] zippedStrBytes = new BASE64Decoder().decodeBuffer(zippedBase64Str);
        final StringBuilder outStr = new StringBuilder();
        if ((zippedStrBytes == null) || (zippedStrBytes.length == 0)) {
            return "";
        }
        if (isCompressed(zippedStrBytes)) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(zippedStrBytes);
            final GZIPInputStream gis = new GZIPInputStream(byteArrayInputStream);
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(gis));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                outStr.append(line);
                outStr.append(System.getProperty("line.separator"));
            }
            byteArrayInputStream.close();
            gis.close();
            bufferedReader.close();
        } else {
            outStr.append(zippedStrBytes);
        }
        return outStr.toString();
    }

    public static boolean isCompressed(final byte[] compressed) {
        return (compressed[0] == (byte) (GZIPInputStream.GZIP_MAGIC)) && (compressed[1] == (byte) (GZIPInputStream.GZIP_MAGIC >> 8));
    }
}