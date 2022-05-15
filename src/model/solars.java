package model;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class solars {
	
	public String insertSolars(String aNum, String fName, String nic, String address, String cNum, String email, String bNum, String bBranch ) 
	{ 
		String output = ""; 
		try
		{ 
			connection con = new connection();
			Connection con1 = con.connect(); 
			if (con1 == null) 
			{return "Error while connecting to the database for inserting."; } 
			// create a prepared statement
			String query = " insert into solars(`solarID`,`accountNum`,`fullName`,`userNic`,`address`,`contactNum`,`email`,`bankAccNum`,`bankBranch`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con1.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, aNum); 
			preparedStmt.setString(3, fName);
			preparedStmt.setString(4, nic);
			preparedStmt.setString(5, address); 
			preparedStmt.setString(6, cNum); 
			preparedStmt.setString(7, email);
			preparedStmt.setString(8, bNum);
			preparedStmt.setString(9, bBranch);
			// execute the statement
			preparedStmt.execute(); 
			con1.close(); 
			String newItems = readSolars();
			
			System.out.println(newItems);

			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";

		} 
		catch (Exception e) 
		{ 
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the solars.\"}";
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	public String readSolars() 
	{ 
		String output = ""; 
		try
		{ 
			connection con = new connection();
			Connection con1 = con.connect(); 
			if (con1 == null) 
			{return "Error while connecting to the database for reading."; } 
			// Prepare the html table to be displayed
			output = "<table class='table table-dark table-hover' border='1'><tr><th>Solar ID</th><th>Account Number</th><th>Full Name</th>" +
					"<th>User NIC</th>" + 
					"<th>Address</th>" +
					"<th>Contact Number</th><th>Email</th><th>Bank Acc_Number</th>"
					+ "<th>Bank Branch</th>" +
					"<th>Update</th><th>Remove</th></tr>"; 

			String query = "select * from solars"; 
			Statement stmt = con1.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String solarID = Integer.toString(rs.getInt("solarID")); 
				String accountNum = rs.getString("accountNum"); 
				String fullName = rs.getString("fullName"); 
				String userNic = rs.getString("userNic");
				String address = rs.getString("address");
				String contactNum = Integer.toString(rs.getInt("contactNum")); 
				String email = rs.getString("email");
				String bankAccNum = rs.getString("bankAccNum");
				String bankBranch = rs.getString("bankBranch");
				// Add into the html table
				output += "<tr><td><input type='hidden' name='hidSolarIDUpdate' id='hidSolarIDUpdate' value='"+solarID+"'>" + solarID + "</td>"; 
				output += "<td>" + accountNum + "</td>"; 
				output += "<td>" + fullName + "</td>"; 
				output += "<td>" + userNic + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + contactNum + "</td>"; 
				output += "<td>" + email + "</td>"; 
				output += "<td>" + bankAccNum + "</td>";
				output += "<td>" + bankBranch + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-outline-warning' data-solarid='"+solarID+"'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-outline-danger' data-solarid='"+solarID+"'></td></tr>"; 
			} 
			con1.close(); 
			// Complete the html table
			output += "</table>"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the solars."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	public String updateSolar(String id, String aNum, String fName, String nic, String address, String cNum, String email, String bNum, String bBranch) 

	{ 
		System.out.println("came here as well");
		String output = ""; 
		try
		{ 
			connection con = new connection();
			Connection con1 = con.connect(); 
			if (con1 == null) 
			{return "Error while connecting to the database for updating."; } 
			// create a prepared statement
			String query = "UPDATE solars SET accountNum=?,fullName=?,userNic=?,address=?,contactNum=?,email=?,bankAccNum=?, bankBranch=? WHERE solarID=?"; 
			PreparedStatement preparedStmt = con1.prepareStatement(query); 
			// binding values
			preparedStmt.setString(1, aNum); 
			preparedStmt.setString(2, fName); 
			preparedStmt.setString(3, nic); 
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, cNum);
			preparedStmt.setString(6, email); 
			preparedStmt.setString(7, bNum);
			preparedStmt.setString(8, bBranch);
			preparedStmt.setInt(9, Integer.parseInt(id)); 
			// execute the statement
			preparedStmt.execute(); 
			con1.close(); 
			String newItems = readSolars();

			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} 
		catch (Exception e) 
		{ 
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the solar details.\"}";
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	public String deleteSolar(String solarID) 
	{ 
		String output = ""; 
		try
		{ 
			connection con = new connection();
			Connection con1 = con.connect(); 
			if (con1 == null) 
			{return "Error while connecting to the database for deleting."; } 
			// create a prepared statement
			String query = "delete from solars where solarID=?"; 
			PreparedStatement preparedStmt = con1.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1,Integer.parseInt(solarID));
			// execute the statement
			preparedStmt.execute(); 
			con1.close(); 
			String newItems = readSolars();

			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} 
		catch (Exception e) 
		{ 
			output =  "{\"status\":\"error\", \"data\": \"Error while deleting the solar details.\"}";
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
}
