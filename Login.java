import java.sql.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;
import java.util.*;
import java.util.Scanner;
public class Login{
    public static void main(String args[]) throws Exception 
	{ 
	    Connection con;
	    PreparedStatement prepared_statement;
	    Scanner input_string = new Scanner(System.in);
	    String empIDTocheck = input_string.nextLine();
	    String passwordTocheck =input_string.nextLine();
	    boolean flag = false;
            MessageDigest md = MessageDigest.getInstance("MD5"); 
            byte[] messageDigest = md.digest(passwordTocheck.getBytes()); 
            BigInteger no = new BigInteger(1, messageDigest); 
            String pwdinMD5 = no.toString(16); 
            while (pwdinMD5.length() < 32) { 
              pwdinMD5 = "0" + pwdinMD5; 
            } 
    try{ 
	    Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = (Connection) DriverManager.getConnection("","","");
            prepared_statement = (PreparedStatement) con.prepareStatement("select * from login where EmpID=? and Password=?");		  
	    prepared_statement.setString(1,empIDTocheck);
	    prepared_statement.setString(2, pwdinMD5);
            ResultSet resultset = prepared_statement.executeQuery();
	    System.out.println("Records Exist "+resultset.next());
	    if(resultset!=null){
		    String empIDtoCompare = resultset.getString("EmpID");
		    String pwtoCompare = resultset.getString("Password");
		    if((empIDTocheck.equals(empIDtoCompare)) && (pwdinMD5.equals(pwtoCompare))){
			    flag = true;
			    System.out.println("Username and password exist");
		    }
		    if(!flag){
			    System.out.println("Please check again");
		    }
	    } 
	    resultset.close();
	    con.close();
    }
	    catch(Exception e){
		    System.out.println(e.toString());
	    }
	}
}
