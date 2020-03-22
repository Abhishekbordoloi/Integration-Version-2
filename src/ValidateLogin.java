//import java.util.Scanner;
//
//public class ValidateLogin implements  adminPackage.ValidateLoginInterface {
//    private String username, password, choice;
//    public void setUsername(String username){ this.username = username; }
//    public void setPassword(String password){ this.password = password; }
//    public void setChoice(String choice){ this.choice = choice; }
//    public String getUsername(){return this.username;}
//    public String getPassword(){return this.password;}
//    public String getChoice(){return this.choice;}
//
//    public void login() {
//        //Validate UserName
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter adminPackage.ValidateLogin.User Name");
//        username = sc.nextLine();
//        if ("Admin".equals(username)) {
//            System.out.println("Enter Password");
//            password = sc.nextLine();
//            if ("Password".equals(password)) {
//                System.out.println("Login Successful!");
//                System.out.println("Please choose one of the following options: ");
//                System.out.println("1) Activate/Deactivate adminPackage.ValidateLogin.User");
//                System.out.println("2) Approve/Disapprove Classifieds");
//                System.out.println("3) Add/Remove Classifieds");
//                System.out.println("4) Generate Report");
//                System.out.println("5) Logout");
//            } else {
//                System.out.println("Incorrect Password");
//                System.out.println("Would you like to continue? Enter Y or N");
//                choice = null;
//                while (choice.equalsIgnoreCase("N")){
//                }
//            }
//        } else {
//            System.out.println("Incorrect Username");
//            System.out.println("Would you like to continue? Enter Y or N");
//            choice = null;
//            while (choice.equalsIgnoreCase("N")){
//
//            }
//        }
//    }
//}
//
