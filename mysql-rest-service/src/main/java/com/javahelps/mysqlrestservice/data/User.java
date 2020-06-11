package com.javahelps.mysqlrestservice.data;
import java.util.*;
import javax.persistence.*;

//import org.springframework.data.annotation.Id;
//import org.hibernate.annotations.Entity;
@Entity
@Table(name = "login")
public class User {
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name="EmpID")
		private String empID;
		@Column(name="Password")
		private String password;
			  public User(){
				  
			  }
		public User(String empID, String password) {
	        this.setEmpID(empID);
	        this.setPassword(password);
		
		}
		public void setEmpID(String empID) {
			this.empID=empID;
		}
		public String getEmpID() {
			return empID;
		}

		public void setPassword(String password) {
			this.password=password;
		}
		public String getPassword() {
			return password;
		}
		 @Override
		    public String toString() {
		        return "User{" +
		                "EmpID=" + empID +
		                ", password='" + password + '\'' +
		                '}';
		    }
}
