package lecture18.example2.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1999);

        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        Scanner receiver = new Scanner(in);
        PrintWriter writer = new PrintWriter(out, true);

        String line;

        Scanner keyboard = new Scanner(System.in);
        System.out.print("msg: ");
        line = keyboard.nextLine();
        
        writer.println(line);

        line = receiver.nextLine();
        System.out.printf("server msg: %s\n", line);


        socket.close();
    }
}
