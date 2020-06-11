package com.javahelps.mysqlrestservice;
import com.javahelps.mysqlrestservice.Repository.*;
import java.sql.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.util.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@ComponentScan({"com.javahelps.mysqlrestservice"})
@EntityScan("com.javahelps.mysqlrestservice")
@EnableJpaRepositories(basePackageClasses=Repositorydata.class)
@SpringBootApplication
public class MysqlRestServiceApplication {

	public static void main(String[] args) throws NoSuchAlgorithmException  {
		SpringApplication.run(MysqlRestServiceApplication.class, args);
		 Connection con;
		    PreparedStatement prepared_statement;
		    String pwdinMD5="";
		    Scanner input_string = new Scanner(System.in);
		    String empIDTocheck = input_string.nextLine();
		    String passwordTocheck = input_string.nextLine();
		    boolean flag = false;
	            MessageDigest md = MessageDigest.getInstance("MD5"); 
	            byte[] messageDigest = md.digest(passwordTocheck.getBytes()); 
	            BigInteger no = new BigInteger(1, messageDigest); 
	            pwdinMD5 = no.toString(16); 
	            while (pwdinMD5.length() < 32) { 
	              pwdinMD5 = "0" + pwdinMD5; 
	            }
	
	            
	    try{ 
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
	            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/helpdesk?user=root&password=1234");
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
