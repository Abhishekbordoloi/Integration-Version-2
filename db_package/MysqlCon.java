/**
 * @author bordoloa
 *
 */
/*Function details
 * 
 * WE CREATE ONLY ONE DB CONNECTION
 * 
 * IMPORT db_package 
 * 
 *  1. CALL MysqlCon.connect() method to get the connection object
 *  
 *     IF SUCCESSFUL: will give a connection OBJECT
 *     IF UNSECCESSFUL: will exit the program
 * 
 *  
 *  2. CALL MysqlCon.disconnect() method to disconnect from 
 *     database and (1. Exit the program 2. Create a New Connection)
 * 
 * */



package db_package;

import java.sql.*;

import logs.DbLog;  

public class MysqlCon {
	
	//#Singleton Design Pattern
	
	private static MysqlCon Instance;
	public Connection connection;
		private MysqlCon() {
			
			int count=0;
			while(count<2) {
			try{   
			    // Check if the database name (classified) exist.
				//
				
		        final String url = "jdbc:mysql://database-1.cda7tdexq29d.us-east-2.rds.amazonaws.com:3306/classified";

		        // Defines username and password to connect to database server.
		        final String username = "root";
		        final String password = "root12345";
		        
		        
		        DriverManager.setLoginTimeout(10);
				connection=DriverManager.getConnection(url, username, password);
				 
				String msg="Successfully connected to the AWS server with "
				+url;
				System.out.println(msg);
				
				DbLog.logger().logging(msg);
				return;
				
			}catch(Exception e){
				count++;	   
			    System.out.println("Error in connection. Reconecting...          Attempt[ "+count+" ]");
			    System.out.println();
			    DbLog.logger().logging(e.getMessage()+"Error is connecting to AWS server Attempt[ "+count+" ]");
			    if(count>=2) {
			    	System.out.println("Unable to establish connection with AWS. Please check log files for more details at:"
			    			+ " C:\\Users\\Public\\IntraClassifieldsLogs\\Database_login.txt");
			    	//ALTERNATIVE LOCAL DATABASE CONNECTION
			    	
			    	System.out.println("Attempting to create local database....");
			    	alternateconnection();
			    	}
				}  
			}
			
		}
		
		private void alternateconnection() {
			// TODO Auto-generated method stub
			 final String url = "jdbc:mysql://localhost:3306/";
			 final String username = "root";
		     final String password = "bordoloa";
		     System.out.println("Connecting with classified at local host");
			try {
				connection=DriverManager.getConnection(url, username, password);
				
				String msg="Hosting the client in local database port 3306 server  "
						+url;
				System.out.println(msg);
				DbLog.logger().logging(msg);
				return;
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			    DbLog.logger().logging(e.getMessage()+"Error is Mysql local database connection");
			    System.out.println("Unable to establish connection with localhost port 3306. Please check log files for more details at:"
			    			+ " C:\\Users\\Public\\IntraClassifieldsLogs\\Database_login.txt");
			    System.exit(0); /// exiting the program
			    }
		}

		public static MysqlCon connect() {
			if(Instance==null) {
				Instance=new MysqlCon();
			}
			
			return Instance;
		}
		
		private void commit() throws SQLException{
				Instance.connection.commit();
			}
		
		 public void disconnect() {
			try {
				Instance.connection.close();
				Instance=null;
				String msg="Changes are commited and connection is closed";
				DbLog.logger().logging(msg);
				System.out.println(msg);
				
				return;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				DbLog.logger().logging(e.getMessage());
			}
		}


}
