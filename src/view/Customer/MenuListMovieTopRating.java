package view.Customer;
import controller.MovieController;
import view.MenuBase;
import modal.*;
import java.util.*;
import view.*;
import static view.utilF.*;

public class MenuListMovieTopRating extends MenuBase {
    public MenuListMovieTopRating(MenuBase initialMenu) {
        super(initialMenu);
    }

    /*
     Display the top five movies by rating to the user
     @return return the corresponding menu that the user has selected
     */


    public MenuBase execute() {
        System.out.println("Top 5 by Rating");
        MovieController mc = new MovieController();
        ArrayList<Movie> movies =  mc.read();

        ArrayList<String> options = new ArrayList<>();

        try {

            mc.sortRating(movies);

            //Display the top 5 movies by ratings
            int top = 1;

            //we display the top 5 movies of all time
            for (Movie movie : movies) {
                options.add(movie.getRating() + " Overall Rating for " + movie.getTitle());
                //If top<5, break
                if (top++ == 5) {
                    break;
                }


            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        options.add("List Top 5 Movies By Sales");
        options.add("Go Back to Previous Menu");
        options.add("Quit The Application");
        printMenu(options, 1);

        int choice = readIntInput("Enter your choice");

        MenuBase nextMenu = this;

        /*
           Option 1: Movie 1
           Option 2: Movie 2
           Option 3: Movie 3
           Option 4: Movie 4
           Option 5: Movie 5
           Option 6: List Top 5 Movies By Sales
           Option 7: Go Back to Previous Menu
           Option 8: Quit The Application
         */

        
        if (choice <= options.size()) {
            nextMenu = new MenuMovieInfo(this, movies.get(choice));
        }
        else if (choice == options.size() + 1) {
            nextMenu = new MenuListMovieTopSale(this.getPreviousMenu());
        }
        else if (choice == options.size() + 2) {
            nextMenu = this.getPreviousMenu();
        }
        else if (choice == options.size() + 3) {
            nextMenu = new Quit(null);
        }
        return nextMenu;

    }



}
