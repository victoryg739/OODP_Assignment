package modal;

import modal.Enums.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Movie implements Serializable {

    private int id, runtime;
    private String title, synopsis, director, language;

    private MovieType type;
    private MovieRating ContentRating;
    private Date DateStart, DateEnd;

    private ArrayList<String> cast;

    private int ratingTimes;


    private int ticketSales;

    private ShowingStatus showingStatus;

    public Movie(int id,String title, MovieType type, ShowingStatus ss,MovieRating ContentRating, String synopsis, int runtime, Date DateStart, Date DateEnd, String director, ArrayList<String> cast){
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

    public Movie(int ticketSales){
        this.ticketSales = ticketSales;
    }

    public Movie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public MovieType getType() {
        return type;
    }

    public void setType(MovieType type) {
        this.type = type;
    }

    public MovieRating getContentRating() {
        return ContentRating;
    }

    public void setContentRating(MovieRating ContentRating) {
        this.ContentRating = ContentRating;
    }

    public Date getDateStart() {
        return DateStart;
    }

    public void setDateStart(Date dateStart) {
        DateStart = dateStart;
    }

    public Date getDateEnd() {
        return DateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        DateEnd = dateEnd;
    }

    public ArrayList<String> getCast() {
        return cast;
    }

    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    public ShowingStatus getShowingStatus() {
        return showingStatus;
    }

    public void setShowingStatus(ShowingStatus showingStatus) {
        this.showingStatus = showingStatus;
    }

    public int getTicketSales() {
        return ticketSales;
    }

    public void setTicketSales(int ticketSales) {
        this.ticketSales = ticketSales;
    }

    public void addTicketSales(int delta) {
        this.ticketSales += delta;
    }
}
