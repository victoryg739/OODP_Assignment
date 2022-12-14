package model;

import model.Enums.MovieRating;
import model.Enums.MovieType;
import model.Enums.ShowingStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import static view.utilF.print;
import static view.utilF.println;

/**
 * Represents a movie
 *
 * @author Bryan Tay
 * @version 1.0
 * @since 2022-08-11
 */
public class Movie implements Serializable {

    private int id, runtime;
    private String title, synopsis, director;
    private MovieType type;
    private MovieRating ContentRating;
    private Date DateStart, DateEnd;

    private ArrayList<String> cast;

    private int ratingTimes;

    private double rating;

    private ArrayList<Review> reviews;
    private int ticketSales;

    private ShowingStatus showingStatus;

    /**
     * Creates a new movie
     *
     * @param id            new ID of movie
     * @param title         title of the movie
     * @param type          type of the movie
     * @param ss            Showing status of the movie
     * @param ContentRating Content rating of the movie
     * @param synopsis      Details of the movie
     * @param runtime       runtime of the movie
     * @param DateStart     start Date of the movie
     * @param DateEnd       end Date of the movie
     * @param director      director of the movie
     * @param cast          casts of the movie
     */
    public Movie(int id, String title, MovieType type, ShowingStatus ss, MovieRating ContentRating, String synopsis, int runtime, Date DateStart, Date DateEnd, String director, ArrayList<String> cast) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.ContentRating = ContentRating;
        this.synopsis = synopsis;
        this.runtime = runtime;
        this.DateStart = DateStart;
        this.DateEnd = DateEnd;
        this.director = director;
        this.cast = cast;
        this.showingStatus = ss;
    }

    /**
     * Get the unique ID of this Movie
     *
     * @return int      Unique ID of this Movie
     */
    public int getId() {
        return id;
    }

    /**
     * Get the duration of this Movie
     *
     * @return double       Duration of this Movie
     */
    public int getRuntime() {
        return runtime;
    }

    /**
     * Change the duration of this Movie
     *
     * @param runtime New duration of this Movie
     */
    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    /**
     * Get the title of this Movie
     *
     * @return String   Title of this Movie
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of this Movie
     *
     * @param title New title of this Movie
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Change the synopsis of this Movie
     *
     * @param synopsis New synopsis of this Movie
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * Change the director for this Movie
     *
     * @param director New director of this Movie
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Get the MovieType of this Movie
     *
     * @return MovieType of this Movie
     */
    public MovieType getType() {
        return type;
    }

    /**
     * Get the MovieType of this Movie
     *
     * @param type    MovieType of this Movie
     */
    public void setType(MovieType type) {
        this.type = type;
    }

    /**
     * Change the Content rating of this Movie
     *
     * @param ContentRating New Content rating of this Movie
     */

    public void setContentRating(MovieRating ContentRating) {
        this.ContentRating = ContentRating;
    }

    /**
     * Change the release date of this Movie
     *
     * @param dateStart New release date of this Movie
     */
    public void setDateStart(Date dateStart) {
        DateStart = dateStart;
    }

    /**
     * Change the end date of this Movie
     *
     * @param dateEnd New end date of this Movie
     */
    public void setDateEnd(Date dateEnd) {
        DateEnd = dateEnd;
    }


    /**
     * Get the list of casts for this Movie
     *
     * @return String    List of casts of this Movie
     */
    public ArrayList<String> getCast() {
        return cast;
    }


    /**
     * Change the list of casts for this Movie
     *
     * @param cast New list of casts of this Movie
     */
    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    /**
     * Get the showing status for this Movie
     *
     * @return ShowingStatus    showing status of this Movie
     */
    public ShowingStatus getShowingStatus() {
        return showingStatus;
    }

    /**
     * Change the showing status for this Movie
     *
     * @param showingStatus new showing status of the movie
     */
    public void setShowingStatus(ShowingStatus showingStatus) {
        this.showingStatus = showingStatus;
    }

    /**
     * Get the ticket sales for this Movie
     *
     * @return int    number of ticket sales
     */
    public int getTicketSales() {
        return ticketSales;
    }


    /**
     * Adds a sales to the ticket sales
     *
     * @param delta incremental variable to increase the ticket sales
     */
    public void addTicketSales(int delta) {
        this.ticketSales += delta;
    }

    /**
     * Adds a review for a movie
     *
     * @param review
     */
    public void addReview(Review review) {
        if (this.reviews == null) {
            ArrayList<Review> r = new ArrayList<Review>();
            this.setReviews(r);
        }
        reviews.add(review);
        double overallRating = (this.rating * ratingTimes + review.getRating()) / (ratingTimes + 1);
        setRating(overallRating);
        addRatingTimes(1);
    }

    /**
     * Increase the number of ratings by 1
     *
     * @param delta
     */
    public void addRatingTimes(int delta) {
        this.ratingTimes += delta;
    }

    /**
     * Print Movie information (Movie Id, Movie Title and Showing Status)
     */

    public void printMovie() {
        System.out.println("ID: " + id + " Name: " + title + "\n" + "Showing Status: " + showingStatus);
        System.out.println(" ");

    }

    /**
     * Print All Movie Information
     * Movie Title, Showing Status, Content Rating, Runtime, Movie Type, Director,
     * Cast, Opening, Ending, Synopsis, Overall Rating, Last 3 Reviews
     */
    public void printALLInfoMovie() {
        //Movie Title
        print("Title: " + title);

        //Movie Status
        print("Showing Status: " + showingStatus);

        //Movie Content Rating (PG13, N16, R21, ...)
        print("Content Rating: " + ContentRating);

        //Movie Runtime
        print("Runtime: " + runtime + " minutes");

        //Movie Type
        print("Movie Type: " + type);

        //Movie Director
        print("Director: " + director);

        //Movie Cast
        System.out.print("Cast: ");
        StringBuilder s = new StringBuilder();
        for (String r : getCast()) { //get list of the entire cast for the movie
            s.append(r + "; ");
        }

        //print the list of cast
        print(s.toString());

        //Opening date of the movie
        print("Opening: " + DateStart);

        //Ending date of the movie
        print("Ending: " + DateEnd);

        //Movie Synopsis
        print("Synopsis: " + synopsis);

        //Movie Overall Rating
        print("Overall Rating:");
        System.out.printf("%,.2f\n", getRating());

        //Show the last 3 rating together with comments
        print("Last 3 reviews: ");
        int count = 0;
        if (getRating() != 0) {
            for (Review r : getReviews()) {
                if (count == 3) break;
                String message = (count + 1) + ": Review: " + r.getComment() + " | " + "Rating: " + printStars();
                print(message);
                count++;
            }
        }
        println("");

    }

    /**
     * Print the number of stars for each rating
     *
     * @return String   rating stars
     */
    public String printStars() {
        String s = String.format("%.1f", getRating());
        String x;
        if (getRating() <= 1)
            x = ("??????????????? (" + s + ")");
        else if (getRating() <= 2)
            x = ("??????????????? (" + s + ")");
        else if (getRating() <= 3)
            x = ("??????????????? (" + s + ")");
        else if (getRating() <= 4.9)
            x = ("??????????????? (" + s + ")");
        else
            x = ("??????????????? (" + s + ")");
        return x;
    }

    /**
     * Get list of reviews
     *
     * @return reviews
     */

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    /**
     * Set Review
     *
     * @param reviews
     */
    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * Get the Overall Rating
     *
     * @return Overall Rating
     */

    public double getRating() {
        return rating;
    }

    /**
     * Set Rating
     *
     * @param rating
     */

    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Get the total number of rating
     *
     * @return total number of rating
     */

    public int getRatingTimes() {
        return ratingTimes;
    }


}
