package utilities;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ReadFile {
    public static String readTextFile(String filename) throws Exception{
        StringBuilder sb = new StringBuilder();
        try {
            InputStreamReader isreader = new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8);

            final BufferedReader bufferedReader = new BufferedReader(isreader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                sb.append(System.getProperty("line.separator"));
            }
            bufferedReader.close();
        }
        catch (Exception ex){
            System.out.println("Error: "+ filename + " file not exist.");
        }
        return sb.toString();
    }
}
