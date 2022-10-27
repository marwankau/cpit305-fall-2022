package lecture18.example1.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        // 1. Create server socket
        ServerSocket server = new ServerSocket(1999);

        System.out.println("Server waiting for connection...");
        Socket socket = server.accept();
        
        System.out.println("Welcome to server");

        System.out.println(socket.getInputStream().read());
    }
}
