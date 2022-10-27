package lecture19.group_chat.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 1999);

        Receiver receiver = new Receiver(socket);
        receiver.start();
        new Sender(socket).start();
    }
}
