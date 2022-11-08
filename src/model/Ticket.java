package model;

import model.Enums.AgeType;
import model.Enums.ClassCinema;
import model.Enums.Day;
import model.Enums.MovieType;

import java.io.Serializable;


public class Ticket implements Serializable {

    private double price;
    private MovieType movieType; // 3D or blockbuster
    private ClassCinema classCinema; // platinum suites or normal
    private AgeType age; // age of movie goer e.g adult/child
    private Day day; //monday,.. friday to sunday more ex
    private Seat seat; // each ticket is tied to a seat


    public Ticket() {
    }

    /**
     * Booking CLass constructor
     * @param age
     * @param day
     * @param classCinema
     * @param price
     * @param seat
     * @param movieType
     */
    public Ticket(double price, MovieType movieType, ClassCinema classCinema, AgeType age, Day day, Seat seat) {
        this.price = price;
        this.movieType = movieType;
        this.classCinema = classCinema;
        this.age = age;
        this.day = day;
        this.seat = seat;
    }

    /**
     * Get Day
     * @return Day
     */
    public Day getDay() {
        return day;
    }

    /**
     * Set Day
     * @param day
     */
    public void setDay(Day day) {
        this.day = day;
    }

    /**
     * Get Seat
     * @return seat
     */
    public Seat getSeat() {
        return seat;
    }

}
