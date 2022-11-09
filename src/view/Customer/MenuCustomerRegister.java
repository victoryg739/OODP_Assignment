package view.Customer;

import controller.CustomerController;
import view.MenuBase;

import java.util.Scanner;

import static view.utilF.*;


public class MenuCustomerRegister extends MenuBase {

    private static Scanner sc = new Scanner(System.in);
    private CustomerController customerCtrl = new CustomerController();

    public MenuCustomerRegister(MenuBase initialMenu) {
        super(initialMenu);
    }

    // Registration Menu to create new accounts
    public MenuBase execute() {

        int choice;

        printHeader(" Customer Registration ");
        print("1. Continue Registration        \n" +
                "2. Previous Menu         \n");
        choice = readIntInput("Enter your choice");

        MenuBase nextMenu = this;

        switch (choice) {
            case 1:
                customerCtrl.customerRegistration();
                break;
            case 2:
                return this.getPreviousMenu();
            default:
                System.out.println("Please enter an option between 1-2");
                break;
        }

        return this.getPreviousMenu();
    }


}


