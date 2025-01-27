public class Sender {
    private Receiver receiver;

    public Sender(Receiver receiver) {
        this.receiver = receiver;
    }

    public void sendPacket(String data) {
        Packet packet = createPacket(data);
        receiver.receive(packet);
    }

    private Packet createPacket(String data) {
        //String header = createHeader();
        //int checksum = computeChecksum(data);
        //return new Packet(header, data, checksum);
        return new Packet( data);
    }

    /*private String createHeader() {
        return "Header: Seq=0";
    }

    private int computeChecksum(String data) {
        int sum = 0;
        for (char ch : data.toCharArray()) {
            sum += (int) ch;
        }
        return sum % 256;  // Checksum in range 0-255
    }*/

    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Sender sender = new Sender(receiver);

        String data = "Hello, world!";

        for (int i = 0; i < 5; i++) {
            sender.sendPacket(data);
            try {
                Thread.sleep(1000);  // Pause to simulate time between transmissions
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}