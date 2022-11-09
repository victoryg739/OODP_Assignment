package view.Customer;

import controller.MovieController;
import model.Movie;
import model.Review;
import view.MenuBase;

import static view.utilF.*;

/**
 * Menu Interface for Customer to set reviews for a particular movie
 *
 * @author Aloysius Tan
 * @version 1.0
 * @since 2022-08-11
 */

public class MenuMovieReviews extends MenuBase {
    private final Movie movie;
    MovieController mc = new MovieController();

    /**
     * Menu to enable user to input the reviews and ratings towards the movie
     */
    public MenuMovieReviews(MenuBase previousMenu, Movie movie) {
        super(previousMenu);
        this.movie = movie;
    }

    /**
     * Ask user to input reviews and ratings
     *
     * @return return the movie infor menu
     */
    public MenuBase execute() {

        if (!mc.validReviewMovie(movie.getId())) {
            println("Reviews can only be set for movies that are currently showing, preview or just ended. ");
            return this.getPreviousMenu();
        }
        Review review = new Review(read("Please Enter your comment for " + movie.getTitle() + ": "), readReviewInput("Please enter your rating for " + movie.getTitle(), 1, 5));
        mc.updateMovie(12, movie.getId(), review);
        println("Successfully wrote review for " + movie.getTitle());
        return this.getPreviousMenu();
    }
}
