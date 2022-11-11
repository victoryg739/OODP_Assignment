package view.admin;

import controller.CinemaController;
import controller.CineplexController;
import controller.MovieController;
import controller.SessionController;
import view.MenuBase;

import java.util.Date;

import static view.utilF.*;
/**
 * Menu to update session from data file
 *
 * @author Victor Yoong
 * @version 1.0
 * @since 2022-08-11
 */
public class MenuStaffMovieSessionUpdate extends MenuBase {

    private CineplexController cineplexCtrler = new CineplexController();
    private CinemaController cinemaCtrler = new CinemaController();
    private SessionController sessionCtrler = new SessionController();
    private MovieController movieCtrler = new MovieController();
    public MenuStaffMovieSessionUpdate(MenuBase initialMenu) {
        super(initialMenu);
    }

    /**
     * Display updating Session menu
     * User can choose to update by movieID or session date
     *
     * @return configure menu
     */
    public MenuBase execute() {
        if (sessionCtrler.sessionIsEmpty()) {
            System.out.println("There is no sessions found!");
            return getPreviousMenu();
        }
        printHeader("Updating Session");
        sessionCtrler.printAllSession();

        int session_id = readIntInput("Enter session id: ");
        if (sessionCtrler.readById(session_id) == null) {
            System.out.println("Session id does not exist!\n" +
                    "Returning to menu...");
            return getPreviousMenu();
        }


        int choice = readIntInput("Select variable to update: \n" +
                "1. Movie\n" +
                "2. Date & Time \n");

        switch (choice) {
            case 1:
                int movie_id = readIntInput("Enter new Movie id: ");
                if (movieCtrler.readByID(movie_id) == null) {
                    System.out.println("Movie does not exist!\n" +
                            "Returning to menu... ");
                    return getPreviousMenu();
                }
                ;
                sessionCtrler.updateById(1, session_id, movieCtrler.readByID(movie_id));
                cinemaCtrler.cinemaUpdateBySessionId(1, session_id, movieCtrler.readByID(movie_id));
                cineplexCtrler.updateCineplex(1, session_id, movieCtrler.readByID(movie_id));
                break;

            case 2:

                Date dateTime = readDateTime("Enter new Date & Time: ");
                sessionCtrler.updateById(2, session_id, dateTime);
                cinemaCtrler.cinemaUpdateBySessionId(2, session_id, dateTime);
                cineplexCtrler.updateCineplex(2, session_id, dateTime);
                break;

        }
        cineplexCtrler.printAllCineplex();
        sessionCtrler.printAllSession();
        cinemaCtrler.printAllCinema();


        System.out.println("Session " + session_id + " successfully updated!");

        return this.getPreviousMenu();
    }

}


