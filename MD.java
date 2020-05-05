import java.sql.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;
import java.util.*;
public class MD{
/*	
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
*/    
public static void main(String args[]) throws Exception 
    {     
Connection con;
        PreparedStatement pstm, pstm1;
        String uid1 = JOptionPane.showInputDialog("uid1");
        String pwd1 = JOptionPane.showInputDialog("pwd1");
		boolean flag = false;
        try { 
		
              Class.forName("com.mysql.jdbc.Driver").newInstance();
           con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sample","root","1234");
       pstm = (PreparedStatement) con.prepareStatement("select * from users where USER_ID=? and PASSWORD=?");
       pstm.setString(1,uid1);
		pstm.setString(2, pwd1);
       
       ResultSet rs = pstm.executeQuery();
	   while(rs.next()){
		   String emp = rs.getString("USER_ID");
		   String pw = rs.getString("PASSWORD");
		   if((uid1.equals(emp)) && (pwd1.equals(pw))){
			   flag = true;
			   JOptionPane.showMessageDialog(null, "Username and Password exist");
		   }
		   rs.close();
		   if(!flag){
			   JOptionPane.showMessageDialog(null, "Please Check Again");
		   }
	   }
		 
con.close();
    } catch(Exception e)
        {
         System.out.println(e.toString());
        }
	}
}
