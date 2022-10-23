package lecture18.example4.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        // 1. Create server socket
        ServerSocket server = new ServerSocket(1999);

        System.out.println("Server waiting for connection...");

        while (true) {
            Socket socket = server.accept();
            
            MyThread thread = new MyThread(socket);
            thread.start();
           

        }
    }
}
