import java.io.*; 
import java.net.*; 
 
public class FTPServer { 
    private static final int PORT = 5000; 
 
    public static void main(String[] args) { 
        try (ServerSocket serverSocket = new ServerSocket(PORT)) { 
            System.out.println("Server is listening on port " + PORT); 
             
            while (true) { 
                Socket socket = serverSocket.accept(); 
                System.out.println("Client connected: " + socket.getInetAddress()); 
 
                DataInputStream dis = new DataInputStream(socket.getInputStream()); 
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream()); 
 
                // Receive filename request from client 
                String fileName = dis.readUTF(); 
                File file = new File(fileName); 
 
                if (file.exists()) { 
                    dos.writeUTF("FOUND"); 
                    FileInputStream fis = new FileInputStream(file); 
                    byte[] buffer = new byte[4096]; 
                    int bytesRead; 
 
                    while ((bytesRead = fis.read(buffer)) != -1) { 
                        dos.write(buffer, 0, bytesRead); 
                    } 
                    fis.close(); 
                    System.out.println("File sent: " + fileName); 
                } else { 
                    dos.writeUTF("NOT_FOUND"); 
                    System.out.println("File not found: " + fileName); 
                } 
 
                socket.close(); 
            } 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
    } 
}