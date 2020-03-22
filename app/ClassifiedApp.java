package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.regex.Pattern;

import logs.DbLog;
import service.ClassifiedService;
import service.impl.ClassifiedServiceImpl;
import until.enumeration.CategoryType;
import user.User;
import util.Classified;
import until.enumeration.ClassifiedStatus;

public class ClassifiedApp {
	
	public static User user=null;
	
	public ClassifiedApp() {
		//for admin
	}
	
	public ClassifiedApp(User user) {
		
		// for user login
		ClassifiedApp.user=user;
	}

	private static String userName;

	// create classified
	private static void createClassified(ClassifiedService classifiedService, Scanner scan){
//		System.out.println("how many products you want to register");
//		int n = scan.nextInt();
		System.out.println();
		System.out.println("***Creating a New Classified***");
		String s = "N";
		int cont = 0;
		do {
			Classified classified = new Classified();
			// for (int i = 0; i < n; i++) {
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));// This can read input from
																								// anywhere
				System.out.println("PLEASE CHOOSE YOUR CATEGORY:");
				for (CategoryType cattype : CategoryType.values()) {
					System.out.println(String.format("Press %d to select category %s", cattype.ordinal() + 1,
							cattype.getCategoryType()));
				}
				System.out.println("Please select Category from above:");
				int selCat = Integer.parseInt(reader.readLine()); // to convert string into int
				classified.setCategory(CategoryType.getCategoryType(selCat));
				// System.out.println("You have selected category: ");
				System.out.println("Please enter price for your product");
				classified.setPrice(Double.parseDouble(reader.readLine()));
				System.out.println("Please enter Title");
				classified.setTitle(reader.readLine());
				System.out.println("Please Add some discription of your product");
				classified.setDescription(reader.readLine());
				
				if(user!=null) {
					classified.setCreatedBy(user.getUsername());
				}
				else {
					classified.setCreatedBy("ADMIN");
				}
				
				classified.setCreatedAt(Calendar.getInstance().getTime());
				// classified.setStatus(ClassifiedStatus.ACTIVE);
				Classified savedClassified = classifiedService.createClassified(classified);
				System.out.println(savedClassified);
				System.out.println("Wow! Your classified added successfully");
				System.out.println("Do you want to add one more classified: y/n");
				s = scan.next();
			} catch(IOException e) {
				String msg="Invalid entry";
				System.out.println(msg);
				DbLog.logger().classfied(msg+" "+e.getMessage());
				cont--;
			}catch (Exception e) {
				// TODO: handle exception\
				//e.getMessage();
				String msg="Constraints entry";
				DbLog.logger().classfied(msg+" "+e.getMessage()+" "+e.getCause());
				System.out.println(msg);
				cont--;
			}
			cont++;
		} while ("Y".equalsIgnoreCase(s));
		System.out.println("You have added " + cont + " Classifieds");
	}
	
	private static void print(ClassifiedService classifiedService) throws Exception {
		if(user==null) {
			printClassifiedList(classifiedService);
		}
		else {
			printClassifiedUserList(classifiedService);
		}
	}

	private static void printClassifiedUserList(ClassifiedService classifiedService) throws Exception{
		// TODO Auto-generated method stub
		System.out.println();
		List<Classified> list=classifiedService.getClassifiedList();
		
		int size=list.size();
		System.out.println(size);
		int i=0;

		while(size !=0 && i<size) {
			Classified classified=list.get(i);
			if(classified.getStatus().equals(ClassifiedStatus.ACTIVE)) {
				System.out.print("ID: "+classified.getClassifiedId());
				System.out.println();
				
				if(classified.getTitle()!=null && classified.getCategory()!=null) {
					System.out.print("Title: "+classified.getTitle());
					System.out.print("\t");
					System.out.print("Category: "+classified.getCategory());
					System.out.println();
				}
				
				if(classified.getCreatedAt()!=null && classified.getCreatedBy()!=null && classified.getStatus()!=null
						&& classified.getPrice()!=null) {
					System.out.print("Created BY: "+classified.getCreatedBy());
					System.out.print("\t");
					System.out.print("Price: "+classified.getPrice());
					System.out.println();
				}
				
				if(classified.getDescription()!=null) {
					System.out.print("Description: "+classified.getDescription());
				}
				System.out.println();
				System.out.println();
				
			}
			else {
				if(i==0)
					System.out.println("THERE ARE NO NEW CLASSIFIED TO BUY AS OF NOW");
			}
			i++;
		}
		System.out.println();
	}

	// print all classified
	private static void printClassifiedList(ClassifiedService classifiedService) throws Exception{
			System.out.println();
			List<Classified> list=classifiedService.getClassifiedList();
			printall(list);
	}
	
	private static void printall(List<Classified> list) {
		// TODO Auto-generated method stub
		int size=list.size();
		//System.out.println(size);
		int i=0;
		while(size !=0 && i<size) {
			
			Classified classified=list.get(i);
			System.out.print("ID: "+classified.getClassifiedId());
			System.out.println();
			
			if(classified.getTitle()!=null && classified.getCategory()!=null) {
				System.out.print("Title: "+classified.getTitle());
				System.out.print("\t");
				System.out.print("Category: "+classified.getCategory());
				System.out.println();
			}
			
			if(classified.getCreatedAt()!=null && classified.getCreatedBy()!=null && classified.getStatus()!=null
					&& classified.getPrice()!=null) {
				System.out.print("Created AT: "+classified.getCreatedAt());
				System.out.print("\t");
				System.out.print("Created BY: "+classified.getCreatedBy());
				System.out.print("\t");
				System.out.print("Status: "+classified.getStatus());
				System.out.print("\t");
				System.out.print("Price: "+classified.getPrice());
				System.out.println();
			}
			
			if(classified.getDescription()!=null) {
				System.out.print("Description: "+classified.getDescription());
			}
			System.out.println();
			System.out.println();
			i++;
		}
		System.out.println();
	}

	private void update(ClassifiedService classifiedService, Scanner scan) throws Exception{
		// TODO Auto-generated method stub
		if(user!=null) {
			/* .. */
			
			updateClassified(classifiedService, scan);
		}
		else {
			
			
			updateClassifieduser(classifiedService,scan);
		}
	}

	private void updateClassifieduser(ClassifiedService classifiedService, Scanner scan) {
		// TODO Auto-generated method stub
		
		/*DISPLAY ALL CLASSIFIEDS THIS FUNCTION CAN ONLY BE CALLED BY ADMIN
		 * 
		 * AS SUCH IT WILL DISPLAY ALL THE CLASSIFIEDS
		 * 
		 * */
		
	}

	// update classified
	private static void updateClassified(ClassifiedService classifiedService, Scanner scan) throws Exception{
		String s = "N";
		int contc = 0;
		do {
			Classified classified = new Classified();
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));// This can read input from
																								// anywhere
				classified.setCreatedBy(user.getUsername());
				List<Classified> classifiedList = classifiedService.getClassifiedListByUserName(classified.getCreatedBy());
				if(classifiedList == null || classifiedList.isEmpty()) {
					System.out.println("You have not created any classifieds");
					return;
				}
				System.out.println("Displaying all Classifieds by you: ");
				printall(classifiedList);
				System.out.println();System.out.println();
				System.out.println("Please select classifiedId you want to update: ");
				int sc = scan.nextInt();
				classified.setClassifiedId(sc);
					System.out.println("Please updateCategory from below: ");
					for (CategoryType cattype : CategoryType.values()) {
						System.out.println(String.format("Press %d to select category %s", cattype.ordinal() + 1,
								cattype.getCategoryType()));
					}
					int selCat = Integer.parseInt(reader.readLine()); // to convert string into int
					classified.setCategory(CategoryType.getCategoryType(selCat));
					System.out.println("Please update price for your product");
					classified.setPrice(Double.parseDouble(reader.readLine()));
					System.out.println("Please update Title");
					classified.setTitle(reader.readLine());
					System.out.println("Please update discription of your product");
					classified.setDescription(reader.readLine());
					
					
					classified.setCreatedAt(Calendar.getInstance().getTime());
					// classified.setStatus(ClassifiedStatus.ACTIVE);
					Classified savedClassified = classifiedService.updateClassified(classified);
					System.out.println(savedClassified);
					System.out.println("Your classified has been updated successfully");
					System.out.println("Do you want to update one more classified: y/n");
					s = scan.next();
			}catch (Exception e) {
				// TODO: handle exception\
				System.out.println("Please enter valid details");
			}
			contc++;
		} while ("Y".equalsIgnoreCase(s));
		System.out.println("Thanks! you have updated "+contc+" Classifieds ");
	}

	public void menu() throws Exception {
		
		boolean flag=true;
		Scanner scan = new Scanner(System.in);
		while(flag) {
			try {
				
				Pattern oldDelimiter = scan.delimiter();
				scan.useDelimiter("\\r\\n|[\\n\\x0B\\x0C\\r\\u0085\\u2028\\u2029]");
				scan.useDelimiter(oldDelimiter);
			
			
				System.out.println("***** Welcome to classified service *****");
				System.out.println();
				
				System.out.println("MAIN MENU");
				System.out.println("Press 1 to see classified list!");
				System.out.println("Press 2 to create new classified!");
				System.out.println("Press 3 to update classified!");
				System.out.println("Press 4 to logout ");
				int actionNo = scan.nextInt();
				ClassifiedService classifiedService = new ClassifiedServiceImpl();
				switch (actionNo) {
				case 1:
					print(classifiedService);
					break;
				case 2:
					createClassified(classifiedService, scan);
					String msg="Created a Classified: ";
					DbLog.logger().classfied(msg);
					break;
				case 3:
					update(classifiedService, scan);
					break;
				case 4:
					if(user!=null) {
						System.out.println("LOGING OUT "+user.getUsername());
						user=null;
						flag=false;
					}
					else {
						System.out.println("Logout ADMIN");
						user=null;
						flag=false;
					}
					break;
				default:
					System.out.println("You have selected Invalid option !");
					break;
				}
				System.out.println();System.out.println();
				//2scan.close();
			}
			catch(InputMismatchException e) {
				String msg="INVALID INPUT";
				DbLog.logger().classfied(msg+" "+e.getMessage());
				scan.next();
			}
			catch(SQLException e) {
				String msg="Error with SQL statement";
				DbLog.logger().classfied(msg+" "+e.getMessage());
				scan.next();
			}
			catch(Exception e) {
				String msg="Exception occured";
				DbLog.logger().classfied(msg+" "+e.getMessage());
				scan.next();
			}
		}
	}
}
