package lecture20.udp.chat.instance2;

import java.io.IOException;
import java.net.DatagramSocket;

public class Demo {
    public static void main(String[] args) throws IOException, InterruptedException {
        DatagramSocket dgs = new DatagramSocket();
        Sender sender = new Sender(dgs);
        Receiver receiver = new Receiver(dgs);

        sender.start();
        receiver.start();

        receiver.join();

        dgs.close();
    }
}
