package lecture21.tcpip.filesender;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyDataInputStream extends DataInputStream {

    public MyDataInputStream(InputStream in) {
        super(in);
    }

    public String nextLine() throws IOException {
        StringBuilder str = new StringBuilder();

        while (true) {
            int c = this.read();
            if (c == '\n') {
                break;
            } else if (c == -1) {
                return null;
            }
            str.append((char) c);
        }


        return str.toString();
    }
    
}
