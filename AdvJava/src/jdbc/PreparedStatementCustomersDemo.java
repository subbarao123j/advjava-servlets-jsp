package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.subba.jdbc.ConnectionUtils;

public class PreparedStatementCustomersDemo {

	public static void main(String[] args) throws Exception {

		Connection con = ConnectionUtils.getConnection();

		Scanner sc = new Scanner(System.in);

		System.out.println("Eanter number of records");

		int noOfRecords = sc.nextInt();

		// taking global variables

		int totalrecordsInserted = 0;

		int noofrecordsinserted;

		if (con != null) {

			PreparedStatement pstmt = con.prepareStatement("insert into customer values( ?,?,?)");
			for (int i = 1; i <= noOfRecords; i++) {
				System.out.println("Enter customer id");

				int customerId = sc.nextInt();

				System.out.println("customer Name");

				String customerName = sc.next();

				System.out.println("Customr Location");

				String custoemerLocation = sc.next();

				/*System.out.println("Customr email");

				String custoemerEmail = sc.next();*/

				pstmt.setInt(1, customerId);

				pstmt.setString(2, customerName);

				pstmt.setString(3, custoemerLocation);
				//pstmt.setString(4, custoemerEmail);

				noofrecordsinserted = pstmt.executeUpdate();

				if (noofrecordsinserted > 0) {

					// To know no of records inserted into db

					// totalrecordsInserted = noofrecordsinserted +totalrecordsInserted;
					// sorted method
					totalrecordsInserted += noofrecordsinserted;
				}

			}

			System.out.println("Total no of rows inserted:::" + totalrecordsInserted);

			// close the jdbc objects

			if (pstmt != null)
				pstmt.close();
			con.close();

		}

		else {

			System.out.println("Database not connected");
		}

	}

}
