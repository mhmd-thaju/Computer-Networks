import java.util.Scanner;
public class GBN {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the total number of frames to send : ");
        int totalFrames = sc.nextInt();
        System.out.print("\nEnter the window size : ");
        int windowSize = sc.nextInt();
        int curretnframe =0;
        while(curretnframe < totalFrames){
            int frameSend = Math.min(windowSize,totalFrames-curretnframe);
            System.out.println("Sending Frames\n");
            for(int i=curretnframe;i<curretnframe+frameSend;++i){
                System.out.println("Frame "+i+" Sent.");
            }
            System.out.println("Enter the last acknowledged frame (or -1 if no acknowledgment) : ");
            int ack =sc.nextInt();
            if(ack >= curretnframe && ack<curretnframe+frameSend){
                curretnframe = ack+1;
                System.out.println("Acknowledgment received for frames upto : "+ack);
            }else{
                System.out.println("Acknowledgment failed. Resending window from frame : "+curretnframe);
            }
        }
        System.out.println("All frames sent successfully !");
        sc.close();
    }
}
