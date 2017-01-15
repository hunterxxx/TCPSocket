package tcp;

import java.io.*;
import java.net.*;

public class PortScanner {

    public static void main(String[] args) {
        for (int port = 4000; port <= 4500; port++) {
            try {
// the next line will fail and drop into the catch block if
// there is already a server running on the port
                ServerSocket server = new ServerSocket(port);
            } catch (IOException ex) {
                System.out.println("There is a server on port " + port + ".");
            }
        }
    }
}
