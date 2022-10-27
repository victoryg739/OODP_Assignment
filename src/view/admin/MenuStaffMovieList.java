package view.admin;
import modal.Enums.*;
import controller.*;
import modal.*;
import view.MenuBase;

import java.util.ArrayList;
import java.util.Date;

public class MenuStaffMovieList extends MenuBase {
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
            movieList.forEach(movie -> printMovie(movie));
        }
        return this.getPreviousMenu();
    }

    public void printMovie(Movie movie){
        int id = movie.getID();
        String title = movie.getTitle();
        MovieType movieType = movie.getType();
        MovieRating movieRating = movie.getRating();
        String synopsis = movie.getSynopsis();
        int runtime = movie.runtime();
        Date DateStart = movie.getDateStart();
        Date DateEnd = movie.getDateEnd();
        String director = movie.getDirector();
        //ArrayList<String> casts = movie.getCast();
        String castString = "";
        for (int i=0; i< movie.getCast().size(); i++)
            castString = castString.concat(movie.getCast().get(i) + ",");
        castString = castString.substring(0, castString.length()-1);

        String movieString = "ID: " + id + " | " + "Title: " + title + " | " + "Type " + movieType + " | " + "Rating " + movieRating + " | " + "Synopsis: " + synopsis + " | "
                + "Runtime: " + runtime + " | " + "DateStart: " + DateStart + " | " + "DateEnd: " + DateEnd + " | " + "Director: " + director + " | "
                + "Cast: " + castString;
        System.out.println(movieString);
        System.out.println("-------------------");
    }

}
