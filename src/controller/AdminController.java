package controller;

import modal.*;
import java.io.*;
import java.util.*;
import modal.Enums.*;

// Database for Admin control //
public class AdminController {
    public final static String FILENAME = "data/movies.txt";

    public AdminController() {
    }

    // Creates a movie and writes it to movies.txt
    public void createMovie(String title, MovieType type, MovieRating rating, String synopsis, int runtime, Date DateStart, Date DateEnd, ArrayList<String> cast, String director) {
            // Creates a movie object
            Movie movie = new Movie(title, type, rating, synopsis, runtime, DateStart, DateEnd, director, cast);
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


    // Read a movie object from movies.txt//
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
