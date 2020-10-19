package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.subba.jdbc.ConnectionUtils;

public class PreparedStatementLoginDemo {

	public static void main(String[] args) throws Exception {
		
		Connection con = ConnectionUtils.getConnection();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter username to validate");
		
		String username = sc.next();
		System.out.println("Enter password to validate");
		
		String password = sc.next();
		
		
		if (con !=null)
		{
		//create Preparedstatement object
		
		
		PreparedStatement pstmt = con.prepareStatement("select * from users where username=? and pwd =?");
		
		//setting the values to the place holders/indexed parameters
		
		pstmt.setString(1, username);
		pstmt.setString(2, password);
		
		//executiong the sql query
		
		ResultSet rs = pstmt.executeQuery();
		
		//getting the count value
		
		rs.next();
		
		int countvalue = rs.getInt(1);
		
		if (countvalue >0) {
			
			
			System.out.println("Login success");
		}else {
			
			System.out.println("login failed");
			
		}
		
		//close the jdbc objects
		
		if (pstmt!= null) pstmt.close();
		
		con.close();
		
		
		

		
		
		
		
		}else {
		
		System.out.println("Database NOT connected");
	}
		
		
	
	}
}
