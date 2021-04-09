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
            int n = Integer.parseInt(str); // input convert to integer type
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/railwayreservation","root","roor");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM EMPLOYEE WHERE deptno ="+n);
            while(rs.next())
                dout.writeUTF(rs.getString("deptno") + "   " +rs.getString("name")+"   "+rs.getString("job")+"   "+rs.getString("salary"));
            dout.writeUTF("");
            // int n = st.executeUpdate("INSERT/DELETE/UPDATE....");
            // dout.flush();
            rs.close();
            st.close();
            con.close();
            // ss.close();
            // s.close(); 
            // din.close();

        }
        catch(Exception e){
            System.out.print(e.getMessage());
            System.exit(1);
        }

    }
}
