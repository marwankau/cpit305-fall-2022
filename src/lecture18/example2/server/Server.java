package lecture18.example2.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        // 1. Create server socket
        ServerSocket server = new ServerSocket(1999);

        System.out.println("Server waiting for connection...");

        while (true) {
            Socket socket = server.accept();
            
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            Scanner receiver = new Scanner(in);
            PrintWriter writer = new PrintWriter(out, true);

            String line;

            line = receiver.nextLine();

            System.out.printf("client msg: %s\n", line);

            Scanner keyboard = new Scanner(System.in);
            System.out.print("msg: ");
            line = keyboard.nextLine();

            writer.println(line);

            socket.close();

        }
    }
}
