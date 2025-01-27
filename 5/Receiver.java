public class Receiver {

    public void receive(Packet packet) {
       /* if (!validateChecksum(packet)) {
            System.out.println("Packet discarded (checksum error).");
            return;
        }*/
        processData(packet.getData());
    }

    /*private boolean validateChecksum(Packet packet) {
        int expectedChecksum = packet.getChecksum();
        int calculatedChecksum = computeChecksum(packet.getData());
        return expectedChecksum == calculatedChecksum;
    }

    private int computeChecksum(String data) {
        int sum = 0;
        for (char ch : data.toCharArray()) {
            sum += (int) ch;
        }
        return sum % 256;
    }*/

    private void processData(String data) {
        System.out.println("Received data: " + data);
    }
}