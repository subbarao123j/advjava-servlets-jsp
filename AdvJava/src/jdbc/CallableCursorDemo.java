package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import com.subba.jdbc.ConnectionUtils;

import oracle.jdbc.internal.OracleTypes;

/*
create or replace 
procedure GetAllEmployees(Details Out Sys_Refcursor) As
Begin
Open Details for select empno,ename,job,sal from emp;

End;
 */

public class CallableCursorDemo {

	public static void main(String[] args) throws Exception {

		Connection con = ConnectionUtils.getConnection();

		if (con != null) {

			String query = "{call GetAllEmployees(?)}";

			CallableStatement cstmt = con.prepareCall(query);

			cstmt.registerOutParameter(1, OracleTypes.CURSOR);

			boolean flag = cstmt.execute();

			if (!flag) {

				System.out.println("Procedure executed");

				ResultSet rs = (ResultSet) cstmt.getObject(1);

				while (rs.next()) {

					System.out.println(rs.getString(1) + "----------------" + rs.getString(2) + "----------"
							+ rs.getString(3) + "--------" + rs.getString(4));

				}

				if (rs != null)
					rs.close();
				if (cstmt != null)
					cstmt.close();

				con.close();

			}
		

		} else {

			System.out.println("connection not suceess");

		}

	}

}
