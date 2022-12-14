package controller;

import model.Booking;
import model.Constant;
import model.Customer;

import java.io.*;
import java.util.*;

import static view.utilF.read;

/**
 * The main controller class, of the program, controlling the access to DataFile
 * Also, Contains logic for Authentication (Login) and Registration
 *
 * @author Tan Wei Zhong
 * @version 1.0
 * @since 2022-08-11
 */

public class CustomerController {

    private static Scanner sc = new Scanner(System.in);
    private String customerUsername;
    private String password;
    private String password2;
    private String email;
    private boolean consistentPassword = false;
    private String phoneNumber;

    public CustomerController() {

    }

    /**
     * Create a new Customer account and add into customerAccounts.txt
     *
     * @param customer customer object
     */
    public static void createCustomer(Customer customer) {
        // Creates an ArrayList of customer
        ArrayList<Customer> allData = new ArrayList<Customer>();
        File tempFile = new File(Constant.CUSTOMERACCOUNTFILE);

        // If it exists then read() the existing data
        if (tempFile.exists())
            allData = readAll();
        try {
            // Write the data to the movie
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(Constant.CUSTOMERACCOUNTFILE));
            allData.add(customer);
            out.writeObject(allData);
            out.flush();
            out.close();
        } catch (IOException e) {
            // ignore error
        }
    }

    /**
     * Read the database file of Customer
     *
     * @return arraylist of Customer files
     */
    public static ArrayList<Customer> readAll() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Constant.CUSTOMERACCOUNTFILE));
            ArrayList<Customer> customerListing = (ArrayList<Customer>) ois.readObject();
            ois.close();
            return customerListing;
        } catch (ClassNotFoundException | IOException e) {
            // ignore error
        }
        return new ArrayList<Customer>();
    }

    /**
     * READ and return a Customer username by searching for one with matching username in the customerAccounts.txt file
     *
     * @param valueToSearch username of admin to search for
     * @return String           Return Customer username if found, else null object
     */
    public static Customer readByUsername(String valueToSearch) {
        ArrayList<Customer> allData = readAll();
        for (int i = 0; i < allData.size(); i++) {
            Customer c = allData.get(i);
            if (c.getUsername().equals(valueToSearch))
                return c;
        }
        return null;
    }

    /**
     * READ and return a customer password by searching for one with matching password in the customerAccounts.txt file
     *
     * @param valueToSearch password of customer to search for
     * @return String            Return password if found, else null object
     */

    public static Customer readByPassword(String valueToSearch) {
        ArrayList<Customer> allData = readAll();
        for (int i = 0; i < allData.size(); i++) {
            Customer c = allData.get(i);
            if (c.getPassword().equals(valueToSearch))
                return c;
        }
        return null;
    }

    /**
     * Function to prompt user to create a STRONG password
     * which is called by MenuStaffRegister
     *
     * @param password password input from user
     * @return Boolean            Returns true if user input a STRONG password , else false
     */
    public static boolean validatePasswordStrength(String password) {
        // Checking lower alphabet in string
        int n = password.length();
        boolean hasLower = false, hasUpper = false,
                hasDigit = false, specialChar = false;
        Set<Character> set = new HashSet<Character>(Arrays.asList('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+'));
        for (char i : password.toCharArray()) {
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

    /**
     * UPDATE a Customer by updating based on Customer Username
     *
     * @param valueToSearch   username of Customer to search for
     * @param newBooking New Booking of Movie's attribute
     */
    public void CustomerUpdate(Object valueToSearch, Booking newBooking) {
        ArrayList<Customer> customerList = readAll();
        ArrayList<Booking> bookingList = new ArrayList<Booking>();

        for (int j = 0; j < customerList.size(); j++) {
            if (customerList.get(j).getUsername().equals((String) valueToSearch)) {
                if (customerList.get(j).getBookings() != null) {
                    bookingList = customerList.get(j).getBookings(); //old list of session in cinema
                }
                bookingList.add(newBooking);
                customerList.get(j).setBookings(bookingList);
            }
        }

        replace(customerList);
    }

    /**
     * Replace existing file to a new file
     *
     * @param data ArrayList of Customer objects
     */
    public void replace(ArrayList<Customer> data) {
        File tempFile = new File(Constant.CUSTOMERACCOUNTFILE);
        if (tempFile.exists())
            tempFile.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(Constant.CUSTOMERACCOUNTFILE));
            out.writeObject(data);
            out.flush();
            out.close();
        } catch (IOException e) {
            //
        }
    }

    /**
     * Authenticates username and password entered by user with the username and password in the customerAccounts.txt file
     * which is called by menuCustomerLogin
     *
     * @param username username input from user
     * @param password password input from user
     * @return Boolean            Return true if found, else false
     */
    public boolean authenticate(String username, String password) {
        // Case : There is no customer object in the file
        if (readByUsername(username) == null || readByPassword(password) == null) {
            return false;
        } else if (username.equals(readByUsername(username).getUsername()) && password.equals(readByPassword(password).getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Registers a new customer account by taking in the username and password input from the user
     * Validates the password to ensure that it follows the guideline of a STRONG password
     * Ensure password created is what user intended by prompting the user to re-enter password
     * Writes the validated username and password into the customerAccounts.txt file
     */
    public void customerRegistration() {
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
            if (consistentPassword) {
                this.createCustomer(customer);
                System.out.println("You have registered Customer Account successfully");
            } else {
                System.out.println("Password not consistent. Enter again");
            }
        }
        while (!consistentPassword);
    }

    /**
     * Validate if user inputs an email address that follows valid email format
     *
     * @param email email input from user
     * @return Boolean            Returns true if matches email format, else false
     */
    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

}
