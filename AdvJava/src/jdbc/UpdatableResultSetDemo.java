package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.subba.jdbc.ConnectionUtils;

public class UpdatableResultSetDemo {

	public static void main(String[] args) throws Exception {

		Connection con = ConnectionUtils.getConnection();

		if (con != null) {

			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet rs = st.executeQuery("select empno, ename, sal from emp");

			System.out.println("Empno\tEmpname\tempsal");

			System.out.println("=================");

			while (rs.next()) {

				System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));

				if (rs.getInt(3) > 1000) {

					int increaseAmount = rs.getInt(3) + 500;

					rs.updateInt(3, increaseAmount);

					rs.updateRow();

					System.out.println("salary incremented!!!!");

				}

				System.out.println();

			}

			if (rs != null)
				rs.close();

			if (st != null)
				st.close();
			con.close();

		} else {

			System.out.println("Database not connected");
		}

	}

}
