package tcp;

import java.io.*;
import java.net.*;

class TCPClient {
    
    public static void main(String argv[]) throws Exception {
        String sentence;
        String modifiedSentence;
        String host = "localhost";
        Socket clientSocket = null;
        
        System.out.println("'exit' to disconnect");
        while (true) {
            try {
                clientSocket = new Socket(host, 4242);
                //server problem timeout in 15 seconds to prevent stuck
                clientSocket.setSoTimeout(15000);
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                
                System.out.println("Please type the city name: ");
                sentence = inFromUser.readLine();
                outToServer.writeBytes(sentence + '\n');
                modifiedSentence = inFromServer.readLine();
                System.out.println("FROM SERVER: " + modifiedSentence);

                //escape word
                if (sentence.equals("exit")) {
                    System.exit(0);
                }
                
            } catch (IOException ex) {
                System.err.println(ex);
            } finally {
                if (clientSocket != null) {
                    //Java 7 auto close
                    clientSocket.close();
                }
            }
        }
        //while (!sentence.trim().equals("bye"));
    }
}
