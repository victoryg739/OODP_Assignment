package view.admin;

import controller.AdminController;
import modal.Admin;
import view.MenuBase;
import java.util.Scanner;
import static view.utilF.read;


public class MenuStaffRegister extends MenuBase {

    public MenuStaffRegister(MenuBase initialMenu) {
        super(initialMenu);
    }
    private String staffUsername;
    private String password;
    private String password2;
    private int role;
    private boolean consistentPassword = false;

    // Instantiate AdminController object to use methods
    private AdminController adminsCtrl = new AdminController();

    private static Scanner sc = new Scanner(System.in);

    // Registration Menu to create new accounts
    public MenuBase execute(){
        do {

            // Display to get new account Username and Password
            staffUsername = read("Create staffUsername: ");
            password = read("Create Password: ");
            password2 = read("Re-Enter Password: ");
            consistentPassword = password.equals(password2);

            // Creating new staff account object
            Admin admin = new Admin(staffUsername, password);

            // Create new admin account when the 2 input passwords match
            if(consistentPassword){
                adminsCtrl.create(admin);
                System.out.println("You have registered Staff Account successfully");
            }
            else {
                System.out.println("Password not consistent. Enter again");
            }
        }
        while(!consistentPassword);

        return this.getPreviousMenu();
    }
}

