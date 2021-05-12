package com.sliit.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Buyer {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/gadgetbadgetdb?useTimezone=true&serverTimezone=UTC", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	public String insertBuyer(String name, String gender, String contactNo, String email, String address) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = " insert into buyer_table_gui(`buyerID`,`buyerName`,`buyerGender`,`buyerContactNo`,`buyerEmail`,`buyerAddress`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, gender);
			preparedStmt.setString(4, contactNo);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, address);

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newBuyers = readBuyers();
//			output = "{\"status\":\"success\", \"data\": \"" + newBuyers + "\"}";
			output = "{\"status\": \"success\", \"data\": \" " +newBuyers + "\"}"; 
//			output = "{\"name\":\"John\", \"age\":31, \"city\":\"New York\"}";

		} catch (Exception e) {
			output = "{\"status\": \"error\", \"data\": \"Error while inserting the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;

	}

	public String readBuyers() {

		String output = "";
		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			output = "<table class='table table-dark table-hover' ><tr><th>Buyer Name</th><th>Gender</th><th>Contact</th><th>Email</th> <th>Address</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from buyer_table_gui";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String buyerID = Integer.toString(rs.getInt("buyerID"));
				String buyerName = rs.getString("buyerName");
				String buyerGender = rs.getString("buyerGender");
				String buyerContact = rs.getString("buyerContactNo");
				String buyerEmail = rs.getString("buyerEmail");
				String buyerAddress = rs.getString("buyerAddress");

				output += "<tr><td><input id='hidBuyerIDUpdate' name='hidBuyerIDUpdate'  type='hidden' value='"
						+ buyerID + "'>" + buyerName + "</td>";
				output += "<td>" + buyerGender + "</td>";
				output += "<td>" + buyerContact + "</td>";
				output += "<td>" + buyerEmail + "</td>";
				output += "<td>" + buyerAddress + "</td>";

				output += "<td><input name='btnUpdate'  type='button' value='Update'  class=' btnUpdate btn btn-secondary' data-buyerid='"
						+ buyerID + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-buyerid='"
						+ buyerID + "'></td></tr>";

			}
			con.close();

			output += "</table>";

		} catch (Exception e) {
			output = "Error while reading buyers";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String updateBuyer(String ID, String name, String gender, String contactNo, String email, String address) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE buyer_table_gui SET buyerName=?,buyerGender=?,buyerContactNo=?,buyerEmail=?,buyerAddress=?WHERE buyerID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, gender);
			preparedStmt.setString(3, contactNo);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, address);
			preparedStmt.setInt(6, Integer.parseInt(ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newBuyers = readBuyers();
			output = "{\"status\": \"success\", \"data\": \" " +newBuyers + "\"}"; 
			/* output = "Updated successfully"; */
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": " + "\"Error while updating the buyer.\"}";
			/* output = "Error while updating the buyer."; */
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteBuyer(String buyerID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from buyer_table_gui where buyerID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(buyerID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newBuyers = readBuyers();
			output = "{\"status\":\"success\", \"data\": \"" + newBuyers + "\"}";
			/* output = "Deleted successfully"; */
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": " + "\"Error while deleting the item.\"}";
			/* output = "Error while deleting the buyer."; */
			System.err.println(e.getMessage());
		}
		return output;
	}

}
