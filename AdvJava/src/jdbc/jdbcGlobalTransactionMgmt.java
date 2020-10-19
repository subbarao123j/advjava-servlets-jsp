package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class jdbcGlobalTransactionMgmt {

	public static void main(String[] args) throws Exception {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection oraConn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "manu");
		Connection msqlConn = DriverManager.getConnection("jdbc:mysql://@localhost:3306/emp?serverTimezone=UTC", "root","Subbarao1516");

		if (oraConn != null && msqlConn != null) {

			Scanner sc = new Scanner(System.in);

			System.out.println("Enter source account number");

			int sourceAccNo = sc.nextInt();

			System.out.println("Enter Destination  account number");

			int destinationeAccNo = sc.nextInt();

			System.out.println("Enter Transfer amount");

			int transAmount = sc.nextInt();

			// disabling the commit mode for both db

			oraConn.setAutoCommit(false);

			msqlConn.setAutoCommit(false);

			// creating statement objects for both databases

			Statement oracleStmt = oraConn.createStatement();

			Statement sqlStmt = msqlConn.createStatement();

			// perform update oepration

			int oraclResult = oracleStmt.executeUpdate("update account set accountbal=accountbal - " + transAmount + "where accountNo=" + sourceAccNo);
			
			System.out.println("update account set balance = balance +" +transAmount+" where accountNo=" + destinationeAccNo);

			
			int mysqlResultsql = sqlStmt.executeUpdate("update account set balance = balance +" +transAmount+" where accountNo=" + destinationeAccNo);
			
			
			System.out.println(mysqlResultsql);
			

			// checking the status

			if (oraclResult == 1 && mysqlResultsql == 1) {
				
				System.out.println(oraclResult);
				
				System.out.println(mysqlResultsql);

				oraConn.commit();
				msqlConn.commit();

				System.out.println("Transaction success");

			} else {
				oraConn.rollback();
				msqlConn.rollback();
				System.out.println("Transaction Failed");

			}

		} else {

			System.out.println("Connection not created");

		}

	}

}
