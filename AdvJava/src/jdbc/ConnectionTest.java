package jdbc;

import java.sql.Connection;

import com.subba.jdbc.ConnectionUtils;

public class ConnectionTest {


	public static void main(String[] args) throws Exception {
		

		
		Connection con = ConnectionUtils.getConnection();
		
		if (con != null) {
			
			System.out.println("Conncetion success");
		}else {
			
			System.out.println("Conncetion NOT success");
			
			
			
		}
	}

}
