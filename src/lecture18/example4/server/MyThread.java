package lecture18.example4.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MyThread extends Thread {
  Socket socket;

  public MyThread(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    InputStream in;
    try {
      in = socket.getInputStream();
      
      OutputStream out = socket.getOutputStream();

      Scanner receiver = new Scanner(in);
      PrintWriter writer = new PrintWriter(out, true);

      String line;

      while (true) {
          line = receiver.nextLine();

          System.out.printf("client msg: %s\n", line);

          if (line.equalsIgnoreCase("exit")) break;

          Scanner keyboard = new Scanner(System.in);
          System.out.print("msg: ");
          line = keyboard.nextLine();

          writer.println(line);
      }


      socket.close();
    } catch (IOException e) {
    }
  }

  
}
