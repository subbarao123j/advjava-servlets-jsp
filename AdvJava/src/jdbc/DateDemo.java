package jdbc;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateDemo {

	public static void main(String[] args) throws ParseException {
		
		//Displaying curent  date  in required format
		
		java.util.Date d = new java.util.Date();
		
		System.out.println(d);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMM/yyyy");
		
		String currentDate = sdf.format(d);
		System.out.println(currentDate);
		
		
		//Usr given input displaying in required format
		Scanner sc = new Scanner (System.in);
		System.out.println("Please enter date following format (dd/mm/yy)");
		
		String inputdate = sc.next();
		
		java.util.Date d2 =sdf.parse(inputdate);
		
		System.out.println(d2);
		
		System.out.println(sdf.format(d2));
		
		//obtaining the java.sql.Date object from java.util.date
		
		
		java.sql.Date d5 = new java.sql.Date(d2.getTime());
		
		System.out.println(d5);
		
		
			
		
		
		
		
	}

}
