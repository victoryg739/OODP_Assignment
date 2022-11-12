package view.Customer;

import controller.MovieController;
import controller.SettingController;
import model.Movie;
import view.MenuBase;
import view.admin.MenuStaffTopFiveRating;
import view.admin.MenuStaffTopFiveSales;

import java.util.ArrayList;

import static view.utilF.*;
/**
 * Menu Interface for Customer to search for the movie they want
 *
 * @author Aloysius Tan
 * @version 1.0
 * @since 2022-08-11
 */

public class MenuSearchMovie extends MenuBase {


    MovieController mc = new MovieController();
    SettingController setc = new SettingController();

    public MenuSearchMovie(MenuBase initialMenu) {
        super(initialMenu);
    }

    /**
     * Ask user to input come part of movie title
     * Display list of movies whose title contains the string provided by the user
     * Ask user which movie to check information
     *
     * @return corresponding menu that the user has selected
     **/
    public MenuBase execute() {
        MenuBase nextMenu;

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
        int flag = setc.returnResult(); // a variable to determine what to show to the user
        /* Flag == 2 implies that it only shows Rating */
        if (flag == 2) {
            print("1. View Movie Details\n" +
                    "2. Set Movie Review\n" +
                    "3. Show top 5 movies by ratings \n" +
                    "4. Back\n");
            int choice = readIntInput("Enter choice: ");
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
            /* Flag == 1 implies that it only shows Sales */
        } else if (flag == 1) {
            print("1. View Movie Details\n" +
                    "2. Set Movie Review\n" +
                    "3. Show top 5 by sales \n" +
                    "4. Back\n");
            int choice = readIntInput("Enter choice: ");
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
                    nextMenu = new MenuStaffTopFiveSales(this);
                    break;
                default:
                    nextMenu = this.getPreviousMenu();
                    break;
            }
            /* implies that it is default which only shows both Sales and ratings */
        } else {
            print("1. View Movie Details\n" +
                    "2. Set Movie Review\n" +
                    "3. Show top 5 by sales \n" +
                    "4. Show top 5 by ratings \n" +
                    "5. Back\n");
            int choice = readIntInput("Enter choice: ");

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
                    nextMenu = new MenuStaffTopFiveSales(this);
                    break;
                case 4:
                    nextMenu = new MenuStaffTopFiveRating(this);
                    break;
                default:
                    nextMenu = this.getPreviousMenu();
                    break;
            }
        }
        return nextMenu;
    }
}

