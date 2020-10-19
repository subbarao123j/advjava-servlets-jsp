package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.Scanner;

import com.subba.jdbc.ConnectionUtils;


/*create or replace procedure user_login_process (uname in varchar2, pwd in varchar2, loginstatus out varchar2) is
countvalue number;

begin

select count(*) into countvalue from users where userName = uname and passWord = pwd;

if (countvalue <> 0 ) then 

loginstatus := 'Login Success';
else
loginstatus := 'Login Not success';

end if;

end;*/



public class CallableStatementValidUserLogin {

	public static void main(String[] args) throws Exception {

		Connection con = ConnectionUtils.getConnection();

		if (con != null) {

			System.out.println("Connection created");

			Scanner sc = new Scanner(System.in);
			System.out.println("Enter valid UseId");

			String  validUser = sc.next();
			
			System.out.println("Enter valid password");

			String  validPassword = sc.next();

			String query = "{call USER_LOGIN_PROCESS_NEW(?,?,?,?)}";

			CallableStatement cstmt = con.prepareCall(query);

			// Registering out parameters

			cstmt.registerOutParameter(3, Types.INTEGER);

			cstmt.registerOutParameter(4, Types.VARCHAR);

			// setting the in parameters

			cstmt.setString(1, validUser);
			cstmt.setString(2, validPassword);

			// executing the input parameters

			boolean flag = cstmt.execute();

			if (!flag) {

				System.out.println(" Procedure executed");

				System.out.println("Count value :::" + cstmt.getInt(3)); //countvalue

				System.out.println("Login status::::" + cstmt.getString(4));//loginstatus

			} else {

				System.out.println("Procedure not executed");
			}

			if (cstmt != null)
				cstmt.close();

			con.close();

			if (sc != null)
				sc.close();

		} else {

			System.out.println("Connection not created");
		}

	}

}
