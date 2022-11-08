package view.admin;

import controller.MovieController;
import view.MenuBase;

import static view.utilF.*;

/**
 * Menu to remove movie by updating the showingstatus to ENDING_SHOW
 *
 * @author Bryan Tay
 * @version 1.0
 * @since 2022-08-11
 */
public class MenuStaffMovieRemove extends MenuBase {
    MovieController mc = new MovieController();

    public MenuStaffMovieRemove(MenuBase initialMenu) {
        super(initialMenu);
    }

    /**
     * Display remove Movie menu
     * Ask user to input information about the Movie ID
     * and bring user back to configure menu.
     *
     * @return configure menu
     */

    public MenuBase execute() {

        mc.listMovies();

        printHeader("Removing Movies");
        print("Which movie do you want to remove by setting the showing status to END_SHOWING?");
        int movieID = readIntInput("Enter move ID: ");

        if (mc.readByID(movieID) == null) {
            print("Movie does not exist!");
            return this.getPreviousMenu();
        }

        if (mc.removeMovie(movieID)) {
            print("Movie has been removed.");
        } else {
            print("Error! Showing status is already END_SHOWING.");
        }
        return this.getPreviousMenu();
    }
}
