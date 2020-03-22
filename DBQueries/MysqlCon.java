package DBQueries;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.sql.*;

/*   @bordoloa   */
///DELETE THIS CLASS AS WILL CREATE CONFUSION. 
// WHERE EVER YOU HAVE USED THIS USE 
//CONNECTION OBJ {db_package.MysqlCon.connect().connection} INSTEAD


public class MysqlCon {

    public Connection connect(){
        int count=0;
        Log log=new Log();
        while(count<2) {
            try{
                Connection con=DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/classified","root","MySQL123");
                //here Classfield is database name, root is username and password is bordoloa
                count++;
                String msg="Success";
                log.logging(count, msg);
                return con; //returning the connection instance : FOR SUCCESS

            }catch(Exception e){
                count++;
                System.out.println("Error in connection. Reconecting... ");
                log.logging(count, e.toString());
            }
        }
        return null; //Returning NULL: for fail
    }

}