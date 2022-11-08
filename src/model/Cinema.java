package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a Cinema inside one cineplex
 * A cineplex can multiple cinemas
 *
 * @author Victor Yoong
 * @version 1.0
 * @since 2022-08-11
 */
public class Cinema implements Serializable {
    private String cinemaNo; //e.g your ticket writes E35 cinema

    private Enums.ClassCinema classCinema;

    private ArrayList<Session> sessions;

    private ArrayList<ArrayList<Seat>> seats = new ArrayList<ArrayList<Seat>>();

    /**
     * Creates a new Cinema with multiple sessions
     *
     * @param row         This is the row of the seating plan of the cinema
     * @param col         This is the column of the seating plan of the cinema
     * @param cinemaNo    This is the cinema number for the cinema.
     * @param classCinema This is the class of cinema.
     * @param sessions    This is the Sessions contains inside the cinema
     */
    public Cinema(int row, int col, String cinemaNo, Enums.ClassCinema classCinema, ArrayList<Session> sessions) {
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

        this.cinemaNo = cinemaNo;
        this.classCinema = classCinema;
        this.sessions = sessions;
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
     * Gets the cinema number of the cinema
     *
     * @return cinema number.
     */
    public String getCinemaNo() {
        return cinemaNo;
    }

    public void setCinemaNo(String cinemaNo) {
        this.cinemaNo = cinemaNo;
    }

    /**
     * Gets the cinema class of the cinema
     *
     * @return cinema class.
     */
    public Enums.ClassCinema getClassCinema() {
        return classCinema;
    }


    /**
     * Gets the Sessions of the cinema
     *
     * @return sessions of the cinema.
     */
    public ArrayList<Session> getSessions() {
        return sessions;
    }

    /**
     * Change the sessions of the cinema
     *
     * @param sessions This changes sessions.
     *                 Should be in an arraylist
     */
    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }

}
