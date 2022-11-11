package view;

import model.Constant;
import model.Enums;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/* Functions to help us to process printing/reading inputs */
@SuppressWarnings("deprecation")
public class utilF {
    private static Scanner sc = new Scanner(System.in);

    /**
     * Method to notify user about the input String
     *
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

    /**
     * Method to notify user about the input Int
     *
     * @param Message info about input
     * @return validate Int otherwise Exception
     */
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
    /**
     * Method to notify user about the input String for DateTime
     *
     * @param msg info about input
     * @return validate String otherwise Exception
     */

    public static Date readDateTime(String msg) {
        SimpleDateFormat sdf;
        sdf = Constant.datetimeFormat;
        do {
            try {
                String date = read(msg + " (" + sdf.toPattern() + "): ");
                return sdf.parse(date);
            } catch (ParseException ime) {
                System.out.println("Please enter the correct date format!");
            }
        } while (true);
    }
    /**
     * Method to return the Enum.Day class based on the date input
     *
     * @param date
     * @return Enums.Day class
     */
    public static Enums.Day returnEnumsDay(Date date) {
        int dayOfWeek = date.getDay();
        int hours = date.getHours();
        if (dayOfWeek >= 1 && dayOfWeek <= 3) {
            if (hours < 18) {
                return Enums.Day.MON_WED_BEF_SIX;
            } else {
                return Enums.Day.MON_WED_AFT_SIX;
            }
        } else if (dayOfWeek == 4) {
            if (hours < 18) {
                return Enums.Day.THU_BEF_SIX;
            } else {
                return Enums.Day.THU_AFT_SIX;
            }
        } else if (dayOfWeek == 5) {
            if (hours < 18) {
                return Enums.Day.FRI_BEF_SIX;
            } else {
                return Enums.Day.FRI_AFT_SIX;
            }
        } else {
            return Enums.Day.SAT_SUN;
        }
    }


    /**
     * This method will only read in a format of the date with label
     * wrapper of readDate(String label, String format)
     *
     * @param label is the message to be printed when asking for input
     * @return Date when a correct format if entered, Otherwise keep prompting
     */
    public static Date readDate(String label) {
        return readDate(label, "");
    }

    /**
     * This method will only read in a format of the date with label
     *
     * @param label  is the message to be printed when asking for input
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
            }
        } while (true);
    }

    /**
     * Method to notify user about the input Date
     *
     * @return validate Date Object
     */
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
                return sdf.parse(date);
            } catch (ParseException ime) {
                System.out.println("Please enter a correct date format");
                sc.nextLine();
            }
        } while (true);
    }

    /**
     * Method to notify user about the input Int to return the correct showing status
     *
     * @param message info about input
     * @return validate Enum.ShowingStatus class otherwise Exception
     */
    public static Enums.ShowingStatus readShowingStatus(String message) {
        Enums.ShowingStatus ss;
        System.out.println(message);

        while (true) {
            try {
                String s = sc.next();
                int c = Integer.parseInt(s);
                switch (c) {
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
    /**
     * Method to notify user about the input Int to return the correct Enum.MovieRating class
     *
     * @param Message info about input
     * @return Enum.MovieRating class otherwise Exception
     */
    public static Enums.MovieRating readMovieRatingInput(String Message) {
        Enums.MovieRating movieRating;
        System.out.println(Message);
        while (true) {
            try {
                String s = sc.next();
                int c = Integer.parseInt(s);
                switch (c) {
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
    /**
     * Method to notify user about the input Int to return the correct Enum.MovieType class
     *
     * @param Message info about input
     * @return Enum.MovieType class otherwise Exception
     */
    public static Enums.MovieType readMovieTypeInput(String Message) {
        Enums.MovieType movieType;
        System.out.println(Message);
        while (true) {
            try {
                String s = sc.next();
                int c = Integer.parseInt(s);

                switch (c) {
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
    /**
     * Method to notify user about the input Int to return the correct Enum.Day class
     *
     * @param Message info about input
     * @return Enum.MovieDay class otherwise Exception
     */
    public static Enums.Day readDay(String Message) {
        Enums.Day day;
        System.out.println(Message);
        while (true) {
            try {
                String s = sc.next();
                int c = Integer.parseInt(s);
                switch (c) {
                    case 1:
                        day = Enums.Day.MON_WED_BEF_SIX;
                        break;
                    case 2:
                        day = Enums.Day.MON_WED_AFT_SIX;
                        break;
                    case 3:
                        day = Enums.Day.THU_BEF_SIX;
                        break;
                    case 4:
                        day = Enums.Day.THU_AFT_SIX;
                        break;
                    case 5:
                        day = Enums.Day.FRI_BEF_SIX;
                        break;
                    case 6:
                        day = Enums.Day.FRI_AFT_SIX;
                        break;
                    case 7:
                        day = Enums.Day.SAT_SUN;
                        break;
                    default:
                        System.out.println("Default Saturday and Sunday is selected! ");
                        day = Enums.Day.SAT_SUN;
                        break;
                }

                return day;
            } catch (NumberFormatException e) {
                System.out.println("Please, input a valid decimal number. ");
            }
        }
    }
    /**
     * Method to notify user about the input Int to return the correct Enum.ClassCinema class
     *
     * @param Message info about input
     * @return Enum.ClassCinema class otherwise Exception
     */
    public static Enums.ClassCinema readClassCinema(String Message) {
        Enums.ClassCinema classCinema;
        System.out.println(Message);
        while (true) {
            try {
                String s = sc.next();
                int c = Integer.parseInt(s);

                switch (c) {
                    case 1:
                        classCinema = Enums.ClassCinema.PLATINUM;
                        break;
                    case 2:
                        classCinema = Enums.ClassCinema.NORMAL;
                        break;
                    default:
                        System.out.println("Default class cinema normal selected! ");
                        classCinema = Enums.ClassCinema.NORMAL;
                        break;

                }


                return classCinema;
            } catch (NumberFormatException e) {
                System.out.println("Please, input a valid decimal number. ");
            }
        }
    }
    /**
     * Method to notify user about the input Int to return the correct Enum.AgeType class
     *
     * @param Message info about input
     * @return Enum.AgeType class otherwise Exception
     */
    public static Enums.AgeType readAgeType(String Message) {
        Enums.AgeType ageType;
        System.out.println(Message);
        while (true) {
            try {
                String s = sc.next();
                int c = Integer.parseInt(s);
                switch (c) {
                    case 1:
                        ageType = Enums.AgeType.NORMAL;
                        break;
                    case 2:
                        ageType = Enums.AgeType.STUDENT;
                        break;
                    case 3:
                        ageType = Enums.AgeType.SENIOR;
                        break;
                    default:
                        System.out.println("Default age type normal selected! ");
                        ageType = Enums.AgeType.NORMAL;
                        break;
                }
                return ageType;
            } catch (NumberFormatException e) {
                System.out.println("Please, input a valid decimal number. ");
            }
        }
    }
    /**
     * Method to notify user about the seat input Int
     *
     * @param message info about input
     * @param min info about the minimmum value user can input
     * @param max info about hte maximum value user can input
     * @return the Int value for the seat
     */
    public static int readSeatInput(String message, int min, int max) {
        int c = 0;
        do {
            if (min == max)
                readIntInput(message + " (" + max + "):");
            else
                c = readIntInput(message + " (" + min + "~" + max + "): ");
            if (!(c >= min && c <= max))
                System.out.println("Please enter valid option. ");
        } while (!(c >= min && c <= max));
        return c;
    }
    /**
     * Method to notify user about the Review input Int
     *
     * @param message info about input
     * @param min info about the minimum value user can input
     * @param max info about hte maximum value user can input
     * @return the Int value for the review
     */
    public static int readReviewInput(String message, int min, int max) {
        int c = 0;
        do {
            if (min == max)
                readIntInput(message + " (" + max + "):");
            else
                c = readIntInput(message + " (" + min + "~" + max + "): ");
            if (!(c >= min && c <= max))
                System.out.println("Please enter valid option. ");
        } while (!(c >= min && c <= max));
        return c;
    }
    /*
     Confirmation Message:
     This method only accept 'Yes' or 'No' as input
     return true  if input is 'Yes' or
     return false if input is 'No'
     Otherwise it will repeatedly prompt user for input
     */
    /**
     * Method to notify user about the confirmation
     *
     * @param message info about input
     * @return Boolean value (True/False)
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
    /**
     * Method to get the max length for String
     * @para
     * @return the Int len value of the string
     */
    private static int getMaxLength(String... strings) {
        int len = Integer.MIN_VALUE;
        for (String str : strings) {
            len = Math.max(str.length(), len);
        }
        return len;
    }
    /**
     * Method to fill the characters with len
     *
     * @param ch character to fill up
     * @param len max length of the string
     * @return the String
     */
    private static String fill(char ch, int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(ch);
        }
        return sb.toString();
    }

    /**
     * Method to pad the String
     *
     * @param str String to pad
     * @param len maximum length of the String
     * @return the padded String
     */
    private static String padString(String str, int len) {
        StringBuilder sb = new StringBuilder(str);
        return sb.append(fill(' ', len - str.length())).toString();
    }
    /**
     * Method to print string in Header format
     *
     * @param strings to print in Header format
     */
    public static void printHeader(String... strings) {
        int maxBoxWidth = getMaxLength(strings);
        String line = "=" + fill('=', maxBoxWidth + 2) + "=";
        System.out.println(line);
        for (String str : strings) {
            System.out.printf("| %s |%n", padString(str, maxBoxWidth));
        }
        System.out.println(line);
    }

    /**
     * Method to print divider
     *
     * @return the divider String
     */
    public static void printDivider() {
        print("===============================================");
    }
    /**
     * Print Message
     * @param message message to be display
     */
    public static void print(String message) {
        System.out.println(message);
    }
    /**
     * Print Message with an extra line
     * @param message message to be display
     */
    public static void println(String message) {
        System.out.println(message + "\n");
    }


}
