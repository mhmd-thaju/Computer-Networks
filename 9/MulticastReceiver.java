import java.net.*;

public class MulticastReceiver {
    @SuppressWarnings("deprecation")
    public static void main (String [] args)
    {
        int port =5000;
        String group = "225.4.5.6";
        try {
            MulticastSocket ms = new MulticastSocket(port);
            InetAddress groupAddress = InetAddress.getByName(group);
            ms.joinGroup(groupAddress );
            System.out.println("Joined multicast group : " + group);
            byte[] buf = new byte[1024];
            DatagramPacket pack = new DatagramPacket(buf,buf.length);
            while(true){
                ms.receive(pack);
                String receivedMessage = new String(pack.getData(),0,pack.getLength());
                if(receivedMessage.equalsIgnoreCase("exit")){
                    System.out.println("Exit message received, shutting down receiver....");break;
                } 
                System.out.println("Message Received");
                System.out.println("Message "+receivedMessage+ " ");
            }
            ms.leaveGroup(groupAddress);
            ms.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
}
