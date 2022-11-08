package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable, User {
    private String username;
    private String password;


    private String email;

    private int customerID;
    private ArrayList<Booking> bookings;

    private String phoneNumber;

    /**
     * Customer CLass constructor
     *
     * @param username
     * @param email
     * @param password
     * @param phoneNumber
     */

    public Customer(String username, String password, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.bookings = new ArrayList<>();
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get Customer username
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set Customer username
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get Customer password
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get booking ArrayList
     *
     * @return booking ArrayList
     */
    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    /**
     * Set booking ArrayList
     *
     * @@param bookings
     */
    public void setBookings(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }

    /**
     * Set Customer ID
     *
     * @param customerID
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * Add Booking Object to the Customer's Arraylist of objects
     *
     * @param new_bookings
     */
    public void addBookings(Booking new_bookings) {
        if (bookings == null) {
            this.bookings = new ArrayList<>();
            this.bookings.add(new_bookings);
        } else {
            this.bookings.add(new_bookings);
        }
    }

    /**
     * Get Customer email
     *
     * @return email
     */

    public String getEmail() {
        return email;
    }

    /**
     * Get Customer Phone Number
     *
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
}