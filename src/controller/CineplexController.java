package controller;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

import static view.utilF.*;
/**
 /**
 * The Cineplex controller class, of the program, controlling each of the cineplex
 * Write, read, replace Cineplex into database
 * Also controls the logic and control of the cineplex object
 *
 * @author Victor Yoong
 * @version 1.0
 * @since 2022-08-11
 */

public class CineplexController {


    /**
     * Appends cineplex session with location
     *
     * @param location This is the location of cineplex.
     * @param cinemaNo  This is the Cinemas contains inside that cineplex.
     * @param session This is the Sessions contains inside that cineplex
     */
    public void appendByLocation(String location, String cinemaNo, Session session) {
        ArrayList<Cineplex> allData = read();

        for (int i = 0; i < allData.size(); i++) {
            if (location.equals(allData.get(i).getLocation())) {
                ArrayList<Session> tempS = new ArrayList<Session>();

                if (allData.get(i).getSessions() != null) {
                    tempS = allData.get(i).getSessions();
                }
                tempS.add(session);
                allData.get(i).setSessions(tempS);
            }
        }
        for (int i = 0; i < allData.size(); i++) {
            for (int j = 0; j < allData.get(i).getCinemas().size(); j++) {
                if (allData.get(i).getCinemas().get(j).getCinemaNo().equals(cinemaNo)) {
                    ArrayList<Session> tempS = new ArrayList<Session>();

                    if (allData.get(i).getCinemas().get(j).getSessions() != null) {
                        tempS = allData.get(i).getCinemas().get(j).getSessions();
                    }
                    tempS.add(session);
                    allData.get(i).getCinemas().get(j).setSessions(tempS);
                }


            }
        }
        replace(allData);
    }

    /**
     * Update cineplex information with sessionId
     *
     * @param choice This is the choice to update by either movie or the day
     * @param sessionId  This is the sessionID in sessions
     * @param newValue Any new value object parse in
     */

    public void updateCineplex(int choice, int sessionId, Object newValue) {
        ArrayList<Cineplex> allData = read();

        for (int i = 0; i < allData.size(); i++) {
            if (allData.get(i).getSessions() != null) {
                for (int j = 0; j < allData.get(i).getSessions().size(); j++) {
                    if (allData.get(i).getSessions().get(j).getSessionId() == sessionId) {
                        if (choice == 1) {
                            allData.get(i).getSessions().get(j).setMovie((Movie) newValue);
                        } else if (choice == 2) {
                            allData.get(i).getSessions().get(j).setDateTime((Date) newValue);
                            allData.get(i).getSessions().get(j).setDay(returnEnumsDay((Date) newValue));

                        }
                    }
                }
            }
        }

        for (int i = 0; i < allData.size(); i++) {
            for (int j = 0; j < allData.get(i).getCinemas().size(); j++) {
                if (allData.get(i).getCinemas().get(j).getSessions() != null) {
                    for (int k = 0; k < allData.get(i).getCinemas().get(j).getSessions().size(); k++) {

                        if (allData.get(i).getCinemas().get(j).getSessions().get(k).getSessionId() == sessionId) {
                            if (choice == 1) {
                                allData.get(i).getCinemas().get(j).getSessions().get(k).setMovie((Movie) newValue);
                            } else if (choice == 2) {
                                allData.get(i).getCinemas().get(j).getSessions().get(k).setDateTime((Date) newValue);
                                allData.get(i).getCinemas().get(j).getSessions().get(k).setDay(returnEnumsDay((Date) newValue));

                            }
                        }
                    }
                }
            }
        }
        replace(allData);
    }

    /**
     * Remove session inside Cineplex
     *
     * @param sessionId  This is the sessionID in sessions
     */
    public void removeSession(int sessionId) {
        ArrayList<Cineplex> allData = read();

        for (int i = 0; i < allData.size(); i++) {
            if (allData.get(i).getSessions() != null) {
                for (int j = 0; j < allData.get(i).getSessions().size(); j++) {
                    if (allData.get(i).getSessions().get(j).getSessionId() == sessionId) {
                        allData.get(i).getSessions().remove(j);
                    }
                }
            }
        }

        for (int i = 0; i < allData.size(); i++) {
            for (int j = 0; j < allData.get(i).getCinemas().size(); j++) {
                if (allData.get(i).getCinemas().get(j).getSessions() != null) {
                    for (int k = 0; k < allData.get(i).getCinemas().get(j).getSessions().size(); k++) {
                        if (allData.get(i).getCinemas().get(j).getSessions().get(k).getSessionId() == sessionId) {
                            allData.get(i).getCinemas().get(j).getSessions().remove(k);
                        }

                    }
                }
            }
        }
        replace(allData);
    }

    /**
     * Replace existing cineplex data file with new cineplex data file
     *
     * @param data  New cineplex data file
     */
    public void replace(ArrayList<Cineplex> data) {
        File tempFile = new File(Constant.CINEPLEXFILE);
        if (tempFile.exists())
            tempFile.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(Constant.CINEPLEXFILE));
            out.writeObject(data);
            out.flush();
            out.close();
        } catch (IOException e) {

        }
    }

    /**
     * Read the cineplex file
     *
     * @return cineplex data
     */
    public ArrayList<Cineplex> read() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Constant.CINEPLEXFILE));
            ArrayList<Cineplex> cineplex = (ArrayList<Cineplex>) ois.readObject();
            ois.close();
            return cineplex;
        } catch (ClassNotFoundException | IOException e) {
        }
        return new ArrayList<Cineplex>();
    }

    /**
     * Get Cineplex object by location
     *
     * @return Cineplex object
     */
    public Cineplex readByLocation(String location) {
        ArrayList<Cineplex> allData = read();
        for (int i = 0; i < allData.size(); i++) {
            Cineplex cin = allData.get(i);
            if (cin.getLocation().equals(location))
                return cin;
        }
        return null;
    }

    /**
     * Print cineplex information tied to the movieID
     *
     * @param movieId movieID of a movie
     */
    public void printByMovieId(int movieId) {
        ArrayList<Cineplex> allData = read();
        System.out.printf("| %10s | %10s | %10s | %15s | %-30s | %n", "SessionNo", "Location", "CinemaNo", "CinemaClass", "DateTime");

        String location = "";
        for (int i = 0; i < allData.size(); i++) {
            location = allData.get(i).getLocation();
            if (allData.get(i).getSessions() != null) {
                for (int j = 0; j < allData.get(i).getSessions().size(); j++) {
                    if (allData.get(i).getSessions().get(j).getMovie().getId() == movieId)
                        System.out.printf("| %10s | %10s | %10s | %15s | %30s | %n", allData.get(i).getSessions().get(j).getSessionId(), location, allData.get(i).getSessions().get(j).getCinema().getCinemaNo(), allData.get(i).getSessions().get(j).getCinema().getClassCinema(), allData.get(i).getSessions().get(j).getDateTime());
                }
            }
        }

    }

    /**
     * Returns location corresponding to the sessionID
     *
     * @return location
     */
    public String returnLocationBySessionId(int sessionId) {
        ArrayList<Cineplex> allData = read();
        String location = "";
        for (int i = 0; i < allData.size(); i++) {
            location = allData.get(i).getLocation();
            if (allData.get(i).getSessions() != null) {
                for (int j = 0; j < allData.get(i).getSessions().size(); j++) {
                    if (sessionId == allData.get(i).getSessions().get(j).getSessionId()) {
                        return location;
                    }
                }
            }
        }
        return location;
    }
    /**
     * Print all cineplex data
     *
     */
    public void printAllCineplex() {
        ArrayList<Cineplex> cp = read();
        println("");
        printHeader("Printing all Cineplexes");
        System.out.printf("| %10s | %10s | %10s | %n", "Location", "Cinema", "Sessions");
        System.out.printf("---------------------------------------------------------------\n");


        for (int a = 0; a < cp.size(); a++) {

            System.out.printf("| %10s |", cp.get(a).getLocation());

            if (cp.get(a).getCinemas() != null) {
                System.out.printf("|");
                for (int i = 0; i < cp.get(a).getCinemas().size(); i++) {
                    if (i != 0) {
                        System.out.printf(",");
                    }
                    System.out.print(cp.get(a).getCinemas().get(i).getCinemaNo());

                }
                System.out.printf("   |");
            } else {
                System.out.print("No Cinemas");
            }

            if (cp.get(a).getSessions() != null) {
                System.out.printf("|");
                for (int i = 0; i < cp.get(a).getSessions().size(); i++) {
                    if (i != 0) {
                        System.out.printf(",");
                    }
                    System.out.print(cp.get(a).getSessions().get(i).getSessionId());

                }
                System.out.printf("       |");

            } else {
                System.out.print("No Sessions  |");
            }

            System.out.printf("\n");
        }
    }


}