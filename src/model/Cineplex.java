package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a cineplex of Cathay Cineplexes
 * A cineplex can 1 or more cinemas
 *
 * @author Victor Yoong
 * @version 1.0
 * @since 2022-08-11
 */
public class Cineplex implements Serializable {

    private String location;
    private ArrayList<Cinema> cinemas;


    private ArrayList<Session> sessions;

    /**
     * Creates a new cineplex with location, with multiple cinemas and sessions
     *
     * @param location This is the location of cineplex.
     * @param cinemas  This is the Cinemas contains inside that cineplex.
     * @param sessions This is the Sessions contains inside that cineplex
     */
    public Cineplex(String location, ArrayList<Cinema> cinemas, ArrayList<Session> sessions) {
        this.location = location;
        this.cinemas = cinemas;
        this.sessions = sessions;
    }

    /**
     * Gets the location of the cineplex
     *
     * @return location.
     */
    public String getLocation() {
        return location;
    }


    /**
     * Gets the cinemas of the cineplex
     *
     * @return arraylist of cinemas.
     */
    public ArrayList<Cinema> getCinemas() {
        return cinemas;
    }

    /**
     * Change the cinemas of this cineplex
     *
     * @param cinemas This is the change cinemas.
     *                Should be in an arraylist
     */
    public void setCinemas(ArrayList<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    /**
     * Gets the sessions of the cineplex
     *
     * @return arraylist of sessions.
     */
    public ArrayList<Session> getSessions() {
        return sessions;
    }

    /**
     * Change the sessions of this cineplex
     *
     * @param sessions This is the change sessions.
     *                 Should be in an arraylist
     */
    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }

}
