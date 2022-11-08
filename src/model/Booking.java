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

    /**
     * Booking CLass constructor
     * @param cinemaNo
     * @param cineplexLocation
     * @param tid unique tid
     * @param username
     * @param phoneNumber
     * @param movie
     * @param tickets tickets along with booking
     * @param session
     * @param totalPrice totalprice for all tickets
     */
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
    /**
     * Get Ticket ID
     * @return TID of booking
     */
    public String getTID() {
        return tid;
    }
    /**
     * Get Movie Object
     * @return Movie Object
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * Set Movie Object
     * @param movie
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * Get list of Tickets
     * @return ArrayList<Ticket>
     */
    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    /**
     * Get Session Object
     * @return Session Object
     */
    public Session getSession() {
        return session;
    }

    /**
     * Set Session Object
     * @param session
     */
    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * Get total price of the booking transaction
     * @return totalPrice
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Get Customer ID
     * @return customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Get Customer Username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Print Booking Summary
     */
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

    /**
     * Print Email Booking Summary
     * @return String of the summary
     */
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
