import java.io.*; 
import java.net.*; 
 
public class FTPClient { 
    private static final String SERVER_IP = "127.0.0.1";  // Change this to the server's IP 
    private static final int PORT = 5000; 
 
    public static void main(String[] args) { 
        try (Socket socket = new Socket(SERVER_IP, PORT)) { 
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream()); 
            DataInputStream dis = new DataInputStream(socket.getInputStream()); 
 
            // Request file from server 
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
            System.out.print("Enter file name to download: "); 
            String fileName = br.readLine(); 
            dos.writeUTF(fileName); 
 
            // Check if file exists on the server 
            String response = dis.readUTF(); 
            if ("FOUND".equals(response)) { 
                FileOutputStream fos = new FileOutputStream("downloaded_" + fileName); 
                byte[] buffer = new byte[4096]; 
                int bytesRead; 
 
                while ((bytesRead = dis.read(buffer)) != -1) { 
                    fos.write(buffer, 0, bytesRead); 
                } 
 
                fos.close(); 
                System.out.println("File downloaded successfully as downloaded_" + fileName); 
            } else { 
                System.out.println("File not found on server."); 
            } 
 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
    } 
}