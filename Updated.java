package ticketmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Updated {

	public void orders(Connection con) throws Exception
	{
		
		
		int price=0;
		int totalprice=0;
		int totalprice1=totalprice;
		PreparedStatement preparedStatement = null;
	try(Statement stmt=con.createStatement())
	{
		Scanner in=new Scanner(System.in);
		Statement stmt1=con.createStatement();
		ResultSet rs =stmt.executeQuery("select theater_name from theaters");
		while(rs.next())
		{
			System.out.println(rs.getString(1));
		}
		System.out.println("--------------------------------");
		System.out.println("Confirm to Book Your theater : ");
		int ans=in.nextInt();
		ResultSet rs1= stmt1.executeQuery("select price from theaters where theater_id ="+ans+";");
		while (rs1.next())
		{
			price =rs1.getInt(1);
		}
		System.out.println("Enter Your Ticket Count :");
		int ticket_count =in.nextInt();
		totalprice=ticket_count*price;
		System.out.print("Your Total Price = ");
		System.out.println(totalprice);	
		System.out.println(" debug2 --> Enter Your payment method : ");
		String payment_via = in.next();

		
		String price1=payment_via;
		System.out.println(price1);
		System.out.println(totalprice1);
	  /* if(payment_via==totalprice1);
		{
			System.out.println("your payment Succesfully done");
		}
		else
		{
			System.out.println("your Not ");
		}
		//System.out.println("Enter Your movie_id : ");
		//int movie_id=in.nextInt();
		con.setAutoCommit(false);
		preparedStatement =con.prepareStatement("insert into orders(ticket_count,amount,payment_via) values(?,?,?)");
		preparedStatement.setInt(1, price);
		preparedStatement.setInt(2, ticket_count);
		preparedStatement.setString(3,payment_via);
		//preparedStatement.setInt(4,m_id);
	
	//	preparedStatement.setInt(5,);
		preparedStatement.addBatch();
		int[] count = preparedStatement.executeBatch();
		con.commit();
		preparedStatement.executeUpdate();
		preparedStatement.close();
		
		
		con.close();*/
	
	}
	catch ( Exception e)
	{
		System.out.println(e);
		
	}
 }

	public static void main(String[] args)
	{
				PreparedStatement preparedStatement = null;
				Updated obj=new Updated();
			

			try{
				Class.forName("com.mysql.jdbc.Driver");  
				Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/theater_management","root","tiger");  
				 con.setAutoCommit(false);
				 preparedStatement =con.prepareStatement("update theaters set price=price+20 where price<=150 ");
				 preparedStatement.addBatch();	 
				 int[]count=preparedStatement.executeBatch();
				 con.commit();
				 
				 preparedStatement.executeUpdate();
				 obj.orders(con);
				 preparedStatement.close();
				 con.close();
				 
				 System.out.println("inserted Successfully.");
				 obj.orders(con);
				 
			}
			catch(Exception e)
			{ 
				System.out.println(e);
			} 
		}
	

	}

