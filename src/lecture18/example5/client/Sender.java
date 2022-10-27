package lecture18.example5.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Sender extends Thread {

  Socket socket;

  public Sender(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    String line;

    try {
      OutputStream out = socket.getOutputStream();
      PrintWriter writer = new PrintWriter(out, true);
      
      while (true) {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("msg: ");
        line = keyboard.nextLine();
    
        writer.println(line);
        if (line.equalsIgnoreCase("exit")) break;

      }

      socket.shutdownOutput();
      
    } catch (IOException e) {}
  }
  
}
