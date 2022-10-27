package lecture19.group_chat.client;

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
        if (line.equalsIgnoreCase("bye")) break;
  
        System.out.println(line);
  
      }

      socket.shutdownInput();

    } catch (IOException e) {
    }
  }
  
}
