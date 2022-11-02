package view.Customer;

import java.util.*;
import static view.utilF.*;
import view.*;
import modal.*;


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

        movie.addReview(review);

        return this.getPreviousMenu();
    }
}
