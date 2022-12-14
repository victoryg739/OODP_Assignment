package view.admin;

import controller.MovieController;
import model.Enums.MovieRating;
import model.Enums.MovieType;
import model.Enums.ShowingStatus;
import view.MenuBase;

import java.util.ArrayList;
import java.util.Date;

import static view.utilF.*;

/**
 * Configure for admin to create a movie
 *
 * @author Bryan Tay
 * @version 1.0
 * @since 2022-08-11
 */

public class MenuStaffMovieCreate extends MenuBase {
    MovieController adminC = new MovieController();

    public MenuStaffMovieCreate(MenuBase initialMenu) {
        super(initialMenu);
    }

    /**
     * Display create new movie menu
     * Ask user to input information of the new movie
     * Return to main staff menu
     *
     * @return corresponding menu that the user has selected
     */
    public MenuBase execute() {
        printHeader("Creating movie listing....");
        String title = read("Enter movie title: ");

        MovieType movieType = readMovieTypeInput("Possible movie types: \n" +
                "	1. 2D\n" +
                "	2. 3D\n" +
                "	3. Blockbuster\n\n" +
                "Select movie type (number): ");

        MovieRating movieRating = readMovieRatingInput("Enter movie rating (G / PG13 / NC16 / M18 / R21) : \n" +
                "	1. G\n" +
                "	2. PG13\n" +
                "	3. NC16\n" +
                "    4. M18\n" +
                "    5. R21\n" +
                "Select movie type (number): ");

        String synopsis = read("Enter synopsis: ");
        int runtime = readIntInput("Enter movie runtime(minutes):");
        Date DateStart = readDate("Input opening: ");
        Date DateEnd = readDate("Input ending date:");
        ShowingStatus ss = readShowingStatus("Enter Showing status : \n" +
                "	1. COMING SOON\n" +
                "	2. PREVIEW\n" +
                "	3. NOW_SHOWING\n" +
                "Select movie type (number): ");
        if (DateEnd.before(DateStart)) {
            System.out.println("Invalid Movie End Date!");
            return this.getPreviousMenu();
        }

        ArrayList<String> casts = new ArrayList<>();
        int noOfCast = readIntInput("Enter No of casts: (at least 2)");
        if (noOfCast < 2) {
            System.out.println("Invalid casts!");
            return this.getPreviousMenu();
        }

        for (int i = 0; i < noOfCast; i++) {
            casts.add(read("Enter name of cast " + (i + 1) + ": "));
        }
        String director = read("Enter Director:");
        adminC.createMovie(title, movieType, movieRating, ss, synopsis, runtime, DateStart, DateEnd, casts, director);

        return this.getPreviousMenu();
    }

}
