package controller;

import modal.*;
import java.io.*;
import java.util.*;


// Database for Admin control //
public class AdminController {
    public final static String FILENAME = "data/movies.txt";

    public AdminController() {
    }

    public void create(String title) {
            // Creates a movie object
            Movie movie = new Movie(title);
            // Creates an ArrayList of movie
            ArrayList<Movie> allData = new ArrayList<Movie>();
            File tempFile = new File(FILENAME);


            // If it exists then read() the existing data
            if (tempFile.exists())
                allData = read();
            try {
            // Write the data to the movie
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
                allData.add(movie);
                out.writeObject(allData);
                out.flush();
                out.close();
            } catch (IOException e) {
                // ignore error
            }
        }

    public ArrayList<Movie> read() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
            ArrayList<Movie> movieListing = (ArrayList<Movie>) ois.readObject();
            ois.close();
            return movieListing;
        } catch (ClassNotFoundException | IOException e) {
            // ignore error
        }
        return new ArrayList<Movie>();
    }


}
