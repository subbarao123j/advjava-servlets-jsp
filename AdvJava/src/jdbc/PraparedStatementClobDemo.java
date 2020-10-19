package jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.subba.jdbc.ConnectionUtils;

public class PraparedStatementClobDemo {

	public static void main(String[] args) throws Exception {

		Connection con = ConnectionUtils.getConnection();

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter customer id");
		int customerID = sc.nextInt();

		System.out.println("Enter customer name");
		String customerName = sc.next();

		System.out.println("Enter customer loc");
		String customerLocation = sc.next();

		File f = new File("D:\\MySQLReadfile.txt");

		InputStream fis = new FileInputStream(f);
		Reader r = new InputStreamReader(fis);

		if (con != null) {
			PreparedStatement pstmt = con.prepareStatement("insert into customer values (?,?,?,?,?)");
			// setting the values to indexed parameters

			pstmt.setInt(1, customerID);

			pstmt.setString(2, customerName);
			pstmt.setString(3, customerLocation);

			pstmt.setBinaryStream(4, fis, f.length());

			// System.out.println(rs.setString(2) + "====>" + rs.setString(3) + "====>" +
			// rs.setString(4));

			pstmt.setCharacterStream(5, r, f.length());

			// System.out.println(pstmt);

			int rs = pstmt.executeUpdate();

			if (rs > 0) {

				System.out.println("txt file instered with txt file");

			} else {
				System.out.println("txt file  NOT instered with Image");
			}

			if (pstmt != null)
				pstmt.close();
			con.close();

		} else {

			System.out.println("Database not connected");
		}

	}

}
