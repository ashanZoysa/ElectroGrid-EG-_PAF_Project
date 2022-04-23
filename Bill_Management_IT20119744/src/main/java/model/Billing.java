package model; 
import java.sql.*; 

public class Billing
{ //A common method to connect to the DB
		private Connection connect(){ 
			
						Connection con = null; 
						
						try{ 
								Class.forName("com.mysql.jdbc.Driver"); 
 
								//Provide the correct details: DBServer/DBName, username, password 
								con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electro_grid(eg)_db", "root", ""); 
						} 
						catch (Exception e) {
							e.printStackTrace();
							} 
						
						return con; 
			} 
		
		
		public String createBills(String MeterNumber, String ConsumerName, String Address, String DurationPeriod, String NoOfUnits, String TotalAmount){ 
			
					String output = ""; 
					
					try
					{ 
						Connection con = connect(); 
						
						if (con == null) 
						{
							return "Error while connecting to the database for inserting."; 
							
						} 
						// create a prepared statement
						
						String query = " insert into billing_management (`meterNumber`,`consumerName`,`address`,`durationPeriod`,`noOfUnits`,`totalAmount`)"+" values (?, ?, ?, ?, ?, ?)"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1,Integer.parseInt(MeterNumber)); 
						preparedStmt.setString(2, ConsumerName); 
						preparedStmt.setString(3, Address); 
						preparedStmt.setDouble(4, Integer.parseInt(DurationPeriod)); 
						preparedStmt.setInt(5, Integer.parseInt(NoOfUnits)); 
						preparedStmt.setDouble(6, Double.parseDouble(TotalAmount));
						// execute the statement
 
						preparedStmt.execute(); 
						con.close(); 
						output = "Inserted successfully"; 
					} 
					
					catch (Exception e) 
					{ 
						output = "Error while inserting the Bills."; 
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
		
		
		public String readBills(){ 
			
			String output = ""; 
			
			try{ 
					Connection con = connect(); 
					if (con == null){
						
						return "Error while connecting to the database for reading."; 
						
						} 
					// Prepare the html table to be displayed
					output = "<table border='1'><tr><th>BillingID</th><th>Meter Number</th>" +
							"<th>Consumer Name</th>" + 
							"<th>Address</th>" +
							"<th>Period Of Duration</th>" +
							"<th>No Of Units</th>"+
							"<th>Total Amount</th>"+
							"<th>Update</th><th>Remove</th></tr>"; 

					String query = "select * from billing_management"; 
					Statement stmt = con.createStatement(); 
					ResultSet rs = stmt.executeQuery(query); 
					// iterate through the rows in the result set
					while (rs.next()) 
					{ 
							String billingID = Integer.toString(rs.getInt("billingID")); 
							String MeterNumber = Integer.toString(rs.getInt("meterNumber"));
							String ConsumerName = rs.getString("consumerName"); 
							String Address = rs.getString("address");
							String DurationPeriod = Integer.toString(rs.getInt("durationPeriod"));
							String NoOfUnits = Integer.toString(rs.getInt("noOfUnits"));
							String TotalAmount = Double.toString(rs.getDouble("totalAmount")); 
							
							// Add into the html table
							output += "<tr><td>" + billingID + "</td>"; 
							output += "<td>" + MeterNumber + "</td>"; 
							output += "<td>" + ConsumerName + "</td>"; 
							output += "<td>" + Address + "</td>";
							output += "<td>" + DurationPeriod + "</td>"; 
							output += "<td>" + NoOfUnits + "</td>"; 
							output += "<td>" + TotalAmount + "</td>"; 
							// buttons
							output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
									+ "<td><form method='post' action='billing.jsp'>"
									+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
									+ "<input name='billingID' type='hidden' value='" + billingID 
									+ "'>" + "</form></td></tr>"; 
					} 
						con.close(); 
						// Complete the html table
						output += "</table>"; 
				} 
				catch (Exception e){ 
							output = "Error while reading the bills."; 
							System.err.println(e.getMessage()); 
				} 
				return output; 
				} 
		
		
		public String updateBills(String bill_ID, String MeterNumber, String ConsumerName, String Address, String DurationPeriod, String NoOfUnits, String TotalAmount){ 
			
			String output = ""; 
			
			try{ 
					Connection con = connect(); 
					if (con == null){
						return "Error while connecting to the database for updating.";
						} 
					// create a prepared statement
					String query = "UPDATE billing_management SET meterNumber=?,consumerName=?,address=?,durationPeriod=?,noOfUnits=?,totalAmount=? WHERE billingID=?"; 
					PreparedStatement preparedStmt = con.prepareStatement(query); 
					// binding values
					preparedStmt.setInt(1,Integer.parseInt(MeterNumber)); 
					preparedStmt.setString(2, ConsumerName); 
					preparedStmt.setString(3, Address); 
					preparedStmt.setDouble(4, Integer.parseInt(DurationPeriod)); 
					preparedStmt.setInt(5, Integer.parseInt(NoOfUnits)); 
					preparedStmt.setDouble(6, Double.parseDouble(TotalAmount));
					preparedStmt.setInt(7, Integer.parseInt(bill_ID));
					// execute the statement
					preparedStmt.execute(); 
					con.close(); 
					output = "Updated successfully"; 
			} 
			
			catch (Exception e){ 
				
				output = "Error while updating the bills."; 
				System.err.println(e.getMessage()); 
				
			} 
			
			return output; 
	} 
		
		
		public String deleteBills(String bill_ID){ 
			
			String output = ""; 
			
			try{ 
				Connection con = connect(); 
				
				if (con == null){
					return "Error while connecting to the database for deleting."; 
					} 
				// create a prepared statement
				String query = "delete from billing_management where billingID=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(bill_ID)); 
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				output = "Deleted successfully"; 
			} 
			
			catch (Exception e){ 
				output = "Error while deleting the Bill."; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
	} 		
		
		
		
		
		
		
		
		
		
}