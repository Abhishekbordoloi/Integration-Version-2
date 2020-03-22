/**
 * @author bordoloa
 *
 */


package db_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


import user.*;

public class Sql_util_user implements Sql_util  {
	private Connection con;
	private Statement stmt;
	private Map<String, User> hashmapuser=new HashMap<String, User>();
	private static Sql_util_user sql_util_user_Instance;
	
	private Sql_util_user()  {
		
		//private constructor FOR SINGLETON
	}
	
	public static Sql_util_user InstanceCreation() throws SQLException{
		if(sql_util_user_Instance!=null) {
			return sql_util_user_Instance;
		}
		else {
			sql_util_user_Instance=new Sql_util_user();
			sql_util_user_Instance.con=MysqlCon.connect().connection;
			sql_util_user_Instance.stmt=sql_util_user_Instance.con.createStatement();
			return sql_util_user_Instance;
		}
	}
	
	//Should be used for the MANAGE PROFILE FUNCTION
	//WILL return a USER Object related to the person who just logged in
	//Input: Username
	//Output: USER Object
	//NULL is the method is used without login method
	public User read(String username){
		// TODO Auto-generated method stub
		if(hashmapuser.containsKey(username))
			return hashmapuser.get(username);
		else
			return null;
	}
	
	
	//NEW USER REQUEST
	//INPUT: USER class object
	//Result: String messages 
	//1. Account details submitted. Please wait for ADMIN validation
	//2. Account exists
	public String newuser(User user) throws SQLException {
		// TODO Auto-generated method stub
		String username=user.getUsername();
		String password=user.getPassword();
		String phonenumber=user.getPhonenumber();
		//SELECT * FROM tbl_users where username = "bordoloa" OR password = "abhi";
		String msg="SELECT * FROM classified.tbl_users WHERE username =\""+username+
				"\" OR password = \""+password+"\" OR Phonenumber = \""+phonenumber+"\"";
		
		if(!hashmapuser.containsKey(username)) {
			ResultSet results=connect_user(msg);

			if(!results.first()) {
				msg="INSERT INTO classified.tbl_users (Username, Password, Phonenumber) values ('"+username
						+"' , '"+password+"' , '"+phonenumber+"')";
				stmt.executeUpdate(msg);
				System.out.println();
				return "Account details submitted. Please wait for ADMIN validation";
				
			}
			else {
				System.out.println();
				return "Account exists";
			}
		}
		else {
			System.out.println();
			return "Account exists";
		}
	}
	
	//USE THIS FUNCTION FOR CUSTOMIZED QUERRY TO RETRIEVE DATA FROM DATABASE TABLE.
	//RETURNS A RESULTSET OBJECTS OF THE DATABASE.
	@Override
	public ResultSet connect_user(String querry) throws SQLException {
		ResultSet results=stmt.executeQuery(querry);
		return results;
	}

	 
	// RETURNS A USER OBJECT if USERNAME FOUND. If not found RETURN NULL.
    // CONTAINING ***** USERNAME PASSWORD PHONE NUMBER AND FLAG ********
	// USE GETTER STTERS OF USER CLASS TO GET DATA.
	public User login(String username) throws SQLException {
		// TODO Auto-generated method stub
				if(!hashmapuser.containsKey(username)) {
					User user=new User();
					String msg="SELECT * FROM classified.tbl_users where username =\""+username+"\"";
					ResultSet results=connect_user(msg);
					
					if(results.first()) {
						String Username=results.getString("Username");
						user.setUsername(Username);
							
						String Password=results.getString("Password");
						user.setPassword(Password);
						
						String Status=results.getString("Status");
						user.setFlag(Status);
						
						String Phonenumber=results.getString("Phonenumber");
						//Long phonenumber=Long.parseLong(Phonenumber);
						user.setPhonenumber(Phonenumber);
						
						//System.out.println(Password);
						//Storing in Hashmap for cache
						storing20login(Username, user);
						return user;
					}
					else {
						storing20login(username, null);
						return null;
					}

				}else {
					System.out.println("Value already stored returning hashmap object");
					return hashmapuser.get(username);

				}

			}

	//Temporary cache of size 20 to store login objects
	private void storing20login(String username, User user) {
		// TODO Auto-generated method stub
		
		if(hashmapuser.size()<40) {
		hashmapuser.putIfAbsent(username, user);
		System.out.println("Stored in Hashmap");
		}
		else {
				System.out.println("Flashing out hashmap");
				hashmapuser.clear();
				hashmapuser.putIfAbsent(username, user);

		}
	}	
}
