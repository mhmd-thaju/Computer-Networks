import java.io.*;
import java.io.DataInputStream;
import java.net.*;

public class MyServer{
    public static void main(String[] args){
        try{
            ServerSocket se = new ServerSocket(6666);
            Socket s = se.accept();
            DataInputStream dis =new DataInputStream(s.getInputStream());
            String str = (String)dis.readUTF();
            System.out.println("Message = "+str);
            se.close();
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
}