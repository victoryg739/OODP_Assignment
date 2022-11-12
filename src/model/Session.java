package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Represents a session tied to a movie
 *
 * @author Victor Yoong
 * @version 1.0
 * @since 2022-08-11
 */
//this session is tied to which movie is showing at which timing
// one session is tag to one cinema and one movie
public class Session implements Serializable {


    private Movie movie; // which movie the session is tied to
    private Cinema cinema;
    private int sessionId;
    private Date dateTime;

    private Enums.Day day;

    private ArrayList<ArrayList<Seat>> seats = new ArrayList<ArrayList<Seat>>();

    public Session() {


    }

    /**
     * Creates a new Cinema with multiple sessions
     *
     * @param row       This is the row of the seating plan of the cinema
     * @param col       This is the column of the seating plan of the cinema
     * @param cinema    This is the cinema object that contains cinema information
     * @param movie     This is the movie object that contains movie information.
     * @param sessionId This is the unique sessionId
     * @param dateTime  This is the datetime
     * @param day       This is the day in a range
     */
    public Session(int row, int col, Cinema cinema, Movie movie, int sessionId, Date dateTime, Enums.Day day) {
        for (int i = 0; i < row; i++) {
            this.seats.add(new ArrayList<Seat>());
            for (int j = 0; j < col; j++) {
                Seat tmpSeat = new Seat();
                if (i < 4 && j < 2) {
                    tmpSeat = new Seat(j, i, false, true, false);

                } else if (i > row - 2) {
                    tmpSeat = new Seat(j, i, false, false, true);
                } else {
                    tmpSeat = new Seat(j, i, false, false, false);

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

    /**
     * Gets the seatingplan
     *
     * @return seatingplan got seats.
     */
    public ArrayList<ArrayList<Seat>> getSeats() {
        return seats;
    }


    /**
     * Gets the cinema object
     *
     * @return cinema object.
     */
    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    /**
     * Gets the movie object
     *
     * @return movie object.
     */
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * Gets the session id
     *
     * @return session id.
     */
    public int getSessionId() {
        return sessionId;
    }

    /**
     * Gets the date time
     *
     * @return dateTime.
     */
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * Change the datetime of the session
     *
     * @param dateTime This changes the datetime
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Gets the enums date range
     *
     * @return enums date range.
     */
    public Enums.Day getDay() {
        return day;
    }

    /**
     * Change the day of the session
     *
     * @param day This changes the day
     */
    public void setDay(Enums.Day day) {
        this.day = day;
    }

    /**
     * Change the seating plan of the session
     *
     * @param seatList This changes seating plan
     */
    public void setSeatPlan(ArrayList<ArrayList<Seat>> seatList) {
        this.seats = seatList;
    }


}
