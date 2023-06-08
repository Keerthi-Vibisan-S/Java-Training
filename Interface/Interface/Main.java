package Interface.Interface;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            while(true) {
                System.out.println("Enter your message to send: ");
                String msg = in.nextLine();

                System.out.println("How do you wish to send your message");
                System.out.println("1. Sms");
                System.out.println("2. Sockets");
                int ch = in.nextInt();
                in.nextLine();

                if(ch == 1) sendMessage(new Sms(), msg);
                else if(ch == 2) {
                    sendMessage(new MySocket(), msg);
                    //ms.sendMessage(msg);
                }

                else System.out.println("Enter a Valid Choice");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void sendMessage(Message send, String message) {
        send.sendMessage(message);
    }
}