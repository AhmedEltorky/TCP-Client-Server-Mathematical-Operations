package mathoperationshandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Ahmed El-Torky
 */
public class MathOperationsHandler extends Thread {

    private static final int RECEIVE_NUM = 0;
    private static final int RECEIVE_OP = 1;
    private static final int CANCEL_OP = 2;

    private Socket clientSocket;
    private DataInputStream in;
    private DataOutputStream out;
    private ArrayList<Integer> arr;

    public MathOperationsHandler(Socket clientSocket) {
        arr = new ArrayList<>();
        try {
            this.clientSocket = clientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            this.start();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }

    }

    @Override
    public void run() {
        try {
            int input = in.readInt();
            String op;
            while (true) {
                if (input == RECEIVE_NUM) {
                    arr.add(in.readInt());
                } else if (input == RECEIVE_OP) {
                    op = in.readUTF();
                    calc(arr, op);
                    break;
                } else if (input == CANCEL_OP) {
                    break;
                }
                input = in.readInt();
            }
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }

    }

    private void calc(ArrayList<Integer> arr, String op) {
        int sum = -1;
        try {
            switch (op) {
                case "+":
                    sum = 0;
                    for (int x : arr) {
                        sum += x;
                    }
                    break;
                case "-":
                    sum = 0;
                    for (int x : arr) {
                        sum -= x;
                    }
                    break;
                case "*":
                    sum = 1;
                    for (int x : arr) {
                        sum *= x;
                    }
                    break;
                default:
                    break;
            }
            out.writeInt(sum);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

}
