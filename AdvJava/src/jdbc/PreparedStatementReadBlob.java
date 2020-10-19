package jdbc;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.subba.jdbc.ConnectionUtils;

public class PreparedStatementReadBlob {

	public static void main(String[] args) throws Exception {

		Connection con = ConnectionUtils.getConnection();

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter customer ID");

		int customerId = sc.nextInt();

		System.out.println("customerId");

		if (con != null) {

			PreparedStatement pstmt = con.prepareStatement("select * from customer where customerId =?");

			pstmt.setInt(1, customerId);
			ResultSet rs = pstmt.executeQuery();

			rs.next();

			System.out.println(rs.getString(1) + "====>" + rs.getString(2) + "====>" + rs.getString(3));

			InputStream is = rs.getBinaryStream(4);

			FileOutputStream fos = new FileOutputStream("D:\\readingImageFromDB.jpeg");

			byte[] buf = new byte[4096];

			int readByte = 0;

			while (is.read(buf) != -1) {

				fos.write(buf, 0, readByte);

			}

			System.out.println("Image is avaialbile at destination location ");

			if (rs != null)
				rs.close();

			if (pstmt != null)
				pstmt.close();

			con.close();

		} else {

			System.out.println("DB not connected");
		}

	}

}
