package controller;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

import static view.utilF.*;


public class CinemaController {
    private CineplexController cineplexController = new CineplexController();

    public void append(int row, int col, String cinemaNo, Enums.ClassCinema classCinema, ArrayList<Session> sessions) {
        Cinema cinema = new Cinema(row, col, cinemaNo, classCinema, sessions);

        // Creates an ArrayList of movie
        ArrayList<Cinema> allData = new ArrayList<Cinema>();
        File tempFile = new File(Constant.CINEMAFILE);

        // If it exists then read() the existing data
        if (tempFile.exists())
            allData = read();
        try {
            // Write the data to the movie
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(Constant.CINEMAFILE));
            allData.add(cinema);
            out.writeObject(allData);
            out.flush();
            out.close();
        } catch (IOException e) {
            // ignore error
        }

    }

    public void replace(ArrayList<Cinema> data) {
        File tempFile = new File(Constant.CINEMAFILE);
        if (tempFile.exists())
            tempFile.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(Constant.CINEMAFILE));
            out.writeObject(data);
            out.flush();
            out.close();
        } catch (IOException e) {
            //
        }
    }

    /**
     * READ and return every Cinema in the Database file
     *
     * @return Model.{@link Cinema}    Return list of Cinemas if found, else empty list
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Cinema> read() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Constant.CINEMAFILE));
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

        for (int j = 0; j < cinemaListing.size(); j++) {
            cinema = cinemaListing.get(j);
            if (cinema.getCinemaNo().equals((String) valueToSearch))
                returnData.add(cinema);

        }
        return returnData;
    }

    public void cinemaUpdateSession(Object valueToSearch, Session newSession) {
        ArrayList<Cinema> cinemaListing = read();
        ArrayList<Session> sessionList = new ArrayList<Session>();

        for (int j = 0; j < cinemaListing.size(); j++) {
            if (cinemaListing.get(j).getCinemaNo().equals((String) valueToSearch)) {
                if (cinemaListing.get(j).getSessions() != null) {
                    sessionList = cinemaListing.get(j).getSessions(); //old list of session in cinema
                }
                sessionList.add(newSession);
                cinemaListing.get(j).setSessions(sessionList);
            }
        }
        replace(cinemaListing);

    }


    public void cinemaUpdateBySessionId(int choice, int sessionId, Object newValue) { //update cinema session arrayList
        ArrayList<Cinema> cinemaListing = read();
        ArrayList<Session> sessionList = new ArrayList<Session>();
        for (int j = 0; j < cinemaListing.size(); j++) {
            sessionList = cinemaListing.get(j).getSessions();
            if (sessionList != null) {
                for (int i = 0; i < sessionList.size(); i++) {
                    if (sessionList.get(i).getSessionId() == sessionId) {
                        if (choice == 1) {
                            cinemaListing.get(j).getSessions().get(i).setMovie((Movie) newValue);
                        } else if (choice == 2) {
                            cinemaListing.get(j).getSessions().get(i).setDateTime((Date) newValue);
                            cinemaListing.get(j).getSessions().get(i).setDay(returnEnumsDay((Date) newValue));


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
        for (int j = 0; j < cinemaListing.size(); j++) {
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
        for (int i = 0; i < allData.size(); i++) {
            Cinema cinema = allData.get(i);
            if (cinema.getCinemaNo().equals(valueToSearch))
                return cinema;
        }
        return null;
    }

    /**
     * READ and return every Cinema of a given Cineplex in the Database file
     *
     * @param name Name of cineplex to search for
     * @return Model.{@link Cinema}    Return list of Cinemas if found, else empty list
     */
    public ArrayList<Cinema> readByCineplexName(String name) {
        ArrayList<Cinema> returnData = new ArrayList<Cinema>();
        ArrayList<Cineplex> cineplexListing = cineplexController.read();
        ArrayList<Cinema> cinemaListing = this.read();


        Cineplex cineplex = null;
        ArrayList<Cinema> cinema = new ArrayList<Cinema>();
        for (int i = 0; i < cineplexListing.size(); i++) {
            cineplex = cineplexListing.get(i);
            if (cineplex.getLocation().equals(name)) { // Find list of cineplex of same name
                cinema = cineplex.getCinemas();

            }
        }
        for (int j = 0; j < cinema.size(); j++) {
            for (int a = 0; a < cinemaListing.size(); a++) {
                if (cinemaListing.get(a).getCinemaNo().equals(cinema.get(j).getCinemaNo())) {
                    returnData.add(cinemaListing.get(a));
                }
            }

        }

        return returnData;
    }

    public int[] getSeatsByCinemaNo(String cinemaNo) {
        ArrayList<Cinema> cf = read();
        int[] rowCol = new int[2];
        for (int a = 0; a < cf.size(); a++) {
            if (cf.get(a).getCinemaNo().equals(cinemaNo)) {
                rowCol[0] = cf.get(a).getSeats().size();
                rowCol[1] = cf.get(a).getSeats().get(0).size();
                return rowCol;
            }
        }
        return rowCol;

    }

    public void printAllCinema() {
        ArrayList<Cinema> cf = read();
        println("");
        printHeader("Printing all Cinema");
        System.out.printf(" %5s | %10s | %6s | %-30s %n", "CinNo", "Class", "Seats", "Sessions");
        System.out.printf("---------------------------------------------------------------\n");


        for (int a = 0; a < cf.size(); a++) {

            System.out.printf(" %5s | %10s | %3s,%3s |", cf.get(a).getCinemaNo(), cf.get(a).getClassCinema(), cf.get(a).getSeats().size(), cf.get(a).getSeats().get(0).size());

            if (cf.get(a).getSessions() != null) {
                for (int i = 0; i < cf.get(a).getSessions().size(); i++) {
                    if (i != 0) {
                        System.out.printf(",");
                    }
                    System.out.print(cf.get(a).getSessions().get(i).getSessionId());
                }
            } else {
                System.out.print("No Sessions");
            }
            System.out.printf("\n");
        }
    }
}
