public class Packet {
    //private String header;
    private String data;
    //private int checksum;

    public Packet(/*String header,*/ String data/* , int checksum*/) {
        //this.header = header;
        this.data = data;
        //this.checksum = checksum;
    }

    /*public String getHeader() {
        return header;
    }*/

    public String getData() {
        return data;
    }

    /*public int getChecksum() {
        return checksum;
    }*/
}
