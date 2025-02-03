import java.util.Scanner;
import java.util.Random;

class Sender {
    private static final int TIMEOUT = 2000;
    private boolean isFrameAcknowledged = false;

    public void sendData(int[] data) {
        isFrameAcknowledged = false;
        System.out.println("Sender: Sending data: " + java.util.Arrays.toString(data));
        long startTime = System.currentTimeMillis();

        while (!isFrameAcknowledged) {
            if (System.currentTimeMillis() - startTime > TIMEOUT) {
                System.out.println("Sender: Timeout! Retransmitting the data...");
                startTime = System.currentTimeMillis(); 
            }
        }
    }

    public void acknowledgeFrame() {
        isFrameAcknowledged = true;
        System.out.println("Sender: Frame acknowledged by receiver.");
    }
}

class Receiver {
    private Random random = new Random();

    public void receiveData(int[] data, Sender sender) {
        System.out.println("Receiver: Data received: " + java.util.Arrays.toString(data));

        boolean isAcknowledged = random.nextBoolean();

        if (isAcknowledged) {
            System.out.println("Receiver: Acknowledging the received frame.");
            sender.acknowledgeFrame();
        } else {
            System.out.println("Receiver: Acknowledgment failed. Not sending acknowledgment.");
        }
    }
}

public class SimplexStopAndWaitProtocol {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Sender sender = new Sender();
        Receiver receiver = new Receiver();

        System.out.println("Simplex Stop-and-Wait Protocol - Data Link Layer Simulation");

        while (true) {
            System.out.print("\nEnter data to send (space-separated integers) or type 'exit' to quit: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the simulation...");
                break;
            }

            try {
                String[] inputStrings = input.split(" ");
                int[] dataToSend = new int[inputStrings.length];
                for (int i = 0; i < inputStrings.length; i++) {
                    dataToSend[i] = Integer.parseInt(inputStrings[i]);
                }

                sender.sendData(dataToSend);

                receiver.receiveData(dataToSend, sender);

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter integers only.");
            }
        }

        scanner.close();
    }
}

