package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.Scanner;

import com.subba.jdbc.ConnectionUtils;



/*create or replace 
procedure getBalance (accountnumber in number, accountbalance out number, status out varchar2) is  
		begin
		select amt into accountbalance from naresh_bank where accnumber =accountnumber;
		if (accountbalance <> 0) then
		status := 'Account Found in database';
		else
		status := 'Account Not found in database';
		end if;
 end;*/

public class CallableStatementBalanceDemo {

	public static void main(String[] args) throws Exception {

		Connection con = ConnectionUtils.getConnection();

		if (con != null) {

			System.out.println("Connection created");

			Scanner sc = new Scanner(System.in);
			System.out.println("Enter account number");

			int accNumber = sc.nextInt();

			String query = "{call getBalance(?,?,?)}";

			CallableStatement cstmt = con.prepareCall(query);

			// Registering out parameters

			cstmt.registerOutParameter(2, Types.VARCHAR);

			cstmt.registerOutParameter(3, Types.VARCHAR);

			// setting the in parameters

			cstmt.setInt(1, accNumber);

			// executing the input parameters

			boolean flag = cstmt.execute();

			if (!flag) {

				System.out.println(" Procedure executed");

				System.out.println("AccoutnBalance :::" + cstmt.getInt(2)); //account balance

				System.out.println("Status::::" + cstmt.getString(3));//statu column

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
