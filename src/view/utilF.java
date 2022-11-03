package view;
import modal.Constant;
import modal.Customer;
import modal.Enums;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/* Functions to help us to process printing/reading inputs */
@SuppressWarnings("deprecation")
public class utilF {
    private static Scanner sc = new Scanner(System.in);
    public static int SCREEN_WIDTH = 80;
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


    public static Date readDateTime(String msg) {
        SimpleDateFormat sdf;

        sdf = Constant.datetimeFormat;

        do {
            try {
                String date = read(msg + " (" + sdf.toPattern()+ "): ");
                return sdf.parse(date);
            } catch (ParseException ime) {
                System.out.println("Please enter a correct date format");
                sc.nextLine();
            }
        } while (true);
    }

    public static Enums.Day returnEnumsDay(Date date){
        System.out.println("dayofweek" + date.getDay());
        System.out.println("timeofday" + date.getHours());
        int dayOfWeek = date.getDay();
        int hours  = date.getHours();
        if(dayOfWeek >= 1 && dayOfWeek <= 3){
            if(hours < 18 ) {
                return Enums.Day.MON_WED_BEF_SIX;
            }else{
                return Enums.Day.MON_WED_AFT_SIX;
            }
        }else if(dayOfWeek == 4){
            if(hours < 18 ) {
                return Enums.Day.THU_BEF_SIX;
            }else{
                return Enums.Day.THU_AFT_SIX;
            }
        }else if(dayOfWeek == 5){
            if(hours < 18 ) {
                return Enums.Day.FRI_BEF_SIX;
            }else{
                return Enums.Day.FRI_AFT_SIX;
            }
        }else{
            return Enums.Day.SAT_SUN;
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

    /**
     * This method will only read in a format of the date with label
     *
     * @param label is the message to be printed when asking for input
     * @param format format of date datatype refer to Constant
     * @return Date when a correct format if entered, Otherwise keep prompting
     */
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
                sc.nextLine();
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

    public static Enums.ShowingStatus readShowingStatus(String message){
        Enums.ShowingStatus ss;
        System.out.println(message);

        while (true) {
            try {
                String s = sc.next();
                int c = Integer.parseInt(s);
                switch(c) {
                    case 1:
                        ss = Enums.ShowingStatus.COMING_SOON;
                        break;
                    case 2:
                        ss = Enums.ShowingStatus.PREVIEW;
                        break;
                    case 3:
                        ss = Enums.ShowingStatus.NOW_SHOWING;
                        break;
                    case 4:
                        ss = Enums.ShowingStatus.END_SHOWING;
                        break;
                    default:
                        System.out.println("Default Showing Status selected! ");
                        ss = Enums.ShowingStatus.NOW_SHOWING;
                        break;
                }

                return ss;
            } catch (NumberFormatException e) {
                System.out.println("Please, input a valid decimal number. ");
            }
        }

    }
    public static Enums.MovieRating readMovieRatingInput(String Message) {
        Enums.MovieRating movieRating;
        System.out.println(Message);
        while (true) {
            try {
                String s = sc.next();
                int c = Integer.parseInt(s);

                switch(c) {
                    case 1:
                        movieRating = Enums.MovieRating.G;
                        break;
                    case 2:
                        movieRating = Enums.MovieRating.PG13;
                        break;
                    case 3:
                        movieRating = Enums.MovieRating.NC16;
                        break;
                    case 4:
                        movieRating = Enums.MovieRating.M18;
                        break;
                    case 5:
                        movieRating = Enums.MovieRating.R21;
                        break;
                    default:
                        System.out.println("Default Moving Rating (PG) selected! ");
                        movieRating = Enums.MovieRating.G;
                        break;
                }

                return movieRating;
            } catch (NumberFormatException e) {
                System.out.println("Please, input a valid decimal number. ");
            }
        }
    }

    public static Enums.MovieType readMovieTypeInput(String Message) {
        Enums.MovieType movieType;
        System.out.println(Message);
        while (true) {
            try {
                String s = sc.next();
                int c = Integer.parseInt(s);

                switch(c) {
                    case 1:
                        movieType = Enums.MovieType.TWO_D;
                        break;
                    case 2:
                        movieType = Enums.MovieType.THREE_D;
                        break;
                    case 3:
                        movieType = Enums.MovieType.BLOCKBUSTER;
                        break;
                    default:
                        System.out.println("Default Moving Type (2D) selected! ");
                        movieType = Enums.MovieType.TWO_D;
                        break;
                }

                return movieType;
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

    public static int readReviewInput(String message, int min, int max) {
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

    public static void printHeader(String message){
        System.out.println("==================== " + message+ " =====================\n");
    }

    public static void print(String message){
        System.out.println(message);
    }

    public static void println(String message){
        System.out.println(message+"\n");
    }












}
