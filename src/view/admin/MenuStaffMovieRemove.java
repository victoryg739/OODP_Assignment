package view.admin;

import controller.MovieController;
import modal.Movie;
import view.MenuBase;

/*
Todo list:
1. movieID validation (what if the movie does not exists)
2. listingMovie validation ( what if theres no movie in the first place)

 */import java.util.ArrayList;

import static view.utilF.*;

public class MenuStaffMovieRemove extends MenuBase {
    MovieController mc = new MovieController();

    public MenuStaffMovieRemove(MenuBase initialMenu) {
        super(initialMenu);
    }

    public MenuBase execute() {
        mc.listMovies();

        printHeader("Removing Movies");
        print("Which movie do you want to remove by setting the showing status to END_SHOWING?");
        int movieID = readIntInput("Enter move ID: ");

        if(mc.readByID(movieID) == null) {
            print("Movie does not exist!");
            return this.getPreviousMenu();
        }

        if(mc.removeMovie(movieID)){
            print("Movie has been removed.");
        }else{
            print("Error! Showing status is already END_SHOWING.");
        }
        return this.getPreviousMenu();
    }
}
