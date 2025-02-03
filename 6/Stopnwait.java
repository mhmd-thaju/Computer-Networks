public class Stopnwait {
    private static int MAXFRAME = 5;

    static void sendframe (int framenumber){
        System.out.println("Sending Frame : "+framenumber);
    }
    static boolean receiveack(int framenumber){
        System.out.println("Acknowledgment for Frame "+framenumber+" received");
        return true;
    }

    static void Stopnwait(){
        int frame2send = 0;
        while(frame2send < MAXFRAME)
        {
            sendframe(frame2send);

            if(receiveack(frame2send)){
                frame2send++;
            }

            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("\nAll Frames Send Successfully!");
    }
    public static void main(String [] args)
    {
        System.out.println("Simplex Stop-and-Wait protocol\n");
        Stopnwait();
    }
}