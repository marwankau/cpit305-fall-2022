package lecture20.udp.chat.instance1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class Receiver extends Thread {
    DatagramSocket dgs;
    Sender sender;

    public Receiver(DatagramSocket dgs, Sender sender) {
        this.dgs = dgs;
        this.sender = sender;
    }
    
    @Override
    public void run() {
        byte[] data = new byte[1024];
        DatagramPacket pkt = new DatagramPacket(data, data.length);
        try {
            dgs.receive(pkt);
            sender.port = pkt.getPort();
            sender.inet = pkt.getAddress();
            System.out.println("a peer is connect");
            sender.start();
            while (true) {
                dgs.receive(pkt);

                
                data = pkt.getData();
                String msg = lecture20.udp.chat.instance2.Receiver.byte2string(data, data.length);

                if (msg.equals("bye")) break;
                
                System.out.println(msg);
            }
        } catch (IOException e) {}

    }
}
