package view.admin;

import controller.AdminController;
import modal.Admin;
import view.MenuBase;
import java.util.Scanner;
import static view.utilF.read;

import java.io.*;
import java.util.*;


public class MenuStaffRegister extends MenuBase {

    public MenuStaffRegister(MenuBase initialMenu) {
        super(initialMenu);
    }
    private String staffUsername;
    private String password;
    private String password2;
    private boolean consistentPassword = false;
    private AdminController adminsCtrl = new AdminController();

    private static Scanner sc = new Scanner(System.in);

    // Function to prompt user to create a strong password
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

    // Registration Menu to create new accounts
    public MenuBase execute(){
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
            if(consistentPassword){
                adminsCtrl.createAdmin(admin);
                System.out.println("You have registered Staff Account successfully");
            }
            else {
                System.out.println("Password not consistent. Enter again");
            }
        }
        while(!consistentPassword);

        return this.getPreviousMenu();
    }
}

