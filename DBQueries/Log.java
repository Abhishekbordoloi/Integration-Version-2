package DBQueries;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

    void logging(int count, String msg) {

        //DATE
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        //CREATE LOG FILE
        try {
            FileWriter fileWriter = new FileWriter
                    ("C:\\Users\\rachnav\\Desktop\\Project\\log.txt", true);

            // CREATE A PATH "C:\Users\rachnav\Desktop\Project\log.txt" FOR LOGS
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println("Connection: " + count + "   " + msg + "   " + dtf.format(now));
            printWriter.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}