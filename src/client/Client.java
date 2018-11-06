package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Ahmed El-Torky
 */
public class Client {

    private static final int SEND_NUM = 0;
    private static final int SEND_OP = 1;

    public static void main(String[] args) {
        Socket clientSocket = null;
        try {
            clientSocket = new Socket("localhost", 1991);
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            System.out.println("Client Connected to Server....");
            System.out.println("please enter your input:");
            Scanner scan = new Scanner(System.in);
            String input = scan.next();
            while (!is_operation(input)) {
                if (valid_input(input)) {
                    out.writeInt(SEND_NUM);
                    out.writeInt(Integer.parseInt(input));
                } else {
                    System.out.println("Invalid input!!!");
                    System.out.println("please enter valid input:");
                }
                input = scan.next();
            }
            out.writeInt(SEND_OP);
            out.writeUTF(input);

            System.out.println("The ans = " + in.readInt());

            in.close();
            out.close();
            clientSocket.close();

        } catch (IOException | NumberFormatException ex) {
            System.out.println(ex.toString());
        } finally {

        }
    }

    private static boolean is_operation(String op) {
        return op.equals("+") || op.equals("-") || op.equals("*");
    }

    private static boolean valid_input(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

}
