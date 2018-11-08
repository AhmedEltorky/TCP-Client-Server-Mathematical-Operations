package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

/**
 *
 * @author Ahmed El-Torky
 */
public class ServerShutdown extends Thread {

    private ServerSocket serverSocket;

    public ServerShutdown(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("enter keyword \"shutdown\" to shutdown the server:");
                String input = new Scanner(System.in).next();
                if (input.equals("shutdown")) {
                    this.serverSocket.close();
                    break;
                } else {
                    System.out.println("invalid cmd !!!");
                }
            }
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }

}
