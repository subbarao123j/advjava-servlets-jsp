package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.subba.jdbc.ConnectionUtils;

public class PreparedStatementDatdemo {

	public static void main(String[] args) throws Exception {

		Connection con = ConnectionUtils.getConnection();

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter customer id");
		int customerId = sc.nextInt();

		System.out.println("Enter customer Name");
		String customername = sc.next();
		System.out.println("Enter customer Location");
		String customerLocation = sc.next();
		System.out.println("Enter date of joing (dd/monthname/yyyy)");

		String dateOfJoining = sc.next();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMM/yyyy");

		// System.out.println(sdf.format(dateOfJoining));

		java.util.Date dateOfJoin = sdf.parse(dateOfJoining);

		java.util.Date d = new java.util.Date();

		if (con != null) {

			PreparedStatement pstmt = con.prepareStatement("insert into customer values(?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, customerId);
			pstmt.setString(2, customername);
			pstmt.setString(3, customerLocation);
			pstmt.setBinaryStream(4, null);
			pstmt.setBinaryStream(5, null);

			// setting value for date of joining column

			pstmt.setDate(6, new java.sql.Date(dateOfJoin.getTime()));

			// setting value for date of record created
			pstmt.setDate(7, new java.sql.Date(d.getTime()));

			// setting value for date of record updation

			pstmt.setDate(8, new java.sql.Date(d.getTime()));

			int rowCount = pstmt.executeUpdate();

			if (rowCount > 0) {

				System.out.println("Row inserted");

			} else {

				System.out.println("Row not Inserted");
			}

			if (pstmt != null)
				pstmt.close();

			con.close();

		} else {

			System.out.println("database not connected");
		}

	}

}
