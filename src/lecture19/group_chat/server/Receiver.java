package lecture19.group_chat.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Receiver extends Thread {

  Socket socket;
  List<PrintWriter> clientsReceivers;
  PrintWriter pw;

  public Receiver(Socket socket, List<PrintWriter> clientsReceivers, PrintWriter pw) {
    this.socket = socket;
    this.clientsReceivers = clientsReceivers;
    this.pw = pw;
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

        for (PrintWriter pw : clientsReceivers) {
          if (pw != this.pw) {
            pw.println(line);
          }
        }
      }

      pw.println("bye");
      clientsReceivers.remove(pw);  // critical Section, it should be handle

      socket.close();

    } catch (IOException e) {
    }
  }
  
}
