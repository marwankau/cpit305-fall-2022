package lecture18.example1.client;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1999);

        socket.getOutputStream().write(8);
    }
}
