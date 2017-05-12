package Assignment;

import java.sql.*;

public class FindData {
	
	// Building a null constructor for class
	public static void findDATA() {
			}
	// Connecting to database
	public static double datamethod(String sql) throws Exception{
		//

		// JDBC driver name and database URL
		 final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		 final String DB_URL = "jdbc:mysql://localhost/";
		 final String DISABLE_SSL = "?useSSL=false";		 
		
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
		 	
		 // Connect to the created database STUDENTS and create table REGISTRATION
		 conn = DriverManager.getConnection(DB_URL + "PowerSystem3" + DISABLE_SSL, USER, PASS);
		 stmt = conn.createStatement();	
		
		 //Find data
		 //String sql = "SELECT x FROM ACLine";
	     ResultSet rs = stmt.executeQuery(sql);
	     
	   //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         double x  = rs.getDouble("x");	       
	         //String type  = rs.getString("name");

	         //Display values
	         System.out.println("rdfId: " + x);	 
	         //System.out.println("type: " + type);
	      }	      	     
	     
	     System.out.println("SELECT rdfId FROM PowerSystem3 = " + rs);
		 
	
		 conn.close();
	 	}
		 catch(Exception e){System.out.println(e);}
			//return null;
	 
	 	return x;
		}	
	 
	
}

