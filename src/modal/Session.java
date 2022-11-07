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
    private Date dateTime;

    private Enums.Day day;

    private ArrayList<ArrayList<Seat>> seats = new ArrayList<ArrayList<Seat>>();

    public Session(){


    }
    public Session(int row, int col , Cinema cinema, Movie movie, int sessionId, Date dateTime, Enums.Day day) {
        for(int i=0;i<row;i++)
        {
            this.seats.add(new ArrayList<Seat>());
            for(int j=0;j<col;j++)
            {
                Seat tmpSeat = new Seat();
                if(i<4 && j<2){
                    tmpSeat = new Seat(j,i,false,true,false);

                }else if(i>row-2) {
                    tmpSeat = new Seat(j, i, false, false, true);
                }else{
                    tmpSeat = new Seat(j,i,false,false,false);

                }

                this.seats.get(i).add(tmpSeat);
            }
        }
        this.movie = movie;
        this.sessionId = sessionId;
        this.dateTime = dateTime;
        this.day = day;
        this.cinema = cinema;
    }
    public ArrayList<ArrayList<Seat>> getSeats() {
        return seats;
    }

    public void setSeats(int row, int col) {
        for(int i=1;i<=row;i++)
        {
            for(int j=1;j<=col;j++)
            {
                Seat tmpseat = new Seat(j,i,false,false,false);
                this.seats.get(i).add(tmpseat);
            }
        }
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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Enums.Day getDay() {
        return day;
    }

    public void setDay(Enums.Day day) {
        this.day = day;
    }

    public void setSeatPlan(ArrayList<ArrayList<Seat>> seatList) {this.seats = seatList;}


}
