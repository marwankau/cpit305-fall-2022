package lecture21.tcpip.obj;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        ServerSocket server = new ServerSocket(4569);

        Socket socket = server.accept();

        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        Employee emp = (Employee) ois.readObject();

        System.out.println(emp);

        socket.close();

        server.close();

    }
}
