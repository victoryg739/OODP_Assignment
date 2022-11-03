package controller;

import modal.*;
import java.io.*;
import java.util.*;
import modal.Enums.*;

/* ToDO list:
    1. Change getLastID code
    2. Delete by ID (change the code)
    3. SortbySale and SortbyRating code


*/
// Database for Admin control //
public class MovieController {
    public final static String FILENAME = "data/movies.txt";

    public MovieController() {

    }
    // Creates a movie and writes it to movies.txt
    public void createMovie(String title, MovieType type, MovieRating rating, ShowingStatus ss,String synopsis, int runtime, Date DateStart, Date DateEnd, ArrayList<String> cast, String director) {
        // Creates a movie object
        Movie movie = new Movie(getLastId() + 1, title, type, ss, rating, synopsis, runtime, DateStart, DateEnd, director, cast);
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
        int id = movie.getId();
        String title = movie.getTitle();
        MovieType movieType = movie.getType();
        MovieRating movieRating = movie.getContentRating();
        String synopsis = movie.getSynopsis();
        int runtime = movie.getRuntime();
        Date DateStart = movie.getDateStart();
        Date DateEnd = movie.getDateEnd();
        String director = movie.getDirector();
        ShowingStatus ss = movie.getShowingStatus();
        //ArrayList<String> casts = movie.getCast();
        String castString = "";
        for (int i=0; i< movie.getCast().size(); i++)
            castString = castString.concat(movie.getCast().get(i) + ",");

        castString = castString.substring(0, castString.length()-1);
        String movieString = "ID: " + id + " | " + "Title: " + title + " | " + "Type " + movieType + " | " + "Rating " + movieRating + " | " + "Synopsis: " + synopsis + " | "
                + "Runtime: " + runtime + " | " + "DateStart: " + DateStart + " | " + "DateEnd: " + DateEnd + " | " + "Director: " + director + " | "
                + "Cast: " + castString + " | " + "Showing: " + ss;
        System.out.println(movieString);
        System.out.println("-------------------");
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

    public Movie readByID(int valueToSearch) {
        ArrayList<Movie> allData = read();
        for (int i=0; i<allData.size(); i++){
            Movie m = allData.get(i);
            if (m.getId() == valueToSearch)
                return m;
        }
        return null;
    }
    public int getLastId() {
        int lastId = -1;
        int movieID;
        ArrayList<Movie> allData = read();
        for (int i = 0; i < allData.size(); i++) {
            movieID = allData.get(i).getId();
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
            if (!(m.getId() == id))
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
            if (m.getId() == movieID) {
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
                        m.setContentRating((MovieRating) newValue);
                        break;
                    case 5:
                        m.setRuntime((int) newValue);
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
                    case 10:
                        m.setShowingStatus((ShowingStatus) newValue);
                        break;
                    case 11:
                        m.setTicketSales((int) newValue);
                        break;
                    case 12:
                        m.addReview((Review) newValue);
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

    public ArrayList<Movie> readByTitle(Object valueToSearch) {
        ArrayList<Movie> allData = read();
        ArrayList<Movie> returnData = new ArrayList<Movie>();
        for (int i = 0; i < allData.size(); i++) {
            Movie m = allData.get(i);
            if (m.getTitle().toLowerCase().contains(valueToSearch.toString().toLowerCase())) {
                returnData.add(m);
            }
        }

        return returnData;
    }

    //NOT working yet
    public void sortTicketSales(ArrayList<Movie> movies){
        Collections.sort(movies , new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                return Double.compare(m1.getTicketSales(), m2.getTicketSales());
            }
        });

    }

    //NOT working yet
    public void sortRating(ArrayList<Movie> movies) {
        Collections.sort(movies, new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                return Double.compare(m2.getRating(), m1.getRating());
            }
        });
    }

    public void printStars(double rating) {
        String s = String.format("%.1f", rating);
        if(rating <= 1)
            System.out.println("★☆☆☆☆(" + s + ")");
        else if(rating <= 2)
            System.out.println("★★☆☆☆(" + s + ")");
        else if(rating <= 3)
            System.out.println("★★★☆☆(" + s + ")");
        else if(rating <= 4)
            System.out.println("★★★★☆(" + s + ")");
        else
            System.out.println("★★★★★(" + s + ")");
        System.out.println(" ");
    }
}
