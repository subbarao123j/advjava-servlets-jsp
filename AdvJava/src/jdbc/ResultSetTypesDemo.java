package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.subba.jdbc.ConnectionUtils;

public class ResultSetTypesDemo {

	public static void main(String[] args) throws Exception {

		Connection con = ConnectionUtils.getConnection();

		if (con != null) {

			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs = st.executeQuery("select * from emp order by empno ");

			// processing the records towards in forward direction
			while (rs.next()) {

				System.out.println(rs.getString(1) + "=====>" + rs.getString(2) + "===========>" + rs.getString(3));

			}

			// ResultSet pointer position availabile at ALR position

			System.out.println();

			System.out.println("backward Direction");

			while (rs.previous()) {

				System.out.println(rs.getString(1) + "=====>" + rs.getString(2) + "===========>" + rs.getString(3));

			}
			// ResultSet pointer availabile at BFR position

			rs.next();

			//

			if (rs.relative(2)) {

				System.out.println(rs.getRow() + "=====>" + rs.getString(1) + "=====>" + rs.getString(2)
						+ "===========>" + rs.getString(3));

			}

			if (rs.relative(-2)) {

				System.out.println(rs.getRow() + "=====>" + rs.getString(1) + "=====>" + rs.getString(2)
						+ "===========>" + rs.getString(3));

			}

			if (rs.relative(5)) {

				System.out.println(rs.getRow() + "=====>" + rs.getString(1) + "=====>" + rs.getString(2)
						+ "===========>" + rs.getString(3));

			}

			System.out.println();

			System.out.println("fetching 5th record with respective to BFR position");

			if (rs.absolute(5)) {

				System.out.println(rs.getRow() + "=====>" + rs.getString(1) + "=====>" + rs.getString(2)
						+ "===========>" + rs.getString(3));

			}

			if (rs.absolute(-5)) {

				System.out.println(rs.getRow() + "=====>" + rs.getString(1) + "=====>" + rs.getString(2)
						+ "===========>" + rs.getString(3));

			}

			// closing connections
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();

			con.close();

		} else {

			System.out.println("connection not success");
		}

	}

}
