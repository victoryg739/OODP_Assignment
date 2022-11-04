package view.Customer;

import view.*;
import modal.*;
import java.util.*;
import controller.*;
import static view.utilF.*;

//Menu to list the top five movies by sales

public class MenuListMovieTopSale extends MenuBase {
    public MenuListMovieTopSale(MenuBase initialMenu) {
        super(initialMenu);
    }


     //Display the top five menu by sales


    public MenuBase execute() {
        MovieController mc = new MovieController();
        int count = 1;
        ArrayList<Movie> movies = mc.read();
        System.out.println("Top 5 by Sales");
        ArrayList<String> options = new ArrayList<>();

        try {

            mc.sortTicketSales(movies);

            //top refers to the top 5 movies
            int top = 1;

            //We display the top 5 movies of all time
            for (Movie movie : movies) {
                options.add(movie.getTicketSales() + " Tickets for " + movie.getTitle());

                //If top < 5, break
                if (top++ == 5) {
                    break;
                }
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < options.size(); i++) {
            println(count + " " + options.get(i));
            count++;
        }
        println((count) + " List Top 5 Movies By Ratings            \n" +
                "\n" +
                (count + 1) + " Go Back to Previous Menu            \n" +
                "\n" +
                (count + 2) + " Quit The Application                \n");

        int choice = readIntInput("Enter choice: ");

        MenuBase nextMenu = null;

        System.out.println(options.size());

        /*
           Option 1: Movie 1
           Option 2: Movie 2
           Option 3: Movie 3
           Option 4: Movie 4
           Option 5: Movie 5
           Option 6: List Top 5 Movies By Ratings
           Option 7: Go Back to Previous Menu
           Option 8: Quit The Application
         */


        //Depending on the c input, display the relevant options by subtracting
        if (choice <= options.size() - 3) {
            nextMenu = new MenuMovieInfo(this, movies.get(choice));
        }
        else if (choice == options.size() - 2) {
            //nextMenu = new MenuListTopRating(this.getPreviousMenu());
        }
        else if (choice == options.size() - 1) {
            nextMenu = this.getPreviousMenu();
        }
        else if (choice == options.size()) {
            nextMenu = new Quit(null);
        }
        return nextMenu;
    }


}



