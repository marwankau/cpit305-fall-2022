package lecture21.tcpip.filesender;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MyDataOutputStream extends DataOutputStream {

    public MyDataOutputStream(OutputStream out) {
        super(out);
    }

    public void println() throws IOException {
        this.write('\n');
    }

    public void println(String str) throws IOException {
        write(str.getBytes());
        write('\n');
    }
    
}
