package view.Customer;

import view.MainMenu;
import view.MenuBase;

import static view.utilF.*;

/**
 * Menu Interface for Customer when they first open the app
 *
 * @author Aloysius Tan
 * @version 1.0
 * @since 2022-08-11
 */
public class MenuCustomerMain extends MenuBase {
    public MenuCustomerMain(MenuBase initialMenu) {
        super(initialMenu);
    }

    /**
     * Display main user menu
     * Ask user to choice the next menu
     * and bring user to next stage of application
     * Consist of four menu
     * 1. Search for movies
     * 2. List all movies
     * 3. View Booking history ( login required through MenuCustomerLogin)
     * 4. Back to previous menu
     *
     * @return to the menu that the user has selected
     */
    public MenuBase execute() {
        printHeader("Customer Menu");
        print("1. Search For Movies        \n" +
                "2. List All Movies         \n" +
                "3. Booking History         \n" +
                "4. Go Back to Main Menu\n");

        //read the input
        int choice = readIntInput("Enter your choice");

        MenuBase nextMenu = this;
        switch (choice) {
            case 1:
                nextMenu = new MenuSearchMovie(this);
                break;
            case 2:
                nextMenu = new MenuListMovie(this);
                break;
            case 3:
                //nextMenu = new Menu
                nextMenu = new MenuCustomerLogin(this, 2, null);
                break;
            case 4:
                nextMenu = new MainMenu(this);
                break;
        }
        return nextMenu;

    }
}