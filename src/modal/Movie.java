package modal;

import modal.Enums.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Movie implements Serializable {

    private int id;
    private int runtime;
    private String title;
    private String synopsis;
    private String director;
    private String language;
    private MovieType type;
    private MovieRating ContentRating;
    private Date DateStart, DateEnd;
    private ArrayList<String> cast;
    private double ticketSales;
    //Store the overall stars for rating
    private double rating;
    //Store the number of ratings
    private int ratingTimes;
    private ArrayList<Review> reviews;


    public Movie(int id,String title, MovieType type, MovieRating ContentRating, String synopsis, int runtime, Date DateStart, Date DateEnd, String director, ArrayList<String> cast){
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
    }

    public Movie() {
    }

    public void setID(int id){
        this.id = id;
    }

    public int getID(){
        return this.id;
    }
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public MovieType getType() {
        return this.type;
    }

    public void setType(MovieType type){
        this.type =type;
    }
    public MovieRating getContentRating(){
        return this.ContentRating;
    }

    public void setContentRating(MovieRating ContentRating){
        this.ContentRating =ContentRating;
    }

    public String getSynopsis(){
        return this.synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int runtime(){
        return this.runtime;
    }


    public Date getDateStart(){
        return this.DateStart;
    }

    public Date getDateEnd(){
        return this.DateEnd;
    }
    public ArrayList<String> getCast(){
        return this.cast;
    }

    public void setCast(ArrayList<String> cast){
        this.cast = cast;
    }

    public String getDirector(){
        return this.director;
    }

    public void setDirector(String director){
        this.director = director;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }
//    public void setSynopsis(String synopsis) {
//        this.synopsis = synopsis;
//    }
    public void setDateStart(Date dateStart){
        this.DateStart = dateStart;
    }

    public void setDateEnd(Date dateEnd){
        this.DateEnd = dateEnd;
    }

    public double getTicketSales() {
        return ticketSales;
    }

    public void setTicketSales(double ticketSales) {
        this.ticketSales = ticketSales;
    }

    public void addTicketSales(double totalPrice) {
        this.ticketSales += totalPrice;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void addRating(double CustomerRating) {
        this.rating += CustomerRating;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        if (this.reviews == null) {
            ArrayList<Review> r = new ArrayList<Review>();
            this.setReviews(r);
        }
        reviews.add(review);
        double r = (this.rating * ratingTimes + review.getRating()) / (ratingTimes+1);
        setRating(r);
        addRatingTimes(1);
    }

    public void setRatingTimes(int ratingTimes) {
        this.ratingTimes = ratingTimes;
    }
    public void addRatingTimes(int delta) {
        this.ratingTimes += delta;
    }

    public void removeReview(Review review) {
        for (Review rev : this.reviews) {
            if (this.reviews.equals(review)) {
                this.reviews.remove(review);
            }
        }
    }
}
