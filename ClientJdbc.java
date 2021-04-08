import java.sql.*;
import java.util.*;
import java.io.*;
import java.net.*;
public class ClientJdbc{
    public static void main(String []args){
        try(
            Scanner kbd = new Scanner(System.in);
            Socket s = new Socket("localhost",9999);
            DataOutputStream dout = new DataOutputStream (s.getOutputStream());
            DataInputStream din = new DataInputStream(s.getInputStream())) {
            // Scanner kbd = new Scanner(System.in); 
            // Socket s = new Socket("localhost",9999);//create socket
            // DataOutputStream dout = new DataOutputStream (s.getOutputStream());
            String str="",num =kbd.nextLine();
            // DataInputStream din = new DataInputStream(s.getInputStream());
            dout.writeUTF(num);// send to the server
            dout.flush();//clear the message
            str = (String) din.readUTF(); //receive the respone
            while(!str.equals(""))
            {  
               System.out.println(str); // print the response
               dout.flush(); // clear the messages
               str = (String) din.readUTF();//receive the respone
            }
            dout.flush();
            dout.close();
            // s.close();
            kbd.close();   
        }
        catch(Exception e){
            System.out.print(e.getMessage());
            System.exit(1);
        }

    }
}