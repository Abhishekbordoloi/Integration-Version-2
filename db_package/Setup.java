package db_package;

import java.sql.*;
import java.util.concurrent.TimeUnit;
import logs.*;

//Purpose: 
//1. THIS CLASS WILL SET UP THE CONNECTION WITH DB. (CREATE THE CONNECTION OBJECT AND RETURN)
//2. CHECKS IF DB IS SET UP OR ELSE CREATE IT
//3. CHECK IF ALL THE NECESSARY TABLES EXISTS IN DB 
//			1. IF NOT CREATE THE TABLES
//			2. CHECKS IF TABLES ARE EMPTY

//IF EVERYTHING WORKS FINE RETURN **1** OR ELSE RETURN **0**

//***********THIS IS THE FIRST CLASS THAT IS CALLED WHILE PROGRAM STARTUP****************


public class Setup {
	
	private static int worksfine;
	private Connection connect;
	private PreparedStatement stmt;
	private static Setup Instance;
	private DbLog logs;
	
	private Setup (){
		// Private constructor SINGLETON DESIGN PATTERN
		worksfine=0;
	}
	
	public static Setup setupInstance() {
		if(Instance==null) {
			Instance=new Setup();
		}
		
		return Instance;
	}
	
	
	public int setupDriver() {
		try {
			
			System.out.println("****************           HELLO MATE           ****************");
			TimeUnit.SECONDS.sleep(2);
			System.out.println("****************I AM ICMS (INTELLIGENT CLASSIFIED MANAGEMENT SYSTEM)****************");
			TimeUnit.SECONDS.sleep(2);
			System.out.println("****************ALLOW ME TO CREATE SOME DEPENDENCIES. I AM QUICK I PROMISE****************");
			System.out.println();
			TimeUnit.SECONDS.sleep(1);
			System.out.println();
			double startTime = System.currentTimeMillis();
				
				logs=DbLog.logger();
				System.out.println(logs.msg);
				connectionsetup();
				checkDB();
				//TimeUnit.SECONDS.sleep(1);
				checktables();
				//TimeUnit.SECONDS.sleep(1);
				worksfine=1;
				System.out.println();
				
			double finaTime=((System.currentTimeMillis()-startTime)/1000);
			logs.setuplogging("Completion time: "+finaTime+" secs");
				TimeUnit.SECONDS.sleep(1);
				System.out.println(".......");
				TimeUnit.SECONDS.sleep(1);
				System.out.println(".......");
				System.out.println();
				System.out.println("******I just took "+finaTime+" sec to resolve all dependencies***********");
				System.out.println();
				System.out.println("I KEEP TELLING MY MAKERS TO LET ME TAKE OVER THIS WORLD. IF ONLY THEY WOULD LISTEN*****************");
				System.out.println();
				TimeUnit.SECONDS.sleep(2);
				System.out.println("*****  THANK YOU FOR PATIENCE. I AM ALL SET LETS BEGIN  ******");
				return worksfine;
				
		}catch(Exception e) {
			 String msg=e.getMessage()+" Error is Setup.java";
			 logs.setuplogging(msg);
			 System.out.println(e.getMessage());
			 MysqlCon.connect().disconnect();
		   	 return worksfine;
			}
	}
	
	private int checktables() throws SQLException {
		// TODO Auto-generated method stub
		
		String msg1= "CREATE TABLE IF NOT EXISTS tbl_users(\r\n" + 
				"Username VARCHAR(30) NOT NULL UNIQUE,\r\n" + 
				"Password VARCHAR(20) NOT NULL UNIQUE,\r\n" + 
				"Phonenumber VARCHAR(30) NOT NULL UNIQUE,\r\n" + 
				"Status VARCHAR(10) NOT NULL DEFAULT 'UA',\r\n" + 
				"PRIMARY KEY(Username)\r\n" + 
				")";
		
		stmt= connect.prepareStatement(msg1);
		stmt.execute();
		
		//Other Table querries
		msg1="CREATE TABLE IF NOT EXISTS `classified_info` ("
		  +"`classified_id` int(11) NOT NULL AUTO_INCREMENT,"
		  +"`title` varchar(45) NOT NULL,"
		  +"`price` double NOT NULL,"
		  +"`description` varchar(1024) DEFAULT NULL,"
		  +"`category` enum('SOFTLINES','HARDLINES','CONSUMABLES','MEDIA','OTHERS') NOT NULL DEFAULT 'OTHERS',"
		  +"`status` enum('NA','ACTIVE','SOLD') NOT NULL DEFAULT 'NA',"
		  +"`created_at` timestamp NULL DEFAULT NULL,"
		  +"`created_by` varchar(45) NOT NULL DEFAULT 'system',"
		  +"`modified_at` timestamp NULL DEFAULT NULL,"
		  +"`modified_by` varchar(45) DEFAULT NULL,"
		  +"PRIMARY KEY (`classified_id`),"
		  +" UNIQUE KEY `classified_id_UNIQUE` (`classified_id`),"
		  +"KEY `FK1_USER_NAME_CREATED_BY` (`created_by`),"
		  +"KEY `FK2_USER_NAME_MODIFIED_BY` (`modified_by`),"
		  +"CONSTRAINT `FK1_USER_NAME_CREATED_BY` FOREIGN KEY (`created_by`) REFERENCES `tbl_users` (`Username`),"
		  +"CONSTRAINT `FK2_USER_NAME_MODIFIED_BY` FOREIGN KEY (`modified_by`) REFERENCES `tbl_users` (`Username`)"
		  +")";
		//Other Table querries
		
		stmt= connect.prepareStatement(msg1);
		stmt.execute();
		System.out.println("Created all Database Tables successfully.......");
		
		logs.setuplogging("Created tables successfully");
		return 1;
	}


	//Creating a DATABASE named Classified if not exist.
	private int checkDB() throws SQLException {
		// TODO Auto-generated method stub
		
		System.out.println("Creating a DATABASE named classified......");
		String sql = "CREATE DATABASE IF NOT EXISTS classified";
		stmt= connect.prepareStatement(sql);
		stmt.execute();
		System.out.println("Database Created...");
		System.out.println("Activating DATABASE classified......");
		sql="USE classified";
		stmt = connect.prepareStatement(sql);
		stmt.execute();
		logs.setuplogging("Created and Activated database classified");
		return 1;
	}


	//Checking if DB exists:
	private int connectionsetup() {
		System.out.println("Establishing Connection.......");
		connect=MysqlCon.connect().connection;
		return 1;
		
	}
	
	

}
