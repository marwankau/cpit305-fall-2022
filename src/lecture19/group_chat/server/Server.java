package lecture19.group_chat.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1999);
        List<PrintWriter> clientsReceivers = new ArrayList<>();

        System.out.println("Server waiting for connection...");

        while (true) {
            Socket socket = server.accept();
            
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            clientsReceivers.add(pw);

            new Receiver(socket, clientsReceivers, pw).start();
        }
    }
}
