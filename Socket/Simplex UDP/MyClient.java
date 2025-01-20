import java.net.*;
public class MyClient {
    public static void main (String[] args)
    {
        DatagramSocket socket = null;
        try{
            socket = new DatagramSocket();
            String msg = "Hello, this is a messagem from sender!";
            InetAddress receiverAddress = InetAddress.getByName("localhost");
            int receiverPort = 9876;
            
            byte[] sendData = msg.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,receiverAddress,receiverPort);
            socket.send(sendPacket);
            System.out.println("Message sent : " + msg);
        }catch(Exception e)
        {
            e.printStackTrace();
        }finally{
            if(socket != null && !socket.isClosed() ){
                socket.close();
            }
        }
    }
}
