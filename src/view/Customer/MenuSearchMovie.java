package view.Customer;

import controller.MovieController;
import controller.SessionController;
import modal.Enums;
import modal.Movie;
import modal.Session;
import view.MenuBase;
import view.Quit;
import view.admin.MenuStaffTopFiveRating;

import java.util.ArrayList;
import java.util.Scanner;

import static view.utilF.*;
/* ToDO list:
    1. Remove the space in the input name and search
    eg. Iron Man == IronMan
*/

public class MenuSearchMovie extends MenuBase {


    private MovieController mc = new MovieController();
    public MenuSearchMovie(MenuBase initialMenu) {
        super(initialMenu);
    }

    public MenuBase execute() {
        SessionController sc = new SessionController();
        ArrayList<Session> sessionlist = sc.read();
        MenuBase nextMenu = null;
        String movieName;
        int choice;

        printHeader("Movie Search");
        movieName = read("Input movie name to search: ");

        //obtain a ArrayList of Movie objects with the same name list
        ArrayList<Movie> movieList = mc.readByTitle(movieName);
        if (sessionlist.isEmpty()) {
            print("Sorry, no result found.");
            print("1. Back\n" +
                    "2. Quit\n");

            choice = readIntInput("Choice: ");

            switch (choice){
                case 1:
                    nextMenu = this.getPreviousMenu();
                    break;
                default:
                    nextMenu = new Quit(this);
                    break;
            }

        }
        else { // if movie is found
            mc.listMovies(Enums.ShowingStatus.PREVIEW, Enums.ShowingStatus.NOW_SHOWING, movieList);
            print("===============================================");
            print("1. Buy Ticket/ Set Review\n" +
                    "2. Back \n" +
                    "3. Quit\n");

            choice = readIntInput("Choice: ");
            switch (choice) {
                case 1:
                    int movieID = readIntInput("Enter movieID: ");

                    if(mc.validMovieSession(movieID)) {
                        nextMenu = new MenuMovieInfo(this, mc.read().get(movieID));
                    }else {
                        print("Invalid movieID");
                    }
                    break;
                case 2:
                    nextMenu = this.getPreviousMenu();
                    break;
                default:
                    nextMenu = new Quit(this);
                    break;
            }

        }
        return nextMenu;

    }



    }

