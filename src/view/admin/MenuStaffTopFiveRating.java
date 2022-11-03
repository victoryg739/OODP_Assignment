package view.admin;

import controller.MovieController;
import modal.Enums;
import modal.Movie;
import view.MenuBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MenuStaffTopFiveRating extends MenuBase {
    MovieController ac = new MovieController();
    public MenuStaffTopFiveRating(MenuBase initialMenu) {
        super(initialMenu);
    }

    @Override
    public MenuBase execute() {
        ArrayList<Movie> movieList = ac.read();

        try {
            sortRating(movieList);
            int top = 1;
            for(Movie movie: movieList){
                if(movie.getShowingStatus() != Enums.ShowingStatus.END_SHOWING && movie.getShowingStatus() != Enums.ShowingStatus.COMING_SOON)
                {
                    System.out.println(movie.getTitle() + " | " + movie.getRating());
                    if (top++ == 5) {
                        break;
                    }
                }
            }
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        return this.getPreviousMenu();
    }

    private void sortRating(ArrayList<Movie> movies) {
        Collections.sort(movies, new Comparator<Movie>() {
            public int compare(Movie m1, Movie m2) {
                return Double.compare(m2.getRating(), m1.getRating());
            }
        });
    }
}
