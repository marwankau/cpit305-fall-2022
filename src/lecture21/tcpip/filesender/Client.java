package lecture21.tcpip.filesender;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class Client {
  public static void main(String[] args) throws UnknownHostException, IOException {
    Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 4444);
    Scanner keyboard = new Scanner(System.in);
    MyDataOutputStream dos = new MyDataOutputStream(socket.getOutputStream());
    String line;
    final int BUFFER_SIZE = 1024 * 1024; // 1 mb
    byte[] buff = null;
    while (true) {
      System.out.print("Enter your command (msg: send message, file: send file, bye: exit): ");
      line = keyboard.nextLine();

      if (line.equals("msg")) {
        line = keyboard.nextLine();

        dos.println("msg");
        dos.println(line);
      } else if (line.equals("file")) {
        if (buff == null) buff = new byte[BUFFER_SIZE];  // initialize buffer

        JFileChooser chooser = new JFileChooser();
        int btn = chooser.showOpenDialog(null);
        if (btn == JFileChooser.APPROVE_OPTION) {
          dos.println("file");
          dos.writeLong(chooser.getSelectedFile().length());
          dos.writeUTF(chooser.getSelectedFile().getName());
          FileInputStream fis = new FileInputStream(chooser.getSelectedFile());

          while (true) {
            int num_of_read_bytes = fis.read(buff);
            System.out.println(num_of_read_bytes);

            if (num_of_read_bytes == 0) break;
            dos.write(buff, 0, num_of_read_bytes);
            if (num_of_read_bytes < BUFFER_SIZE) break;
          }
          System.out.println("File sent completely");

          fis.close();
        }
      } else if (line.equals("bye")) {
        break;
      }
    }

    socket.close();
  }
}
