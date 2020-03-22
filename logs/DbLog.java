
/**
 * @author bordoloa
 *
 */
/*INTERNAL CLASS TO LOG ERRORS WITH DB CONNECTION*/

package logs;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DbLog implements Logger{
	private static DbLog settinglog;
	private static boolean flag=true;
	private DateTimeFormatter dtf;
	private LocalDateTime now;
	private File file;
	public String msg="Creating all Log files..";
	private DbLog() {
		// Private constructor SINGLETON DESIGN PATTERN
	}
	
	public static DbLog logger() {
		if(settinglog==null) {
			settinglog=new DbLog();
		}
		return settinglog;
	}
	
	@Override
	public void logging(String msg) {
		String location="C:\\Users\\Public\\IntraClassifieldsLogs\\Database_login.txt";
		mainlog(msg,location);
	}
	
	@Override
	public void setuplogging(String msg) {
		// TODO Auto-generated method stub
		String location="C:\\Users\\Public\\IntraClassifieldsLogs\\Setup_Login.txt";
		mainlog(msg,location);
	}

	@Override
	public void mainclasslogging(String msg) {
		// TODO Auto-generated method stub
		String location="C:\\Users\\Public\\IntraClassifieldsLogs\\Main_class_erros.txt";
		mainlog(msg,location);
		
	}
	
	
	private void mainlog(String msg, String location){ 
		
		//DATE
		int count=1;
		dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		now = LocalDateTime.now();
		   
		   //CREATE LOG FILE
		try {
						file = new File("C:\\Users\\Public\\IntraClassifieldsLogs");
			 			file.mkdir();
			 			
			 			FileWriter fileWriter = new FileWriter
						(location, true);
						
						
						PrintWriter printWriter = new PrintWriter(fileWriter);
						
						printWriter.println("Log ID: "+count+++"   "+msg+"   "+dtf.format(now));  
					    printWriter.close();
					    
			 		
		}catch(Exception e) {
			
			if(flag==true) {
				flag=false;
				System.out.println(e.getMessage());
				System.out.println("Disabling LOGS...");
			}
		}
	}

	public void userlogin_logging(String msg) {
		// TODO Auto-generated method stub
		String location="C:\\Users\\Public\\IntraClassifieldsLogs\\UserLogin.txt";
		mainlog(msg,location);
	}

	public void classfied(String msg) {
		// TODO Auto-generated method stub
		String location="C:\\Users\\Public\\IntraClassifieldsLogs\\Classifiedlogs.txt";
		mainlog(msg,location);
		
	}
}
