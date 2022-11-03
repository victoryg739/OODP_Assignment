package view.admin;

import modal.Enums.*;
import controller.MovieController;
import view.MenuBase;

import java.util.ArrayList;
import java.util.Date;

import static view.utilF.*;

public class MenuStaffMovieUpdate extends MenuBase {
    public MenuStaffMovieUpdate(MenuBase initialMenu) {
        super(initialMenu);
    }

    MovieController mController = new MovieController();
    MenuStaffMovieList listMenu = new MenuStaffMovieList(this);

    public MenuBase execute() {
        MenuBase nextMenu = this;
        boolean stay = true;
        System.out.println("Updating movie...");
        listMenu.execute();
        System.out.println("Select movie to be updated: ");

        int movieID = readIntInput("Enter move ID: ");
        System.out.println("Select movie attribute to update");
        ;
        int choice = readIntInput("1. Title\n" +
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
                mController.updateMovie(choice, movieID, title);
                break;
            case 2:
                int movieTypesNo = readIntInput("\nPossible movie types: \n" +
                        "1. 2D\n" +
                        "2. 3D\n" +
                        "3. Blockbuster\n\n" +
                        "Select movie type (number): ");
                MovieType movieType = null;
                switch (movieTypesNo) {
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
                        System.out.println("Wrong input!");
                        break;
                }
                mController.updateMovie(choice, movieID, movieType);
                break;
            case 3:
                String synopsis = read("Enter New Synopsis:");
                mController.updateMovie(choice, movieID, synopsis);
                break;
            case 4:
                MovieRating movieRating;
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
                mController.updateMovie(choice, movieID, movieRating);
                break;
            case 5:
                int runtime = readIntInput("Enter movie runtime(minutes):");
                mController.updateMovie(choice, movieID, runtime);
                break;
            case 6:
                Date DateStart = readDate("Input opening: ");
                mController.updateMovie(choice, movieID, DateStart);
                break;
            case 7:
                Date DateEnd = readDate("Input ending date:");
                mController.updateMovie(choice, movieID, DateEnd);
                break;
            case 8:
                int noOfCast = readIntInput("Enter No of casts: (at least 2)");
                if (noOfCast < 2){
                    System.out.println("Invalid casts!");
                    return this.getPreviousMenu();
                }
                ArrayList<String> casts = new ArrayList<>();
                for (int i = 0; i < noOfCast; i++) {
                    casts.add(read("Enter name of cast " + (i+1) + ": "));
                }
                mController.updateMovie(choice, movieID, casts);
                break;
            case 9:
                String director = read("Enter Director:");
                mController.updateMovie(choice, movieID, director);
                break;
            case 10:
                ShowingStatus ss = readShowingStatus("\nEnter Showing status : \n" +
                        "	1. COMING SOON\n" +
                        "	2. PREVIEW\n" +
                        "	3. NOW_SHOWING\n" +
                        "   4. END_SHOWING\n" +
                        "Select movie type (number): ");
                mController.updateMovie(choice, movieID, ss);
                break;
            default:
                System.out.println("Wrong input!\n" +
                        "Returning to menu...");
                return this.getPreviousMenu();

        }

        return this.getPreviousMenu();
    }
}
