package view.Customer;
import view.MenuBase;
import view.admin.MenuStaffMovieList;

import java.util.*;
import static view.utilF.*;

public class MovieGoerMainMenu extends MenuBase {
    public MovieGoerMainMenu(MenuBase initialMenu) {
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
        String choice = sc.next();
        int c = readIntInput(choice);

        MenuBase nextMenu = this;
        switch (c) {
            case 0:
                nextMenu = new MovieSearchMenu(this);
                break;
            case 1:
                //MenuStaffMovieList = ListMovieMenu
                nextMenu = new MenuStaffMovieList(this);
                break;
            case 2:
                //nextMenu = new BookingHistoryMenu(this);
                break;
            case 3:
                nextMenu = this.getPreviousMenu();
                break;
        }
        return nextMenu;

    }
}