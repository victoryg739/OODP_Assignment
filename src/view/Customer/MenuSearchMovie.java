package view.Customer;

import controller.MovieController;
import controller.SessionController;
import modal.Movie;
import modal.Session;
import view.MenuBase;
import view.Quit;

import java.util.ArrayList;

import static view.utilF.*;

public class MenuSearchMovie extends MenuBase {


    MovieController mc = new MovieController();
    SessionController sc = new SessionController();

    public MenuSearchMovie(MenuBase initialMenu) {
        super(initialMenu);
    }

    public MenuBase execute() {
        ArrayList<Session> sessionList = sc.read();
        MenuBase nextMenu = null;
        int choice;

        printHeader("Movie Search");
        String movieName = read("Input movie name to search: ");

        ArrayList<Movie> movieList = mc.readByTitle(movieName);
        // If session is Empty or MovieList is Empty then no results found
        if (sessionList.isEmpty() || movieList.isEmpty()) {
            print("Sorry, no result found.");
            return this.getPreviousMenu();
        }

        mc.listMovies(movieList);
        printDivider();

        print("1. Buy Ticket/ Set Review\n" +
                "2. Back \n" +
                "3. Quit\n");

        choice = readIntInput("Choice: ");

        switch (choice) {
            case 1:
                int movieID = readIntInput("Enter movieID: ");

                if (mc.validMovieSession(movieID)) {
                    nextMenu = new MenuMovieInfo(this, mc.read().get(movieID));
                } else {
                    print("Invalid movieID");
                    nextMenu = this.getPreviousMenu();
                }
                break;
            case 2:
                nextMenu = this.getPreviousMenu();
                break;
            default:
                nextMenu = new Quit(this);
                break;
        }


        return nextMenu;

    }


}

