package view;

import java.util.Scanner;

/* Functions to help us to process printing/reading inputs */
public class utilF {
    private static Scanner sc = new Scanner(System.in);
    public static void printMovieTitle(String movieTitle){
        System.out.println("Movie is " + movieTitle);
    }
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

}
