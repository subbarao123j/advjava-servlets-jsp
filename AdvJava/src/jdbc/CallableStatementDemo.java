package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Scanner;

import com.subba.jdbc.ConnectionUtils;

public class CallableStatementDemo {

	public static void main(String[] args) throws Exception {

		Connection con = ConnectionUtils.getConnection();

		if (con != null) {

			Scanner sc = new Scanner(System.in);

			System.out.println("Enter account number::::");

			int accNumber = sc.nextInt();
			System.out.println("Enter account name::::"+accNumber);

			String accName = sc.next();

			System.out.println("Enter account Balance::::");

			int accBalance = sc.nextInt();
			System.out.println("Enter account Type::::");

			String accType = sc.next();
			  System.out.println("Enter account Address::::");

			String accAddres = sc.next();
			System.out.println("Enter account Phoen Number::::");

			int accPhno = sc.nextInt();

			// Prepare database call to execute the plsql procedure

			String query = "{call create_account(?,?,?,?,?,?)}";

			// Creating the callable statement object
			CallableStatement cstmt = con.prepareCall(query);

			// setting the input values to plsql procedure

			cstmt.setInt(1, accNumber);
			cstmt.setString(2, accName);
			cstmt.setInt(4, accBalance);

			cstmt.setString(3, accType);

			cstmt.setString(5, accAddres);
			cstmt.setInt(6, accPhno);

			// executing the plsql procedure

			boolean flag = cstmt.execute();

			if (!flag) {

				System.out.println("Procedure is executed");
			} else {

				System.out.println("Procedure not executed");
			}

			// clsoe all jdbc objects

			if (cstmt != null)
				cstmt.close();

			con.close();

		} else {

			System.out.println("database not connected");
		}

	}

}
