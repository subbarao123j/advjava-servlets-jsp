package jdbc;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

import com.subba.jdbc.ConnectionUtils;

public class JDBCLocalTransactionMgt {

	public static void main(String[] args) throws Exception {

		Connection con = ConnectionUtils.getConnection();

		if (con != null) {

			Scanner sc = new Scanner(System.in);

			System.out.println("Enter source account number");

			int sourceAccNo = sc.nextInt();

			System.out.println("Enter Destination  account number");

			int destinationeAccNo = sc.nextInt();

			System.out.println("Enter Transfer account number");

			int transAmount = sc.nextInt();

			// converting the jdbc application from commit mode to uncommit mode

			con.setAutoCommit(false);

			// Creating statement object

			Statement st = con.createStatement();

			// deducting the balance feom source acc

			st.addBatch("update account set accountBal=accountBal- " + transAmount + "where accountNo=" + sourceAccNo);

			// adding the balance to destination account

			st.addBatch("update account set  accountBal = accountBal + " + transAmount + "where accountNo="
					+ destinationeAccNo);

			// executing the batch commands

			int rowAffected [] = st.executeBatch();

			// process the rowAffected[]

			Boolean transactionstatus = Boolean.TRUE;

			for (int i = 0; i < rowAffected.length; i++) {
				
				System.out.println("status value:::"+rowAffected[i]);

				if (rowAffected[i] == 0) {
					transactionstatus = Boolean.FALSE;

				}

			}

			// checking the transactionstatus flag

			if (transactionstatus) {

				con.commit();

				System.out.println("Transaction success Amount transgerd succesfully!!!!");

			} else {
				
				con.rollback();

				System.out.println("Transaction failedAmount transgerd failed !!!!");

			}

		} else {

			System.out.println("Connection not created");
		}

	}

}
