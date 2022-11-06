package controller;

import modal.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

import static view.utilF.*;

public class CinemaController {
    private CineplexController cineplexController = new CineplexController();

    public final static String FILENAME = "data/cinema.txt";


        public final static int NAME = 0;
        public final static int CINEMAS = 1;

        public void append(int row ,int col,String cinemaNo, Movie movie, Enums.ClassCinema classCinema, ArrayList<Session> sessions ){
            Cinema cinema  = new Cinema(row,col,cinemaNo,movie,classCinema,sessions);

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

    public void cinemaUpdateSession(Object valueToSearch,Session newSession) {
        ArrayList<Cinema> cinemaListing = read();
        ArrayList<Session> sessionList = new ArrayList<Session>();

        for (int j=0; j<cinemaListing.size(); j++) {
            if (cinemaListing.get(j).getCinemaNo().equals((String) valueToSearch)) {
                if(cinemaListing.get(j).getSessions()  != null) {
                    sessionList = cinemaListing.get(j).getSessions(); //old list of session in cinema
                }
                sessionList.add(newSession);
                cinemaListing.get(j).setSessions(sessionList);
            }
        }
        replace(cinemaListing);

    }

    public void cinemaUpdateBySessionId(int choice,int sessionId,Object newValue) { //update cinema session arrayList
        ArrayList<Cinema> cinemaListing = read();
        ArrayList<Session> sessionList = new ArrayList<Session>();
        for (int j=0; j<cinemaListing.size(); j++) {
            sessionList =  cinemaListing.get(j).getSessions();
            if(sessionList != null) {
                for (int i = 0; i < sessionList.size(); i++) {
                    if (sessionList.get(i).getSessionId() == sessionId) {
                        if (choice == 1) {
                            cinemaListing.get(j).getSessions().get(i).setMovie((Movie) newValue);
                        } else if (choice == 2) {
                            cinemaListing.get(j).getSessions().get(i).setDateTime((Date) newValue);
                            cinemaListing.get(j).getSessions().get(i).setDay(returnEnumsDay((Date) newValue));


                        } else if (choice == 3) {
                            //return seats
                        }
                    }
                }
            }

        }
        replace(cinemaListing);


    }

    public void cinemaRemoveSession(int sessionId) {
        ArrayList<Cinema> cinemaListing = read();
        ArrayList<Session> sessionList = new ArrayList<Session>();
        for (int j=0; j<cinemaListing.size(); j++) {
            sessionList = cinemaListing.get(j).getSessions();
            if (sessionList != null) {
                for (int i = 0; i < sessionList.size(); i++) {
                    if (sessionList.get(i).getSessionId() == sessionId) {
                        cinemaListing.get(j).getSessions().remove(i);

                    }
                }
            }
        }
        replace(cinemaListing);
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

    public void printAllCinema() {
        ArrayList<Cinema> cinemaFile = read();
        println("");
        printHeader("Printing all Cinema");

        for(int a =0 ; a<cinemaFile.size();a++){
            System.out.print(cinemaFile.get(a).getCinemaNo()+ "\t");
            System.out.print(cinemaFile.get(a).getClassCinema() + "\t");
            //System.out.print(cinemaFile.get(a).getSeats() + "\t");
            if(cinemaFile.get(a).getSessions() != null) {
                System.out.print("Sessions id: ");
                for (int i = 0; i < cinemaFile.get(a).getSessions().size(); i++) {
                    System.out.print(cinemaFile.get(a).getSessions().get(i).getSessionId() + "\t");

                }
            }else{
                System.out.print("No Sessions");
            }
            System.out.printf("\n");
        }
    }





}
