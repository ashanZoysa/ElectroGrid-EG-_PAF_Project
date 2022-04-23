package com; 

import model.Inquiry; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 




@Path("/Inquiry") 
public class InquiryService{ 
	
			Inquiry inquiryObj = new Inquiry(); 
			
			
			@GET
			@Path("/") 
			@Produces(MediaType.TEXT_HTML) 
			public String readInquiry() 
			 { 
				return inquiryObj.readInquiry(); 
			}
			
			@POST
			@Path("/") 
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
			@Produces(MediaType.TEXT_PLAIN) 
			public String insertInquiry(@FormParam("nic") String nic, 
									 @FormParam("name") String name, 
									 @FormParam("address") String address, 
									 @FormParam("postalCode") String postalCode,
									 @FormParam("issue") String issue){ 
				
				String output = inquiryObj.insertInquiry(nic, name, address, postalCode, issue); 
				return output; 
				
			}
			
			
			@PUT
			@Path("/") 
			@Consumes(MediaType.APPLICATION_JSON) 
			@Produces(MediaType.TEXT_PLAIN) 
			public String updateInquiry(String inquiryData) 
			{ 
				//Convert the input string to a JSON object 
				JsonObject inquiryObject = new JsonParser().parse(inquiryData).getAsJsonObject(); 
				//Read the values from the JSON object
				String inquiryNo = inquiryObject.get("inquiryNo").getAsString(); 
				String nic = inquiryObject.get("nic").getAsString(); 
				String name = inquiryObject.get("name").getAsString(); 
				String address = inquiryObject.get("address").getAsString(); 
				String postalCode = inquiryObject.get("postalCode").getAsString(); 
				String issue = inquiryObject.get("issue").getAsString();
				String output = inquiryObj.updateInquiry(inquiryNo, nic, name, address, postalCode,issue); 
				return output; 
				
			}
			
			
			@DELETE
			@Path("/") 
			@Consumes(MediaType.APPLICATION_XML) 
			@Produces(MediaType.TEXT_PLAIN) 
			public String deleteInquiry(String inquiryData) 
			{ 
				//Convert the input string to an XML document
				Document doc = Jsoup.parse(inquiryData, "", Parser.xmlParser()); 
			 
				//Read the value from the element <itemID>
				String inquiryNo = doc.select("inquiryNo").text(); 
				String output = inquiryObj.deleteInquiry(inquiryNo); 
				return output; 
			}

			
					
}