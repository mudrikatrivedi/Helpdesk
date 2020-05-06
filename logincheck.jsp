
<%@page import="java.util.*"%>
<%@page import="java.io.IOException"%>
<%@page import="java.sql.*"%>
<%@page import="java.math.BigInteger"%>
<%@page import="java.security.MessageDigest"%>
<%@page import="java.security.NoSuchAlgorithmException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<a href="logincheck.jsp"></a>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login check Page</title>
    </head>
    <body>
        <%  
        Connection con;
        PreparedStatement pstm;
        String uid1 = request.getParameter("uid1");
        String pwd = request.getParameter("pwd");
        
        MessageDigest md = MessageDigest.getInstance("MD5");			     
        byte[] messageDigest = md.digest(pwd.getBytes());
            // digest() method is called to calculate message digest           
         BigInteger no = new BigInteger(1, messageDigest);
            // Convert byte array into signum representation
 
            String pwd1 = no.toString(16);
            while(pwd1.length() < 32)
            {
                pwd1 = "0" + pwd1;
            }

	   try { 
             Class.forName("com.mysql.jdbc.Driver");
             con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/helpdeskDB","root","");
            pstm = (PreparedStatement) con.prepareStatement("select * from login where EmpId=? and Password=?");
            pstm.setString(1,uid1);
            pstm.setString(2, pwd1);
             ResultSet rs = pstm.executeQuery();
         if(rs.next())
            {     
             session=request.getSession();
             session.setAttribute("uid1",uid1);
             session.setAttribute("pwd1",pwd1); 
             response.sendRedirect("next.jsp");
                con.close();
            } 

    else{ 
        out.print("Sorry username or password error"); 
        }
    
           } catch(Exception e)
                {
                     out.println(e.toString());
                }
           
        
        %>
    </body>
</html>
