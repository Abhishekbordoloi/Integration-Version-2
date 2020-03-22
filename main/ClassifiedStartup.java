package main;

import java.io.Console;
import java.io.IOException;
import java.sql.SQLException;
import java.util.TimerTask;

import adminPackage.ValidateLogin;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Timer;

import user.*;
import logs.*;
import db_package.*;
import app.ClassifiedApp;
import adminPackage.ValidateLogin;

public class ClassifiedStartup extends Thread {
	private static Scanner scanner = new Scanner(System.in);
	static Thread thread = new Thread();

	TimerTask task = new TimerTask() {

		@Override
		public void run() {
			// TODO Auto-generated method stub

			synchronized (this) {
				System.out.println("Interrupt");
				thread.notifyAll();
				System.out.println("Invalid input");
			}
		}

	};

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Setup setup = Setup.setupInstance();
		if (setup.setupDriver() != 1) {
			System.out.println();
			System.out.println("***********I am unable to setup the connection due to " + "dependencies********");
			System.out.println("***********Please check log files to debug and contact ADMIN***********");

			System.out.println("***********I am exiting the program***********");
			System.exit(0);
		}
		System.out.println();
		System.out.println();
		while (true) {

			// Step 1:
			try {

				ClassifiedStartup startup = new ClassifiedStartup();
				// Setup.setupDriver();
				startup.menu();
				int option = scanner.nextInt();
				// System.out.println("You chos"option);

				switch (option) {
				case 1:
					System.out.println("*********ADMIN LOGIN PAGE**************");
/*PLEASE REMOVE*/	System.out.println("USERNAME: Admin  PASSWORD: Password");
					ValidateLogin obj1 = new ValidateLogin();
			        obj1.login();
					break;

				case 2:
					System.out.println();
					System.out.println();
					System.out.println("*********USER LOGIN PAGE*********");
					System.out.println(
							"PRESS 1: IF YOU ARE NEW TO THIS SYSTEM. WE WILL" + " SETUP A NEW ACCOUNT FOR YOU");
					System.out.println("PRESS 2: IF YOU ARE EXISTING USER WITH US.");
					System.out.println();
					System.out.print("I AM WAITING FOR YOUR INPUT  :");

					/*
					 * Timer timer=new Timer(); timer.schedule(startup.task, 10000);
					 * System.out.println("Scheduled");
					 */
					int optionUserAdmin = scanner.nextInt();

					switch (optionUserAdmin) {
					case 1:
						startup.newuser();
						break;
					case 2:
						User user = startup.existing_user();
						if (user != null) {
							DbLog.logger().userlogin_logging("Successful login by: " + user.getUsername());
							ClassifiedApp classifiedApp = new ClassifiedApp(user);
							classifiedApp.menu();
							break;
						}

						/**************************************************/
						/*FOR TESTING PLEASE REMOVE //
						 * ********SETTING ADMIN USERNAME AND PASSWORD AS
						 * ADMIN AND ADMIN
						 */
						//REMOVING TESTING BLOCK
						/*
						 * System.out.println("ENTER ADMIN USERNAME: "); String username=scanner.next();
						 * System.out.println("ENTER ADMIN PASSWORD: "); String password=scanner.next();
						 * if(username.equals("admin")&& password.equals("admin")) { ClassifiedApp
						 * classifiedApp=new ClassifiedApp(); classifiedApp.menu(); break; }
						 */
						 
						 //FOR TESTING PLEASE  REMOVE
						 /**************************************************/													 
					 break;															 
					}
					/*
					 * System.out.println("return"); timer.cancel();
					 */
					break;

				case 3:
					// scanner.close();
					startup.sysexit();
					break;

				default:
					System.out.println("YOU CANNOT TRICK ME. CHOOOSE A VALID OPTION");

				}
			} catch (InputMismatchException e) {
				DbLog.logger()
						.mainclasslogging(e.getMessage() + "  Invalid Input in Main fucntion by User in main class");
				System.out.println("PLEASE GIVE A VALID INPUT");
				scanner.next();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				DbLog.logger().mainclasslogging("Exception generated in main class: " + e.getMessage());
				System.out.println("WE ARE FACING A CHALLENGE AND WAS UNABLE TO PERFORM THE OPERATION. "
						+ "PLEASE CHECK LOGS FOR MORE DETAILS");
				System.out.println("PLEASE TRY AGAIN");
				scanner.next();
			}
		}
	}

	private void sysexit() throws InputMismatchException {
		// TODO Auto-generated method stub
		System.out.println("HEY I THOUGHT WE WERE HAVING A GOOD TIME TOGETHER.");
		System.out.println("ARE YOU SURE " + "YOU WANT TO EXIT THE PROGRAM: [1. YES OR 2. NO]");
		int yesno = scanner.nextInt();
		if (yesno == 1) {
			System.out.println("SURE I WILL TURN DOWN FOR NOW");
			System.out.println("CLOSING ALL DEPENDENCIES......");
			MysqlCon.connect().disconnect();
			System.exit(0);
		}
	}

	private User existing_user() throws SQLException {
		// TODO Auto-generated method stub
		Sql_util_user utilities = Sql_util_user.InstanceCreation();
		
		System.out.println("ENTER USERNAME: ");
		String username = scanner.next();
		User user = utilities.login(username);
		boolean flag =true;
		int attempt=0;
		while(user ==null && flag && attempt<3) {
			System.out.println("USERNAME "+username+" NOT FOUND");
			System.out.println("PLEASE RECHECK THE USERNAME AND TYPE AGAIN");
			System.out.println("RE-ENTER USERNAME: ");
			username = scanner.next();
			user = utilities.login(username);
			//System.out.flush();
			if(user !=null) {
				flag=false;
			}
			if(attempt==2 && flag==true) {
				
					System.out.println("IT SEEMS YOU HAVE NOT CREATED AN ACCOUNT WITH US.");
					System.out.println();
					System.out.println("WOULD YOU LIKE TO CREATE ONE? IT'S VERY SIMPLE.");
					System.out.println("PRESS 1. TO CREATE A ACCOUNT");
					System.out.println("PRESS 2 TO GO TO THE MAIN MENU");
					System.out.println("PLEASE LET ME KNOW: ");
					int choice = scanner.nextInt();
					switch (choice) {
					case 1:
						newuser();
						break;
					case 2:
						break;
					}
				
				System.out.println("YOU HAVE ATTEMPTED MULTIPLE TIMES. I AM TAKING YOU BACK"
						+ " TO THE MAIN PAGE");
				return null;
			}
			attempt++;
		}
		flag=false;
		System.out.println("ENTER PASSWORD: ");
		String password = scanner.next();
		int count = 1;
		
		while(flag==false) {
			if (password.equals(user.getPassword())
					&& user.getFlag().equals("A")) {
				System.out.println("*************HELLO " + user.getUsername() + "*************");
				System.out.println("I HOPE YOU HAVE BEEN DOING GOOD");
				return user;
				
			} else if (password.equals(user.getPassword())
					&& user.getFlag().equals("UA")) {
				System.out.println("*************Hi " + user.getUsername() + "*************");
				System.out.println();
				System.out.println("IT SEEMS YOUR ACCOUNT IS NOT ACTIVE AS OF NOW");
				return null;
				
			}else if (password.equals(user.getPassword())
					&& user.getFlag().equals("D")) {
				System.out.println("*************Hi " + user.getUsername() + "*************");
				System.out.println();
				System.out.println("YOUR ACCOUNT HAS BEEN DEACTIVATED. "
						+ "PLEASE CONTACT ADMIN OR CREATE A NEW ACCOUNT WITH US");
				return null;
				
			} else {
				
				System.out.println("THE PASSWORD YOU HAVE ENTERED MIGHT BE INCORRECT. "
							+ "PLEASE RETYPE THE PASSWORD: [attempt " + count + " OF 3:]");
				password = scanner.next();
					if (count>=3) {
						flag=true;
						System.out.println("GOING BACK TO MAIN MENU");
						DbLog.logger().userlogin_logging("UnSuccessful login by: " + username);
					}
					count++;
					
			} 
		
		}
		return null;
	}

	private void newuser() throws SQLException {
		// TODO Auto-generated method stub
		String username;
		String password;
		String phonenumber;
		int count = 0;

		Sql_util_user utilities = Sql_util_user.InstanceCreation();
		System.out.println();
		System.out.println("**************WELCOME NEW USER**************");
		System.out.println("SIT BACK AND RELAX. I WILL HELP YOU IN SETTING UP YOUR NEW ACCOUNT");
		System.out.println();
		System.out.println("PLEASE LET ME KNOW YOUR PREFERRED USERNAME: ");
		username = scanner.next();

		while (count < 3) {
			Console cnsl = System.console();
			System.out.println("YOUR PREFERRED PASSWORD: ");
			if (cnsl != null) {
				char[] pwd = cnsl.readPassword();
				password = String.valueOf(pwd);
			} else {
				password = scanner.next();
			}
			System.out.println();
			if (regex_passwordvalidation1(username, password)) {

				System.out.println("PLEASE RE ENTER YOUR PASSWORD TO CONFIRM:");
				if (scanner.next().equals(password)) {

					count=0;
					System.out.println();
					System.out.println("AND YOUR PHONE NUMBER WOULD BE: ");
					phonenumber = scanner.next();
					if (validate(phonenumber)) {
						System.out.println(utilities.newuser(new User(username, password, phonenumber)));
						break;
					} else {
						System.out.println("Phone number can only be 0-9");
					}
				} else {
					System.out.println("PASSOWRDS DO NOT MATCH. PLEASE TRY AGAIN!!");
				}
			}
			count++;
			if (count >= 3)
				System.out.println("YOU TRIED YOUR BEST!!! LET US BEGIN FROM THE START");

		}

		// utilities.newuser();
	}

	private boolean validate(String phonenumber) {
		// TODO Auto-generated method stub
		if (phonenumber.matches("(.*[0-9].*)"))
			return true;
		else
			return false;
	}

	public boolean regex_passwordvalidation1(String Username, String Password) {
		Boolean validator = true;
		if (Password.length() < 8 || Password.length() > 20) {

			System.out.println("I can only take a Password whose lenght " + "is greater then 8 and less then 20");
			validator = false;
		}
		if (!Password.matches("(.*[A-Z].*)") || !Password.matches("(.*[a-z].*)")) {
			System.out.println("Password should contain atleast one upper case and one lower case alphabet");
			validator = false;
		}

		if (Password.indexOf(Username) > -1) {
			System.out.println("Password Should not be same as user name");
			validator = false;
		}

		if (!Password.matches("(.*[0-9].*)")) {
			System.out.println("Password should contain atleast one number.");
			validator = false;
		}

		if (!Password.matches("(.*[,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*$)")) {
			System.out.println("Password should contain atleast one special character");
			validator = false;
		}

		return validator;
	}

	private static void menu() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("PRESS 1. ADMIN");
		System.out.println("PRESS 2. USER");
		System.out.println("PRESS 3. EXIT");
		System.out.print("Please choose: ");
		System.out.println();

	}

}
