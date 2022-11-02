package modal;

import java.util.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;

public class Booking implements Serializable{
    // tid = XXXYYYYMMDDhhmm (Y : year, M : month, D : day, h : hour, m :
    //minutes, XXX : cinema code in letters)
    private  String tid;

    //unique customer email
    private String email;

    //movie title
    private Movie movie;

    // ArrayList of all the tickets for this booking transaction
    private ArrayList<Ticket> ticket;

    //Get the details of the session (Date and Show Time)
    private Session session;

    //CinemaNo
    private String cinemaNo;

    //total price for the entire booking transaction
    private double totalPrice;

    //Constructor
    public Booking (String cinemaNo, String tid, String email, String password, Movie movie,
                    ArrayList<Ticket> ticket, Session session, double totalPrice) {
        this.tid = tid;
        this.cinemaNo = cinemaNo;
        this.email = email;
        this.movie = movie;
        this.ticket = ticket;
        this.session = session;
        this.totalPrice = totalPrice;
    }

    public String getTID() {
        return tid;
    }

    public void setTID(String tid) {
        this.tid = tid;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public ArrayList<Ticket> getTicket() {
        return ticket;
    }

    public void setTicket(ArrayList<Ticket> ticket) {
        this.ticket = ticket;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
