
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) {
        int port = 8010;
        String serverAddress = "localhost"; 

        try {
            
            InetAddress address = InetAddress.getByName(serverAddress);
            Socket socket = new Socket(address, port);

            try (
                PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()))
            ) {
                // Send a message to the server
                toServer.println("Hello from Client " + socket.getLocalSocketAddress());

                // Read and print the server's response
                String response = fromServer.readLine();
                System.out.println("Response from Server: " + response);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // Close the socket after communication
                socket.close();
            }

        } catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}
