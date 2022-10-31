package lecture21.tcpip.filesender;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
  public static void main(String[] args) throws IOException {
    ServerSocket server = new ServerSocket(4444);
    
    Socket socket = server.accept();

    Scanner receiver = new Scanner(socket.getInputStream());
    String line = receiver.nextLine();

    while (true) {
      if (line.equals("msg")) {
        line = receiver.nextLine();
        System.out.println(line);
      } else if (line.equals("file")) {
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        long len = dis.readLong();
        String filename = dis.readUTF();
        FileOutputStream fos = new FileOutputStream("src/lecture21/tcpip/filesender/uploads/" + filename);

        for (int i = 0; i < len; i++) {
          byte b = (byte) dis.read();
          fos.write(b);
        }
        fos.close();
      } else if (line.equals("bye")) {
        break;
      }
    }
    socket.close();

    server.close();
  }
}
