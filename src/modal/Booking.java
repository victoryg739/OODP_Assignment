package modal;

import java.util.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;

public class Booking implements Serializable{
    // tid = XXXYYYYMMDDhhmm (Y : year, M : month, D : day, h : hour, m :
    //minutes, XXX : cinema code in letters)
    private  String tid;

    //unique customerId
    private String customerId;

    //unique customer email
    private String email;

    //unique phone number
    private String phoneNumber;

    //movie title
    private Movie movie;

    // ArrayList of all the tickets for this booking transaction
    private ArrayList<Ticket> ticket;

    //Get the details of the session (Date and Show Time)
    private Session session;

    //total price for the entire booking transaction
    private double totalPrice;

    //Constructor
    public Booking (String cinemaNo, String customerId, String email, String phoneNumber, Movie movie,
                    ArrayList<Ticket> ticket, Session session, double totalPrice) {
        SimpleDateFormat bookingFormat = new SimpleDateFormat(Constant.FORMAT_BOOKING_ID);
        this.tid = cinemaNo.concat(String.valueOf(bookingFormat));
        this.customerId = customerId;
        this.email = email;
        this.phoneNumber = phoneNumber;
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
