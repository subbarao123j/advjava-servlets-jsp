package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.subba.jdbc.ConnectionUtils;

public class ResultSetInsertingDemo {

	public static void main(String[] args) throws Exception {
		
		
 Connection con = ConnectionUtils.getConnection();
 
 
		Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		ResultSet rs = st.executeQuery("select * from customer");
		
		
		//inserting new row into Resultset object
		
		rs.moveToInsertRow();
		rs.updateInt(1, 1234533);
		rs.updateString(2, "ResulatSetInsert");
		rs.updateString(3, "Hyderabad");
		rs.insertRow();
		
		System.out.println("new customer record created through updatabile resultSet");
		
		
		if (rs != null) rs.close();
		
		if (st != null) st.close();
		
		if (con != null) con.close();
		
		
		
		
	}

}
