package view.admin;

import controller.AdminController;
import view.MenuBase;

import java.util.Scanner;

import static view.utilF.*;


public class MenuStaffRegister extends MenuBase {

    private static Scanner sc = new Scanner(System.in);
    // Instantiate AdminController object to use methods
    private AdminController adminsCtrl = new AdminController();

    public MenuStaffRegister(MenuBase initialMenu) {
        super(initialMenu);
    }

    // Registration Menu to create new accounts
    public MenuBase execute() {

        int choice;

        printHeader(" Admin Registration ");
        print("1. Continue Registration        \n" +
                "2. Previous Menu         \n");
        choice = readIntInput("Enter your choice");

        MenuBase nextMenu = this;

        switch (choice) {
            case 1:
                adminsCtrl.adminRegistration();
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

