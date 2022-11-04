package view.admin;

import controller.CinemaController;
import controller.CineplexController;
import controller.MovieController;
import controller.SessionController;
import modal.Cinema;
import modal.Cineplex;
import view.MenuBase;

import java.util.ArrayList;

import static view.utilF.read;
import static view.utilF.readIntInput;

public class MenuStaffMovieSessionRemove extends MenuBase {

    public MenuStaffMovieSessionRemove(MenuBase initialMenu) {
        super(initialMenu);
    }

    private CineplexController cineplexCtrler = new CineplexController();
    private CinemaController cinemaCtrler = new CinemaController();
    private SessionController sessionCtrler = new SessionController();
    public MenuBase execute() {
        System.out.println("Deleting Session...\n\n");
        System.out.println("\nCineplex List:");
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
                System.out.print(cinemaArray.get(j).getCinemaNo()+ " ");

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

        int sessionId = readIntInput("Enter Session Id:");
        if (sessionCtrler.readById(sessionId) == null) {
            System.out.println("Session id does not exist!\n" +
                    "Returning to menu...");
            return getPreviousMenu();
        }
        sessionCtrler.remove(sessionId);
        cinemaCtrler.cinemaRemoveSession(sessionId);

        sessionCtrler.printAllSession();
        cinemaCtrler.printAllCinema();

        return this.getPreviousMenu();
    }

}


