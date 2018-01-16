package compression;

import utilities.ReadFile;
import org.xerial.snappy.Snappy;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Gzip Compression.....");
            String fileContent = ReadFile.readTextFile(getFile("sample.txt"));
            String compressedData = GZIPCompression.compress(fileContent);
            System.out.println("Compressed: " + compressedData.toString());
            String decompressedData = GZIPCompression.decompress(compressedData);
            System.out.println("Decompressed: "+decompressedData);

            System.out.println("Snappy data compression library.....");
            String input = "Hello snappy-java! Snappy-java is a JNI-based wrapper of "
                    + "Snappy, a fast compresser/decompresser.\n Really";
            byte[] compressed = Snappy.compress(input.getBytes("UTF-8"));
            byte[] uncompressed = Snappy.uncompress(compressed);

            String result = new String(uncompressed, "UTF-8");
            System.out.println(result);

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String getFile(String filename) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return classLoader.getResource(filename).getPath();
    }

}
