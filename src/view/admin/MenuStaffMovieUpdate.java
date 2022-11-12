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
 * Menu to update movie by updating the attributes
 *
 * @author Bryan Tay
 * @version 1.0
 * @since 2022-08-11
 */
public class MenuStaffMovieUpdate extends MenuBase {
    MovieController mc = new MovieController();

    public MenuStaffMovieUpdate(MenuBase initialMenu) {
        super(initialMenu);
    }

    /**
     * Display update Movie menu
     * Ask user to input information about the individual attributes
     * and bring user back to configure menu.
     *
     * @return configure menu
     */

    public MenuBase execute() {
        printHeader("Updating Movie");
        mc.listMovies();
        print("Select movie to be updated: ");
        int movieID = readIntInput("Enter movie ID:");

        /* Checks if movieID is in the database */
        if (mc.readByID(movieID) == null) {
            print("Movie does not exist!");
            return this.getPreviousMenu();
        }

        int choice = readIntInput("\nSelect movie attribute to update\n" +
                "1. Title\n" +
                "2. Type\n" +
                "3. Synopsis\n" +
                "4. Rating\n" +
                "5. Duration \n" +
                "6. Movie Release date\n" +
                "7. End of Showing date\n" +
                "8. Cast\n" +
                "9. Director\n" +
                "10. Showing Status\n\n" +
                "Enter option: ");

        switch (choice) {
            case 1:
                String title = read("Enter New Title:");
                mc.updateMovie(choice, movieID, title);
                break;
            case 2:

                MovieType movieType = readMovieTypeInput("\nPossible movie types: \n" +
                        "1. 2D\n" +
                        "2. 3D\n" +
                        "3. Blockbuster\n\n" +
                        "Select movie type (number): ");
                mc.updateMovie(choice, movieID, movieType);
                break;
            case 3:
                String synopsis = read("Enter New Synopsis:");
                mc.updateMovie(choice, movieID, synopsis);
                break;
            case 4:
                MovieRating movieRating = readMovieRatingInput("\nEnter movie rating (G / PG13 / NC16 / M18 / R21) : \n" +
                        "	1. G\n" +
                        "	2. PG13\n" +
                        "	3. NC16\n" +
                        "   4. M18\n" +
                        "   5. R21\n" +
                        "Select movie type (number): ");
                mc.updateMovie(choice, movieID, movieRating);
                break;
            case 5:
                int runtime = readIntInput("Enter movie runtime(minutes):");
                mc.updateMovie(choice, movieID, runtime);
                break;
            case 6:
                Date DateStart = readDate("Input opening: ");
                mc.updateMovie(choice, movieID, DateStart);
                break;
            case 7:
                Date DateEnd = readDate("Input ending date:");
                mc.updateMovie(choice, movieID, DateEnd);
                break;
            case 8:
                int noOfCast = readIntInput("Enter No of casts: (at least 2)");
                if (noOfCast < 2) {
                    print("Invalid casts!");
                    return this.getPreviousMenu();
                }
                ArrayList<String> casts = new ArrayList<>();
                for (int i = 0; i < noOfCast; i++) {
                    casts.add(read("Enter name of cast " + (i + 1) + ": "));
                }
                mc.updateMovie(choice, movieID, casts);
                break;
            case 9:
                String director = read("Enter Director:");
                mc.updateMovie(choice, movieID, director);
                break;
            case 10:
                ShowingStatus ss = readShowingStatus("Enter Showing status : \n" +
                        "	1. COMING SOON\n" +
                        "	2. PREVIEW\n" +
                        "   3. NOW_SHOWING\n" +
                        "   4. END_SHOWING\n" +
                        "Select movie type (number): ");
                mc.updateMovie(choice, movieID, ss);
                break;
            default:
                print("Wrong input!\n" +
                        "Returning to menu...");
                return this.getPreviousMenu();
        }
        print("Successfully updated Movie details!");
        return this.getPreviousMenu();
    }
}
