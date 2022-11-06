package controller;

import modal.*;

import java.io.*;
import java.util.ArrayList;
import java.util.*;

import static view.utilF.read;

public class CustomerController {
    public final static String FILENAME = "data/customer.txt";

    public CustomerController() {

    }
    // Creates a movie and writes it to customer.txt
    public static void createCustomer(Customer customer) {
        // Creates an ArrayList of customer
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

    public void CustomerUpdate(Object valueToSearch,Booking newBooking) {
        ArrayList<Customer> customerList = readAll();
        ArrayList<Booking> bookingList = new ArrayList<Booking>();

        for (int j=0; j<customerList.size(); j++) {
            if (customerList.get(j).getUsername().equals((String) valueToSearch)) {
                if(customerList.get(j).getBookings()  != null) {
                    bookingList = customerList.get(j).getBookings(); //old list of session in cinema
                }
                bookingList.add(newBooking);
                customerList.get(j).setBookings(bookingList);
            }
        }
        replace(customerList);

    }

    public void replace(ArrayList<Customer> data) {
        File tempFile = new File(FILENAME);
        if (tempFile.exists())
            tempFile.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
            out.writeObject(data);
            out.flush();
            out.close();
        } catch (IOException e) {
            //
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

    public int readByCustomerID(int valueToSearch) {
        ArrayList<Customer> allData = readAll();
        for (int i=0; i<allData.size(); i++){
            Customer u = allData.get(i);
            if (u.getCustomerID() == valueToSearch )
                return u.getCustomerID();
        }
        return -1;
    }

    public static ArrayList<Booking> retrieveByUsername(String valueToSearch) {
        ArrayList<Customer> allData = readAll();
        ArrayList<Booking> returnData = new ArrayList<>();
        for (int i=0; i<allData.size(); i++){
            Customer c = allData.get(i);
            //compare the customer object with the one that login
            if (c.getUsername().equals(valueToSearch)) { //if found correct
                returnData = c.getBookings();
                return returnData;
            }

        }
        return null;
    }

    public boolean authenticate(String username, String password) {
        if (username.equals(readByUsername(username).getUsername()) && password.equals(readByPassword(password).getPassword())) {
            return true;
        } else {
            return false;
        }
    }


}
