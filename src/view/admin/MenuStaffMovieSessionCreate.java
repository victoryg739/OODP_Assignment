package view.admin;

//import controller.AdminController;

import controller.CinemaController;
import controller.CineplexController;
import controller.MovieController;
import controller.SessionController;
import model.*;
import view.MenuBase;

import java.util.ArrayList;
import java.util.Date;

import static view.utilF.*;

public class MenuStaffMovieSessionCreate extends MenuBase {

    public MenuStaffMovieSessionCreate(MenuBase initialMenu) {
        super(initialMenu);
    }

    private CineplexController cineplexCtrler = new CineplexController();
    private CinemaController cinemaCtrler = new CinemaController();
    private MovieController movieCtrler = new MovieController();
    private SessionController sessionCtrler = new SessionController();


    public MenuBase execute() {
        printHeader("Creating a new movie session");
        println("List of Cineplexes");
        ArrayList<Cineplex> cineplexArray = cineplexCtrler.read();
        if (cineplexArray.isEmpty()) {
            println("No cineplexes found!");
            return getPreviousMenu();
        }

        for (int i = 0; i < cineplexArray.size(); i++) {
            cineplexArray.get(i);
            print(cineplexArray.get(i).getLocation() + ":");
            ArrayList<Cinema> cinemaArray = cineplexArray.get(i).getCinemas();
            for (int j = 0; j < cinemaArray.size(); j++) {
                print(cinemaArray.get(j).getCinemaNo() + " ");

            }
            print("");
        }


        String cineplexLocation = read("Enter Cineplex Location:");
        Cineplex cineplex = cineplexCtrler.readByLocation(cineplexLocation);


        if (cineplex == null) {
            println("Cineplex is not found!");
            println("Returning to main menu");
            return getPreviousMenu();
        }

        println("\nCinemas in " + cineplex.getLocation() + ":");
        ArrayList<Cinema> cinemaArray = cineplex.getCinemas();
        cinemaArray.forEach(Cinema -> print("Cinema No: " + Cinema.getCinemaNo()));

        String cinemaNo = read("\nEnter Cinema No: ");
        Cinema cinema = cinemaCtrler.readByCinemaNo(cinemaNo);


        if (cinemaCtrler.readByAttribute(cinemaNo).isEmpty()) {
            print("Cinema is not found!");
            println("Returning to main menu");
            return getPreviousMenu();
        }

        movieCtrler.listMovies();
        int movie_id = readIntInput("Enter movie id: ");
        Movie m = movieCtrler.readByID(movie_id);
        //need to implement movie controller not done
        if (m == null) {
            print("Movie is not found!");
            println("Returning to main menu");
            return getPreviousMenu();
        } else {
            println(m.getTitle());
        }


        Date sessionDateTime = readDateTime("Enter session date and time: ");
        Enums.Day enumsDay = returnEnumsDay(sessionDateTime);


        int[] rowCol = cinemaCtrler.getSeatsByCinemaNo(cinemaNo);
        int lastSessionId = sessionCtrler.getLastSessionId();
        Session session = new Session(rowCol[0], rowCol[1], cinema, m, lastSessionId + 1, sessionDateTime, enumsDay);

        //update both session and cinema txt file
        cineplexCtrler.appendByLocation(cineplexLocation, cinemaNo, session);
        sessionCtrler.append(session);
        cinemaCtrler.cinemaUpdateSession(cinemaNo, session);

        cineplexCtrler.printAllCineplex();
        sessionCtrler.printAllSession();
        cinemaCtrler.printAllCinema();

        println("Session created successfully!");

        return getPreviousMenu();
    }

}
