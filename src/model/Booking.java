package model;

import java.io.Serializable;
import java.util.ArrayList;

import static view.utilF.print;
import static view.utilF.println;

public class Booking implements Serializable {
    // tid = XXXYYYYMMDDhhmm (Y : year, M : month, D : day, h : hour, m :
    //minutes, XXX : cinema code in letters)
    private String tid;

    //movie title
    private Movie movie;

    // ArrayList of all the tickets for this booking transaction
    private ArrayList<Ticket> tickets;

    //Get the details of the session (Date and Show Time)
    private Session session;

    //CinemaNo
    private String cinemaNo;

    //total price for the entire booking transaction
    private double totalPrice;

    //CustomerId
    private int customerId;

    //username
    private String username;

    //cineplex Location
    private String cineplexLocation;

    private String phoneNumber;

    //Constructor
    public Booking(String cinemaNo, String cineplexLocation, String tid, String username, String phoneNumber, Movie movie,
                   ArrayList<Ticket> tickets, Session session, double totalPrice) {
        this.tid = tid;
        this.cinemaNo = cinemaNo;
        this.movie = movie;
        this.tickets = tickets;
        this.session = session;
        this.totalPrice = totalPrice;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.cineplexLocation = cineplexLocation;
    }

    public String getTID() {
        return tid;
    }

    public void setTID(String tid) {
        this.tid = tid;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getUsername() {
        return username;
    }

    public String getCineplexLocation() {
        return cineplexLocation;
    }

    public void setCineplexLocation(String cineplexLocation) {
        this.cineplexLocation = cineplexLocation;
    }


    public void printBookingSummary() {
        int seatCount = 1;
        println("Booking Summary: ");
        print("tid: " + this.tid + "\n" +
                "Movie: " + this.movie.getTitle() + "\n" +
                "Cineplex: " + this.cineplexLocation + "\n" +
                "Number of tickets purchased: " + this.tickets.size() + "\n" +
                "Total Price: " + this.totalPrice + "\n" +
                "Seats: ");
        for (Ticket ticket : this.tickets) {
            print(seatCount + ") Row: " + (ticket.getSeat().getRow() + 1) + " Col: " + (ticket.getSeat().getCol() + 1));
            seatCount++;
        }
    }

    public String printEmailBookingSummary() {
        return "<h3> Dear " + username + ", </h3> <br>" +
                "Thank you for using Moblima <br>" +
                "For more information, please view the details below <br><br> " +
                "<p>" +
                "Transaction ID:  " + tid + "<br>" +
                "Movie Name: " + movie.getTitle() + "<br>" +
                "Cineplex: " + cineplexLocation + "<br>" +
                "Total Price: " + totalPrice +
                "</p>" +
                "Hope to see you again! <br>" +
                "Moblima";

    }
}
