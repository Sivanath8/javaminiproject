package ticketmanagement;
	import java.sql.*;
	import java.util.*;
	import java.sql.PreparedStatement;
	public class TicketManagement2 
	{
		
		public static void main(String[] args) 
		{
	        Scanner in=new Scanner(System.in);
			PreparedStatement preparedStatement = null;
					
			 System.out.println("Enter Your Id : ");
			 int id =in.nextInt();
			 System.out.println("Enter Your Movie Name : ");
			 String name=in.nextLine();
			 in.nextLine();
			 System.out.println("Enter Your show time : ");
			 double time=in.nextDouble();
			 System.out.println("Enter Your show time1 : ");
			 double time1=in.nextDouble();
			 System.out.println("Enter Your Theater_id : ");
			 int theater_id2 =in.nextInt();
		
			
			try{
				Class.forName("com.mysql.jdbc.Driver");  
				Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/theater_management","root","tiger");  
				 con.setAutoCommit(false);
				 preparedStatement =con.prepareStatement("insert into movies (movie_id,movie_names,show_time,show_time1,theater_id)values(?,?,?,?,?)");
				 preparedStatement.setInt(1,id);
				 preparedStatement.setString(2,name);
				 preparedStatement.setDouble(3,time);
				 preparedStatement.setDouble(4,time1);
				 preparedStatement.setInt(5,theater_id2); 
				 preparedStatement.addBatch();
				 
				 
				 int[]count=preparedStatement.executeBatch();
				 con.commit();
				 
				 preparedStatement.executeUpdate();
				 preparedStatement.close();
				 con.close();
				 
				 System.out.println("inserted Successfully.");
				 
			}
			catch(Exception e)
			{ 
				System.out.println(e);
			} 
		}
	}
