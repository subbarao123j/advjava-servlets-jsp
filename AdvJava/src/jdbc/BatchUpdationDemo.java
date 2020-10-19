package jdbc;

import java.sql.Connection;
import java.sql.Statement;

import com.subba.jdbc.ConnectionUtils;

public class BatchUpdationDemo {

	public static void main(String[] args) throws Exception {

		Connection con = ConnectionUtils.getConnection();
		
		if (con!=null) {
			
			System.out.println("Database connected");
			
			Statement st = con.createStatement();
			// adding the different queries to batch processing using addBatch() on statement object
			
			st.addBatch("insert into customer values(8877,'vustomer','pune'");
			st.addBatch("update customer set custoemrname ='XYZ' wehre customerid =2");
			
			st.addBatch("delete from customer where customerid=5 ");
			
			st.addBatch("insert into users values ('xyz','xyz', 'xyz@gmail.com', 1256)");
			//esxecuting the batch of sql commands using execute batch();
			
			int rowaffected [] = st.executeBatch();
			
			//processing the rowaffected[]
			
			for (int i=0; i<rowaffected.length ; i++) {
				
				
				System.out.println(" Row status of rowaffected["+i+"]=="+ rowaffected[i]);
				
			}
			
			if (st != null) st.close();
			
			con.close();
			
			
		} else {
			
			
			
			System.out.println("database Not connected");
		}
		
		
		
	}

}
