import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Properties;

public class App {

    public static void main(String[] args) throws IOException {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "local.properties";

        Properties properties = new Properties();
        properties.load( new FileInputStream(appConfigPath));
        Socket_TCP client = new Socket_TCP( properties.getProperty("show_logs").equals("true"));
        Socket_UDP listenerSocket = new Socket_UDP( properties.getProperty("show_logs").equals("true"));
        try {
            client.startConnection(
                    properties.getProperty("server_host"),
                    Integer.parseInt(properties.getProperty("server_port"))
            );

            client.sendMessage("helloiam " + properties.getProperty("username"));
            int messageLength = Integer.parseInt(
                    client.sendMessage("msglen").split(" ")[1]
            );
            //System.out.println( "Server:" + messageLength);
            listenerSocket.startConnection(
                    Integer.parseInt(properties.getProperty("client_udp_port")),
                    messageLength*2
            );
            client.sendMessage("givememsg 8085");
            String message = listenerSocket.getData();
            String decodedMessage = Utils.decodeBase64String(message);

            client.sendMessage("chkmsg "+ Utils.encryptMD5InHex(decodedMessage));


            client.sendMessage("bye");
            System.out.println("The message is: "+ decodedMessage);
        } catch (FileNotFoundException fileNotFoundException){
            System.out.println("An error ocurred reading the properties");
        }catch (SocketTimeoutException te){
            System.out.println("The Server its not responding");
            System.out.println("Please try again later");
        }catch (IOException ioException){
            System.out.println("An error have ocurred in the Server");
            System.out.println("Server error: "+ioException.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error have ocurred in the Server");
            System.out.println("Please try again later or check your inputs");

        }finally {
            if ( listenerSocket.isConnected()) listenerSocket.stopConnection();
            if ( client.isConnected()) client.stopConnection();
        }

    }
}
