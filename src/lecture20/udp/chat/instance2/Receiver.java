package lecture20.udp.chat.instance2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Receiver extends Thread {
    DatagramSocket dgs;

    public Receiver(DatagramSocket dgs) {
        this.dgs = dgs;
    }
    
    @Override
    public void run() {
        byte[] data = new byte[1024];
        DatagramPacket pkt = new DatagramPacket(data, data.length);
        try {
            dgs.receive(pkt);
            while (true) {
                dgs.receive(pkt);

                data = pkt.getData();
                String msg = byte2string(data, data.length);

                if (msg.equals("bye")) break;
                
                System.out.println(msg);
            }
        } catch (IOException e) {}

    }
    
    public static String byte2string(byte[] buff, int len) {
        String str = "";
        for (int i = 0; i < buff.length && i < len; i++) {
            if (buff[i] == 0) break;
            str += (char) buff[i];
        }

        return str;
    }
}
