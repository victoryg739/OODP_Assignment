package view.admin;

import modal.Enums.*;
import controller.*;
import view.MenuBase;

import java.util.*;
import static view.utilF.*;
import static view.utilF.readIntInput;

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

        int movieTypeNo = readIntInput("\nPossible movie types: \n" +
                "	1. 2D\n" +
                "	2. 3D\n" +
                "	3. Blockbuster\n\n" +
                "Select movie type (number): ");
        switch(movieTypeNo) {
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
                System.out.println("Wrong input!\n" +
                        "Returning to menu...");
                return this.getPreviousMenu();
        }

        int movieRatingNo = readIntInput("\nEnter movie rating (G / PG13 / NC16 / M18 / R21) : \n" +
                "	1. G\n" +
                "	2. PG13\n" +
                "	3. NC16\n" +
                "   4. M18\n" +
                "   5. R21\n" +
                "Select movie type (number): ");


        switch(movieRatingNo) {
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
                System.out.println("Wrong input!\n" +
                        "Returning to menu...");
                return this.getPreviousMenu();
        }

        // Input Movie synopsis
        String synopsis = read("Enter synopsis: ");
        int runtime = readIntInput("Enter movie runtime(minutes):");
        // Input movie release date
        Date DateStart = readDate("Input opening: ");
        // Input movie end date
        Date DateEnd = readDate("Input ending date:");

        // Input number of cast
        int noOfCast = readIntInput("Enter No of casts: (at least 2)");
        if (noOfCast < 2){
            System.out.println("Invalid casts!");
            return this.getPreviousMenu();
        }
        ArrayList<String> casts = new ArrayList<>();
        for (int i = 0; i < noOfCast; i++) {
            casts.add(read("Enter name of cast \" + (i+1) + \": "));
        }
        // Input movie director
        String director = read("Enter Director:");


       adminC.createMovie(title, movieType, movieRating, synopsis, runtime, DateStart, DateEnd, casts, director );
       return this.getPreviousMenu();
    }
}
