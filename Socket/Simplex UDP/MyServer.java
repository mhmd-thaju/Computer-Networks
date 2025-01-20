import java.net.*;

public class MyServer{
    public static void main (String[] args){
        DatagramSocket socket= null;
        try{
            socket = new DatagramSocket(9876);
            byte[] recieveData = new byte[2048];

            System.out.println("Waiting for messages...");
            DatagramPacket receivePacket = new DatagramPacket(recieveData, recieveData.length);
            socket.receive(receivePacket);
            String message = new String(receivePacket.getData(),0,receivePacket.getLength());
            System.out.println("Received message: "+ message );
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(socket != null && !socket.isClosed())
            {
                socket.close();
            }
        }
    }
}