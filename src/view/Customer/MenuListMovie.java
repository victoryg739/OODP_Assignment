package view.Customer;

import controller.MovieController;
import controller.SessionController;
import controller.SettingController;
import modal.Enums;
import modal.Movie;
import view.MenuBase;
import view.Quit;
import view.admin.MenuStaffTopFiveRating;
import view.admin.MenuStaffTopFiveSales;

import java.util.*;

import static view.utilF.*;

public class MenuListMovie extends MenuBase {
    MovieController mc = new MovieController();
    SettingController setc = new SettingController();

    public MenuListMovie(MenuBase initialMenu) {
        super(initialMenu);
    }


    public MenuBase execute() {
        MenuBase nextMenu;


        printHeader("Movie Listing");
        mc.listMovies(null);
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
                    if(mc.validMovie(movieID)) {
                        nextMenu = new MenuMovieInfo(this, mc.read().get(movieID));
                    }else{
                        print("Invalid movieID, try again.");
                        nextMenu = this.getPreviousMenu();
                    }
                    break;
                case 2:
                    movieID = readIntInput("Which movie would you like to set a review? (MovieID): ");
                    nextMenu = new MenuMovieReviews(this, mc.readByID(movieID));

                    break;
                case 3:
                    nextMenu =  new MenuStaffTopFiveRating(this);
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
                    if(mc.validMovie(movieID)) {
                        nextMenu = new MenuMovieInfo(this, mc.read().get(movieID));
                    }else{
                        print("Invalid movieID, try again.");
                        nextMenu = this.getPreviousMenu();
                    }
                    break;
                case 2:
                    movieID = readIntInput("Which movie would you like to set a review? (MovieID): ");
                    nextMenu = new MenuMovieReviews(this, mc.readByID(movieID));
                    break;
                case 3:
                    nextMenu =  new MenuStaffTopFiveSales(this);
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
                    if(mc.validMovie(movieID)) {
                        nextMenu = new MenuMovieInfo(this, mc.read().get(movieID));
                    }else{
                        print("Invalid movieID, try again.");
                        nextMenu = this.getPreviousMenu();
                    }
                    break;
                case 2:
                    movieID = readIntInput("Which movie would you like to set a review? (MovieID): ");
                    nextMenu = new MenuMovieReviews(this, mc.readByID(movieID));
                    break;
                case 3:
                    nextMenu =  new MenuStaffTopFiveSales(this);
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
