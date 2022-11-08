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
     *
     * @param comment user's comment for the review
     * @param rating  user's rating for the review
     */
    public Review(String comment, int rating) {
        this.comment = comment;
        this.rating = rating;
    }

    public Review() {

    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Check if a review already exists
     *
     * @param o object need to be checked
     * @return boolean value of the whether a review exists
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;

        Review review = (Review) o;

        if (rating != review.rating) return false;
        return getComment() != null ? getComment().equals(review.getComment()) : review.getComment() == null;
    }

}