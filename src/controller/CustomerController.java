package controller;

import modal.Admin;
import modal.Customer;
import modal.Enums;
import modal.Movie;

import java.io.*;
import java.util.ArrayList;
import java.util.*;

import static view.utilF.read;

public class CustomerController {
    public final static String FILENAME = "data/customer.txt";

    public CustomerController() {

    }
    // Creates a movie and writes it to movies.txt
    public static void createCustomer(Customer customer) {
        // Creates an ArrayList of movie
        ArrayList<Customer> allData = new ArrayList<Customer>();
        File tempFile = new File(FILENAME);

        // If it exists then read() the existing data
        if (tempFile.exists())
            allData = readAll();
        try {
            // Write the data to the movie
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
            allData.add(customer);
            out.writeObject(allData);
            out.flush();
            out.close();
        } catch (IOException e) {
            // ignore error
        }
    }

    public static ArrayList<Customer> readAll() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
            ArrayList<Customer> customerListing = (ArrayList<Customer>) ois.readObject();
            ois.close();
            return customerListing;
        } catch (ClassNotFoundException | IOException e) {
            // ignore error
        }
        return new ArrayList<Customer>();
    }

    //request user for username and email to verify a user
    public static Customer login() {
        // Login
        Customer customer = null;
        do {
            String username = read("Username: ");
            String password = read("Password: ");
            customer = readByUsername(username);
            if (customer == null) {
                System.out.println("Incorrect username or phone number, please try again.");
            }
            else { //Verfiy the password to get the correct Customer Object
                if (customer.getPassword() == password) {
                    return customer;
                }
            }
        } while (customer == null);
        return customer;
    }

    public static Customer readByUsername(String valueToSearch) {
        ArrayList<Customer> allData = readAll();
        for (int i=0; i<allData.size(); i++){
            Customer c = allData.get(i);
            System.out.println(c.getUsername());
            if (c.getUsername().equals(valueToSearch))
                return c;
        }
        return null;
    }

    /**
     * READ and return an Admin by searching for one with matching email in the Database file
     * @param valueToSearch     Email of admin to search for
     * @return Admin            Return Admin if found, else null object
     */
    public static Customer readByPassword(String valueToSearch) {
        ArrayList<Customer> allData = readAll();
        for (int i=0; i<allData.size(); i++){
            Customer c = allData.get(i);
            if (c.getPassword().equals(valueToSearch))
                return c;
        }
        return null;
    }


}
