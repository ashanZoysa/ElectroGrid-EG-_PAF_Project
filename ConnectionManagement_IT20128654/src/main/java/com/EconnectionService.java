package com;

import model.Econnection; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 




@Path("/Econnection") 
public class EconnectionService{ 
	
			Econnection econnectionObj = new Econnection(); 
			
			
			@GET
			@Path("/") 
			@Produces(MediaType.TEXT_HTML) 
			public String readEconnection() 
			 { 
				return econnectionObj.readEconnection(); 
			}
			
			@POST
			@Path("/") 
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
			@Produces(MediaType.TEXT_PLAIN) 
			public String insertEconnection(@FormParam("userName") String userName, 
									 @FormParam("address") String address, 
									 @FormParam("mainTown") String mainTown, 
									 @FormParam("postalCode") String postalCode,
				                     @FormParam("postNumber") String postNumber){ 
				
				String output = econnectionObj.insertEconnection(userName, address, mainTown, postalCode, postNumber); 
				return output; 
				
			}
			
			
			@PUT
			@Path("/") 
			@Consumes(MediaType.APPLICATION_JSON) 
			@Produces(MediaType.TEXT_PLAIN) 
			public String updateEconnection(String econnectionData) 
			{ 
				//Convert the input string to a JSON object 
				JsonObject econnectionObject = new JsonParser().parse(econnectionData).getAsJsonObject(); 
				//Read the values from the JSON object
				String connectionNo = econnectionObject.get("connectionNo").getAsString(); 
				String userName = econnectionObject.get("userName").getAsString(); 
				String address = econnectionObject.get("address").getAsString(); 
				String mainTown = econnectionObject.get("mainTown").getAsString(); 
				String postalCode = econnectionObject.get("postalCode").getAsString(); 
				String postNumber = econnectionObject.get("postNumber").getAsString(); 
				String output = econnectionObj.updateEconnection(connectionNo, userName, address, mainTown, postalCode, postNumber ); 
				return output; 
				
			}
			
			
			@DELETE
			@Path("/") 
			@Consumes(MediaType.APPLICATION_XML) 
			@Produces(MediaType.TEXT_PLAIN) 
			public String deleteEconnection(String econnectionData) 
			{ 
				//Convert the input string to an XML document
				Document doc = Jsoup.parse(econnectionData, "", Parser.xmlParser()); 
			 
				//Read the value from the element <connectionNo>
				String connectionNo = doc.select("connectionNo").text(); 
				String output = econnectionObj.deleteEconnection(connectionNo); 
				return output; 
			}

			
					
}
