package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.subba.jdbc.ConnectionUtils;

public class ResultSetDeleteDemo {

	public static void main(String[] args) throws Exception {

		
		Connection con = ConnectionUtils.getConnection();
		
		
		Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				
				Scanner sc = new Scanner (System.in);
		
		int customerId = sc.nextInt();
		
		ResultSet rs = st.executeQuery("select * from customer");
		
		while (rs.next()) {
			
			System.out.println(rs.getString(1)+"======>"+rs.getString(2)+"=====>"+rs.getString(3));
			
			
			if (rs.getInt(1) == customerId) {
				//deleting the row in resultset object
				
				rs.deleteRow();
				
				System.out.println("Record deleted");
				
				
				
			}
			
			
		}
		
		
		if (rs != null) rs.close();
		if (st !=null) st.close();
		if (con != null)con.close();

		
		
	}

}
