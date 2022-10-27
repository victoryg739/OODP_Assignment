package view.Customer;

import java.util.Scanner;
import java.util.*;
import static view.utilF.*;

/*
public class MoviesListTopRatings extends MenuBase {
    public MoviesListTopRatings(MenuBase initialMenu) {
        super(initialMenu);
    }


     Display the top five movies by rating to the user
     @return return the corresponding menu that the user has selected
     */

    /*
    public MenuBase execute() {
        Scanner sc = new Scanner(System.in);
        Manager manager = Manager.getInstance();

        printTitle("Top 5 by Rating");
        ArrayList<Movie> movies =  manager.getAll(Tables.MOVIE);

        ArrayList<String> choices = new ArrayList<>();

        try {

            sortRating(movies);

            //Display the top 5 movies by ratings
            int top = 1;

            //For each movie, if the movie is still currently showing, we display the top 5 movies
            for (Movie movie : movies) {
                if(movie.getShowingStatus() != Constant.ShowingStatus.END_SHOWING && movie.getShowingStatus() != Constant.ShowingStatus.COMING_SOON){
                    choices.add(movie.getOverAllRating() + " Overall Rating for " + movie.getTitle());
                    //If top<5, break
                    if (top++ == 5) {
                        break;
                    }
                }

            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        choices.add("List Top 5 Movies By Sales");
        choices.add("Go Back to Previous Menu");
        choices.add("Quit The Application");
        printMenu(choices, 1);

        String choice = sc.next();
        int c = readIntInput(choice);

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

        /*
        if (c <= choices.size() - 3) {
            nextMenu = new MovieInfo(this, movies.get(c));
        } else if (c == choices.size() - 2) {
            nextMenu = new MovieListTopSale(this.getPreviousMenu());
        } else if (c == choices.size() - 1) {
            nextMenu = this.getPreviousMenu();
        }
        else if (c == choices.size()) {
            nextMenu = new Quit(null);
        }
        return nextMenu;

    }

    private void sortRating(ArrayList<Movie> movies) {
        Collections.sort(movies, new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                return Double.compare(m2.getOverAllRating(), m1.getOverAllRating());
            }
        });
    }

}
