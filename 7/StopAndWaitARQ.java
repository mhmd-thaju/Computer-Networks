import java.util.Random;

public class StopAndWaitARQ {
    
    // Simulate a noisy channel by randomly introducing bit errors
    public static String noisyChannel(String data) {
        Random random = new Random();
        StringBuilder transmittedData = new StringBuilder(data);
        
        // Introduce errors in 10% of bits
        for (int i = 0; i < data.length(); i++) {
            
            if (random.nextDouble() < 0.1) {
                // Flip the bit (0 to 1 or 1 to 0)
                transmittedData.setCharAt(i, transmittedData.charAt(i) == '0' ? '1' : '0');
            }
        }
        return transmittedData.toString();
    }

    // Sender: Sends a data frame and waits for acknowledgment (Stop-and-Wait)
    public static void sender(String data) {
        System.out.println("Sender: Sending data: " + data);
        String transmittedData = noisyChannel(data);  // Simulate the noisy channel
        
        // Simulate the receiver side
        String ack = receiver(transmittedData, data);
        
        // If acknowledgment is received, proceed with the next frame, else retransmit
        while (!ack.equals("ACK")) {
            System.out.println("Sender: Retransmitting due to error or timeout...");
            transmittedData = noisyChannel(data);  // Simulate retransmission due to error
            System.out.println("Sender: Retransmitting data: " + transmittedData);
            ack = receiver(transmittedData, data);  // Simulate the receiver's response again
        }

        System.out.println("Sender: Data transmitted successfully.");
    }

    // Receiver: Receives data, checks for errors, and sends an acknowledgment (ACK or NAK)
    public static String receiver(String receivedData, String originalData) {
        System.out.println("Receiver: Received data: " + receivedData);
        
        // Error detection (simple comparison for this example)
        if (receivedData.equals(originalData)) {
            System.out.println("Receiver: Data received correctly.");
            return "ACK";  // Acknowledgment is sent if no error
        } else {
            System.out.println("Receiver: Error detected! Data doesn't match.");
            return "NAK";  // Negative acknowledgment is sent in case of error
        }
    }

    public static void main(String[] args) {
        // Example data to send
        String dataToSend = "1010101010"; // A simple binary data to send

        // Start the Stop-and-Wait ARQ protocol
        sender(dataToSend);
    }
}
