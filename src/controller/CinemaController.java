package controller;

import modal.*;

import java.io.*;
import java.util.ArrayList;

public class CinemaController {


        private CineplexController cineplexController = new CineplexController();

        public final static String FILENAME = "data/cinema.txt";


        public final static int NAME = 0;
        public final static int CINEMAS = 1;

        public void append(String cinemaNo, Movie movie, Enums.ClassCinema classCinema, ArrayList<Session> sessions ){
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

    public void replace(ArrayList<Cinema> data) {
        File tempFile = new File(FILENAME);
        if (tempFile.exists())
            tempFile.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
            out.writeObject(data);
            out.flush();
            out.close();
        } catch (IOException e) {
            //
        }
    }

    /**
     * READ and return every Cinema in the Database file
     * @return Model.{@link Cinema}    Return list of Cinemas if found, else empty list
     */
    @SuppressWarnings("unchecked")
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

    public boolean cinemaUpdateSession(Object valueToSearch,Session newSession) {
        ArrayList<Cinema> cinemaListing = read();
        ArrayList<Session> sessionList = new ArrayList<Session>();

        boolean flag = false;
        for (int j=0; j<cinemaListing.size(); j++) {
            if (cinemaListing.get(j).getCinemaNo().equals((String) valueToSearch)) {
                if(cinemaListing.get(j).getSessions()  != null) {
                    sessionList = cinemaListing.get(j).getSessions(); //old list of session in cinema
                }
                sessionList.add(newSession);
                cinemaListing.get(j).setSessions(sessionList);
                flag = true;
            }
        }
        if (flag  == false){
            return false;
        }
        else {
            File tempFile = new File(FILENAME);
            if (tempFile.exists())
                tempFile.delete();
            try {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
                out.writeObject(cinemaListing);
                out.flush();
                out.close();

            } catch (IOException e) {
                //
            }
            return true;
        }


    }

    //added
    public Cinema readByCinemaNo(String valueToSearch) {
        ArrayList<Cinema> allData = read();
        for (int i=0; i<allData.size(); i++){
            Cinema cinema = allData.get(i);
            if (cinema.getCinemaNo().equals(valueToSearch))
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
        ArrayList<Cineplex> cineplexListing = cineplexController.read();
        ArrayList<Cinema> cinemaListing = this.read();


        Cineplex cineplex = null;
        ArrayList<Cinema> cinema = new ArrayList<Cinema>();
        for (int i=0; i<cineplexListing.size(); i++){
            cineplex = cineplexListing.get(i);
            if(cineplex.getLocation().equals(name)){ // Find list of cineplex of same name
                cinema = cineplex.getCinemas();

            }
        }
        for(int j =0; j < cinema.size(); j++) {
            for(int a =0; a < cinemaListing.size(); a++){
                if(cinemaListing.get(a).getCinemaNo().equals(cinema.get(j).getCinemaNo())){
                    returnData.add(cinemaListing.get(a));
                }
            }

        }

        return returnData;
    }





}
