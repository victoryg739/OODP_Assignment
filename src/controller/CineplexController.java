package controller;

import modal.Cinema;
import modal.Cineplex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class CineplexController {

    /**
     * File name of Database file to access
     */
    public final static String FILENAME = "data/cineplex.txt";

    /**
     * Declaring constant for better readability and easier referencing to attribute
     */
    public final static int NAME = 0;
    public final static int CINEMAS = 1;


    /**
     * CREATE a new Cineplex and add it into the database file
     * Attributes are validated before creation
     * If attributes are not allowed, throw error and do nothing
     * If Database file exist, existing records are read and new Cineplex object is aopended before saving
     * If Database file does not exist, Cineplex object will be written to a new file and saved
     * @param name      This cineplex's name
     * @param cinemas   This cineplex's list of cinemas
     */
    public void create(String name, ArrayList<Cinema> cinemas) {
        if (CineplexesLayer.isCineplexValid(name, cinemas)) {
            Cineplex cineplex = new Cineplex(name, cinemas);
            ArrayList<Cineplex> allData = new ArrayList<Cineplex>();
            File tempFile = new File(FILENAME);
            if (tempFile.exists())
                allData = read();
            try {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
                allData.add(cineplex);
                out.writeObject(allData);
                out.flush();
                out.close();
            } catch (IOException e) {
                // ignore error
            }
        } else {
            // do nothing
        }
    }


}