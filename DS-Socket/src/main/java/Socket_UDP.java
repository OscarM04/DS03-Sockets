import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Socket_UDP {

    private DatagramSocket socket;
    private byte[] buffer;
    private boolean connected = false;
    private SimpleConsoleLogger LOGGER;

    public Socket_UDP(boolean showlogs){
        this.LOGGER = new SimpleConsoleLogger(showlogs);
    }

    public boolean startConnection( int port, int length) {
        try {
            this.socket = new DatagramSocket( port);
            this.buffer = new byte[length];
            this.connected = true;
        } catch (SocketException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean isConnected() {
        return connected;
    }

    public String getData(){
        String received = null;
        DatagramPacket packet = new DatagramPacket( this.buffer, this.buffer.length);
        try {
            this.socket.receive( packet);
            received = new String( packet.getData(), 0, packet.getLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.LOGGER.LOG( "Recieved message in UDP port: "+received);
        return received;
    }

    public void stopConnection(){
        this.socket.close();
        this.connected = false;
    }
}
