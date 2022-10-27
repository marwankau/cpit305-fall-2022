package lecture18.example5.client;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1999);

        new Sender(socket).start();
        new Receiver(socket).start();
    }
}
