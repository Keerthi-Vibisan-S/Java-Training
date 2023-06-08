package Interface.Interface;

import java.io.*;
import java.net.*;

public class SocketServer {
    public static void main(String args []) {
        System.out.println("Server Started");
        try {
            // Creating server
            ServerSocket ss = new ServerSocket(9994);


            while(true) {
                Socket s = ss.accept();

                // To write output
                BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

                // To read input
                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String str = br.readLine();
                System.out.println("Client Data: "+str);

                wr.write("Received your data at Server Side. Thankyou");
                wr.newLine();
                wr.flush();

                if(str == "999") break;
                br.close();
                s.close();
            }

            ss.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

