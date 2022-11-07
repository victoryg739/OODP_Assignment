package view.admin;

//import controller.AdminController;
import controller.*;
import modal.*;
import view.MenuBase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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


    public MenuBase execute() {
        printHeader("Creating a new movie session");
        println("List of Cineplexes");
        ArrayList<Cineplex> cineplexArray = cineplexCtrler.read(); //using cinplexcontroller to read the cineplex object from the dat file
        if (cineplexArray.isEmpty()) {
            println("No cineplexes found!");
            return getPreviousMenu();
        }

        for(int i = 0; i<cineplexArray.size(); i++) {
            cineplexArray.get(i);
            print(cineplexArray.get(i).getLocation()+":");
            ArrayList<Cinema> cinemaArray = cineplexArray.get(i).getCinemas();
            for(int j= 0;j < cinemaArray.size();j++){
                print(cinemaArray.get(j).getCinemaNo()+ " ");

            }
            print("");
        }



        String cineplexLocation = read("Enter Cineplex Location:");
        Cineplex cineplex  = cineplexCtrler.readByLocation(cineplexLocation);


        if (cineplex == null) {
            println("Cineplex is not found!");
            println("Returning to main menu");
            return getPreviousMenu();
        }

        println("\nCinemas in " + cineplex.getLocation()+ ":");
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
        int movie_id  = readIntInput("Enter movie id: ");
        Movie m  = movieCtrler.readByID(movie_id);
        //need to implement movie controller not done
        if (m == null) {
            print("Movie is not found!");
            println("Returning to main menu");
            return getPreviousMenu();
        }else{
            println(m.getTitle());
        }


        Date sessionDateTime  = readDateTime("Enter session date and time: ");
        Enums.Day enumsDay  = returnEnumsDay(sessionDateTime);

        //take Session datetime convert to this format
//        DateFormat outputFormat = new SimpleDateFormat("dd MM yyyy");
//        HolidayController h = new HolidayController();
//        boolean test = false;
//        String formattedDate = outputFormat.format(sessionDateTime);
//        Date finalDate = new Date();
//        print(formattedDate);
//        try {
//            finalDate = outputFormat.parse(formattedDate);
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//
//        test = h.isHoliday(finalDate);
//
//
//        System.out.println("hhhh\t"+test);

        int[] rowCol =cinemaCtrler.getSeatsByCinemaNo(cinemaNo);
        int lastSessionId = sessionCtrler.getLastSessionId();
        print(rowCol[0] + " " + rowCol[1]);
        Session session = new Session(rowCol[0],rowCol[1],cinema,m,lastSessionId+1, sessionDateTime,enumsDay);

        //update both session and cinema txt file
        sessionCtrler.append(session);
        cinemaCtrler.cinemaUpdateSession(cinemaNo,session);

        sessionCtrler.printAllSession();
        cinemaCtrler.printAllCinema();

        println("Session created successfully!");

        return getPreviousMenu();
        }

}
