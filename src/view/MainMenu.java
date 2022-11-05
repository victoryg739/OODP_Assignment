package view;

import view.Customer.MenuCustomerMain;
import view.admin.MenuStaffLogin;
// import view.Customer.MenuCustomerRegister;

import java.util.Scanner;

import static view.utilF.*;

public class MainMenu extends MenuBase {

    public MainMenu(MenuBase initialMenu) {
        super(initialMenu);
    }

    public MenuBase execute() {
        int choice;
        printHeader("Welcome to MOblima Application");
        print("1. Customer App        \n" +
              "2. Staff App         \n" +
                "3. Quit App         \n");

        //read the input
       choice = readIntInput("Enter your choice");
            MenuBase nextMenu = this;

            switch (choice) {
                case 1:
                    nextMenu = new MenuCustomerMain(this);
                    break;
                case 2:
                    nextMenu = new MenuStaffLogin(this);
                    break;
                case 3:
                    nextMenu = new Quit(this);
                    System.out.println("Thank you for using our MOBLIMA APP");
                    break;
                default:
                    System.out.println("Please enter an option between 0-2");
                    break;
            }
            return nextMenu;

    }
}
