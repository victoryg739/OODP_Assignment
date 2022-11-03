package view.admin;
import modal.Enums.*;
import controller.*;
import modal.*;
import view.MenuBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static view.utilF.print;
import static view.utilF.readIntInput;

public class MenuStaffMovieList extends MenuBase {
    MovieController ac = new MovieController();

    public MenuStaffMovieList(MenuBase initialMenu) {
        super(initialMenu);
    }

    public MenuBase execute() {
        MovieController ac = new MovieController();

        ArrayList<Movie> movieList = ac.read();

        if (movieList.isEmpty()) {
            print("No movies in the database.");
        } else {
            for (int i = 0; i < movieList.size(); i++) {
                    Movie m = movieList.get(i);
                    m.printMovie();
            }

        }
        return this.getPreviousMenu();
    }



}





