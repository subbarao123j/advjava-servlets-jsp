package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestConnection {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		//1. Registering the Jdbc driver
		
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		
		//2. Getting the conenction object from Driver manager
		
		Connection con =DriverManager.getConnection("jdbc:odbc:SubbaJdbc","system","manager");
		
		
		// checking con object if it is not null connection is success otherwise connection not success
		
		if (con!=null) {
			
			//3 Creating the statement object
			
			Statement st = con.createStatement();
			//4 Executing the query
			
			Scanner sc = new Scanner (System.in);
			
			System.out.println("Enter customer id to insert");
			int customerID=sc.nextInt();
			
			System.out.println("Enter customer name to insert");
			String custname=sc.next();
			
			System.out.println("Enter customer location to insert");
			String custLocation=sc.next();
			
			String query ="insert into customer values ("+customerID+","+custname+","+custLocation+")";
					int rowAffected =st.executeUpdate(query);
					
			//5.Process the business logic
					
					
			if (rowAffected>0) {
				
				System.out.println("row inserted successfully");
				
			} else {
				
				
				System.out.println("row NOT inserted successfully");
			}
			
			// close all jdbc objects
			
			if (st != null) st.close();
			
			if (con !=null) con.close();
			
			{
				
			}
			System.out.println("Conenction success");
			 {
				
				
				System.out.println("Connection Not success");
			}
			
			
		}
		
	}
	
	
	

}
