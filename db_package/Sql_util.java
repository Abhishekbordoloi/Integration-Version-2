/**
 * 
 */
package db_package;

import java.sql.ResultSet;
import java.sql.SQLException;

import user.User;

/**
 * @author bordoloa
 *
 */
public interface Sql_util {

	/*
	 * //public User login(String username,String password) throws SQLException;
	 * public User read(String username) throws SQLException; public void
	 * newuser(User user) throws SQLException; public void update(int option);
	 */
	public ResultSet connect_user(String querry) throws SQLException;

}
