package view.admin;

import modal.Enums.*;
import controller.*;
import view.MenuBase;

import java.util.*;
import static view.utilF.*;

public class MenuStaffMovieCreate extends MenuBase {

    public MenuStaffMovieCreate(MenuBase initialMenu) {
        super(initialMenu);
    }

    MovieType movieType;
    MovieRating movieRating;
    MovieController adminC = new MovieController();

    public MenuBase execute() {

        System.out.println("Creating movie listing....");
        String title = read("Enter movie title: ");

        MovieType movieType = readMovieTypeInput("\nPossible movie types: \n" +
                "	1. 2D\n" +
                "	2. 3D\n" +
                "	3. Blockbuster\n\n" +
                "Select movie type (number): ");

        MovieRating movieRating = readMovieRatingInput("\nEnter movie rating (G / PG13 / NC16 / M18 / R21) : \n" +
                "	1. G\n" +
                "	2. PG13\n" +
                "	3. NC16\n" +
                "   4. M18\n" +
                "   5. R21\n" +
                "Select movie type (number): ");

        String synopsis = read("Enter synopsis: ");
        int runtime = readIntInput("Enter movie runtime(minutes):");
        Date DateStart = readDate("Input opening: ");
        Date DateEnd = readDate("Input ending date:");

        if(DateEnd.before(DateStart)){
            System.out.println("Invalid Movie End Date!");
            return this.getPreviousMenu();
        }

        ArrayList<String> casts = new ArrayList<>();
        int noOfCast = readIntInput("Enter No of casts: (at least 2)");
        if (noOfCast < 2){
            System.out.println("Invalid casts!");
            return this.getPreviousMenu();
        }

        for (int i = 0; i < noOfCast; i++) {
            casts.add(read("Enter name of cast " + (i+1) + ": "));
        }

        // Input movie director
        String director = read("Enter Director:");


       adminC.createMovie(title, movieType, movieRating, synopsis, runtime, DateStart, DateEnd, casts, director );

       return this.getPreviousMenu();
    }
}
