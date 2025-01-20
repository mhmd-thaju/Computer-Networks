import java.net.*;
import java.util.Scanner;

public class MyServer {
    public static void main (String[] args){
        DatagramSocket socket= null;
        Scanner sc = new Scanner(System.in);
        try{
            socket = new DatagramSocket(9876);
            byte[] recieveData = new byte[2048];

            System.out.println("Waiting for messages...");
            while(true)
            {
                DatagramPacket receivePacket = new DatagramPacket(recieveData, recieveData.length);
                socket.receive(receivePacket);
                String receivedMessage = new String(receivePacket.getData(),0,receivePacket.getLength());
                System.out.println("Client: "+ receivedMessage );

                if (receivedMessage.equalsIgnoreCase("stop")) {
                    System.out.println("Server : Communication Stoped");
                    break;

                }

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                System.out.print("Enter your response to sent : ");
                String serverResponse = sc.nextLine();
                byte[] sendData = serverResponse.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,clientAddress,clientPort);
                socket.send(sendPacket);

                if(serverResponse.equalsIgnoreCase("stop")){
                    System.out.println("Server : Communication Stopped");
                }

            }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(socket != null && !socket.isClosed())
            {
                socket.close();
            }
            sc.close();
        }
    }
}