package lecture21.tcpip.filesender;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  public static void main(String[] args) throws IOException {
    final int BUFFER_SIZE = 1024 * 1024; // 1 mb
    byte[] buff = null;
    
    ServerSocket server = new ServerSocket(4444);
    
    Socket socket = server.accept();

    MyDataInputStream receiver = new MyDataInputStream(socket.getInputStream());
    
    while (true) {
      String line = receiver.nextLine();
      if (line == null) break;
      if (line.equals("msg")) {
        line = receiver.nextLine();
        System.out.println(line);
      } else if (line.equals("file")) {
        if (buff == null) buff = new byte[BUFFER_SIZE];  // initialize buffer

        long len = receiver.readLong();
        String filename = receiver.readUTF();
        FileOutputStream fos = new FileOutputStream("src/lecture21/tcpip/filesender/uploads/" + filename);

        // len / BUFFER_SIZE
        int n = (int) len / BUFFER_SIZE;
        for (int i = 0; i < n; i++) {
          partiallyDownload(receiver, fos, BUFFER_SIZE, buff);

          System.out.println("read 1 mb");
        }
        if (len % BUFFER_SIZE != 0) {
          int l = (int) len % BUFFER_SIZE;
          System.out.println(l);
          partiallyDownload(receiver, fos, l, buff);
        }
        System.out.println("File received completely");
        fos.close();
      } else if (line.equals("bye")) {
        break;
      }
    }
    socket.close();

    server.close();
  }

  public static void partiallyDownload(InputStream in, OutputStream out, int len, byte[] buff) throws IOException {
    int x = 0, b = 0, e = len, sum = 0;
    do {
      x = in.read(buff, b, e);
      System.out.printf("x: %d, b: %d, e: %d\n", x, b, e);
      sum += x;
      // read the remaining bytes to complete 1 mb per iteration
      if (sum < len) {
        b = sum;
        e = e - x;
      } else {
        System.out.printf("sum = %d\n", sum);
        break;
      }
    } while (true);
    out.write(buff, 0, len);
  }
}
