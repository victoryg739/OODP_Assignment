package view;

import view.Customer.MenuCustomerMain;
import view.admin.MenuStaffLogin;

import static view.utilF.*;

/**
 * The Main Menu of the program
 * Allows MovieGoer or Admin to access their options to view the respective Menu
 * @author Bryan Tay Peng Keat
 * @version 1.0
 * @since 2022-08-11
 */

public class MainMenu extends MenuBase {

    public MainMenu(MenuBase initialMenu) {
        super(initialMenu);
    }


    /**
     * Display main menu for listing
     * consists of 3 menus
     * 1. Customer App
     * 2. Staff App
     * 3. Exit
     * @return corresponding menu that the user has selected
     */
    public MenuBase execute() {
        int choice;
        printHeader("Welcome to MOBLIMA Application");
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
                print("Thank you for using our MOBLIMA APP");
                break;
            default:
                print("Please enter an option between 0-2");
                break;
        }
        return nextMenu;

    }
}
