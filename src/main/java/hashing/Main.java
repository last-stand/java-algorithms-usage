package hashing;

import java.security.GeneralSecurityException;
import static hashing.PBKDF2.computePBKDF;

public class Main {
    public static void main(String args[]) {

        String password = "somethingSecret";
        char[] PASSWORD = password.toCharArray();

        String hashedPassword = null ;
        try {
            hashedPassword = computePBKDF(PASSWORD) ;
        } catch(GeneralSecurityException genSecExc) {
            System.out.println(genSecExc.getMessage() + " " + genSecExc.getCause() ) ; System.exit(1) ;
        }
        System.out.println("PDKDF2 = " + hashedPassword) ;
    }
}
