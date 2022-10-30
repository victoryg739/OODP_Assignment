package view;
import modal.Constant;
import modal.Customer;
import modal.Enums.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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


    public static MovieRating readMovieRatingInput(String Message) {
        MovieRating movieRating;
        System.out.println(Message);
        while (true) {
            try {
                String s = sc.next();
                int c = Integer.parseInt(s);

                switch(c) {
                    case 1:
                         movieRating = MovieRating.G;
                        break;
                    case 2:
                         movieRating = MovieRating.PG13;
                        break;
                    case 3:
                         movieRating = MovieRating.NC16;
                        break;
                    case 4:
                         movieRating = MovieRating.M18;
                        break;
                    case 5:
                        movieRating = MovieRating.R21;
                        break;
                    default:
                        System.out.println("Default Moving Rating (PG) selected! ");
                        movieRating = MovieRating.G;
                        break;
                }

                return movieRating;
            } catch (NumberFormatException e) {
                System.out.println("Please, input a valid decimal number. ");
            }
        }
    }

    public static MovieType readMovieTypeInput(String Message) {
        MovieType movieType;
        System.out.println(Message);
        while (true) {
            try {
                String s = sc.next();
                int c = Integer.parseInt(s);

                switch(c) {
                    case 1:
                        movieType = MovieType.TWO_D;
                        break;
                    case 2:
                        movieType = MovieType.THREE_D;
                        break;
                    case 3:
                        movieType = MovieType.BLOCKBUSTER;
                        break;
                    default:
                        System.out.println("Default Moving Type (2D) selected! ");
                        movieType = MovieType.TWO_D;
                        break;
                }

                return movieType;
            } catch (NumberFormatException e) {
                System.out.println("Please, input a valid decimal number. ");
            }
        }
    }


    /**
     * This method will only read in a format of the date with label
     * wrapper of readDate(String label, String format)
     * @param label is the message to be printed when asking for input
     * @return Date when a correct format if entered, Otherwise keep prompting
     */
    public static Date readDate(String label) {
        return readDate(label, "");
    }


    public static Date readDate(String label, String format) {
        SimpleDateFormat sdf;

        if (format.isEmpty()) { // default value
            sdf = Constant.dateFormatShort;
            format = Constant.FORMAT_DATE_SHORT;
        } else {
            sdf = new SimpleDateFormat(format);
        }
        sdf.setLenient(false);
        do {
            try {
                String date = read(label + " (" + format + "): ");
                return sdf.parse(date);
            } catch (ParseException ime) {
                System.out.println("Please enter a correct date format");
                //sc.nextLine();
            }
        } while (true);
    }



    /* Tester function */

    public static Date readDate() {
        String label = "01/05/1998";
        String format = Constant.FORMAT_DATE_SHORT;
        SimpleDateFormat sdf;

        if (format.isEmpty()) { // default value
            sdf = Constant.dateFormatShort;
            format = Constant.FORMAT_DATE_SHORT;
        } else {
            sdf = new SimpleDateFormat(format);
        }
        sdf.setLenient(false);
        do {
            try {
                String date = label + " (" + format + "): ";
                //String date = read(label + " (" + format + "): ");
                return sdf.parse(date);
            } catch (ParseException ime) {
                System.out.println("Please enter a correct date format");
                sc.nextLine();
            }
        } while (true);
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
