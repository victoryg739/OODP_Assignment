package view.Customer;

import controller.MovieController;
import modal.Movie;
import view.MenuBase;
import view.Quit;
import java.util.*;
import static view.utilF.*;
public class MenuListMovie extends MenuBase {
    MovieController mc = new MovieController();
    public MenuListMovie(MenuBase initialMenu) {
        super(initialMenu);
    }

    /*
     Display all movies, Ask user to choice the next menu and bring user to next stage of application
     Consist of four options
     1. Show Top 5 movies by sales
     2. Show Top 5 movies by rating
     3. Back to previous menu
     4. Quit Booking System
     */

    public MenuBase execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Movie Listing");
        ArrayList<String> options = new ArrayList<String>();

        //Retrieve the entire list of movies in the database
        ArrayList<Movie> movies = mc.read();

        //list all the movies out

        for (Movie m : movies) {
            //get the current time
            Date currentTime = Calendar.getInstance().getTime();
            //.getEnding is to retrieve the last showing date
//            if(m.getDateEnd().compareTo(currentTime) < 0) { //not showing anymore
//                m.setShowingStatus(Constant.ShowingStatus.NOT_SHOWING); //set the status to NOT SHOWING
//            }
//            else if (m.getDateEnd().compareTo(currentTime) >= 0) { //currently showing
//                m.setShowingStatus(Constant.ShowingStatus.CURRENTLY_SHOWING);
//            }
            String title = m.getTitle();
            //+ (m.getShowingStatus().equals(Constant.ShowingStatus.END_SHOWING) ? " (End Showing)" : "");
            options.add(title); //add the movie as part of the option
        }



        options.add("Show Top 5 Movies by sale");
        options.add("Shop Top 5 Movies by ratings");
        options.add("Go Back");
        options.add("Quit The Application");
        printMenu(options, 1);
        int choice = readIntInput("Enter the choice: ");

        /* eg. movies.size == 2
           Option 1: Movie 1
           Option 2: Movie 2
           Option 3: Show Top 5 Movies by sale
           Option 4: Shop Top 5 Movies by ratings
           Option 5: Go Back
           Option 6: Quit The Application
         */

        MenuBase nextMenu = this;

        if (choice <= movies.size()) //Selecting 1 of the movies
            nextMenu = new MenuMovieInfo(this, movies.get(choice - 1));
        else if (choice == movies.size() + 1) //Select Top5 Movies by sale
            nextMenu = new MenuListMovieTopSale(this.getPreviousMenu());
        else if (choice == movies.size() + 2) //Select Top5 Movies by rating
            nextMenu = new MenuListMovieTopRating(this.getPreviousMenu());
        else if (choice == movies.size() + 3) //GO back to the previous menu
            nextMenu = this.getPreviousMenu();
        else if (choice == movies.size() + 4) //Quit the App
            nextMenu = new Quit(null);

        return nextMenu;
    }

}
