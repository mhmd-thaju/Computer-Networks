
import java.net.*;
import java.util.Scanner;

public class MulticastSender {
    public static void main(String [] args){
        int port =5000;
        String group = "225.4.5.6";
        int ttl =1;
        Scanner s = new Scanner(System.in);
        try{
            MulticastSocket ms = new MulticastSocket();
            ms.setTimeToLive(ttl);
            System.out.println("Enter the message to send to the multicast group (Type 'exit' to exit) :");
            while (true) {
                String userInput = s.nextLine();
                if(userInput.equalsIgnoreCase("exit")){
                    byte [] exitmessage = "exit".getBytes();
                    DatagramPacket exitPacket = new DatagramPacket(exitmessage, exitmessage.length,InetAddress.getByName(group),port);
                    ms.send(exitPacket);
                    System.out.println("Sent exit message to multicast group, Exiting....");break;
                }
                byte [] buf =userInput.getBytes();
                DatagramPacket pack = new DatagramPacket(buf,buf.length,InetAddress.getByName(group),port);
                ms.send(pack);
                System.out.println("Sent message : " + userInput + " to multicast group "+ group+ " on port "+port);
            }
            ms.close();
            s.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }    
}
