package modal;
import java.io.Serializable;
import java.util.ArrayList;

public class Cinema implements Serializable {
    private String cinemaNo; //e.g your ticket writes E35 cinema

    private Enums.ClassCinema classCinema;

    private Movie movie;
    private ArrayList <Session> sessions;

    public Cinema(String cinemaNo, Movie movie, Enums.ClassCinema classCinema, ArrayList<Session> sessions) {
        this.cinemaNo = cinemaNo;
        this.classCinema = classCinema;
        this.sessions = sessions;
        this.movie = movie;
    }

    public String getCinemaNo() {
        return cinemaNo;
    }

    public void setCinemaNo(String cinemaNo) {
        this.cinemaNo = cinemaNo;
    }

    public Enums.ClassCinema getClassCinema() {
        return classCinema;
    }

    public void setClassCinema(Enums.ClassCinema classCinema) {
        this.classCinema = classCinema;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }

}
