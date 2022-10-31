package view.admin;

import controller.AdminController;
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

    private AdminController adminsCtrl = new AdminController();

    private static Scanner sc = new Scanner(System.in);

    // Registration Menu to create new accounts
    public MenuBase execute(){
        do {

            // Display to get new account Username and Password
            // Note : Currently there is no input validation
            //        => Suppose to create InputController to check inputs
            staffUsername = read("Create staffUsername: ");
            password = read("Create Password: ");
            password2 = read("Re-Enter Password: ");
            consistentPassword = password.equals(password2);

            // Create new admin account when the 2 input passwords match
            if(consistentPassword){
                adminsCtrl.create(staffUsername, password);
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

