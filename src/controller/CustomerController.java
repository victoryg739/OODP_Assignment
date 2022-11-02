package controller;

import modal.Customer;
import modal.Enums;
import modal.Movie;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

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
            allData = read();
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

    public static ArrayList<Customer> read() {
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

}
