package DBQueries;

import adminPackage.Classifieds;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import db_package.*;

/*  @bordoloa  */

/*  IMPORTING DB_PACKAGE FOR ONE TIME CONNECTION   */

//INSTANCE OF CONNECTION OBJ {db_package.MysqlCon.connect().connection} 
//TO CONNECT TO DATABASE OR SERVER DATABASE

/*ANY EXCEPTION FROM "db_package.MysqlCon.connect().connection" WILL 
 * BE HANDLED AT TOP LAYER SO NO NEED TO WORRY.
 *
 *THE FUNCTION SHOULD WORK EVEN IF THE EXCEPTION OCCURS. DONT PRINT STACKTRACE. 
 **/

class DBQueryClassified {
    private static Sql_util_user sql_util_user;
    
    public DBQueryClassified(){
    	
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
    public List<Classifieds> find() {
        List<Classifieds> list = new LinkedList<>();
        try {
            String sql = "select * from classified_info where Status = 'NA'";
            ResultSet result = sql_util_user.connect_user(sql);
            while (result.next()){
                Classifieds classified = new Classifieds();
                classified.setClassified_id(result.getInt(1));
                classified.setTitle(result.getString(2));
                classified.setPrice(result.getInt(3));
                classified.setDescription(result.getString(4));
                classified.setCategory(result.getString(5));
                classified.setDate_Published(result.getDate(6));
                classified.setPublisher(result.getString(7));
                classified.setStatus(result.getString(8));

                list.add(classified);
//                System.out.print(result.getStri.lng(1));
//                System.out.print(result.getString(2));
//                System.out.print(result.getInt(3));
//                System.out.println();
            }
            //System.out.println("Result="+result.getRow());
            return list;
        } catch (SQLException e) {
            e.getMessage();
        }
        return list;
    }
    public void delete() {
    }
}