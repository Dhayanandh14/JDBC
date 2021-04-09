import java.sql.*;
import java.util.*;

import java.io.*;
import java.net.*;
public class ClientJdbc{
    public static void main(String []args){
        try(
            BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));
            Socket s = new Socket("localhost",9999);//create socket
            DataOutputStream dout = new DataOutputStream (s.getOutputStream());
            DataInputStream din = new DataInputStream(s.getInputStream())){

            String str="";
            System.out.print("Enter Your Choice: ");
            String choice =kbd.readLine();
            if(choice.equals("select")){
                System.out.print("You want to show full table Type(YES or NO): ");
                String selectoption =kbd.readLine();
                if(selectoption.equals("no") || selectoption.equals("No")){
                    System.out.println("Now you can enter what are the row and column to display");
                    System.out.print("Enter Column name: ");
                    String deptno =kbd.readLine();
                    System.out.print("Enter row data: ");
                    String dpdata =kbd.readLine();
                    dout.writeUTF("SELECT * FROM EMPLOYEE WHERE "+ deptno +" = "+dpdata);
                }
                else{
                    dout.writeUTF("SELECT * FROM EMPLOYEE");
                }
            }
            if(choice.equals("insert"))
            {
                System.out.print("Enter Employee Number: ");
                String empnum =kbd.readLine();
                System.out.print("Enter Employee Name: ");
                String empname =kbd.readLine();
                System.out.print("Enter Employee Job: ");
                String ejob =kbd.readLine();
                System.out.print("Enter Employee Boss: ");
                String eboss =kbd.readLine();
                System.out.print("Enter Employee HireDate: ");
                String ehire =kbd.readLine();
                System.out.print("Enter Employee Salary: ");
                String esal =kbd.readLine();
                System.out.print("Enter Employee Com: ");
                String ecom =kbd.readLine();
                System.out.print("Enter Employee DeptNo: ");
                String edpt =kbd.readLine();
                dout.writeUTF("INSERT INTO EMPLOYEE"+"("+"empno"+","+"name"+","+"job"+","+"boss"+","+"hiredate"+","+"salary"+","+"comm"+","+"deptno"+")"+" VALUES" +"("+"'"+empnum+"'"+","+"'"+empname+"'"+","+"'"+ejob+"'"+","+eboss+","+ehire+","+esal+","+ecom+","+edpt+")");// send to the server
                
            }
            if(choice.equals("delete")){
                System.out.print("Enter Column name: ");
                String delempcol = kbd.readLine();
                System.out.print("Enter Row name: ");
                String delemprow = kbd.readLine();
                dout.writeUTF("DELETE FROM EMPLOYEE WHERE "+ delempcol +" = "+"'"+delemprow+"'" );
            }
            if(choice.equals("update")){
                System.out.print("Enter Name of column: ");
                String empupdatecol1 = kbd.readLine();
                System.out.print("Enter new data: ");
                String empupdaterow1 = kbd.readLine();
                System.out.print("Enter Which column do you want to change: ");
                String empupdatecol2 = kbd.readLine();
                System.out.print("Enter Which Row do you want to change: ");
                String empupdaterow2 = kbd.readLine();
                dout.writeUTF("UPDATE EMPLOYEE SET " +empupdatecol1 + " = " + "'"+empupdaterow1+ "'"+" WHERE "+ empupdatecol2 +"="+ empupdaterow2);

            }
            System.out.println();
            dout.flush();//clear the message
            System.out.println("CURRENT SQL TABLE IS: ");
            System.out.println();
            str = (String) din.readUTF(); //receive the respone
            while(!str.equals(""))
            {
               System.out.println(str); // print the response
               dout.flush(); // clear the messages
               str = (String) din.readUTF();//receive the respone
            }
            System.out.print(str);
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
