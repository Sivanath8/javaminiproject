package ticketmanagement;
	import java.util.*;
	import java.sql.*; 
	public class Booking {			      
		public static void cutomer_details(Connection con) throws Exception {
			Scanner in = new Scanner(System.in);
			System.out.println("Enter Your Mobile Number : ");
			Long num = in.nextLong();
			Booking obj=new Booking();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from customers where Phone_Number='" + num + "';");
			//boolean has_result = rs.next();
			int c_id=0;
			rs.next();
			
			c_id=rs.getInt("customer_id");
			System.out.println(c_id);
			System.out.println(c_id);
			System.out.println("DEBUG-->1");
			/*if (has_result)
			{
				System.out.println("your Are Already Registered");
				System.out.println("---------------------------");
				obj.Movie_details(con);

			
			}
			else 
			{
			   System.out.println("your Not Register Kindly Register Name And Mobilenumber");
			   System.out.println("-------------------------------------------------------");
			   obj.register(con);
			   ResultSet rs1 = stmt.executeQuery("select customer_id from customers where Phone_Number='" + num + "';");
			   while(rs1.next())
			   {
				   c_id=rs1.getInt("customer_id");
				   System.out.println(c_id);
			   }System.out.println("debug-->2");
			   
			}*/
		}
		public void register(Connection con) throws Exception {
			PreparedStatement preparedStatement = null;
			try {
				Scanner cu = new Scanner(System.in);
				System.out.println("Enter Your Name : ");
				String Name = cu.nextLine();
				System.out.println("Please RE-Enter Your Mobilenumber : ");
				Long phonenumber=cu.nextLong();
				con.setAutoCommit(false);
				preparedStatement = con.prepareStatement("insert into customers(Name,Phone_Number) values(?,?)");
				preparedStatement.setString(1, Name);
				preparedStatement.setLong(2, phonenumber);
				preparedStatement.addBatch();
				int[] count = preparedStatement.executeBatch();
				con.commit();
				preparedStatement.executeUpdate();
				preparedStatement.close();
				con.close();
			}

			catch (Exception e) {
				System.out.println(e);
			}
		}

		public static int Movie_details(Connection con) throws Exception {
			Scanner in = new Scanner(System.in);
			try 
			{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select movie_names from movies " );
				System.out.println("Choose Your Movie : ");
	            System.out.println("----------------------");
				while (rs.next()) {
					System.out.println(rs.getString(1));
				}
				System.out.println("\n Select Your Movie");
				int num = in.nextInt();
				ResultSet rs1=stmt.executeQuery("select theater_name,seat_availablity,price from theaters where movie_id ="+num+";");
				while(rs1.next()){
					System.out.println("Theater : "+rs1.getString(1)+"\n---------------------"+"\nSeats : "+rs1.getInt(2)+"\n---------------------"+"\nprice : "+rs1.getInt(3)+"\n---------------------");
				}
				ResultSet rs2 = stmt.executeQuery("select show_time,show_time1 from movies where movie_id=" + num + ";");
				while (rs2.next()) {
					System.out.println("showtime :" +rs2.getString(1) +"\n---------------------"+ "\nshowtime1 :" +rs2.getString(2)+"\n---------------------" );
				}
				ResultSet rs3 = stmt.executeQuery("select * from movies where movie_names in ('varisu','thunivu','naan','mass')");
				int m_id;
				while (rs3.next())
				{
					m_id=rs3.getInt("movie_id");
					return m_id;
							
				}
			}
				catch (Exception e) {
					System.out.println(e);
				}
			return 0;
		}
		 

		public void orders(Connection con) throws Exception
		{
			
			
			int price=0;
			int totalprice=0;
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
			System.out.println(payment_via);
			if (payment_via==  totalprice)
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
			preparedStatement =con.prepareStatement("insert into orders(ticket_count,amount,payment_via,movie_id,customer_id) values(?,?,?,?,?)");
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
			
			
			con.close();
		
		}
		catch ( Exception e)
		{
			System.out.println(e);
			
		}
	 }
	
		public void booked_details(Connection con,int m_id)throws Exception
		{
			String cusName=null;
			String moviename=null;
			int oid=0;
			int tc=0;
			int pay=0;
			try 
			{
				Statement stmt = con.createStatement();
				ResultSet crs=stmt.executeQuery("select name from customers where customer_id='"+c_id+"'");
				while(crs.next())
				{
					cusName=crs.getString("name");
				}
				ResultSet mrs=stmt.executeQuery("select movie_names from movies where movie_id='"+m_id+"'");
				while(mrs.next())
				{
					moviename=mrs.getString("movie_names");
				}
				ResultSet ors=stmt.executeQuery("select order_id,ticket_count,payment_via from orders where order_id=(select MAX(order_id) from orders)");

			
				while(ors.next())
				{
					oid=ors.getInt("order_id");
					tc=ors.getInt("ticket_count");
					pay=ors.getInt("payment_via");
				}	
				System.out.println(oid);
				System.out.println(cusName);
				System.out.println(moviename);
				System.out.println(tc);
				System.out.println(pay);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}

