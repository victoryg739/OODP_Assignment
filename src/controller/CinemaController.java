package controller;

import modal.Cinema;
import modal.Cineplex;
import modal.Enums;
import modal.Movie;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class CinemaController {

    private CineplexController cineplexCtrl;


        public final static String FILENAME = "data/cinema.txt";


        public final static int NAME = 0;
        public final static int CINEMAS = 1;

        public void create(){

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





}
