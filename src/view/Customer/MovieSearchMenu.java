package view.Customer;
import view.MenuBase;

import java.util.*;
import static view.utilF.*;

public class MovieSearchMenu extends MenuBase {

    //private Manager manager = Manager.getInstance();
    private String movieName;
    public MovieSearchMenu(MenuBase initialMenu) {
        super(initialMenu);
    }

    public MenuBase execute() {
        Scanner sc = new Scanner(System.in);
        MenuBase nextMenu = null;
        //ArrayList<Movie> movies; //List of Movies objects
        movieName = null;
        System.out.println("Movie Search");
        movieName = read("Input movie name to search: ");

        /*
        while (true) {
            movies = manager.getEntries(); //get movie with the same name as the input name
            if (movies.isEmpty()) { //if movie not found
                System.out.println("Sorry, no result found. Press 0 to go back.");
                movieName = read("Input movie name to search: ");
                if(movieName.equals("0"))
                    break;
            }
            else { //if movie is found
                //showing the list of movies found
                System.out.println("Found " + movies.size() + " results:");

                //List of possible options to choose from
                ArrayList<String> choices = new ArrayList<String>();

                for (Movie m : movies) { //iterate through the list of movies
                    choices.add(m.getTitle()); //get the title from the movie class
                }
                choices.add("Go Back");
                choices.add("Quit The Application");
                printMenu(choices, 1); //print menu for the options

                String choice = sc.next();
                int c = readIntInput(choice);

                /* eg. Menu Choices
                    movies.size == 2
                    Option 1: Movie 1
                    Option 2: Movie 2
                    Option 3: Go Back
                    Option 4: Quit The Application


                if (c <= movies.size())
                    //go to MovieInfo Menu
                    //movies.get() == to get the respective movie class
                    nextMenu = new MovieInfo(this, movies.get(c));
                else if (c == movies.size() + 1) //select Go Back option
                    nextMenu = getPreviousMenu();
                else if (c == movies.size() + 2) //Quit the App
                    nextMenu = new Quit(null);
                break;
            }
        }
         */
        if(nextMenu!=null)
            return nextMenu; //move to the next Menu (MovieInfo of the selected movie)
        else //when the choice input is invalid
            return this.getPreviousMenu(); //go back
    }
}
