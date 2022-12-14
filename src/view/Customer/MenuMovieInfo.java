package view.Customer;

import controller.MovieController;
import model.Movie;
import view.MenuBase;
import view.Quit;

import static view.utilF.*;

/**
 * Menu Interface to print out all the movie information
 *
 * @author Aloysius Tan
 * @version 1.0
 * @since 2022-08-11
 */
public class MenuMovieInfo extends MenuBase {
    private final Movie movie;

    private MovieController mc = new MovieController();

    public MenuMovieInfo(MenuBase initialMenu, Movie movie) {
        super(initialMenu);
        this.movie = movie;
    }

    /**
     * Print out all the movie information
     * Ask user if they want to purchase tickets
     *
     * @return corresponding Menus
     **/

    public MenuBase execute() {
        printHeader("Movie Information");
        movie.printALLInfoMovie();

        print("1. Buy Ticket\n" +
                "2. Back to Customer Main Menu\n" +
                "3. Quit");
        int choice = readIntInput("Choose: ");
        MenuBase nextMenu;

        switch (choice) {
            case 1:
                // Only allow User to purchase ticket if it is a valid movie
                if (mc.validateShowingStatus(movie)) {
                    nextMenu = new MenuCustomerLogin(this, 1, this.movie);
                } else {
                    print("This movie is not available for booking. ");
                    nextMenu = this.getPreviousMenu();
                }
                break;
            case 3:
                nextMenu = new Quit(null);
                break;
            default:
                nextMenu = new MenuCustomerMain(this);
        }
        return nextMenu;
    }


}