package view.admin;

import controller.CinemaController;
import controller.CineplexController;
import controller.MovieController;
import controller.SessionController;
import view.MenuBase;


import java.util.Date;

import static view.utilF.*;

public class MenuStaffMovieSessionUpdate extends MenuBase {

    public MenuStaffMovieSessionUpdate(MenuBase initialMenu) {
        super(initialMenu);
    }
    private CineplexController cineplexCtrler = new CineplexController();
    private CinemaController cinemaCtrler = new CinemaController();
    private SessionController sessionCtrler = new SessionController();
    private MovieController movieCtrler = new MovieController();


    public MenuBase execute() {
        printHeader("Updating Session");
        sessionCtrler.printAllSession();

//        System.out.println("List of Cineplexes");
//        ArrayList<Cineplex> cineplexArray = cineplexCtrler.read(); //using cinplexcontroller to read the cineplex object from the dat file
//        if (cineplexArray.isEmpty()) {
//            System.out.println("There are no cineplexes registered!");
//            return getPreviousMenu();
//        }
//
//        for(int i = 0; i<cineplexArray.size(); i++) {
//            cineplexArray.get(i);
//            System.out.print(cineplexArray.get(i).getLocation()+":");
//            ArrayList<Cinema> cinemaArray = cineplexArray.get(i).getCinemas();
//            for(int j= 0;j < cinemaArray.size();j++){
//                System.out.print(cinemaArray.get(j).getCinemaNo()+ " ");
//
//            }
//            System.out.print("\n");
//        }
//
//
//
//        String cineplexLocation = read("Enter Cineplex Location:");
//        Cineplex cineplex  = cineplexCtrler.readByLocation(cineplexLocation);
//
//
//        if (cineplex == null) {
//            System.out.println("Cineplex does not exist!\n" +
//                    "Returning to menu...");
//            return getPreviousMenu();
//        }
//
//        System.out.println("\nCinemas in " + cineplex.getLocation()+ ": \n");
//        ArrayList<Cinema> cinemaArray = cineplex.getCinemas();
//        cinemaArray.forEach(Cinema -> System.out.println("Cinema No:" + Cinema.getCinemaNo()));

        int session_id = readIntInput("Enter session id: ");
        if (sessionCtrler.readById(session_id) == null) {
            System.out.println("Session id does not exist!\n" +
                    "Returning to menu...");
            return getPreviousMenu();
        }


        int choice = readIntInput("Select variable to update: \n" +
                "1. Movie\n" +
                "2. Date & Time \n");

        switch(choice) {
            case 1:
                int movie_id = readIntInput("Enter new Movie id: ");
                if (movieCtrler.readByID(movie_id) == null) {
                    System.out.println("Movie does not exist!\n"+
                            "Returning to menu... ");
                    return getPreviousMenu();
                };
                sessionCtrler.updateById(1, session_id, movieCtrler.readByID(movie_id));
                cinemaCtrler.cinemaUpdateBySessionId(1, session_id, movieCtrler.readByID(movie_id));
                break;

            case 2:

                Date dateTime = readDateTime("Enter new Date & Time: ");
                sessionCtrler.updateById(2, session_id, dateTime);
                cinemaCtrler.cinemaUpdateBySessionId(2, session_id, dateTime);

                break;

        }
        sessionCtrler.printAllSession();
        cinemaCtrler.printAllCinema();

        System.out.println("Session " + session_id + " successfully updated!");

        return this.getPreviousMenu();
    }

}


