package ticketmanagement;



import java.sql.Connection;

import java.sql.DriverManager;
import java.util.*;

public class Main { 
	
		
	public static void main(String[] args) 
	{
		Booking obj=new Booking();
		Scanner in = new Scanner(System.in);			
	try
	{
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/theater_management","root","tiger");
	
		//obj.cutomer_details(con);
	//	int mid =obj.Movie_details(con);
		obj.orders(con);
		//obj.booked_details(con, mid);
		
	}
		catch (Exception e)
	{
			System.out.println(e);
	}
	
	}


		
	}

