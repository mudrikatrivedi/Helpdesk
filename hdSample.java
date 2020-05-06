import java.sql.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;
import java.util.*;
public class hdSample{
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
    try{    
	Class.forName("com.mysql.jdbc.Driver").newInstance();
    Connection con =
    DriverManager.getConnection("jdbc:mysql://localhost:3306/sample?user=root&password=1234");
    String n=JOptionPane.showInputDialog("Enter Employee Id:");
    String s=JOptionPane.showInputDialog("Enter Password:");
    String emp= getMd5(n);
	String pw=getMd5(s);
	//System.out.println(emp +" "+pw);
 // ? : place holder or parameter (parameterized query)
    String sql = "INSERT INTO login1(EmpID, Password) VALUE(?,?)";
    PreparedStatement st = con.prepareStatement(sql);
 //bind data in PST
    st.setString(1, emp);// 1 specifies the first parameter in the query
    st.setString(2, pw); // 2 specifies the second parameter in the query
    st.executeUpdate(); //no agrument
    st.close();
    con.close();
    System.out.println("---SQL executed successfully---");
con.close();
    } catch(Exception e)
        {
         System.out.println(e.toString());
        }
	}
}