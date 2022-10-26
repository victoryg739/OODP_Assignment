package controller;

import modal.Cinema;
import modal.Cineplex;
import modal.Movie;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;



public class CineplexController {


    public final static String FILENAME = "data/cineplex.txt";


    public final static int NAME = 0;
    public final static int CINEMAS = 1;


    public void create(String location, ArrayList<Cinema> cinemas) {
        // Creates a movie object
        Cineplex cineplex = new Cineplex(location,cinemas);

        // Creates an ArrayList of movie
        ArrayList<Cineplex> allData = new ArrayList<Cineplex>();
        File tempFile = new File(FILENAME);

        // If it exists then read() the existing data
        if (tempFile.exists())
            allData = read();
        try {
            // Write the data to the movie
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
            allData.add(cineplex);
            out.writeObject(allData);
            out.flush();
            out.close();
        } catch (IOException e) {
            // ignore error
        }
    }

    public ArrayList<Cineplex> read() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
            ArrayList<Cineplex> cineplex = (ArrayList<Cineplex>) ois.readObject();
            ois.close();
            return cineplex;
        } catch (ClassNotFoundException | IOException e) {
            // ignore error
        }
        return new ArrayList<Cineplex>();
    }

    /**
     * READ and return Cineplex based on name passed in the Database file
     * @param name          Name of cineplex to search for
     * @return Cineplex     Return Cineplex if found, else null object
     */
    public Cineplex readByLocation(String location){
        ArrayList<Cineplex> allData = read();
        for (int i=0; i<allData.size(); i++){
            Cineplex cin = allData.get(i);
            if (cin.getLocation().equals(location))
                return cin;
        }
        return null;
    };

    /**
     * READ and return every Cinema based on a certain value of a given attribute in the Database file
     * @param col                   Given attribute to be check for (based on constant as defined)
     * @param valueToSearch         Value of given attribute to search for
     * @return Model.{@link Cinema}    Return list of Cinemas if any, else empty list
     */
/*    public ArrayList<Cinema> readByAttribute(int col, Object valueToSearch) {
        ArrayList<Cinema> returnData = new ArrayList<Cinema>();
        ArrayList<Cinema> cinemaListing = read();
        Cinema cinema = null;

        for (int j=0; j<cinemaListing.size(); j++){
            cinema = cinemaListing.get(j);
            switch (col) {
                case CODE:
                    if (cinema.getCode().equals((String) valueToSearch))
                        returnData.add(cinema);
                    break;
                case CINEMA_TYPE:
                    if (cinema.getCinemaType().equals((CinemaType) valueToSearch))
                        returnData.add(cinema);
                    break;
                default:
                    break;
            }
        }
        return returnData;
    }*/

}