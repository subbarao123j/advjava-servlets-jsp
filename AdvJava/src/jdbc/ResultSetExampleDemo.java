package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.subba.jdbc.ConnectionUtils;

public class ResultSetExampleDemo {

	public static void main(String[] args) throws Exception {

		Connection con1 = ConnectionUtils.getConnection();

		if (con1 != null) {

			// Statement st1 = con1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
			// ResultSet.CONCUR_READ_ONLY);
			Statement st1 = con1.createStatement();

			Connection con2 = ConnectionUtils.getConnection();

			Statement st2 = con2.createStatement();

			// String updateQuery = "update customer set customerName=\'Rao\' where
			int custoemrId = 1234;

			String updateQuery = "update customer set customerName = 'Suresh' where customerId=" + custoemrId;

			int rowAffected = st2.executeUpdate(updateQuery);

			System.out.println(rowAffected);

			System.out.println("after udpation of Customername ");

			System.out.println("Now reading the data once again from ResultSet object");

			// moving the resultset pointer from ALR to BFR or BFR to ALR

			// rs.beforeFirst(); // It moves cursor from ALR to BFR

			ResultSet rs = st1.executeQuery("select * from customer order by customerName");

			System.out.println("customerId \t CustomerName \t customerLocation");

			System.out.println("================");

			while (rs.next()) {

				System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));

			}
			if (st2 != null)
				st2.close();
			if (rs != null)
				rs.close();
			if (st1 != null)
				st2.close();
			con2.close();
			con1.close();

		}

	}

}
