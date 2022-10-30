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
        Movie movie = new Movie(getLastId() + 1, title, type, rating, synopsis, runtime, DateStart, DateEnd, director, cast);
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


    public void printMovie(Movie movie){
        int id = movie.getID();
        String title = movie.getTitle();
        MovieType movieType = movie.getType();
        MovieRating movieRating = movie.getRating();
        String synopsis = movie.getSynopsis();
        int runtime = movie.runTime();
        Date DateStart = movie.getDateStart();
        Date DateEnd = movie.getDateEnd();
        String director = movie.getDirector();
        //ArrayList<String> casts = movie.getCast();
        String castString = "";
        for (int i=0; i< movie.getCast().size(); i++)
            castString = castString.concat(movie.getCast().get(i) + ",");
        castString = castString.substring(0, castString.length()-1);

        String movieString = "ID: " + id + " | " + "Title: " + title + " | " + "Type " + movieType + " | " + "Rating " + movieRating + " | " + "Synopsis: " + synopsis + " | "
                + "Runtime: " + runtime + " | " + "DateStart: " + DateStart + " | " + "DateEnd: " + DateEnd + " | " + "Director: " + director + " | "
                + "Cast: " + castString;
        System.out.println(movieString);
        System.out.println("-------------------");
    }


    // Read a movie object from movies.txt//
    public static ArrayList<Movie> read() {
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

    public Movie readByID(int valueToSearch) {
        ArrayList<Movie> allData = read();
        for (int i=0; i<allData.size(); i++){
            Movie m = allData.get(i);
            if (m.getID() == valueToSearch)
                return m;
        }
        return null;
    }
    public int getLastId() {
        int lastId = -1;
        int movieID;
        ArrayList<Movie> allData = read();
        for (int i = 0; i < allData.size(); i++) {
            movieID = allData.get(i).getID();
            if (movieID > lastId)
                lastId = movieID;
        }
        return lastId;
    }

    public void deleteMovie(int id) {
        ArrayList<Movie> allData = read();
        ArrayList<Movie> returnData = new ArrayList<Movie>();

        for (int i = 0; i < allData.size(); i++) {
            Movie m = allData.get(i);
            if (!(m.getID() == id))
                returnData.add(m);
        }


        replaceExistingFile(FILENAME, returnData);
    }

    public void updateMovie(int choice, int movieID, Object newValue) {
        ArrayList<Movie> dataList = read();
        ArrayList<Movie> updateList = new ArrayList<Movie>();

        // Delete Sessions with MovieID equal to MovieID passed in

        // Loop through the array list
        for (int i = 0; i < dataList.size(); i++) {
            // Get first object of the array list
            Movie m = dataList.get(i);
            // If the movie is the same as the UpdateMovie ID
            if (m.getID() == movieID) {
                // Start updating the values
                switch (choice) {

                    case 1:
                       m.setTitle((String) newValue);
                        break;
                    case 2:
                        m.setType((MovieType) newValue);
                        break;
                    case 3:
                        m.setSynopsis((String) newValue);
                        break;
                    case 4:
                        m.setRating((MovieRating) newValue);
                        break;
                    case 5:
                        m.setRunTime((int) newValue);
                        break;
                    case 6:
                        m.setDateStart((Date) newValue);
                        break;
                    case 7:
                        m.setDateEnd((Date) newValue);
                        break;
                    case 8:
                        m.setCast((ArrayList<String>) newValue);
                        break;
                    case 9:
                        m.setDirector((String) newValue);
                        break;
                }

            }
            // Add this new data to new ArrayList
            updateList.add(m);
        }
        replaceExistingFile(FILENAME, updateList);
    }


    /* Replace existing file to a new file */
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


    // Tester Function //
    public void createMovie(Movie movie){
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
}
