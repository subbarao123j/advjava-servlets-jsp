package jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.subba.jdbc.ConnectionUtils;

public class PraparedStatementBlobDemo {

	public static void main(String[] args) throws Exception {

		Connection con = ConnectionUtils.getConnection();

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter customer id");
		int customerID = sc.nextInt();
		System.out.println("Enter customer Name");
		String customerName = sc.next();
		System.out.println("Enter customer location");
		String customerLocation = sc.next();

		File f = new File("D:\\CPU.jpeg");

		FileInputStream fis = new FileInputStream(f);

		if (con != null) {
			PreparedStatement pstmt = con.prepareStatement("insert into customer values (?,?,?,?)");
			// setting the values to indexed parameters

			pstmt.setInt(1, customerID);
			pstmt.setString(2, customerName);
			pstmt.setString(3, customerLocation);

			// pstmt.setBlob(parameterIndex, inputStream, length);

			pstmt.setBinaryStream(4, fis, f.length());

			int rowCount = pstmt.executeUpdate();

			if (rowCount > 0) {

				System.out.println("Row instered with Image");

			} else {
				System.out.println("Row NOT instered with Image");
			}

			if (pstmt != null)
				pstmt.close();
			con.close();

		} else {

			System.out.println("Database not connected");
		}

	}



}
