package view.admin;

import controller.CinemaController;
import controller.CineplexController;
import controller.SessionController;
import view.MenuBase;

import static view.utilF.printHeader;
import static view.utilF.readIntInput;
/**
 * Menu to remove session from data file
 *
 * @author Victor Yoong
 * @version 1.0
 * @since 2022-08-1
 * */

public class MenuStaffMovieSessionRemove extends MenuBase {

    private CineplexController cineplexCtrler = new CineplexController();
    private CinemaController cinemaCtrler = new CinemaController();
    private SessionController sessionCtrler = new SessionController();
    public MenuStaffMovieSessionRemove(MenuBase initialMenu) {
        super(initialMenu);
    }

    /**
     * Display remove Session menu
     * User can choose to what session ID to delete from
     *
     * @return configure menu
     */
    public MenuBase execute() {
        if (sessionCtrler.sessionIsEmpty()) {
            System.out.println("There is no sessions found!");
            return getPreviousMenu();
        }
        printHeader("Deleting Session");
        sessionCtrler.printAllSession();


        int sessionId = readIntInput("Enter Session Id:");
        if (sessionCtrler.readById(sessionId) == null) {
            System.out.println("Session id does not exist!\n" +
                    "Returning to menu...");
            return getPreviousMenu();
        }
        sessionCtrler.remove(sessionId);
        cinemaCtrler.cinemaRemoveSession(sessionId);
        cineplexCtrler.removeSession(sessionId);

        cineplexCtrler.printAllCineplex();
        sessionCtrler.printAllSession();
        cinemaCtrler.printAllCinema();

        return this.getPreviousMenu();
    }

}


