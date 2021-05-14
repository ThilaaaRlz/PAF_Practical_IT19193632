package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Fund {
	
	
		
		//A common method to connect to the DB
	//A common method to connect to the DB
	private Connection connect(){
		Connection con = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");

			//Provide the correct details: DBServer/DBName, username, password
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pafclient", "root", "");
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return con;
	}
			
		
		
		//Insert Project Details
	public String insertfund(String Fund_id, String Funders_name, String Project_name, String Amount) {
			String output = "";
			try{
				Connection con = connect();
					if (con == null){
						return "Error while connecting to the database for inserting."; 
				}
				
					
					// create a prepared statement
					 String query = "insert into fundmanagement(`Fund_id`,`Funders_name`,`Project_name`,`Amount`)"+ " values (?, ?, ?, ?)";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					
					 // binding values
					 preparedStmt.setInt(1, 0);
					 preparedStmt.setString(2, Funders_name);
					 preparedStmt.setString(3, Project_name);
					 preparedStmt.setString(4, Amount);
					 
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 
					 String newCart = readfund(); 
					 output = "{\"status\":\"success\", \"data\": \"" + newCart + "\"}";
					 
					 }catch (Exception e)
					 {
						 
						 output = "{\"status\":\"error\", \"data\":\"Error while inserting the funding to project.\"}"; 
						 System.err.println(e.getMessage());
					 }
			 return output;
		 }
		
		
		
		
		public String readfund(){
			String output = "";
			try{
				Connection con = connect();
					if (con == null){
						return "Error while connecting to the database for reading."; 
			}
					
				// Prepare the html table to be displayed
				output = 
						"<table border='1' >"+ 
						"<tr >" +
							 "<th >Funder ID</th>" +
							 "<th >Funder_name</th>" +
							 "<th>Project_name</th>" +
							 "<th>Amount</th>" +
							 "<th>Update</th>" +
							 "<th>Remove</th>" +
						
						 "</tr>";
	
				String query = "select * from `order`";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query);
				 
				 
				 // iterate through the rows in the result set
				 while (rs.next()){
					 
					 
					 String Fund_id =  Integer.toString(rs.getInt("Fund_id"));
					 String Funders_name = rs.getString("Funders_name");
					 String Project_name = rs.getString("Project_name");
					 String Amount = rs.getString("Amount");
					 
	
					 
					 // Add into the html table
					 
					 //output += "<tr><td>" + orderId + "</td>";
					 output += "<td>" + Fund_id + "</td>";
					 output += "<td>" + Funders_name + "</td>";
					 output += "<td>" + Project_name + "</td>";
					 output += "<td>" + Amount + "</td>";
					 
		
					 
					 
					 // buttons
					
					 output += "<td><input name='btnUpdate' type='button' value='Update' "
								+ "class='btnUpdate btn btn-secondary' data-userid='" + Fund_id + "'></td>"
								+ "<td><input name='btnRemove' type='button' value='Remove' "
								+ "class='btnRemove btn btn-danger' data-userid='" + Fund_id + "'></td></tr>"; 
				 }
			 con.close();
			 
			 // Complete the html table
			 output += "</table>";
			 
			 
			 }catch (Exception e){
				 
				 output = "Error while reading the cart orders.";
				 System.err.println(e.getMessage());
			 }
			 return output;
			 
		}
		
		
		
		public String updatefund(String Fund_id, String Funders_name, String Project_name, String Amount){ 
			String output = ""; 
			try{
				Connection con = connect();
				if (con == null){
					return "Error while connecting to the database for updating."; 
				} 
				
				 // create a prepared statement
				String query = "UPDATE `fundmanagement` SET `Funders_name`=?,`Project_name`=?,`Amount`=?, WHERE `Fund_id`=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				  
				 preparedStmt.setString(1, Funders_name);
				 preparedStmt.setString(2, Project_name);
				 preparedStmt.setString(3, Amount);
				 preparedStmt.setString(9, Fund_id);
				 
				// preparedStmt.setString(4, sector);
				
				 
 
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 
				 String newCart = readfund(); 
				 output = "{\"status\":\"success\", \"data\": \"" + newCart + "\"}";
				 
		
				 } catch (Exception e) {
					 
					 output = "{\"status\":\"error\", \"data\": \"Error while updating the funding to project.\"}";
					 System.err.println(e.getMessage()); 
				 } 
				 return output; 
		 }
		
		

		public String deletefund(String Fund_id) { 
			String output = ""; 
			try{ 
				Connection con = connect();
				if (con == null) { 
					return "Error while connecting to the database for deleting."; 
				} 
					// create a prepared statement
				    String query ="DELETE FROM `fundmanagement` WHERE Fund_id=?";
					PreparedStatement preparedStmt = con.prepareStatement(query); 
					
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(Fund_id)); 
					
					// execute the statement
					preparedStmt.execute(); 
					con.close(); 
					
					String newCart = readfund(); 
					output = "{\"status\":\"success\", \"data\": \"" + newCart + "\"}"; 
					
			} catch (Exception e) { 
				output = "{\"status\":\"error\", \"data\": \"Error while deleting the funding to project.\"}"; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		}
		
}