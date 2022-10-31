package lecture21.tcpip.filesender;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class Client {
  public static void main(String[] args) throws UnknownHostException, IOException {
    Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 4444);
    Scanner keyboard = new Scanner(System.in);
    PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
    String line;
    while (true) {
      System.out.print("Enter your command (msg: send message, file: send file, bye: exit): ");
      line = keyboard.nextLine();

      if (line.equals("msg")) {
        line = keyboard.nextLine();

        pw.println("msg");
        pw.println(line);
      } else if (line.equals("file")) {
        JFileChooser chooser = new JFileChooser();
        int btn = chooser.showOpenDialog(null);
        if (btn == JFileChooser.APPROVE_OPTION) {
          pw.println("file");
          dos.writeLong(chooser.getSelectedFile().length());
          dos.writeUTF(chooser.getSelectedFile().getName());
          FileInputStream fis = new FileInputStream(chooser.getSelectedFile());

          while (true) {
            int b = fis.read();
            if (b == -1) break;
            dos.write(b);
          }

          fis.close();
        }
      } else if (line.equals("bye")) {
        break;
      }
    }

    socket.close();
  }
}
