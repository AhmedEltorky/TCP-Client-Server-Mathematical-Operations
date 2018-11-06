package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import mathoperationshandler.MathOperationsHandler;

/**
 *
 * @author Ahmed El-Torky
 */
public class Server {

    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(1991);
            System.out.println("Mathematical Operations Server running....");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                MathOperationsHandler moh = new MathOperationsHandler(clientSocket);
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }

    }

}
