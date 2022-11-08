package controller;

import modal.*;

import java.io.*;
import java.util.ArrayList;
import java.util.*;

import static view.utilF.read;

public class CustomerController {
    public final static String FILENAME = "data/customer.txt";

    private String customerUsername;
    private String password;
    private String password2;
    private String email;
    private boolean consistentPassword = false;
    private String phoneNumber;

    private static Scanner sc = new Scanner(System.in);

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
            ArrayList<Booking> tempList = customerList.get(j).getBookings();
            for (int i = 0; i < tempList.size(); i ++) {
                System.out.println(tempList.get(i));
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

    public Customer readByCustomerID(int valueToSearch) {
        ArrayList<Customer> allData = readAll();
        for (int i=0; i<allData.size(); i++){
            Customer u = allData.get(i);
            if (u.getCustomerID() == valueToSearch )
                return u;
        }
        return null;
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
        // Case : There is no customer object in the file
        if (readByUsername(username) == null || readByPassword(password) == null ){
            return false;
        }
        else if (username.equals(readByUsername(username).getUsername()) && password.equals(readByPassword(password).getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    public void customerRegistration(){
        do {

            customerUsername = read("Create customerUsername: ");

            password = read("Create Password: ");
            while (!validatePasswordStrength(password)) {
                System.out.println("Please ensure password contains at least 8 characters, 1 Upper case, 1 Lower case, 1 special character ");
                password = read("Create Password: ");
            }
            password2 = read("Re-Enter Password: ");


            email = read("Enter email");
            while (!isValidEmailAddress(email)) {
                System.out.println("Please ensure you enter a valid Email");
                email = read("Create Email: ");
            }

            phoneNumber = read("Enter Phone Number");

            consistentPassword = password.equals(password2);

            // Creating new customer account object
            Customer customer = new Customer(customerUsername, password, email, phoneNumber);


            // Create new Customer account when the 2 input passwords match
            if(consistentPassword){
                this.createCustomer(customer);
                System.out.println("You have registered successfully");
            }
            else {
                System.out.println("Password not consistent. Enter again");
            }
        }
        while(!consistentPassword);
    }

    public static boolean validatePasswordStrength(String password) {
        // Checking lower alphabet in string
        int n = password.length();
        boolean hasLower = false, hasUpper = false,
                hasDigit = false, specialChar = false;
        Set<Character> set = new HashSet<Character>(Arrays.asList('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+'));
        for (char i : password.toCharArray())
        {
            if (Character.isLowerCase(i))
                hasLower = true;
            if (Character.isUpperCase(i))
                hasUpper = true;
            if (Character.isDigit(i))
                hasDigit = true;
            if (set.contains(i))
                specialChar = true;
        }

        // Checking Validity of password
        if (hasDigit && hasLower && hasUpper && specialChar && (n >= 8))
            return true;
        else
            return false;
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }


}
