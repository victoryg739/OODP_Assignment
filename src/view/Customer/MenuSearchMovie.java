package view.Customer;

import controller.MovieController;
import modal.Movie;
import view.MenuBase;
import view.Quit;

import java.util.ArrayList;
import java.util.Scanner;

import static view.utilF.*;
/* ToDO list:
    1. Remove the space in the input name and search
    eg. Iron Man == IronMan
*/

public class MenuSearchMovie extends MenuBase {
    private String movieName;

    private MovieController mc = new MovieController();
    public MenuSearchMovie(MenuBase initialMenu) {
        super(initialMenu);
    }

    public MenuBase execute() {
        MenuBase nextMenu = null;
        ArrayList<Movie> movies; //List of Movies objects
        movieName = null;
        System.out.println("Movie Search");
        movieName = read("Input movie name to search: ");

        while (true) {
            //obtain a ArrayList of Movie objects with the same name list
            ArrayList<Movie> movieList = mc.readByTitle(movieName);
            if (movieList.isEmpty()) { //if movie not found
                System.out.println("Sorry, no result found. Press 0 to go back.");
                movieName = read("Input movie name to search: ");
                if(movieName.equals("0"))
                    break;
            }
            else { //if movie is found
                //search through the ArrayList and output the searched movie

                //showing the list of movies found
                System.out.println("Found " + movieList.size() + " results:");

                //List of possible options to choose from
                ArrayList<String> options = new ArrayList<String>();

                for (Movie m : movieList) { //iterate through the list of movies
                    options.add(m.getTitle()); //get the title from the movie class
                }
                options.add("Go Back");
                options.add("Quit The Application");
                printMenu(options, 1); //print menu for the options

                int choice = readIntInput("Please enter your choice: ");
                /* eg. Menu Choices
                    movies.size == 2
                    Option 1: Movie 1
                    Option 2: Movie 2
                    Option 3: Go Back
                    Option 4: Quit The Application
                */
                if (choice <= movieList.size()) {
                    //go to MovieInfo Menu
                    nextMenu = new MenuMovieInfo(this, movieList.get(choice - 1));
                }
                else if (choice == movieList.size() + 1) {
                    //select Go Back option
                    nextMenu = getPreviousMenu();
                }
                else if (choice == movieList.size() + 2) {
                    //Quit the App
                    nextMenu = new Quit(null);
                }
                break;
            }
        }

        if(nextMenu!=null)
            return nextMenu; //move to the next Menu (MovieInfo of the selected movie)
        else //when the choice input is invalid
            return this.getPreviousMenu(); //go back
    }
}
