package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a Customer Object
 * 1 Customer
 *
 * @author Aloysius Tan
 * @version 1.0
 * @since 2022-08-11
 */
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
     * @param username username of Customer
     * @param email email of Customer
     * @param password password of Customer
     * @param phoneNumber phone number of Customer
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
     * @param username username of customer
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
     * @@param bookings arraylist of bookings of customer
     */
    public void setBookings(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }

    /**
     * Set Customer ID
     *
     * @param customerID customerId of customer
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