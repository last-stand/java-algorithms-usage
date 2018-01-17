package compression;

import utilities.ReadFile;
import org.xerial.snappy.Snappy;

public class Main {
    public static void main(String[] args) {
        try {

            String fileContent = ReadFile.readTextFile(getFile("sample.txt"));
            String input = "Hello snappy-java! Snappy-java is a JNI-based wrapper of "
                    + "Snappy, a fast compresser/decompresser.\n Really";

            System.out.println("Gzip Compression.....");
            String compressedData = GZIPCompression.compress(fileContent);
            System.out.println("Compressed: " + compressedData.toString());
            String decompressedData = GZIPCompression.decompress(compressedData);
            System.out.println("Decompressed: "+decompressedData);

            System.out.println("Snappy data compression library.....");
            byte[] compressed = Snappy.compress(input.getBytes("UTF-8"));
            byte[] uncompressed = Snappy.uncompress(compressed);
            System.out.println("Snappy compressed: " + compressed);
            System.out.println("Snappy decompressed: " + uncompressed);


            System.out.println("Deflater Inflater..");
            String comp = DeflaterInflater.compress(input);
            String uncomp = DeflaterInflater.decompress(comp);
            System.out.println("Deflater decompress:" + uncomp);

            System.out.println("XZ or LZMA2 library.....");
            byte[] xzCompressed = XZ_LZMA2.compress(input);
            System.out.println(input);
            System.out.println("XZ Compressed : "+ xzCompressed);
            String xzDecompressed = XZ_LZMA2.decompress(xzCompressed);
            System.out.println("XZ Decompressed : "+ xzDecompressed);

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
