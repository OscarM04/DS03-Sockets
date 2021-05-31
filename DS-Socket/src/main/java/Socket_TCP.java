import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

public class Socket_TCP {

    private Socket socket;
    private boolean connected = false;
    private PrintWriter output;
    private BufferedReader input;
    private SimpleConsoleLogger LOGGER;

    public Socket_TCP(boolean showlogs) {
        this.LOGGER = new SimpleConsoleLogger(showlogs);
    }

    public void startConnection(String ip, int port) throws IOException {
        this.socket = new Socket(ip, port);
        socket.setSoTimeout(10*1000);
        this.output = new PrintWriter( socket.getOutputStream(), true);
        this.input = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );
        this.connected = true;
        this.LOGGER.LOG("Connected to Server: " + ip + ":" + port);
    }

    public String sendMessage(String msg) throws IOException{
        String resp;
        try {
            //System.out.println(msg);
            this.LOGGER.LOG(msg);
            this.output.println(msg);
            resp = this.input.readLine();
        }catch (SocketTimeoutException te){
             throw new SocketTimeoutException();
        }
        if (
                resp.equals("error invalid user name") ||
                        resp.equals("error invalid command") ||
                        resp.equals("error invalid src ip") ||
                        resp.equals("error unvalidated user") ||
                        resp.equals("error invalid checksum format")
        ) {
            throw new IOException(resp);
        }
        this.LOGGER.LOG("Server: " + resp);
        return resp;
    }

    public boolean isConnected() {
        return connected;
    }

    public void stopConnection() throws IOException {
        this.input.close();
        this.output.close();
        this.socket.close();
        this.connected = false;
        this.LOGGER.LOG("Connection to Server Closed");
    }

}
