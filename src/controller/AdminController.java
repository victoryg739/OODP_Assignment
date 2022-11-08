package controller;

import model.Admin;

import java.io.*;
import java.util.*;

/**
 * The main controller class, of the program, controlling the access to database
 * Also, Contains logic for Authentication (Login) and Registration
 *
 * @author Tan Wei Zhong
 * @version 1.0
 * @since 2022-08-11
 */


public class AdminController {

    public final static String FILENAME = "data/admin.txt";

    private String staffUsername;
    private String password;
    private String password2;
    private int role;
    private boolean consistentPassword = false;
    private static Scanner sc = new Scanner(System.in);

    public AdminController() {

    }

    /**
     * Create a new Admin account and add into adminAccounts.txt
     *
     * @param admin admin object
     */
    public void createAdmin(Admin admin) {
        ArrayList<Admin> allData = new ArrayList<Admin>();
        File tempFile = new File(FILENAME);
        // If it exists then read() the existing data
        if (tempFile.exists())
            allData = read();
        try {
            // Write the data to the movie
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
            allData.add(admin);
            out.writeObject(allData);
            out.flush();
            out.close();
        } catch (IOException e) {
            // ignore error
        }

    }

    /**
     * READ and return every Admin in the AdminAccounts.txt
     * If Database file not found, ignore error and return empty list
     *
     * @return Model.{@link Admin}     Return list of Admins if found, else empty list
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<Admin> read() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
            ArrayList<Admin> adminListing = (ArrayList<Admin>) ois.readObject();
            ois.close();
            return adminListing;
        } catch (ClassNotFoundException | IOException e) {
            // ignore error
        }
        return new ArrayList<Admin>();
    }

    /**
     * READ and return an Admin username by searching for one with matching username in the adminAccounts.txt file
     *
     * @param valueToSearch username of admin to search for
     * @return String           Return admin username if found, else null object
     */
    public String readByUsername(String valueToSearch) {
        ArrayList<Admin> allData = read();
        for (int i = 0; i < allData.size(); i++) {
            Admin u = allData.get(i);
            if (u.getUsername().equals(valueToSearch))
                return u.getUsername();
        }
        return null;
    }

    /**
     * READ and return an admin password by searching for one with matching password in the Database file
     *
     * @param valueToSearch password of admin to search for
     * @return Admin            Return password if found, else null object
     */
    public String readByPassword(String valueToSearch) {
        ArrayList<Admin> allData = read();
        for (int i = 0; i < allData.size(); i++) {
            Admin u = allData.get(i);
            if (u.getPassword().equals(valueToSearch))
                return u.getPassword();
        }
        return null;
    }

    /**
     * READ and return an Admin by searching for one with matching adminID in the Database file
     *
     * @param valueToSearch adminID of admin to search for
     * @return Admin            Return adminID if found, else -1
     */
    public int readByAdminID(int valueToSearch) {
        ArrayList<Admin> allData = read();
        for (int i = 0; i < allData.size(); i++) {
            Admin u = allData.get(i);
            if (u.getAdminID() == valueToSearch)
                return u.getAdminID();
        }
        return -1;
    }

    public boolean authenticate(String username, String password) {

        if (username.equals(this.readByUsername(username)) && password.equals(this.readByPassword(password))) {
            return true;
        } else {
            return false;
        }
    }

    public static String read(String message) {
        String input = "";
        System.out.println(message);

        do {
            input = sc.nextLine();
        } while (input.trim().equals(""));

        return input;
    }

    public void adminRegistration() {
        do {

            // Display to get new account Username and Password
            staffUsername = read("Create staffUsername: ");
            password = read("Create Password: ");
            while (!validatePasswordStrength(password)) {
                System.out.println("Please ensure password contains at least 8 characters, 1 Upper case, 1 Lower case, 1 special character ");
                password = read("Create Password: ");
            }
            password2 = read("Re-Enter Password: ");
            consistentPassword = password.equals(password2);

            // Creating new staff account object
            Admin admin = new Admin(staffUsername, password);

            // Create new admin account when the 2 input passwords match
            if (consistentPassword) {
                this.createAdmin(admin);
                System.out.println("You have registered Staff Account successfully");
            } else {
                System.out.println("Password not consistent. Enter again");
            }
        }
        while (!consistentPassword);
    }

    // Function to prompt user to create a strong password
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

}
