package view.Customer;

import controller.CustomerController;
import modal.Admin;
import modal.Customer;
import view.MenuBase;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static view.utilF.read;


public class MenuCustomerRegister extends MenuBase {

    public MenuCustomerRegister(MenuBase initialMenu) {
        super(initialMenu);
    }
    private String customerUsername;
    private String password;
    private String password2;
    private String email;
    private boolean consistentPassword = false;
    private String phoneNumber;

    private CustomerController customerCtrl = new CustomerController();

    private static Scanner sc = new Scanner(System.in);

    // Registration Menu to create new accounts
    public MenuBase execute(){
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
                customerUsername = read("Create Email: ");
            }
            phoneNumber = read("Enter Phone Number");

            consistentPassword = password.equals(password2);

            // Creating new customer account object
            Customer customer = new Customer(customerUsername, password, email);


            // Create new Customer account when the 2 input passwords match
            if(consistentPassword){
                customerCtrl.createCustomer(customer);
                System.out.println("You have registered successfully");
            }
            else {
                System.out.println("Password not consistent. Enter again");
            }
        }
        while(!consistentPassword);

        return this.getPreviousMenu();
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


