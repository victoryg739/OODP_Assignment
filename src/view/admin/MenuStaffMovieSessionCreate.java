package view.admin;

//import controller.AdminController;
import controller.CineplexController;
import modal.Cinema;
import modal.Cineplex;
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


    public MenuBase execute() {
        System.out.println("Creating a new session");
        System.out.println("List of Cinplexes");
        ArrayList<Cineplex> cinplexArray = cineplexCtrler.read(); //using cinplexcontroller to read the cineplex object from the dat file
        if (cinplexArray.isEmpty()) {
            System.out.println("There are no cineplexes registered!");
            return getPreviousMenu();
        }
        cinplexArray.forEach(Cineplex -> System.out.println("Location:" + Cineplex.getCinemas()));



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

        int cinemaNo = readIntInput("Enter Cinema No: ");

/*        if (cineplexCtrler.readByAttribute(0, cinemaCode).isEmpty()) {
            System.out.println("Cinema does not exist!\n"+
                    "Returning to menu...");
            return;
        }*/


        int movie_id  = readIntInput("Enter movie id: ");
        //need to implement movie controller not done
//        if (movieCtrl.readByID(movie_id) == null) {
//            System.out.println("Movie does not exist!\n"+
//                    "Returning to menu...");
//            return;
//        };

        System.out.println("Enter session date and time: ");



          return getPreviousMenu();
        }

}
