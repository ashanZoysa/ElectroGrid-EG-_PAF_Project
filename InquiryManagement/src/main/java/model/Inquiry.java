package model; 
import java.sql.*; 

public class Inquiry
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
		
		
		public String insertInquiry(String nic, String name, String address, String postalcode, String issue){ 
			
					String output = ""; 
					
					try
					{ 
						Connection con = connect(); 
						
						if (con == null) 
						{
							return "Error while connecting to the database for inserting."; 
							
						} 
						// create a prepared statement
						
						String query = " insert into inquiry_management (`inquiryNo`,`nic`,`name`,`address`,`postalCode`,`issue`)"+" values (?, ?, ?, ?, ?, ?)"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, 0); 
						preparedStmt.setString(2, nic); 
						preparedStmt.setString(3, name); 
						preparedStmt.setString(4, address); 
						preparedStmt.setString(5, postalcode); 
						preparedStmt.setString(5, issue); 
						// execute the statement
 
						preparedStmt.execute(); 
						con.close(); 
						output = "Inserted successfully"; 
					} 
					
					catch (Exception e) 
					{ 
						output = "Error while inserting the item."; 
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
		
		
		
			public String readInquiry(){ 
				
					String output = ""; 
					
					try{ 
							Connection con = connect(); 
							if (con == null){
								
								return "Error while connecting to the database for reading."; 
								
								} 
							// Prepare the html table to be displayed
							output = "<table border='1'><tr><th>Inquiry No</th><th>NIC</th>" +
									"<th>Name</th>" + 
									"<th>Address</th>" +
									"<th>Postal Code</th><th>Issue</th>"+
									"<th>Update</th><th>Remove</th></tr>"; 
 
							String query = "select * from inquiry_management"; 
							Statement stmt = con.createStatement(); 
							ResultSet rs = stmt.executeQuery(query); 
							// iterate through the rows in the result set
							while (rs.next()) 
							{ 
									String inquiryNo = Integer.toString(rs.getInt("inquiryNo")); 
									String nic = rs.getString("nic"); 
									String name = rs.getString("name"); 
									String address = rs.getString("address");
									String postalCode = rs.getString("postalCode"); 
									String issue = rs.getString("issue");
									// Add into the html table
									output += "<tr><td>" + inquiryNo + "</td>"; 
									output += "<td>" + nic + "</td>"; 
									output += "<td>" + name + "</td>"; 
									output += "<td>" + address + "</td>";
									output += "<td>" + postalCode + "</td>";
									output += "<td>" + issue + "</td>";
									// buttons
									output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
											+ "<td><form method='post' action='Inquiry.jsp'>"
											+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
											+ "<input name='itemID' type='hidden' value='" + inquiryNo 
											+ "'>" + "</form></td></tr>"; 
							} 
								con.close(); 
								// Complete the html table
								output += "</table>"; 
						} 
						catch (Exception e){ 
									output = "Error while reading the inquiry."; 
									System.err.println(e.getMessage()); 
						} 
						return output; 
						} 
			
			
			public String updateInquiry(String inquiryno, String nic, String name, String address, String postalcode, String issue){ 
				
					String output = ""; 
					
					try{ 
							Connection con = connect(); 
							if (con == null){
								return "Error while connecting to the database for updating.";
								} 
							// create a prepared statement
							String query = "UPDATE inquiry_management SET nic=?,name=?,address=?,postalCode=?,issue=?, WHERE inquiryNo=?"; 
							PreparedStatement preparedStmt = con.prepareStatement(query); 
							// binding values
							preparedStmt.setString(1, nic); 
							preparedStmt.setString(2, name); 
							preparedStmt.setString(3, address);
							preparedStmt.setString(4, postalcode);
							preparedStmt.setString(5, issue);
							preparedStmt.setInt(5, Integer.parseInt(inquiryno)); 
							// execute the statement
							preparedStmt.execute(); 
							con.close(); 
							output = "Updated successfully"; 
					} 
					
					catch (Exception e){ 
						
						output = "Error while updating the inquiry."; 
						System.err.println(e.getMessage()); 
						
					} 
					
					return output; 
			} 
			
			
			public String deleteInquiry(String inquiryNo){ 
				
					String output = ""; 
					
					try{ 
						Connection con = connect(); 
						
						if (con == null){
							return "Error while connecting to the database for deleting."; 
							} 
						// create a prepared statement
						String query = "delete from inquiry where inquiryNo=?"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, Integer.parseInt(inquiryNo)); 
						// execute the statement
						preparedStmt.execute(); 
						con.close(); 
						output = "Deleted successfully"; 
					} 
					
					catch (Exception e){ 
						output = "Error while deleting the inquiry."; 
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
			
			
			
			
} 
