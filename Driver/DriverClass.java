package Driver;

import DBQueries.MysqlCon;
import adminPackage.ValidateLogin;
import db_package.*;

import java.sql.*;

/*   @bordoloa   */
//CORRECTING THE ADMIN DRIVER CLASS TO CREATE 
//INSTANCE OF CONNECTION OBJ {db_package.MysqlCon.connect().connection} 
//TO CONNECT TO DATABASE OR SERVER DATABASE

/*PATCHING TO YOUR VALIDATE LOGIN CLASS DIRECLY AS THIS DRIVER CLASS WILL 
 * BE UN-HELPFUL WHILE THE MAIN CLASS RUNS*/

public class DriverClass {

    public static void main(String[] args) throws InterruptedException {
        MysqlCon mysqlcon = new MysqlCon();
        Connection new_connection =  db_package.MysqlCon.connect().connection; //CHANGED ONLY FOR @RACHNA REFERENCE
        System.out.println("Welcome to Amazon IntraClassifieds! Please Login to Continue");
        ValidateLogin obj1 = new ValidateLogin();
        obj1.login();
    }
}
