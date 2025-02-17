import java.util.Random;

class  Onebit{
    public static void main(String[] args) {
        int frame = 0;
        Random random = new Random();
        int totalFrames = 4;

        for(int i=0;i<totalFrames;++i){
            System.out.println("\nSender : Sending Frame "+ frame);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(random.nextInt(10)<2){
                System.out.println("Sender : Frame lost, retransmitting ...");
                i--;
                continue;
            }
            System.out.println("Receiver : Received Frame "+ frame);
            System.out.println("Receiver : Sending ACK "+ frame);
            System.out.println("Sender : ACK "+ frame+" received.");

            frame=1-frame;
            
        }
        System.out.println("All Frames Transmitted Successfully.");
    }
}
