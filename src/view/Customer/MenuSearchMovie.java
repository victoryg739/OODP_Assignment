package view.Customer;

import controller.MovieController;
import controller.SessionController;
import modal.Movie;
import modal.Session;
import view.MenuBase;
import view.Quit;
import view.admin.MenuStaffTopFiveRating;

import java.util.ArrayList;

import static view.utilF.*;

public class MenuSearchMovie extends MenuBase {


    MovieController mc = new MovieController();
    SessionController sc = new SessionController();

    public MenuSearchMovie(MenuBase initialMenu) {
        super(initialMenu);
    }

    public MenuBase execute() {
        MenuBase nextMenu;
        int choice;

        printHeader("Movie Search");
        String movieName = read("Input movie name to search: ");

        ArrayList<Movie> movieList = mc.readByTitle(movieName);
        // If session is Empty or MovieList is Empty then no results found
        if (movieList.isEmpty()) {
            print("Sorry, no result found.");
            return this.getPreviousMenu();
        }

        mc.listMovies(movieList);
        printDivider();
        print("1. View Movie Details\n" +
                "2. Set Movie Review\n" +
                "3. Show top 5 movies by ratings \n" +
                "3. Back\n");
        choice = readIntInput("Enter choice: ");
        switch (choice) {
            case 1:
                int movieID = readIntInput("Which movie would you like to know more? (MovieID): ");
                nextMenu = new MenuMovieInfo(this, mc.read().get(movieID));
                break;
            case 2:
                movieID = readIntInput("Which movie would you like to set a review? (MovieID): ");
                nextMenu = new MenuMovieReviews(this, mc.readByID(movieID));
                break;
            case 3:
                nextMenu = new MenuStaffTopFiveRating(this);
                break;
            default:
                nextMenu = this.getPreviousMenu();
                break;


        }
        return nextMenu;
    }
}

