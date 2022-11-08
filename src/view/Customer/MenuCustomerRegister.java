package view.Customer;

import controller.CustomerController;
import modal.Admin;
import modal.Customer;
import view.MenuBase;
import view.admin.MenuStaffLogin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static view.utilF.*;


public class MenuCustomerRegister extends MenuBase {

    public MenuCustomerRegister(MenuBase initialMenu) {
        super(initialMenu);
    }

    private CustomerController customerCtrl = new CustomerController();

    private static Scanner sc = new Scanner(System.in);

    // Registration Menu to create new accounts
    public MenuBase execute(){

        int choice;

        printHeader(" Customer Registration ");
        print("1. Continue Registration        \n" +
                "2. Previous Menu         \n");
        choice = readIntInput("Enter your choice");

        MenuBase nextMenu = this;

        switch (choice) {
            case 1:
                customerCtrl.registration();
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


