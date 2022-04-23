package com;

import model.User; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Users")
public class UserService {
	
	User userObj = new User();
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String addNewUsers(@FormParam("First_Name") String FirstName, 
							 @FormParam("Last_Name") String LastName, 
							 @FormParam("User_Address") String userAddress, 
							 @FormParam("Contact_Number") String ContactNumber,
							 @FormParam("User_Email") String Email,
							 @FormParam("User_Type") String UserType){ 
		
		String output = userObj.addNewUsers(FirstName, LastName, userAddress, ContactNumber, Email, UserType); 
		return output; 
		
	}
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String viewAllUsers() 
	 { 
		return userObj.viewAllUsers(); 
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String userData){
		
		//Convert the input string to a JSON object
		JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
		//Read the values from the JSON object
		String userId = userObject.get("userID").getAsString();
		String firstName = userObject.get("FirstName").getAsString();
		String lastName = userObject.get("LastName").getAsString();
		String address = userObject.get("userAddress").getAsString();
		String phone = userObject.get("contactNumber").getAsString();
		String email = userObject.get("Email").getAsString();
		String userType = userObject.get("userType").getAsString();
		
		String output = userObj.updateUser(userId,firstName,lastName,address,phone,email,userType);
		
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String userData) {
		//Convert the input string to an XML document.
		Document doc = Jsoup.parse(userData,"",Parser.xmlParser());
		
		//Read the value from the element <userData>
		String userId = doc.select("userID").text();
		String output = userObj.deleteUser(userId);
		return output;
	}
	
	
	
	

}
