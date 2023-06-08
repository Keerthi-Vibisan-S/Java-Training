package Interface.Interface;

import java.util.*;

public class Sms implements Message{
    Scanner in = new Scanner(System.in);
    @Override
    public void sendMessage(String message) {
        System.out.println("Enter Receiver's Phone number: ");
        int receivers_phone = in.nextInt();
        System.out.println("Sending Message: "+message+", To: "+receivers_phone);
        System.out.println("Transmitting to nearest tower");
    }

    @Override
    public void receiveMessage() {
        System.out.println("Received Message");
    }
}
