package lecture04.text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadText {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("sample.txt");
        InputStreamReader in = new InputStreamReader(fis);
        BufferedReader reader = new BufferedReader(in);


        String line;

        while ((line = reader.readLine()) != null) {
           System.out.println(line);
        }

        reader.close();
    }
}
