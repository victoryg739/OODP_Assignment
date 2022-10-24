package view;

import java.util.*;
import static view.utilF.*;

//Menu to list the top five movies by sales

    /*
public class MovieListTopSale extends MenuBase {
    public MovieListTopSale(MenuBase initialMenu) {
        super(initialMenu);
    }


     //Display the top five menu by sales


    public MenuBase execute() {
        Scanner sc = new Scanner(System.in);
        Manager manager = Manager.getInstance();
        ArrayList<Movie> movies = manager.getAll(Constant.Tables.MOVIE);
        System.out.println("Top 5 by Sales");
        ArrayList<String> choices = new ArrayList<>();

        try {

            sortTicketSales(movies);

            //top refers to the top 5 movies
            int top = 1;

            //For each movie, if the movie is still currently showing, we display the top 5 movies
            for (Movie movie : movies) {
                if(movie.getShowingStatus() != Constant.ShowingStatus.END_SHOWING && movie.getShowingStatus() != Constant.ShowingStatus.COMING_SOON)
                {
                    choices.add(movie.getTicketSales() + " Tickets for " + movie.getTitle());

                    //If top < 5, break
                    if (top++ == 5) {
                        break;
                    }
                }

            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        choices.add("List Top 5 Movies By Ratings");
        choices.add("Go Back to Previous Menu");
        choices.add("Quit The Application");
        printMenu(choices, 1);

        String choice = sc.next();
        int c = readIntInput(choice);


        MenuBase nextMenu = null;

        System.out.println(choices.size());

        /*
           Option 1: Movie 1
           Option 2: Movie 2
           Option 3: Movie 3
           Option 4: Movie 4
           Option 5: Movie 5
           Option 6: List Top 5 Movies By Ratings
           Option 7: Go Back to Previous Menu
           Option 8: Quit The Application
         */

        /*
        //Depending on the c input, display the relevant options by subtracting
        if (c <= choices.size() - 3) {
            nextMenu = new MovieInfo(this, movies.get(c));
        } else if (c == choices.size() - 2) {
            nextMenu = new MoviesListTopRatings(this.getPreviousMenu());
        } else if (c == choices.size() - 1) {
            nextMenu = this.getPreviousMenu();
        }
        else if (c == choices.size()) {
            nextMenu = new Quit(null);
        }
        return nextMenu;
    }

    private void sortTicketSales(ArrayList<Movie> movies) {
        Collections.sort(movies, (m1, m2) -> (m2.getTicketSales() - m1.getTicketSales()));
    }
}
         */


