import controller.CinemaController;
import controller.CineplexController;
//import controller.MovieController;
import controller.MovieController;
import modal.*;
import view.MainMenu;
import view.MenuBase;
import view.Quit;

import java.util.ArrayList;

import static view.utilF.*;


/*

The start of the program
 */

public class mainProgram {
    public static void main(String[] args) {
        /* For Testing Purposes */
        MovieController mc = new MovieController();

        ArrayList<String> casts = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            casts.add("Bryan");
        }

        Movie movie0 = new Movie(0, "Spiderman", Enums.MovieType.TWO_D,Enums.ShowingStatus.PREVIEW ,Enums.MovieRating.M18, "Away from you", 300, readDate(), readDate(), "Bryan Tay", casts);
        Movie movie1 = new Movie(1, "Ironman", Enums.MovieType.THREE_D, Enums.ShowingStatus.PREVIEW,Enums.MovieRating.M18, "Ironman is the best", 150, readDate(), readDate(), "Bryan Tay", casts);
        Movie movie2 = new Movie(2, "Chickenman", Enums.MovieType.TWO_D, Enums.ShowingStatus.PREVIEW, Enums.MovieRating.M18, "Fuck you victor", 300, readDate(), readDate(), "Bryan Tay", casts);
        Movie movie3 = new Movie(3, "Allahman", Enums.MovieType.BLOCKBUSTER, Enums.ShowingStatus.PREVIEW,Enums.MovieRating.G, "Allahu akbar", 300, readDate(), readDate(), "Bryan Tay", casts);
        Movie movie4 = new Movie(4, "Hohoman", Enums.MovieType.TWO_D, Enums.ShowingStatus.PREVIEW,Enums.MovieRating.NC16, "Away from you", 200, readDate(), readDate(), "Bryan Tay", casts);
        Movie movie5 = new Movie(5, "Aloyman", Enums.MovieType.TWO_D, Enums.ShowingStatus.PREVIEW,Enums.MovieRating.NC16, "Away from you", 200, readDate(), readDate(), "Bryan Tay", casts);

        mc.createMovie(movie0);
        mc.createMovie(movie1);
        mc.createMovie(movie2);
        mc.createMovie(movie3);
        mc.createMovie(movie4);
        mc.createMovie(movie5);

        mc.updateMovie(11, 0, 600);
        mc.updateMovie(11, 1, 200);
        mc.updateMovie(11, 2, 300);
        mc.updateMovie(11, 3, 400);
        mc.updateMovie(11, 4, 500);
        mc.updateMovie(11, 5, 100);

        Cinema cinema1  = new Cinema("A1",null, Enums.ClassCinema.PLATINUM,null);
        Cinema cinema2  = new Cinema("A2",null, Enums.ClassCinema.PLATINUM,null);
        Cinema cinema3 =  new Cinema("A3",null, Enums.ClassCinema.NORMAL,null);
        ArrayList<Cinema> cinemaArrayList = new ArrayList<Cinema>();
        cinemaArrayList.add(cinema1);
        cinemaArrayList.add(cinema2);
        cinemaArrayList.add(cinema3);

        CinemaController cinemaCtrler = new CinemaController();
        cinemaCtrler.replace(cinemaArrayList);

        Cineplex cineplex1 = new Cineplex("Jem",cinemaArrayList);
        Cineplex cineplex2 = new Cineplex("Orchard",cinemaArrayList);
        Cineplex cineplex3 = new Cineplex("Funan",cinemaArrayList);
        ArrayList<Cineplex> cineplexArrayList = new ArrayList<Cineplex>();
        cineplexArrayList.add(cineplex1);
        cineplexArrayList.add(cineplex2);
        cineplexArrayList.add(cineplex3);


        CineplexController cinplexController = new CineplexController();
        cinplexController.replace(cineplexArrayList);


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

