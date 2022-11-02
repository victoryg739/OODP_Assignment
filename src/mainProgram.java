import controller.CinemaController;
import controller.CineplexController;
//import controller.MovieController;
import controller.MovieController;
import controller.SessionController;
import modal.*;
import view.MainMenu;
import view.MenuBase;
import view.Quit;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static view.utilF.*;


/*

The start of the program
 */

public class mainProgram {
    public static void main(String[] args) {


        /* For Testing Purposes */
        ArrayList<String> casts = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            casts.add("Bryan");
        }

        Movie movie0 = new Movie(0, "Spiderman", Enums.MovieType.TWO_D, Enums.MovieRating.M18, "Away from you", 300, readDate(), readDate(), "Bryan Tay", casts);
        Movie movie1 = new Movie(1, "Ironman", Enums.MovieType.THREE_D, Enums.MovieRating.M18, "Ironman is the best", 150, readDate(), readDate(), "Bryan Tay", casts);
        Movie movie2 = new Movie(2, "Chickenman", Enums.MovieType.TWO_D, Enums.MovieRating.M18, "Fuck you victor", 300, readDate(), readDate(), "Bryan Tay", casts);
        Movie movie3 = new Movie(3, "Allahman", Enums.MovieType.BLOCKBUSTER, Enums.MovieRating.G, "Allahu akbar", 300, readDate(), readDate(), "Bryan Tay", casts);
        Movie movie4 = new Movie(4, "Hohoman", Enums.MovieType.TWO_D, Enums.MovieRating.NC16, "Away from you", 200, readDate(), readDate(), "Bryan Tay", casts);
        Movie movie5 = new Movie(1, "Ironman 2", Enums.MovieType.THREE_D, Enums.MovieRating.M18, "Ironman 2 is better than Ironman", 150, readDate(), readDate(), "Bryan Tay", casts);

        MovieController mc = new MovieController();
        mc.createMovie(movie0);
        mc.createMovie(movie1);
        mc.createMovie(movie2);
        mc.createMovie(movie3);
        mc.createMovie(movie4);
        mc.createMovie(movie5);


        Cinema cinemaJem1  = new Cinema("A1",movie0, Enums.ClassCinema.PLATINUM,null);
        Cinema cinemaJem2  = new Cinema("A2",movie1, Enums.ClassCinema.PLATINUM,null);
        ArrayList <Cinema> cinemaJemArray = new ArrayList<Cinema>();
        cinemaJemArray.add(cinemaJem1);
        cinemaJemArray.add(cinemaJem2);
        Cineplex cineplex = new Cineplex("JEM",cinemaJemArray);

        CinemaController cinemaCtrler = new CinemaController();
        cinemaCtrler.create("A1",null, Enums.ClassCinema.PLATINUM,null);
        CineplexController cinplexController = new CineplexController();
        cinplexController.create("JEM",cinemaJemArray);

        SessionController sessionController = new SessionController();
        ArrayList<Seat> testSeat = new ArrayList<Seat>();
        Seat seat0 = new Seat(0,0, false);
        Seat seat1 = new Seat(0,1, false);
        Seat seat2 = new Seat(1,0, false);
        Seat seat3 = new Seat(1,1, false);
        testSeat.add(seat0);
        testSeat.add(seat1);
        testSeat.add(seat2);
        testSeat.add(seat3);

        java.util.Date testDate = new Date();
        ArrayList<Session> arrayListSession = new ArrayList<Session>();
        Session testSession0 = new Session(cinemaJem1, movie0, 0,testDate , Enums.Day.FRI_AFT_SIX, testSeat);
        Session testSession1 = new Session(cinemaJem1, movie0, 1,testDate , Enums.Day.SAT_SUN, testSeat);
        arrayListSession.add(testSession0);
        arrayListSession.add(testSession1);
        cinemaJem1.setSessions(arrayListSession);

        sessionController.createSession(cinemaJem1, movie0, 1,readDate() , Enums.Day.FRI_AFT_SIX, testSeat);

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

