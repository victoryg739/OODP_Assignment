package view;

import java.util.*;
import static view.utilF.*;
public class MoviesListMenu extends MenuBase {

    public MoviesListMenu(MenuBase initialMenu) {
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
        //Manager mg = Manager.getInstance(); //get a manager object to access the database

        ArrayList<String> choices = new ArrayList<String>();
        //.getAll() is to retrieve all the movies
        //ArrayList<Movie> movies = mg.getAll();

        //list all the movies out
        /*
        for (Movie m : movies) {
            //get the current time
            Date currentTime = Calendar.getInstance().getTime();
            //.getEnding is to retrieve the last showing date
            if(m.getEnding().compareTo(currentTime) < 0) { //not showing anymore
                m.setShowingStatus(Constant.ShowingStatus.NOT_SHOWING); //set the status to NOT SHOWING
            }
            else if (m.getEnding().compareTO(currentTime) >= 0) { //currently showing
                m.setShowingStatus(Constant.ShowingStatus.CURRENTLY_SHOWING);
            }
            String title = m.getTitle() + (m.getShowingStatus().equals(Constant.ShowingStatus.END_SHOWING) ? " (End Showing)" : "");
            choices.add(title); //add the movie as part of the option
        }

         */

        choices.add("Show Top 5 Movies by sale");
        choices.add("Shop Top 5 Movies by ratings");
        choices.add("Go Back");
        choices.add("Quit The Application");
        printMenu(choices, 1);

        /* eg. movies.size == 2
           Option 1: Movie 1
           Option 2: Movie 2
           Option 3: Show Top 5 Movies by sale
           Option 4: Shop Top 5 Movies by ratings
           Option 5: Go Back
           Option 6: Quit The Application
         */
        String choice = sc.next();
        int c = readIntInput(choice);

        MenuBase nextMenu = this;
        /*
        if (c <= movies.size()) //Selecting 1 of the movies
            nextMenu = new MovieInfo(this, movies.get(c));
        else if (c == movies.size() + 1) //Select Top5 Movies by sale
            nextMenu = new MovieListTopSale(this.getPreviousMenu());
        else if (c == movies.size() + 2) //Select Top5 Movies by rating
            nextMenu = new MoviesListTopRatings(this.getPreviousMenu());
        else if (c == movies.size() + 3) //GO back to the previous menu
            nextMenu = this.getPreviousMenu();
        else if (c == movies.size() + 4) //Quit the App
            nextMenu = new Quit(null);
        */
        return nextMenu;
    }

}
