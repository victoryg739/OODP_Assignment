package view.Customer;
import view.MenuBase;

import java.util.*;
import static view.utilF.*;

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
        System.out.println("Welcome to MOvie Booking and LIsting Management Application (MOBLIMA)");

        Scanner sc = new Scanner(System.in);
        ArrayList<String> choices = new ArrayList<String>();
        choices.add("Search For Movies");
        choices.add("List All Movies");
        choices.add("Booking History");
        choices.add("Go Back");
        //print the menu interface
        printMenu(choices, 0);

        //read the input
        int choice = readIntInput("Enter your choice");

        MenuBase nextMenu = this;
        switch (choice) {
            case 0:
                nextMenu = new MenuSearchMovie(this);
                break;
            case 1:
                //MenuStaffMovieList = ListMovieMenu
                nextMenu = new MenuListMovie(this);
                break;
            case 2:
                nextMenu = new MenuBookingHistory(this);
                break;
            case 3:
                nextMenu = this.getPreviousMenu();
                break;
        }
        return nextMenu;

    }
}