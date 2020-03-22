package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLJDBCUtil {
	private static final String URL = "jdbc:mysql://database-1.cda7tdexq29d.us-east-2.rds.amazonaws.com:3306/classified";
	private static final String USER = "root";
	private static final String PASSWORD = "root12345";
	public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("******Connection stablished with mysql******");
            System.out.println();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Unable to connect to mysql....");
        }
        return conn;
    }
}
