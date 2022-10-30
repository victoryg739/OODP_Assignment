package view.admin;
import modal.Enums.*;
import controller.*;
import modal.*;
import view.MenuBase;

import java.util.ArrayList;
import java.util.Date;

public class MenuStaffMovieList extends MenuBase {
    MovieController ac = new MovieController();
    public MenuStaffMovieList(MenuBase initialMenu) {
        super(initialMenu);
    }
    public MenuBase execute() {
        MovieController ac = new MovieController();
        System.out.println("List all movie title");
        ArrayList<Movie> movieList = ac.read();
        if(movieList.isEmpty()){
            System.out.println("No movies to be listed!");
        }
        else{
            movieList.forEach(movie -> ac.printMovie(movie));
        }
        return this.getPreviousMenu();
    }



}
