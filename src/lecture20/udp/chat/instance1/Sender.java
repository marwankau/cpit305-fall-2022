package lecture20.udp.chat.instance1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Sender extends Thread {
    DatagramSocket dgs;
    int port = -1;
    InetAddress inet;

    public Sender(DatagramSocket dgs) {
        this.dgs = dgs;
    }

    @Override
    public void run() {
        Scanner keyboard = new Scanner(System.in);
        String msg;

        while (true) {
            System.out.print("msg: ");
            msg = keyboard.nextLine();
            byte[] data = msg.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, inet, port);

            try {
                dgs.send(packet);
            } catch (IOException e) { }

            if (msg.equalsIgnoreCase("bye")) {
                break;
            }
        }
    }
    
}
