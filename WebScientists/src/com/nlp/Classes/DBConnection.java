package com.nlp.Classes;
import java.sql.*;

public class DBConnection {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	static final String DB_URL = "jdbc:mysql://localhost:3306/kdm";

	   //  Database credentials
	static final String USER = "root";
	static final String PASS = "";
	   
   public Connection getConnection() {
	   Connection conn = null;
	   try{
	      //STEP 2: Register JDBC driver
		  Class.forName("com.mysql.jdbc.Driver").newInstance();

		  //STEP 3: Open a connection
		  //System.out.println("Connecting to database...");
		  conn = DriverManager.getConnection(DB_URL,USER,PASS);
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	   return conn;
   }
	   
	
}