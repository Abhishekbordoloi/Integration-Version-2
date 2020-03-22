package DBQueries;

import adminPackage.User;
import adminPackage.ValidateLogin;
import db_package.Sql_util_user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import user.*;


/*  @bordoloa  */

/*  IMPORTING DB_PACKAGE FOR ONE TIME CONNECTION   */

//INSTANCE OF CONNECTION OBJ {db_package.MysqlCon.connect().connection} 
//TO CONNECT TO DATABASE OR SERVER DATABASE

/*ANY EXCEPTION FROM "db_package.MysqlCon.connect().connection" WILL 
 * BE HANDLED AT TOP LAYER SO NO NEED TO WORRY.
 *
 *THE FUNCTION SHOULD WORK EVEN IF THE EXCEPTION OCCURS. DONT PRINT STACKTRACE. 
 **/
/*!!!!!!!      STATUS IN USER TABELS       UA (UN APPROVED), A (APPROVED ), R (REJECTED)    !!!!!!*/


class DBQueryUser {
private static Sql_util_user sql_util_user;
    
    public DBQueryUser(){
    	
    	try {
			sql_util_user=Sql_util_user.InstanceCreation();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			String msg= "Was not able to create Instance of sql_util_user. will happen if "
					+ " connection is not set up correctly";
			System.out.println(msg+"  "+e.getMessage()+"  "+e.getClass());
		}
    }
    public void insert() {

    }
    public void update() {


    }
    public List<user.User> find() {
        List<user.User> list = new LinkedList<>();
        try {
            String sql = "select * from tbl_users where Status = 'UA'";
            ResultSet result = sql_util_user.connect_user(sql);
            while (result.next()){
            	user.User user1 = new user.User();
                user1.setUsername(result.getString("Username"));
                user1.setPassword(result.getString("Password"));
                user1.setPhonenumber(result.getString("Phonenumber"));
                user1.setFlag(result.getString("Status"));
                list.add(user1);
//                System.out.print(result.getString(1));
//                System.out.print(result.getString(2));
//                System.out.print(result.getInt(3));
//                System.out.println();
            }
            //System.out.println("Result="+result.getRow());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void delete() {
    }
}
