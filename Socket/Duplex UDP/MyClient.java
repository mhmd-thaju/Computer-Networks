import java.net.*;
import java.util.Scanner;

public class MyClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        Scanner sc = new Scanner(System.in);

        try {
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9876;

            while(true){
                System.out.print("Enter your message to send : ");
                String clientmessage = sc.nextLine();

                if(clientmessage.equalsIgnoreCase("stop")){
                    System.out.println("Client : Communication Stopped");
                    break;
                }
                byte[] sendData = clientmessage.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,serverAddress,serverPort);
                socket.send(sendPacket);

                byte[] receiveData  = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                String serverResponse = new String(receivePacket.getData(),0,receivePacket.getLength());
                System.out.println("Server : "+ serverResponse);

                if (serverResponse.equalsIgnoreCase("stop")) {
                    System.out.println("Client : Communication Stopped");
                    break;

                }

            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            sc.close();
        }
    }
}
