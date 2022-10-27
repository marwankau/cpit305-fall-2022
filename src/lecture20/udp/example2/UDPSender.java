package lecture20.udp.example2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSender {
    public static void main(String[] args) throws IOException {
        DatagramSocket dgs = new DatagramSocket();
        
        byte[] data = "Marwan Alsahafi".getBytes();
        DatagramPacket pkt = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 9999);


        dgs.send(pkt);

        System.out.println("Data sent");

        dgs.receive(pkt);

        data = pkt.getData();
        for (int i = 0; i < pkt.getLength(); ++i) {
            System.out.printf("%c", data[i]);
        }
        System.out.println();
        
    }
}
