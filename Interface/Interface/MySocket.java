package Interface.Interface;

import java.io.*;
import java.net.Socket;

public class MySocket implements Message {

    String ip = "localhost";
    int port = 9994;
    private Socket s;

    private OutputStreamWriter os = null;
    private BufferedWriter write = null;
    private InputStreamReader rd = null;
    private BufferedReader read = null;

    public MySocket() throws IOException {
        s = new Socket(ip, port);
        os = new OutputStreamWriter(s.getOutputStream());
        write = new BufferedWriter(os);
        rd = new InputStreamReader(s.getInputStream());
        read = new BufferedReader(rd);
    }

    @Override
    public void sendMessage(String message) {
        try {
            write.write(message);
            write.newLine();

            // By default network size is 512 Bytes
            // We need to force push it
            write.flush();
            receiveMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void receiveMessage() {
        try {
            System.out.println(read.readLine());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
