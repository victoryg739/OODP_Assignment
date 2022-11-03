package view.Customer;

import java.util.*;
import static view.utilF.*;

import controller.MovieController;
import view.*;
import modal.*;
/* ToDO list:
    1. Update the review into the movie.txt file
 */


public class MenuMovieReviews extends MenuBase {
    private final Movie movie;


    public MenuMovieReviews(MenuBase previousMenu, Movie movie) {
        super(previousMenu);
        this.movie = movie;
    }

    /**
     Ask user to input reviews and rating
     */


    public MenuBase execute() {
        System.out.println(movie.getTitle());

        //Let user comment and rate the movies
        Review review = new Review(read("Please Enter your comment for " + movie.getTitle() + ": ")  , readReviewInput("Please enter your rating for " + movie.getTitle() ,1,5));

        MovieController mc = new MovieController();
        mc.updateMovie(11, movie.getId(), review);
        //a way to update the movie.txt file
        return this.getPreviousMenu();
    }
}
