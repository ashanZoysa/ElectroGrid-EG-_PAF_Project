package model;

 
import java.sql.*; 

public class Econnection
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
		
		
		public String insertEconnection(String username, String address, String maintown, String postalcode, String postnumber){ 
			
					String output = ""; 
					
					try
					{ 
						Connection con = connect(); 
						
						if (con == null) 
						{
							return "Error while connecting to the database for inserting."; 
							
						} 
						// create a prepared statement
						
						String query = " insert into connection_management (`connectionNo`,`userName`,`address`,`mainTown`,`postalCode`,`postNumber`)"+" values (?, ?, ?, ?, ?, ?)"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, 0); 
						preparedStmt.setString(2, username); 
						preparedStmt.setString(3, address); 
						preparedStmt.setString(4, maintown);
						preparedStmt.setString(5, postalcode); 
						preparedStmt.setString(6, postnumber); 
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
		
		
		
			public String readEconnection(){ 
				
					String output = ""; 
					
					try{ 
							Connection con = connect(); 
							if (con == null){
								
								return "Error while connecting to the database for reading."; 
								
								} 
							// Prepare the html table to be displayed
							output = "<table border='1'><tr><th>Connection No</th><th>User Name</th>" +
									"<th>Address</th>" + 
									"<th>Main Town</th>" +
									"<th>Postal Code</th>" +
									"<th>Post Number</th>" +
									"<th>Update</th><th>Remove</th></tr>"; 
 
							String query = "select * from connection_management"; 
							Statement stmt = con.createStatement(); 
							ResultSet rs = stmt.executeQuery(query); 
							// iterate through the rows in the result set
							while (rs.next()) 
							{ 
									String connectionNo = Integer.toString(rs.getInt("connectionNo")); 
									String userName = rs.getString("userName"); 
									String address = rs.getString("address"); 
									String mainTown = rs.getString("mainTown");
									String postalCode = rs.getString("postalCode");
									String postNumber = rs.getString("postNumber");
									// Add into the html table
									output += "<tr><td>" + 	connectionNo + "</td>";
									output += "<td>" + userName + "</td>"; 
									output += "<td>" + address + "</td>"; 
									output += "<td>" + mainTown + "</td>"; 
									output += "<td>" + postalCode + "</td>"; 
									output += "<td>" + postNumber + "</td>"; 
									// buttons
									output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
											+ "<td><form method='post' action='Econnection.jsp'>"
											+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
											+ "<input name='itemID' type='hidden' value='" + connectionNo 
											+ "'>" + "</form></td></tr>"; 
							} 
								con.close(); 
								// Complete the html table
								output += "</table>"; 
						} 
						catch (Exception e){ 
									output = "Error while reading the econnection."; 
									System.err.println(e.getMessage()); 
						} 
						return output; 
						} 
			
			
			public String updateEconnection(String connectionno, String username, String address, String maintown, String postalcode, String postnumber){ 
				
					String output = ""; 
					
					try{ 
							Connection con = connect(); 
							if (con == null){
								return "Error while connecting to the database for updating.";
								} 
							// create a prepared statement
							String query = "UPDATE connection_management SET connectionNo=?,userName=?,address=?,mainTown=?,postalCode=?,postNumber=?, WHERE connectionNo=?"; 
							PreparedStatement preparedStmt = con.prepareStatement(query); 
							// binding values
							
							
							
							preparedStmt.setString(1, username); 
							preparedStmt.setString(2, address); 
							preparedStmt.setString(3, maintown);
							preparedStmt.setString(4, postalcode); 
							preparedStmt.setString(5, postnumber); 
							preparedStmt.setInt(6, Integer.parseInt(connectionno) ); 
							// execute the statement
							preparedStmt.execute(); 
							con.close(); 
							output = "Updated successfully"; 
					} 
					
					catch (Exception e){ 
						
						output = "Error while updating the connection."; 
						System.err.println(e.getMessage()); 
						
					} 
					
					return output; 
			} 
			
			
			public String deleteEconnection(String connectionNo	){ 
				
					String output = ""; 
					
					try{ 
						Connection con = connect(); 
						
						if (con == null){
							return "Error while connecting to the database for deleting."; 
							} 
						// create a prepared statement
						String query = "delete from connection_management where connectionNo=?"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, Integer.parseInt(connectionNo)); 
						// execute the statement
						preparedStmt.execute(); 
						con.close(); 
						output = "Deleted successfully"; 
					} 
					
					catch (Exception e){ 
						output = "Error while deleting the connection."; 
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
			
			
			
			
} 
