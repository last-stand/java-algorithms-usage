package utilities;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ReadFile {
    public static String readTextFile(String filename) throws Exception{
        StringBuilder sb = new StringBuilder();
        try {
            InputStreamReader isreader = new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8);
            Scanner in = new Scanner(isreader);
            while (in.hasNext()) {
                sb.append(in.next());
                sb.append(System.getProperty("line.separator"));
            }
            in.close();
        }
        catch (Exception ex){
            System.out.println("Error: "+ filename + " file not exist.");
        }
        return sb.toString();
    }
}
