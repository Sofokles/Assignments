package Assignment;

import java.sql.*;

public class FindData {
	
	// Building a null constructor for class
	public static void findDATA() {
			}
	// Connecting to database
	public static void datamethod() throws Exception{
		//

		// JDBC driver name and database URL
		 final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		 final String DB_URL = "jdbc:mysql://localhost/";
		 final String DISABLE_SSL = "?useSSL=false";
		 
		//String driver = "com.mysql.jdbc.Driver";
		//String url = "jdbc:mysql://localhost:3306/";
		final String USER  = "root";
		final String PASS = "Korsband321";
		Class.forName(JDBC_DRIVER);
		
	 Connection conn = null;
	 Statement stmt = null;
	 
	 try{
		 // Register JDBC driver
		 Class.forName(JDBC_DRIVER);
		 
		 // Open a connection
		 System.out.println("Connecting to database...");
		 conn = DriverManager.getConnection(DB_URL + DISABLE_SSL, USER, PASS);
		 System.out.println("Conencted");
	
		 // Execute a query to create database
		 //System.out.println("Creating database...");
		 //stmt = conn.createStatement();
		 //String sql = "CREATE DATABASE IF NOT EXISTS " + "Powersystem"; //create database PowerSystem
		 //stmt.executeUpdate(sql);
		 //System.out.println("Database created successfully...");
	
		 // Connect to the created database STUDENTS and create table REGISTRATION
		 conn = DriverManager.getConnection(DB_URL + "PowerSystem3", USER, PASS);
		 stmt = conn.createStatement();
	
		 //sql = "CREATE TABLE IF NOT EXISTS Breakers2" +
		 //			"(rdfId VARCHAR(255) not NULL, " + 
		 //			"type VARCHAR(255), " + 
		 //			"Status VARCHAR(255), " + 
		 //			" nominalValue DECIMAL(7,4), " + 
		 //			" PRIMARY KEY (rdfId ))";
		 //stmt.executeUpdate(sql);
			
		 // create table Breakers with corresponding attributes
		 		
		 //stmt. ; // execute query	
		
		 // System.out.println("Created table in given database successfully...");
	
		 // insert values into the table
		 //sql = "INSERT IGNORE INTO Breakers2 (rdfId,type,Status) " +	 "VALUES (100, 'A', 'open')";	// stmt. …; // repeat the procedure for all rows of the table
		 //stmt.executeUpdate(sql);
		 
		// insert values into the table
		 //sql = "INSERT IGNORE INTO Breakers2 (rdfId,type,Status) " +	 "VALUES (101, 'B', 'close')";	// stmt. …; // repeat the procedure for all rows of the table
		 //stmt.executeUpdate(sql);
		 		  
		 //System.out.println("Inserted records into the table...");
	
		 // create the java mysql update preparedstatement
		 //String query = "UPDATE Breakers2 SET nominalValue = 3 WHERE rdfId = 100 " ; // Update age of Sumit Mittal 	 PreparedStatement preparedStmt = conn.prepareStatement(query);	 … // execute p
		 // PreparedStatement preparedStmt = conn.prepareStatement(query);
		 //String apa = "hej";
		 //preparedStmt.setDouble(1, 7);
		 //preparedStmt.setDouble(2, 3.44);
		 //preparedStmt.executeUpdate();
	
		 // insert a new values to the table with preparedstatement
		
		 // finish the statement	.
		 //System.out.println("The table is updated...");
		 
		 //Find data
		 String sql = "SELECT rdfId FROM BaseVoltage";
	     ResultSet rs = stmt.executeQuery(sql);
	     
	   //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         String id  = rs.getString("rdfId");	       
	         //String type  = rs.getString("name");

	         //Display values
	         System.out.println("rdfId: " + id);	 
	         //System.out.println("type: " + type);
	      }
	    
	      	     
	     
	     System.out.println("SELECT rdfId FROM PowerSystem3 = " + rs);
		 
	
		 conn.close();
	 	}
		 catch(Exception e){System.out.println(e);}
			//return null;
		}
	 
	
}

