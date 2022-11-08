package model;

import java.io.Serializable;

/**
 * The model to hold a review and rating to a movie.
 */
public class Review implements Serializable {
    private String comment;
    private int rating;

    /**
     * Constructor for Review
     * @param comment user's comment for the review
     * @param rating  user's rating for the review
     */
    public Review(String comment, int rating) {
        this.comment = comment;
        this.rating = rating;
    }

    /**
     * Get Comment
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Get Rating
     * @return rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * Set Rating
     * @param rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

}