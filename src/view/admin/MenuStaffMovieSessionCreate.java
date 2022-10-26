package view.admin;

import controller.AdminController;
import modal.Cineplex;
import view.MenuBase;

import static view.utilF.read;
import java.util.ArrayList;
public class MenuStaffMovieSessionCreate extends MenuBase {

        public MenuStaffMovieSessionCreate(MenuBase initialMenu) {
            super(initialMenu);
        }


        public MenuBase execute() {
            System.out.println("Creating Session...\n\n");

            System.out.println("Cineplex List:\n");
            ArrayList<Cineplex> cineplexList = cineplexCtrl.read();
            if (cineplexList.isEmpty()) {
                System.out.println("There are no cineplexes registered!");
                return;
            }
            cineplexList.forEach(Cineplex -> printCineplex(Cineplex));
            System.out.println("\nEnter Cineplex Name:");
            String cineplexName = InputController.getStringFromUser();
            Cineplex cineplex = cineplexCtrl.readByName(cineplexName);
            if (cineplex == null) {
                System.out.println("Cineplex does not exist!\n" +
                        "Returning to menu...");
                return;
            }

            System.out.println("\nCinemas in " + cineplex.getName() + ": \n");
            ArrayList<Cinema> cinemaList = cineplex.getCinemas();
            cinemaList.forEach(Cinema -> printCinemaCode(Cinema));

            System.out.println("Enter cinema code: ");
            String cinemaCode = InputController.getStringFromUser();
            if (cinemaCtrl.readByAttribute(0, cinemaCode).isEmpty()) {
                System.out.println("Cinema does not exist!\n"+
                        "Returning to menu...");
                return;
            }

            System.out.println("Enter movie id: ");
            int movie_id = InputController.getIntFromUser();
            if (movieCtrl.readByID(movie_id) == null) {
                System.out.println("Movie does not exist!\n"+
                        "Returning to menu...");
                return;
            };

            System.out.println("Enter session date and time: ");
            LocalDateTime sessionDateTime = InputController.getDateTimeFromUser();
            Movie movie = movieCtrl.readByID(movie_id);
            sessionCtrl.create(cinemaCode, movie, sessionDateTime);

            System.out.println("Session successfully created!");

            return this.getPreviousMenu();
        }

}
