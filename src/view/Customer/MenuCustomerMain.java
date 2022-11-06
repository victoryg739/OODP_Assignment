package view.Customer;
import view.MenuBase;

import java.util.*;
import static view.utilF.*;
import static view.utilF.print;

public class MenuCustomerMain extends MenuBase {
    public MenuCustomerMain(MenuBase initialMenu) {
        super(initialMenu);
    }

    /*
     Display main user menu
     Ask user to choice the next menu and bring user to next stage of application
     Consist of four menu
     1. Search for movies --> MovieInfo if found --> Buy Tickets/ Write a Review
     2. Display all movies --> Show current movies --> Show Top 5 by sales/review
     3. View history ( login required ) --> Check your booking history
     4. Back to previous menu
     */
    public MenuBase execute() {
        printHeader("Customer Menu");
        print("1. Search For Movies        \n" +
                "2. List All Movies         \n" +
                "3. Booking History         \n" +
                "4. Go Back to Previous Menu\n");

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
                nextMenu = this.getPreviousMenu();
                break;
        }
        return nextMenu;

    }
}