package view;
import modal.Customer;
import java.util.ArrayList;
import java.util.Scanner;

/* Functions to help us to process printing/reading inputs */
public class utilF {
    private static Scanner sc = new Scanner(System.in);

    /**
     * Method to notify user about the input String
     * @param message info about input
     * @return validate String otherwise Exception
     */
    public static String read(String message) {
        String input = "";
        System.out.println(message);

        do {
            input = sc.nextLine();
        } while (input.trim().equals(""));

        return input;
    }

    //Method to check the int value
    public static int readIntInput(String Message) {
        System.out.println(Message);
        while (true) {
            try {
                String s = sc.next();
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Please, input a valid decimal number. ");
            }
        }
    }

    /*
     method to notify user the allowable range of seat input
     */
    public static int readSeatInput(String message, int min, int max) {
        int c = 0;
        do {
            if(min == max)
                readIntInput(message + " (" + max + "):");
            else
                c = readIntInput(message + " (" + min + "~" + max + "): ");
            if (!(c >= min && c <= max))
                System.out.println("Please enter valid option. ");
        } while (!(c >= min && c <= max));
        return c;
    }

    //Method function to print the menu
    public static void printMenu(ArrayList<String> choices, int choiceIdFrom) {
        for (String choice : choices) {
            System.out.println(((choiceIdFrom++) + ": " + choice));
            System.out.println();
        }
    }

    /*
     Confirmation Message:
     This method only accept 'Yes' or 'No' as input
     return true  if input is 'Yes' or
     return false if input is 'No'
     Otherwise it will repeatedly prompt user for input
     */
    public static boolean confirm(String message) {
        while (true) {
            String in = read(message + " (Yes/No): ");
            if (in.equalsIgnoreCase("Yes"))
                return true;
            else if (in.equalsIgnoreCase("No"))
                return false;
        }
    }


     //request user for username and email to verify a user
    public static Customer login() {
        // Login
        //Manager manager = Manager.getInstance();
        Customer customer = null;
        do {
            String name = read("Name: ");
            String phone = read("Email: ");
            //customer = manager.getEntry(USER, (User u) -> (u.getName().equals(name)));
            if (customer == null) {
                System.out.println("Incorrect username or phone number, please try again.");
            }

        } while (customer == null);

        return customer;
    }




}
