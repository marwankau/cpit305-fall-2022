package lecture20.udp.example1;

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
        
    }
}
