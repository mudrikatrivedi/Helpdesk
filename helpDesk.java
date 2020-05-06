import java.sql.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;
import java.util.*;
public class helpDesk{
    public static String getMd5(String input) 
    {
        try { 
  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
              hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 
    public static void main(String args[]) throws Exception 
	{ 
	Connection con;
        PreparedStatement pstm, pstm1;
        String emp = JOptionPane.showInputDialog("emp");
        String pw = JOptionPane.showInputDialog("pwd");
		boolean flag = false;
    try{ 
	    Class.forName("com.mysql.jdbc.Driver").newInstance();
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sample","root","1234");
        pstm = (PreparedStatement) con.prepareStatement("select * from login where EmpID=? and Password=?");
        String e = getMd5(emp);   
        String p = getMd5(pw);		
	        pstm.setString(1,e);
		pstm.setString(2, p);
       
        ResultSet rs = pstm.executeQuery();
	    if(rs!=null){
		    String emp1 = rs.getString("EmpID");
		    String pw1 = rs.getString("Password");
		    if((e.equals(emp1)) && (p.equals(pw1))){
			    flag = true;
			    JOptionPane.showMessageDialog(null, "Username and Password exist");
			    System.out.println("Username and password exist");
		    }
		   rs.close();
		   if(!flag){
			   JOptionPane.showMessageDialog(null, "Please Check Again");
			   System.out.println("Please check again");
		   }
	   }
		 
con.close();
    } catch(Exception e)
        {
         System.out.println(e.toString());
        }
	}
}
