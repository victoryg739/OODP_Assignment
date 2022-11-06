package view.Customer;

import controller.CustomerController;
import modal.Admin;
import modal.Customer;
import view.MenuBase;
import java.util.Scanner;
import static view.utilF.read;


public class MenuCustomerRegister extends MenuBase {

    public MenuCustomerRegister(MenuBase initialMenu) {
        super(initialMenu);
    }
    private String customerUsername;
    private String password;
    private String password2;
    private int role;
    private boolean consistentPassword = false;

    private CustomerController customerCtrl = new CustomerController();

    private static Scanner sc = new Scanner(System.in);

    // Registration Menu to create new accounts
    public MenuBase execute(){
        do {

            // Display to get new account Username and Password
            customerUsername = read("Create customerUsername: ");
            password = read("Create Password: ");
            password2 = read("Re-Enter Password: ");
            consistentPassword = password.equals(password2);

            // Creating new customer account object
            Customer customer = new Customer(customerUsername, password);



            // Create new Customer account when the 2 input passwords match
            if(consistentPassword){
                customerCtrl.createCustomer(customer);
                System.out.println("You have registered successfully");
            }
            else {
                System.out.println("Password not consistent. Enter again");
            }
        }
        while(!consistentPassword);

        return this.getPreviousMenu();
    }
}


