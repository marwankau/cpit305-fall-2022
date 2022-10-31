package lecture21.tcpip.obj;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
  public static void main(String[] args) throws UnknownHostException, IOException {
    Scanner keyboard = new Scanner(System.in);

    System.out.print("Enter employee id: ");
    int id = Integer.parseInt(keyboard.nextLine());
    
    System.out.print("Enter employee name: ");
    String name = keyboard.nextLine();
    
    System.out.print("Enter employee salary: ");
    double salary = Double.parseDouble(keyboard.nextLine());

    Employee emp = new Employee(id, name, salary);
    System.out.println(emp);

    Socket socket = new Socket(InetAddress.getByName("localhost"), 4569);

    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

    oos.writeObject(emp);

    socket.close();
  }
}
