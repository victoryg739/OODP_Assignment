package model;

import model.Enums.AgeType;
import model.Enums.ClassCinema;
import model.Enums.Day;
import model.Enums.MovieType;

import java.io.Serializable;


public class Ticket implements Serializable {

    private double price;
    //enumaration
    private MovieType movieType; // 3D or blockbuster
    private ClassCinema classCinema; // platinum suites or normal
    private AgeType age; // age of movie goer e.g adult/child
    private Day day; //monday,.. friday to sunday more ex
    private Seat seat; // each ticket is tied to a seat


    public Ticket() {
    }

    public Ticket(double price, MovieType movieType, ClassCinema classCinema, AgeType age, Day day, Seat seat) {
        this.price = price;
        this.movieType = movieType;
        this.classCinema = classCinema;
        this.age = age;
        this.day = day;
        this.seat = seat;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public ClassCinema getClassCinema() {
        return classCinema;
    }

    public void setClassCinema(ClassCinema classCinema) {
        this.classCinema = classCinema;
    }

    public AgeType getAge() {
        return age;
    }

    public void setAge(AgeType age) {
        this.age = age;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }


}
