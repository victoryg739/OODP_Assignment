//package view.Customer;
//
//import controller.CustomerController;
//import view.MenuBase;
//import java.util.Scanner;
//import static view.utilF.read;
//
//
//public class MenuCustomerRegister extends MenuBase {
//
//    public MenuCustomerRegister(MenuBase initialMenu) {
//        super(initialMenu);
//    }
//    private String email;
//    private String password;
//    private String password2;
//    private int role;
//    private boolean consistentPassword = false;
//
//    private CustomerController customerCtrl = new CustomerController();
//
//    private static Scanner sc = new Scanner(System.in);
//
//    // Registration Menu to create new accounts
//    public MenuBase execute(){
//        do {
//
//            // Display to get new account Username and Password
//            // Note : Currently there is no input validation
//            email = read("Please enter your email: ");
//            password = read("Create Password: ");
//            password2 = read("Re-Enter Password: ");
//            consistentPassword = password.equals(password2);
//
//            // Create new Staff account when the 2 input passwords match
//            if(consistentPassword){
//                customerCtrl.create(email, password);
//                System.out.println("You have registered successfully");
//            }
//            else {
//                System.out.println("Password not consistent. Enter again");
//            }
//        }
//        while(!consistentPassword);
//
//        return this.getPreviousMenu();
//    }
//}


