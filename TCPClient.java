package tcp;

import java.io.*;
import java.net.*;

class TCPClient {

    public static void main(String argv[]) throws Exception {
        String sentence;
        String modifiedSentence;
        String host = "localhost";
        Socket clientSocket = null;

        System.out.println("Trying 127.0.0.1... ");
        while (true) {
            try {
                clientSocket = new Socket(host, 4242);
                //in case server 
                clientSocket.setSoTimeout(15000);
                //System.out.println("Connected to localhost");
                System.out.println("Please enter a city");
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                sentence = inFromUser.readLine();
                outToServer.writeBytes(sentence + '\n');
                modifiedSentence = inFromServer.readLine();
                System.out.println("FROM SERVER: " + modifiedSentence);
                //unbedingt?
                outToServer.flush();

            } catch (IOException ex) {
                System.err.println(ex);
            } finally {
                if (clientSocket != null) {
                    //Java 7 auto close
                    clientSocket.close();
                }
            }
        }
    }
}
