package com;

import model.solars;

//for REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//for JSON
import com.google.gson.*;

//for XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Solars")
public class solarService {
	
	solars solarObj = new solars();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readSolars() {
		
		return solarObj.readSolars();
		
	}
	
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String insertSolars(@FormParam("accountNum") String accountNum,
							@FormParam("fullName") String fullName,
							@FormParam("userNic") String userNic,
							@FormParam("address") String address,
							@FormParam("contactNum") String contactNum,
							@FormParam("email") String email,
							@FormParam("bankAccNum") String bankAccNUm,
							@FormParam("bankBranch") String bankBranch)

	{
		
		String output = solarObj.insertSolars(accountNum, fullName, userNic, address, contactNum, email, bankAccNUm, bankBranch);
		return output;
		
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateSolars(String solarData)
	{
		//Convert input string to a JSON object
		JsonObject solarObject = new JsonParser().parse(solarData).getAsJsonObject();
		
		//Read values from JSON object
		String solarID = solarObject.get("solarID").getAsString();
		String accountNum = solarObject.get("accountNum").getAsString();
		String fullName = solarObject.get("fullName").getAsString();
		String userNic = solarObject.get("userNic").getAsString();
		String address = solarObject.get("address").getAsString();
		String contactNum = solarObject.get("address").getAsString();
		String email = solarObject.get("email").getAsString();
		String bankAccNUm = solarObject.get("bankAccNUm").getAsString();
		String bankBranch = solarObject.get("bankBranch").getAsString();
		
		String output = solarObj.updateSolar(solarID, accountNum, fullName, userNic, address, contactNum, email, bankAccNUm, bankBranch);
		return output;
		
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteSolars(String solarData)
	{
		//Convert input string to a JSON object
		Document doc = Jsoup.parse(solarData, "", Parser.xmlParser());
		
		//Read values from JSON object
		String solarID = doc.select("solarID").text();
		
		String output = solarObj.deleteSolar(solarID);
		return output;
		
	}

}
