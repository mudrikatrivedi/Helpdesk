import java.sql.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;
import java.util.*;
import java.util.Scanner;
public class Login{
    public static String getMd5(String input) 
    {
        try { 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
            byte[] messageDigest = md.digest(input.getBytes()); 
            BigInteger no = new BigInteger(1, messageDigest); 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
              hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 
    public static void main(String args[]) throws Exception 
	{ 
	Connection con;
        PreparedStatement prepared_statement;
        //String emp = JOptionPane.showInputDialog("EmployeeID");
		Scanner input_string = new Scanner(System.in);
		String employeeIDTocheck = input_string.nextLine();
       // String pw = JOptionPane.showInputDialog("Password");
	   java.io.Console console = System.console();
	   //String pw=input.nextLine();
	   String passwordTocheck = new String(console.readPassword());
		boolean flag = false;
    try{ 
	    Class.forName("com.mysql.jdbc.Driver").newInstance();
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sample","root","1234");
        prepared_statement = (PreparedStatement) con.prepareStatement("select * from login where EmpID=? and Password=?");		
	    
        String empIDinMD5 = getMd5(employeeIDTocheck);   
        String pwdinMD5 = getMd5(passwordTocheck);
		prepared_statement.setString(1,empIDinMD5);
		prepared_statement.setString(2, pwdinMD5);
        ResultSet resultset = prepared_statement.executeQuery();
		System.out.println("Records Exist "+resultset.next());
		if(resultset!=null){
		    String empIDtoCompare = resultset.getString("EmpID");
		    String pwtoCompare = resultset.getString("Password");
			
		    if((empIDinMD5.equals(empIDtoCompare)) && (pwdinMD5.equals(pwtoCompare))){
			    flag = true;
			   // JOptionPane.showMessageDialog(null, "Username and Password exist");
				System.out.println("Username and password exist");
		    }
		   if(!flag){
			  //JOptionPane.showMessageDialog(null, "Please Check Again");
			 System.out.println("Please check again");
		   }
	   } 
		  resultset.close();
con.close();
    } catch(Exception e)
        {
         System.out.println(e.toString());
        }
	}
}