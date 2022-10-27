package view.admin;

import controller.MovieController;
import view.MenuBase;

/*
Todo list:
1. movieID validation (what if the movie does not exists)
2. listingMovie validation ( what if theres no movie in the first place)

 */
import static view.utilF.readIntInput;

public class MenuStaffMovieRemove extends MenuBase {

    public MenuStaffMovieRemove(MenuBase initialMenu) {
        super(initialMenu);
    }
    MovieController adminC = new MovieController();

    public MenuBase execute() {
        MenuStaffMovieList nextMenu = new MenuStaffMovieList(this);
        System.out.println("Deleting movie...");
        System.out.println("Which movie do you want to delete?");
        nextMenu.execute();
        int movieID = readIntInput("Enter move ID: ");

        adminC.deleteMovie(movieID);
        return this.getPreviousMenu();
    }
}
