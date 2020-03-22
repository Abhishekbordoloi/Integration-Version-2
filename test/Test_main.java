/**
 * @author bordoloa
 *
 */

package test;
import java.sql.*;
import java.util.Scanner;
import user.*;
import db_package.*;

public class Test_main {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args){
		// TODO Auto-generated method stub
		//Connection connection=MysqlCon.connect().connection;
		
		try {
			/*
			 * Statement stmt=connection.createStatement(); Scanner sc=new
			 * Scanner(System.in); System.out.println("Enter name: "); String
			 * temp=sc.next(); String
			 * querry="INSERT INTO tbl_test (NAME) values ('"+temp+"')";
			 * stmt.executeUpdate(querry); String querry1="SELECT * FROM tbl_test";
			 * ResultSet results=stmt.executeQuery(querry1);
			 * 
			 * while(results.next()){ //Retrieve by column name int id =
			 * results.getInt("ID"); String name = results.getString("NAME");
			 * System.out.println("ID: "+id+"  "+name); } MysqlCon.connect().disconnect();
			 */
			Sql_util_user Login=Sql_util_user.InstanceCreation();
			System.out.println("ran");
			while(true) {
				int option=menu();
				switch(option) {
						case 1: 
							System.out.println("Enter Username: ");
							String username=scanner.next();
							System.out.println("Enter Password: ");
							String password=scanner.next();
							
							User userdetails=Login.login(username);
							if(userdetails!=null) {
								validatelogin(username, password,userdetails);
							}
							else {
								System.out.println("Login not found");
							}
							break;
						
						case 2:
							User user=new User();
							System.out.println("Welcome new User: ");
							System.out.println("Enter Username: ");
							user.setUsername(scanner.next());
							System.out.println("Enter Password: ");
							user.setPassword(scanner.next());
							System.out.println("Enter Phone Number: ");
							user.setPhonenumber(scanner.next());
							String msg=Login.newuser(user);
							System.out.println(msg);
							break;
					}
				}
			}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	private static int menu() {
		// TODO Auto-generated method stub
		System.out.println("1. Existing User Login");
		System.out.println("2. New User");
		System.out.println("3. Exit");
		return scanner.nextInt();
	}

	private static void validatelogin(String username, String password, User userdetails) {
		// TODO Auto-generated method stub
		int loginattempt=0;
		while(loginattempt<3) {
			if(password.equals(userdetails.getPassword())) {
			//	System.out.println("Login successful");
				if(userdetails.getFlag().equals("A")) {
					System.out.println();
					System.out.println("===============");
					System.out.println("Username: "+userdetails.getUsername());
					System.out.println("Password: "+userdetails.getPassword());
					System.out.println("Phone Number: "+userdetails.getPhonenumber());
					System.out.println("Flag: "+userdetails.getFlag());
					System.out.println("===============");
					System.out.println();
					System.out.println();
					return;
				}
				else if(userdetails.getFlag().equals("UA")){
					System.out.println("Your account is not active");
					return;
				}
				else if(userdetails.getFlag().equals("D")) {
					System.out.println("Account is deactivated");
					return;
				}
				else if(userdetails.getFlag().equals("B")) {
					System.out.println("Account is Blocked");
					System.out.println("Contact Admin");
					return;
				}
				
			}else {
					System.out.println("Password is incorrect");
					System.out.println("Please retype password");
					password=scanner.next();
					loginattempt++;
				}
		}
		
		System.out.println("Maximum Login effort reached. Account is temporarily locked");
		System.out.println("Choose 1 to contact ADMIN");
		System.out.println("Choose 2 to return to main menu");
	}

}
