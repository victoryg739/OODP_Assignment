package modal;
import java.io.Serializable;
import java.util.ArrayList;

public class Cinema implements Serializable {
    private String cinemaNo; //e.g your ticket writes E35 cinema
    private Movie movie;
    private Enums.ClassCinema classCinema;
    private ArrayList <Session> sessions;

    public Cinema(String cinemaNo, Movie movie, Enums.ClassCinema classCinema, ArrayList<Session> sessions) {
        this.cinemaNo = cinemaNo;
        this.movie = movie;
        this.classCinema = classCinema;
        this.sessions = sessions;
    }

    public String getCinemaNo() {
        return cinemaNo;
    }

    public void setCinemaNo(String cinemaNo) {
        this.cinemaNo = cinemaNo;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Enums.ClassCinema getClassCinema() {
        return classCinema;
    }

    public void setClassCinema(Enums.ClassCinema classCinema) {
        this.classCinema = classCinema;
    }

    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }


}
