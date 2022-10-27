package lecture18.example5.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class Receiver extends Thread {

  Socket socket;

  public Receiver(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    InputStream in;
    try {
      in = socket.getInputStream();
      Scanner recScanner = new Scanner(in);
      
      String line;

      while (true) {
        line = recScanner.nextLine();
  
        System.out.printf("client msg: %s\n", line);
  
        if (line.equalsIgnoreCase("exit")) break;
      }

      socket.shutdownInput();

    } catch (IOException e) {
    }
  }
  
}
