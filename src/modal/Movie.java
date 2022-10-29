package modal;

import modal.Enums.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Movie implements Serializable {

    private int id, runtime;
    private String title, synopsis, director, language;
    private MovieType type;
    private MovieRating rating;
    private Date DateStart, DateEnd;

    private ArrayList<String> cast;


    public Movie(int id,String title, MovieType type, MovieRating rating, String synopsis, int runtime, Date DateStart, Date DateEnd, String director, ArrayList<String> cast){
        this.id = id;
        this.title = title;
        this.type = type;
        this.rating = rating;
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
    public MovieRating getRating(){
        return this.rating;
    }

    public void setRating(MovieRating rating){
        this.rating =rating;
    }

    public String getSynopsis(){
        return this.synopsis;
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
}
