package view.admin;

import controller.*;
import modal.*;
import view.MenuBase;

import java.util.ArrayList;

public class MenuStaffMovieList extends MenuBase {
    public MenuStaffMovieList(MenuBase initialMenu) {
        super(initialMenu);
    }

    public MenuBase execute() {
        AdminController ac = new AdminController();
        System.out.println("List all movie title");
        ArrayList<Movie> movieList = ac.read();
        if(movieList.isEmpty()){
            System.out.println("No movies to be listed!");
        }
        else{
            movieList.forEach(movie -> printMovie(movie));
        }
        return this.getPreviousMenu();
    }
    public void printMovie(Movie movie){
        String title = movie.getTitle();
        String movieString = "Title: " + title;
        System.out.println(movieString);
        System.out.println("-------------------");
    }

}
