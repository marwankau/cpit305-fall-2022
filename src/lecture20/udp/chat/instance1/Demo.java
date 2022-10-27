package lecture20.udp.chat.instance1;

import java.io.IOException;
import java.net.DatagramSocket;

public class Demo {
    public static void main(String[] args) throws IOException, InterruptedException {
        DatagramSocket dgs = new DatagramSocket(1950);
        Sender sender = new Sender(dgs);
        Receiver receiver = new Receiver(dgs, sender);
        receiver.start();

        receiver.join();

        dgs.close();
    }
}
