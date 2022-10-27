package lecture20.inet;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Demo {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress inet = InetAddress.getLocalHost();

        System.out.println(inet);
        
        inet = InetAddress.getByName("localhost");
        System.out.println(inet);
    
        inet = InetAddress.getByName("google.com");
        System.out.println(inet);

        inet = InetAddress.getByAddress(new byte[]{127, 0, 0, 1});
        System.out.println(inet);

        InetAddress[] addresses = InetAddress.getAllByName("yahoo.com");

        for (InetAddress address : addresses) {
            System.out.println(address);
        }
    }
}
