package controller;

import modal.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class CinemaController {


        private CineplexController cineplexController = new CineplexController();
        public final static String FILENAME = "data/cinema.txt";


        public final static int NAME = 0;
        public final static int CINEMAS = 1;

        public void create(String cinemaNo, Movie movie, Enums.ClassCinema classCinema, ArrayList<Session> sessions ){
            Cinema cinema  = new Cinema(cinemaNo,movie,classCinema,sessions);

            // Creates an ArrayList of movie
            ArrayList<Cinema> allData = new ArrayList<Cinema>();
            File tempFile = new File(FILENAME);

            // If it exists then read() the existing data
            if (tempFile.exists())
                allData = read();
            try {
                // Write the data to the movie
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
                allData.add(cinema);
                out.writeObject(allData);
                out.flush();
                out.close();
            } catch (IOException e) {
                // ignore error
            }

        }

    /**
     * READ and return every Cinema in the Database file
     * @return Model.{@link Cinema}    Return list of Cinemas if found, else empty list
     */
    public ArrayList<Cinema> read() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
            ArrayList<Cinema> cinemaListing = (ArrayList<Cinema>) ois.readObject();
            ois.close();
            return cinemaListing;
        } catch (ClassNotFoundException | IOException e) {
            // ignore error
        }
        return new ArrayList<Cinema>();
    }

    public ArrayList<Cinema> readByAttribute(Object valueToSearch) {
        ArrayList<Cinema> returnData = new ArrayList<Cinema>();
        ArrayList<Cinema> cinemaListing = read();
        Cinema cinema = null;

        for (int j=0; j<cinemaListing.size(); j++){
            cinema = cinemaListing.get(j);
            if (cinema.getCinemaNo().equals((String) valueToSearch))
                returnData.add(cinema);

        }
        return returnData;
    }

    //added
    public Cinema readByCinemaNo(String valueToSearch) {
        ArrayList<Cinema> allData = read();
        for (int i=0; i<allData.size(); i++){
            Cinema cinema = allData.get(i);
            if (cinema.getCinemaNo() == valueToSearch)
                return cinema;
        }
        return null;
    }

    /**
     * READ and return every Cinema of a given Cineplex in the Database file
     * @param name                  Name of cineplex to search for
     * @return Model.{@link Cinema}    Return list of Cinemas if found, else empty list
     */
    public ArrayList<Cinema> readByCineplexName(String name){
        ArrayList<Cinema> returnData = new ArrayList<Cinema>();
        ArrayList<Cineplex> cineplexListing = this.cineplexController.read();
        Cineplex cineplex = null;

        for (int i=0; i<cineplexListing.size(); i++){
            cineplex = cineplexListing.get(i);
            if(cineplex.getLocation().equals(name)){
                cineplex.getCinemas().forEach(n->returnData.add(n));
            }
        }
        return returnData;
    }






}
