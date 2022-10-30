package view.admin;

//import controller.AdminController;
import controller.CinemaController;
import controller.CineplexController;
import controller.MovieController;
import controller.SessionController;
import modal.*;
import view.MenuBase;

import java.util.ArrayList;
import java.util.Date;

import static view.utilF.*;
import static view.utilF.readDate;

public class MenuStaffMovieSessionCreate extends MenuBase {

        public MenuStaffMovieSessionCreate(MenuBase initialMenu) {
            super(initialMenu);
        }
        private CineplexController cineplexCtrler = new CineplexController();
        private CinemaController cinemaCtrler = new CinemaController();
        private MovieController movieCtrler = new MovieController();
        private SessionController sessionCtrler = new SessionController();
        private Constant datetimeFormat;


    public MenuBase execute() {
        System.out.println("Creating a new movie session");
        System.out.println("List of Cinplexes");
        ArrayList<Cineplex> cineplexArray = cineplexCtrler.read(); //using cinplexcontroller to read the cineplex object from the dat file
        if (cineplexArray.isEmpty()) {
            System.out.println("There are no cineplexes registered!");
            return getPreviousMenu();
        }

        for(int i = 0; i<cineplexArray.size(); i++) {
            cineplexArray.get(i);
            System.out.print(cineplexArray.get(i).getLocation()+":");
            ArrayList<Cinema> cinemaArray = cineplexArray.get(i).getCinemas();
            for(int j= 0;j < cinemaArray.size();j++){
                System.out.print(cinemaArray.get(i).getCinemaNo()+ " ");

            }
            System.out.print("\n");
        }



        String cineplexLocation = read("Enter Cineplex Location:");
        Cineplex cineplex  = cineplexCtrler.readByLocation(cineplexLocation);


        if (cineplex == null) {
            System.out.println("Cineplex does not exist!\n" +
                    "Returning to menu...");
            return getPreviousMenu();
        }

        System.out.println("\nCinemas in " + cineplex.getLocation()+ ": \n");
        ArrayList<Cinema> cinemaArray = cineplex.getCinemas();
        cinemaArray.forEach(Cinema -> System.out.println("Cinema No:" + Cinema.getCinemaNo()));

        String cinemaNo = read("Enter Cinema No: ");
        Cinema cinema = cinemaCtrler.readByCinemaNo(cinemaNo);

        //added


        if (cinemaCtrler.readByAttribute(cinemaNo).isEmpty()) {
            System.out.println("Cinema does not exist!\n"+
                    "Returning to menu...");
            return getPreviousMenu();
        }


        int movie_id  = readIntInput("Enter movie id: ");
        //need to implement movie controller not done
        if (movieCtrler.readByID(movie_id) == null) {
            System.out.println("Movie does not exist!\n"+
                    "Returning to menu...");
            return getPreviousMenu();
        };


        Date sessionDateTime  = readDateTime("Enter session date and time");
        Movie movie = movieCtrler.readByID(movie_id);
        //hard coded change later
        //should get seat plan from Cinema class
        ArrayList<Seat> seat = new ArrayList<Seat>(100);
        int row  = 5;
        int column  = 5;
        for(int i = 1; i <= row ;i++){
            for (int j = 0; j <= column; j++){
                Seat s = new Seat(j,i,false);
                seat.add(s);

            }
        }





        sessionCtrler.createSession(cinema,movie,1, sessionDateTime,seat);

        System.out.println("Session successfully created!");
        // for debugging later remove
        ArrayList<Session> sessionList = sessionCtrler.read();
        for(int i =0; i< sessionList.size();i++){ //return one section by one for the whole session file
            System.out.println(sessionList.get(i).getSessionId());
            //GIVe aloy later
            ArrayList<Seat>  s = sessionList.get(i).getSeat();
            System.out.println(s.get(s.size() - 1).getCol());
            System.out.println(s.get(s.size() - 1).getRow());


        }
        return getPreviousMenu();
        }

}
