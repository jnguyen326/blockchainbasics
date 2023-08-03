import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * This class holds any utility methods needed for P2P networking communication.
 */
public class P2PUtil {


    /**
     * Allows a one time socket call to a server, gets reply, and then closes connection.
     * @param sIP
     * @param iPort
     * @param sMessage
     * @return
     */
    public static String connectForOneMessage(String sIP, int iPort, String sMessage) {

        //#####################

        try(Socket oSocket = new Socket()) {

            oSocket.connect(new InetSocketAddress(sIP,iPort), 500);

            OutputStream output = oSocket.getOutputStream();

            PrintWriter writer = new PrintWriter(output, true);

            writer.println(sMessage);
            writer.flush();

            InputStream oInput = oSocket.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(oInput));

            String sReceivedMessage = reader.readLine();

            oSocket.close(); // closes connection

            return sReceivedMessage;

        }

        catch (IOException ex) {
            System.out.println("[server]: Server exception: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
        //#####################
    }
}
