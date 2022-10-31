import controller.CinemaController;
import controller.CineplexController;
import controller.MovieController;
import modal.Cinema;
import modal.Cineplex;
import modal.Enums;
import modal.Movie;
import view.MainMenu;
import view.MenuBase;
import view.Quit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/*

The start of the program
 */

public class mainProgram {
    public static void main(String[] args) {
        ArrayList<String> casts = new ArrayList<>();
        casts.add("a");
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d;
        try {
            d = sdf.parse("01/01/2021");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Movie m = new Movie(1,"a", Enums.MovieType.TWO_D, Enums.MovieRating.M18,"a",2,d,d,"a",casts);
        MovieController movieCtrler  =new MovieController();
        movieCtrler.createMovie("a", Enums.MovieType.TWO_D, Enums.MovieRating.M18,"a",2,d,d,casts,"a");
        Cinema cinemaJem1  = new Cinema("A1",m, Enums.ClassCinema.PLATINUM,null);
        Cinema cinemaJem2  = new Cinema("A2",m, Enums.ClassCinema.PLATINUM,null);
        ArrayList <Cinema> cinemaJemArray = new ArrayList<Cinema>();
        cinemaJemArray.add(cinemaJem1);
        cinemaJemArray.add(cinemaJem2);
        Cineplex cineplex = new Cineplex("JEM",cinemaJemArray);

        CinemaController cinemaCtrler = new CinemaController();
        cinemaCtrler.create("A1",null, Enums.ClassCinema.PLATINUM,null);
        CineplexController cinplexController = new CineplexController();
        cinplexController.create("JEM",cinemaJemArray);

        // Create first Main Menu
        MainMenu mm = new MainMenu(null);
        // Upcast to a MenuBase
        MenuBase nextMenu = mm;

        // Loop until a user quits the Menu
        do{
            nextMenu =  nextMenu.execute();
        }while(!(nextMenu instanceof Quit));
    }
}

