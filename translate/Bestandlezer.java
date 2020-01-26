package translate;
import java.io.*;

public class Bestandlezer {

    public static BufferedReader one2three(File file) throws NotAnAA {

        String st = "";
        FileInputStream fileStream = null;
        try {
            ;
            fileStream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((st = br.readLine()) != null) ;
//                    System.out.println(st);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert fileStream != null;
        InputStreamReader input = new InputStreamReader(fileStream);
        BufferedReader reader = new BufferedReader(input);
        String line = null;
        assert st != null;

        return reader;
    }
}
