package com;


	
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Byer;

	@Path("/Byer") 
	public class Byer_service {
		
		Byer itemObj = new Byer();
		
		
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readItems()
		 {
		 return itemObj.readBuyer();
		 }
		
		
		//insert
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		
		public String insertItem(@FormParam("FullName") String FullName,
				 @FormParam("PhoneNumber")String PhoneNumber,
				 @FormParam("Email") String Email,
				 @FormParam("Address") String Address,
				 @FormParam("Birthdate") String Birthdate)
				{
				

		String output = itemObj.insertbuyer(FullName, PhoneNumber, Email, Address,Birthdate);
		return output;
		
				}
		
		
		
		
		//update
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updatebuyerservice(String itemData)
		{
		
		// Convert the input string to a JSON object
		 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		 //Read the values from the JSON object
		 String ID = itemObject.get("ID").getAsString();
		 String FullName = itemObject.get("FullName").getAsString();
		 String PhoneNumber = itemObject.get("PhoneNumber").getAsString();
		 String Email = itemObject.get("Email").getAsString();
		 String Address = itemObject.get("Address").getAsString();
		 String Birthdate = itemObject.get("Birthdate").getAsString();
		 String output = itemObj.updatebuyer(ID,FullName, PhoneNumber, Email, Address , Birthdate);
		return output;
		}

		
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deletebuyerservice(String itemData)
		{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

		//Read the value from the element
		 String ID = doc.select("ID").text();
		 String output = itemObj.deletebuyer(ID);
		return output;
		}
		
		//TE-st comment 02

	}



