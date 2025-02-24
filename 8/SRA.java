import java.util.*;

class SRA {

    static int MAX_FRAMES = 7;  // Number of frames
    static int WINDOW_SIZE = 3; // Sender's window size
    static int TIMEOUT = 3;     // Simulate timeout for lost frames (in seconds)
    static boolean[] senderWindow = new boolean[MAX_FRAMES];
    static boolean[] receiverWindow = new boolean[MAX_FRAMES];
    static String[] frames = new String[MAX_FRAMES];
    static boolean[] ack = new boolean[MAX_FRAMES];
    
    static class SenderThread extends Thread {
        int startIndex;
        Scanner sc;
        int[] acknowledge;
        SenderThread(int startIndex, Scanner sc, int[] acknowledge) {
            this.startIndex = startIndex;
            this.sc = sc;
            this.acknowledge = acknowledge;
        }

        @Override
        public void run() {
            sendFrames(startIndex);
            System.out.println("Enter the frames that have been received (or -1 if not): ");
            for (int i = 0; i < WINDOW_SIZE ; i++) {
                acknowledge[i] = sc.nextInt();
                if( startIndex==6)   break;
            }
            for (int i = 0; i < WINDOW_SIZE ; i++) {
                if (acknowledge[i] != -1) {
                    receiveFrame(acknowledge[i]);
                    if( startIndex==6)   break;
                }
            }
            retransmit();
        }

        static void sendFrames(int startIndex) {
            for (int i = startIndex; i < startIndex + WINDOW_SIZE && i < MAX_FRAMES; i++) {
                if (!senderWindow[i]) {  
                    frames[i] = "Frame " + i;
                    senderWindow[i] = true;
                    ack[i] = false; 
                    System.out.println("Sender: Sending " + frames[i]);
                }
            }
        }

        static void receiveFrame(int frameIndex) {
            if (frameIndex < MAX_FRAMES) {
                receiverWindow[frameIndex] = true;
                ack[frameIndex] = true;  
                System.out.println("Receiver: Received and acknowledged " + frames[frameIndex]);
            }
        }

        static void retransmit() {
            for (int i = 0; i < MAX_FRAMES; i++) {
                if (senderWindow[i] && !ack[i]) { 
                    System.out.println("Sender: Timeout! Retransmitting " + frames[i]);
                    receiveFrame(i); 
                }
            }
        }
    }

    static boolean allFramesAcknowledged() {
        for (int i = 0; i < MAX_FRAMES; i++) {
            if (!ack[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws InterruptedException {
        Arrays.fill(senderWindow, false);
        Arrays.fill(receiverWindow, false);
        Arrays.fill(ack, false);
        Scanner sc = new Scanner(System.in);
        int startIndex = 0;
        int[] acknowledge = new int[WINDOW_SIZE];
        while (!allFramesAcknowledged()) {
            SenderThread senderThread = new SenderThread(startIndex, sc, acknowledge);
            senderThread.start();
            senderThread.join(); 
            startIndex = (startIndex + WINDOW_SIZE) % MAX_FRAMES;
            try {
                Thread.sleep(TIMEOUT * 1000);  
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("All frames successfully transmitted and acknowledged!");
    }
}
