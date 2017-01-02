package tcp;

import java.io.*;
import java.net.*;

public class DailyWetterServer {
    String [] wetterList = {"Sonnig, 20 Grad", "Sonnig, 23 Grad",
                      "Windig, 18 Grad", "Regen, 19 Grad", "Neblig, 24 Grad"};
    
    public void go(){
        try{
            ServerSocket serverSock = new ServerSocket(4242);
            System.out.println("Server Started and listening to the port 4242");

            
            while(true){
                //accept request and return a socket on anonymous port
                Socket sock = serverSock.accept();
                
                //get Input
                BufferedReader inFromClient =
               new BufferedReader(new InputStreamReader(sock.getInputStream()));
                String ort = inFromClient.readLine();
                
                //Output2
                PrintWriter writer = new PrintWriter(sock.getOutputStream());
                
                String wetter = getWetter(ort);
                writer.println("Client frag nach Wetter in " + ort + ""
                        + ", Server antwortet " +  wetter);
                writer.close();
                System.out.println(wetter);
            }
            
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    private String getWetter(String ort){
        // 5 deutschen Staedten 
        if(ort.equals("Leipzig")){
            return wetterList[0];
        } else if(ort.equals("Stuttgart")){
            return wetterList[1];
        } else if(ort.equals("Hamburg")){            
            return wetterList[2];
        } else if(ort.equals("Erlangen")){
            return wetterList[3];
        } else if(ort.equals("Konstanz")){
            return wetterList[4];
        } else{
            return "Please enter a proper city\n";
        }
    }
    
    public static void main(String[] args){
        DailyWetterServer server = new DailyWetterServer();
        server.go();
    }
}
