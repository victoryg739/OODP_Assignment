package controller;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

import static view.utilF.*;

/**
 /**
 * The Cinema controller class, of the program, controlling each of the Cinema
 * Write, read, replace Cinema into database
 * Also controls the logic and control of the Cinema object
 *
 * @author Victor Yoong
 * @version 1.0
 * @since 2022-08-11
 */

public class CinemaController {
    private CineplexController cineplexController = new CineplexController();

    /**
     * Append a new cinema object into the database file
     * If attributes are not allowed, throw error and do nothing
     * If Database file exist, existing records are read and new Cinema object is appended before saving
     * If Database file does not exist, Cinema object will be written to a new file and saved
     *
     * @param row         This is cinema row
     * @param col         This is cinema column
     * @param cinemaNo    This is cinema number
     * @param classCinema This is cinema class
     * @param sessions    This is cinema session
     */
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

    /**
     * Replace cinema database file
     *
     * @param data arraylist of cinema data
     */
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
        }
    }

    /**
     * Read and return every Cinema in the Database file
     *
     * @return database of cinema    if empty return arraylist
     */
    public ArrayList<Cinema> read() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Constant.CINEMAFILE));
            ArrayList<Cinema> cinemaListing = (ArrayList<Cinema>) ois.readObject();
            ois.close();
            return cinemaListing;
        } catch (ClassNotFoundException | IOException e) {


        }
        return new ArrayList<Cinema>();
    }

    /**
     * Return arraylist of cinema corresponding with that cinema number
     *
     * @param valueToSearch This is an object parse
     * @return arraylist of cinema
     */
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

    /**
     * Update session in cinema database based on cinema number
     *
     * @param valueToSearch This is an object parse
     * @param newSession    This is the new session object
     */
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

    /**
     * Update session in cinema database based on session id
     *
     * @param choice    This is the choice to update which object to update
     * @param sessionId This is the sessionId
     * @param newValue  This is the new value object to update to
     */
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

    /**
     * Remove session in cinema database based on session id
     *
     * @param sessionId This is the sessionId
     */
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


    /**
     * Return cinema object based on cinema number
     *
     * @param valueToSearch This is the value to match the cinema number
     * @return cinema object
     */
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
     * Return row and column for the seating plan
     *
     * @param cinemaNo This is the value to match the cinema number
     * @return array of int of row and column
     */
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

    /**
     * Print all cinema information
     */
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
