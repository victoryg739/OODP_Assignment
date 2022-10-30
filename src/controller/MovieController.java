package controller;

import modal.*;
import java.io.*;
import java.util.*;
import modal.Enums.*;

/* ToDO list:
    1. Change getLastID code
    2. Delete by ID (change the code)


*/
// Database for Admin control //
public class MovieController {
    public final static String FILENAME = "data/movies.txt";

    public MovieController() {
    }

    // Creates a movie and writes it to movies.txt
    public void createMovie(String title, MovieType type, MovieRating rating, String synopsis, int runtime, Date DateStart, Date DateEnd, ArrayList<String> cast, String director) {
            // Creates a movie object
            Movie movie = new Movie(getLastId() +1, title, type, rating, synopsis, runtime, DateStart, DateEnd, director, cast);
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



    public int getLastId(){
        int lastId = -1;
        int movieID;
        ArrayList<Movie> allData = read();
        for (int i=0; i<allData.size(); i++){
            movieID = allData.get(i).getID();
            if (movieID > lastId)
                lastId = movieID;
        }
        return lastId;
    }

    public void deleteMovie(int id) {
        ArrayList<Movie> allData = read();
        ArrayList<Movie> returnData = new ArrayList<Movie>();

        for (int i=0; i<allData.size(); i++){
            Movie m = allData.get(i);
            if (!(m.getID() == id))
                returnData.add(m);
        }


        replaceExistingFile(FILENAME, returnData);
    }

    /**
     * Overwrite Database file with new data of list of Admin
     * @param filename      Filename to check for
     * @param data          New ArrayList of Movies to be written to the file
     */
    public void replaceExistingFile(String filename, ArrayList<Movie> data) {
        File tempFile = new File(filename);
        if (tempFile.exists())
            tempFile.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(data);
            out.flush();
            out.close();
        } catch (IOException e) {
            //
        }
    }

    /**
     * READ and return every Movie of a given ID in the Database file
     * @param valueToSearch     Id of movie to search for
     * @return Movie            Return Movie if found, else null object
     */
    public Movie readByID(int valueToSearch) {
        ArrayList<Movie> allData = read();
        for (int i=0; i<allData.size(); i++){
            Movie m = allData.get(i);
            if (m.getID() == valueToSearch)
                return m;
        }
        return null;
    }


}
