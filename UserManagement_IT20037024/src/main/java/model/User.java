package model;

import java.sql.*;

public class User {
	
	//Database connection
	public Connection connect() {
		
		Connection con = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			//Provide the correct details: DBServer/DBName, user name, password 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electro_grid(eg)_db", "root", "");
			//For Testing
			System.out.println("Successfully Connected! ");
						
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
				
		return con;
		
	}
	
	
	//Add User Function
	public String addNewUsers(String fName, String lName, String uAddress, String phone, String uEmail, String userType) {
		
		String output="";
		
		try {
			
			Connection con = connect();
			
			if(con==null) {
				return "Error while connecting to the Database!";
			}
			
			// create a prepared statement
			String query = "INSERT INTO user_management (`FirstName`,`LastName`,`userAddress`,`contactNumber`,`Email`,`userType`)"+" VALUES ( ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//binding values
			preparedStmt.setString(1, fName);
			preparedStmt.setString(2, lName);
			preparedStmt.setString(3, uAddress);
			preparedStmt.setString(4, phone);
			preparedStmt.setString(5, uEmail);
			preparedStmt.setString(6, userType);
			
			//execute the statement
			preparedStmt.execute();
			con.close();
			output="User Added Successfully!";	
			
		}catch(Exception e) {
			
			output="Error while adding user";
			System.err.println(e.getMessage());		
			
		}
		
		return output;
	}
	
	
	//View All Users Function
	public String viewAllUsers() {
		String output="";
		
		try {
			
			Connection con = connect();
			
			if(con==null) {
				return "Error while connecting to database for reading all users!";
			}
			
			
			//Prepare the table to be displayed.
			output="<html>"
					
				  +"<head>"
				  +"<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\">"	
				  +"</head>"
				  
				  +"<body>"
				  
				  +"<table border='0.1' class='table'>"
				  
				  +"<tr>"
				  +"<th>User ID</th>"
				  +"<th>First Name</th>"
				  +"<th>Last Name</th>"
				  +"<th>Address</th>"
				  +"<th>Contact Number</th>"
				  +"<th>E-mail</th>"
				  +"<th>User Type</th>"
				  +"<th>Actions</th>"
				  +"</tr>";
				  
				  String query = "SELECT * FROM user_management";
				  Statement stmt = con.createStatement();
				  ResultSet rs = stmt.executeQuery(query);
				  
				  //Iterate through the rows in the result set
				  while(rs.next()) {
					  
					  String userID = Integer.toString(rs.getInt("userID"));
					  String firstName = rs.getString("FirstName");
					  String lastName = rs.getString("LastName");
					  String address = rs.getString("userAddress");
					  String contactNumber = rs.getString("contactNumber");
					  String email = rs.getString("Email");
					  String userType = rs.getString("userType");
					  
					  output+="<tr>"
							  
							 +"<td>"+userID+"</td>"
							 +"<td>"+firstName+"</td>"
							 +"<td>"+lastName+"</td>"  
							 +"<td>"+address+"</td>"
							 +"<td>"+contactNumber+"</td>"
							 +"<td>"+email+"</td>"
							 +"<td>"+userType+"</td>"
							 
							 +"<td>"
							 +"<input class='btn btn-warning' name='btnUpdate' type='button' value='Update'> &nbsp;"
							 + "<input class='btn btn-danger' name='btnRemove' type='submit' value='Delete'>"
							 +"</td>"
							 
							 +"</tr>";
					  
					  
					  	  
				  }
				  
				  con.close();		
				  
			//complete the HTML table.	  
			output+="</table>"
				   +"</body>"	
				   +"</html>";
			
		}catch(Exception e) {
			
			output = "Error while reading the Users!"; 
			System.err.println(e.getMessage()); 
			
		}
		
		
		
		return output;
	}
	
	
	//Update User Function
	public String updateUser(String uID, String fName, String lName, String uAddress, String phone, String email, String userType) {
		
		String output = "" ;
		
		try {
			
			Connection con = connect();
			
			if(con==null) {
				return "Error while connecting to the database for updating the user";
			}
			
			//Create a prepared statement.
			String query = "UPDATE user_management SET FirstName=?, LastName=?, userAddress=?, contactNumber=?, Email=?, userType=? WHERE userID=? ";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			//binding values
			preparedStmt.setString(1,fName);
			preparedStmt.setString(2,lName);
			preparedStmt.setString(3,uAddress);
			preparedStmt.setString(4,phone);
			preparedStmt.setString(5,email);
			preparedStmt.setString(6,userType);
			preparedStmt.setInt(7, Integer.parseInt(uID));
			
			//execute the statement
			preparedStmt.execute();
			con.close();
			
			output="User Updated Successfully!";
			
		}catch(Exception e) {
			
			output="Error while updating the user!";
			System.err.println(e.getMessage());
			
		}		
		
		return output;
	}
	
	
	//Delete User Function
	public String deleteUser(String UserID) {
		
		String output = "";
		
		try {
			
			Connection con = connect();
			
			if(con==null) {
				return "Error while connecting to the database for deleting user !";
			}
			//Create a prepared Statement
			String query = "DELETE FROM user_management WHERE userID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			//binding values
			preparedStmt.setInt(1,Integer.parseInt(UserID));
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "User Deleted Successfully !"; 
			
		}catch(Exception e) {
			
			output = "Error while deleting the User."; 
			System.err.println(e.getMessage()); 
			
		}
			
		return output;
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
