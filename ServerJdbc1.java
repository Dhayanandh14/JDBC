import java.sql.*;
import java.util.*;
import java.io.*;
import java.net.*;
public class ServerJDBC{
    public static void main(String []args){
        try(
            ServerSocket ss = new ServerSocket(9999);// create socket ties to a port
            Socket s = ss.accept();// listen the request
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream  dout = new DataOutputStream(s.getOutputStream());){
            String str = (String) din.readUTF(); // recieve the input(request) from the client
            System.out.print("Recieved Querry Is: ");
            System.out.println(str);
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/railwayreservation","root","roor");
            ResultSet rs =null;
            Statement st = con.createStatement();
            if(str.charAt(0) == 'S' || str.charAt(0) == 's'){
                 rs = st.executeQuery(str);
                while(rs.next())
                  dout.writeUTF(rs.getString("empno") + "   " +rs.getString("name")+"   "+rs.getString("job")+"   "+rs.getString("salary"));
                dout.writeUTF("");
            }
            else{
                int n = st.executeUpdate(str);
                System.out.println(n+" Records Updated");
                dout.writeInt(n);
            }
            dout.writeUTF("");
        }
        catch(Exception e){
            System.out.print(e.getMessage());
            System.exit(1);
        }
    }
}