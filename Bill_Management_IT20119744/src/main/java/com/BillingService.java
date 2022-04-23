

package com; 

import model.Billing; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 




@Path("/Bills") 
public class BillingService{ 
	
			Billing billObj = new Billing(); 
			
			
			@GET
			@Path("/") 
			@Produces(MediaType.TEXT_HTML) 
			public String readItems() 
			 { 
				return billObj.readBills(); 
			}
			
			@POST
			@Path("/") 
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
			@Produces(MediaType.TEXT_PLAIN) 
			public String createBills(@FormParam("MeterNo") String MeterNo, 
									 @FormParam("ConsumerName") String ConsumerName, 
									 @FormParam("Address") String Address, 
									 @FormParam("DurationPeriod") String DurationPeriod,
									 @FormParam("NoOfUnits") String NoOfUnits, 
									 @FormParam("TotalAmount") String TotalAmount){ 
				
				String output = billObj.createBills(MeterNo, ConsumerName, Address, DurationPeriod, NoOfUnits, TotalAmount); 
				return output; 
				
			}
			
			
			@PUT
			@Path("/") 
			@Consumes(MediaType.APPLICATION_JSON) 
			@Produces(MediaType.TEXT_PLAIN) 
			public String updateBills(String billData) 
			{ 
				//Convert the input string to a JSON object 
				JsonObject billingObj = new JsonParser().parse(billData).getAsJsonObject(); 
				//Read the values from the JSON object
				String bill_ID = billingObj.get("bill_ID").getAsString(); 
				String MeterNumber = billingObj.get("MeterNumber").getAsString(); 
				String ConsumerName = billingObj.get("ConsumerName").getAsString(); 
				String Address = billingObj.get("Address").getAsString(); 
				String DurationPeriod = billingObj.get("DurationPeriod").getAsString(); 
				String NoOfUnits = billingObj.get("NoOfUnits").getAsString();
				String TotalAmount = billingObj.get("TotalAmount").getAsString();
				String output = billObj.updateBills(bill_ID, MeterNumber, ConsumerName,  Address, DurationPeriod, NoOfUnits, TotalAmount); 
				return output; 
				
			}
			
			
			@DELETE
			@Path("/") 
			@Consumes(MediaType.APPLICATION_XML) 
			@Produces(MediaType.TEXT_PLAIN) 
			public String deleteBills(String billData) 
			{ 
				//Convert the input string to an XML document
				Document doc = Jsoup.parse(billData, "", Parser.xmlParser()); 
			 
				//Read the value from the element <itemID>
				String billID = doc.select("billingID").text(); 
				String output = billObj.deleteBills(billID); 
				return output; 
			}
			
			
			
			
			
			
			
}			
			