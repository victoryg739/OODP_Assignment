package modal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

//this session is tied to which movie is showing at which timing
// one session is tag to one cinema and one movie
public class Session implements Serializable {


    private Movie movie; // which movie the session is tied to
    private Cinema cinema;
    private int sessionId;
    private Date datetimeFormat;

    private Enums.Day day;
    private ArrayList<Seat> seat;

    public Session(Cinema cinema, Movie movie, int sessionId, Date datetimeFormat,Enums.Day day , ArrayList<Seat> seat) {
        this.movie = movie;
        this.sessionId = sessionId;
        this.datetimeFormat = datetimeFormat;
        this.seat = seat;
        this.day = day;
        this.cinema = cinema;
    }

    public Cinema getCinema (){
        return cinema;
    }
    public void setCinema(Cinema cinema){
        this.cinema = cinema;
    }
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public Date getDatetimeFormat() {
        return datetimeFormat;
    }

    public void setDatetimeFormat(Date datetimeFormat) {
        this.datetimeFormat = datetimeFormat;
    }

    public ArrayList<Seat> getSeat() {
        return seat;
    }

    public void setSeat(ArrayList<Seat> seat) {
        this.seat = seat;
    }

    public Enums.Day getDay() {
        return day;
    }

    public void setDay(Enums.Day day) {
        this.day = day;
    }


}
