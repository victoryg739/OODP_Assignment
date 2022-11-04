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
        System.out.println("List of Cineplexes");
        ArrayList<Cineplex> cineplexArray = cineplexCtrler.read(); //using cinplexcontroller to read the cineplex object from the dat file
        if (cineplexArray.isEmpty()) {
            System.out.println("No cineplexes found!");
            return getPreviousMenu();
        }

        for(int i = 0; i<cineplexArray.size(); i++) {
            cineplexArray.get(i);
            System.out.print(cineplexArray.get(i).getLocation()+":");
            ArrayList<Cinema> cinemaArray = cineplexArray.get(i).getCinemas();
            for(int j= 0;j < cinemaArray.size();j++){
                System.out.print(cinemaArray.get(j).getCinemaNo()+ " ");

            }
            System.out.print("\n");
        }



        String cineplexLocation = read("Enter Cineplex Location:");
        Cineplex cineplex  = cineplexCtrler.readByLocation(cineplexLocation);


        if (cineplex == null) {
            System.out.println("Cineplex not found!\n" +
                    "Returning to menu...");
            return getPreviousMenu();
        }

        System.out.println("\nCinemas in " + cineplex.getLocation()+ ": \n");
        ArrayList<Cinema> cinemaArray = cineplex.getCinemas();
        cinemaArray.forEach(Cinema -> System.out.println("Cinema No: " + Cinema.getCinemaNo()));

        String cinemaNo = read("Enter Cinema No: ");
        Cinema cinema = cinemaCtrler.readByCinemaNo(cinemaNo);


        if (cinemaCtrler.readByAttribute(cinemaNo).isEmpty()) {
            System.out.println("Cinema not found!\n"+
                    "Returning to menu...");
            return getPreviousMenu();
        }


        int movie_id  = readIntInput("Enter movie id: ");
        Movie m  = movieCtrler.readByID(movie_id);
        //need to implement movie controller not done
        if (m == null) {
            System.out.println("Movie does not exist!\n"+
                    "Returning to menu...");
            return getPreviousMenu();
        }else{
            System.out.println(m.getTitle());
        }


        Date sessionDateTime  = readDateTime("Enter session date and time: ");
        Enums.Day enumsDay  = returnEnumsDay(sessionDateTime);



        int lastSessionId = sessionCtrler.getLastSessionId();

        Session session = new Session(cinema,m,lastSessionId+1, sessionDateTime,enumsDay);

        //update both session and cinema txt file
        sessionCtrler.append(session);
        cinemaCtrler.cinemaUpdateSession(cinemaNo,session);

        sessionCtrler.printAllSession();
        cinemaCtrler.printAllCinema();

        System.out.println("Session created successfully!");

        return getPreviousMenu();
        }

}
