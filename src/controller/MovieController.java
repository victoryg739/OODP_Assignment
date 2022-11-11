package controller;

import model.Constant;
import model.Enums.MovieRating;
import model.Enums.MovieType;
import model.Enums.ShowingStatus;
import model.Movie;
import model.Review;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import static view.utilF.print;
import static view.utilF.println;

/**
 * The Movie controller class, of the program, controlling each of the Movie
 * Write, read, replace Movie into database
 * Also controls the logic and control of the movie object
 * @author Bryan Tay Peng Keat
 * @version 1.0
 * @since 2022-08-11
 */
public class MovieController {

    public MovieController() {

    }

    /**
     * Return the ID of the last Movie in the Database field
     *
     * @return int      ID of last Movie in the Database
     */
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


    /**
     * READ and return every Movie of a given ID in the Database file
     *
     * @param valueToSearch Id of movie to search for
     * @return Movie            Return Movie if found, else null object
     */
    public Movie readByID(int valueToSearch) {
        ArrayList<Movie> allData = read();
        for (int i = 0; i < allData.size(); i++) {
            Movie m = allData.get(i);
            if (m.getId() == valueToSearch)
                return m;
        }
        return null;
    }


    /**
     * CREATE a new Movie and add it into the database file
     * Attributes are validated before creation
     * If attributes are not allowed, throw error and do nothing
     * If Database file exist, existing records are read and new Movie object is appended before saving
     * If Database file does not exist, Movie object will be written to a new file and saved
     *
     * @param title     This movie's title
     * @param type      This movie's type
     * @param synopsis  This movie's synopsis
     * @param rating    This movie's rating
     * @param runtime   This movie's duration
     * @param ss        This movie's showing time
     * @param DateStart This movie's start date
     * @param DateEnd   This movie's end date
     * @param director  This movie's director
     * @param cast      This movie's list of cast
     */
    public void createMovie(String title, MovieType type, MovieRating rating, ShowingStatus ss, String synopsis, int runtime, Date DateStart, Date DateEnd, ArrayList<String> cast, String director) {
        // Creates a movie object
        Movie movie = new Movie(getLastId() + 1, title, type, ss, rating, synopsis, runtime, DateStart, DateEnd, director, cast);
        // Creates an ArrayList of movie
        ArrayList<Movie> allData = new ArrayList<Movie>();
        File tempFile = new File(Constant.MOVIEFILE);

        // If it exists then read() the existing data
        if (tempFile.exists())
            allData = read();
        try {
            // Write the data to the movie
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(Constant.MOVIEFILE));
            allData.add(movie);
            out.writeObject(allData);
            out.flush();
            out.close();
        } catch (IOException e) {
            // ignore error
        }
    }

    /**
     * UPDATE a Movie by updating based on choice, movieID and newValue
     *
     * @param choice   Attribute of movie to update
     * @param movieID  ID of Movie to search for
     * @param newValue New value of Movie's attribute
     */

    /* Update  */
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
                        m.addTicketSales((int) newValue);
                        break;
                    case 12:
                        m.addReview((Review) newValue);
                        break;
                }

            }
            // Add this new data to new ArrayList
            updateList.add(m);
        }

        replaceExistingFile(Constant.MOVIEFILE, updateList);
    }

    /**
     * Remove Movie by updating the current showing status to end_showing
     *
     * @param movieID ID of Movie to search for
     * @return boolean           return boolean true if is it successfully removed
     */
    public boolean removeMovie(int movieID) {
        ArrayList<Movie> allData = read();
        for (int i = 0; i < allData.size(); i++) {
            Movie m = allData.get(i);
            if (m.getId() == movieID) {
                if (m.getShowingStatus() == ShowingStatus.END_SHOWING) {
                    return false;
                }
                m.setShowingStatus(ShowingStatus.END_SHOWING);
                break;
            }
        }
        replaceExistingFile(Constant.MOVIEFILE, allData);
        return true;
    }


    /**
     * Read the database file of Movie
     *
     * @return arraylist of Movie files
     */
    public ArrayList<Movie> read() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Constant.MOVIEFILE));
            ArrayList<Movie> movieListing = (ArrayList<Movie>) ois.readObject();
            ois.close();
            return movieListing;
        } catch (ClassNotFoundException | IOException e) {
            // ignore error
        }
        return new ArrayList<Movie>();
    }

    /**
     * List all the movies from the database file
     */
    public void listMovies() {
        ArrayList<Movie> movieList = read();
        if (movieList.isEmpty()) {
            print("No movies in the database.");
        } else {
            for (int i = 0; i < movieList.size(); i++) {
                Movie m = movieList.get(i);
                m.printMovie();
            }
        }
    }

    /**
     * List all the movies from the database file
     */
    public void listMovies(ArrayList<Movie> searchMovies) {

        ArrayList<Movie> movieList = new ArrayList<Movie>();
        // If searchMovies is empty means it is listingMovie
        if (searchMovies == null) {
            movieList = read();
        } else { // If search Empty has something inside then append to movielist
            movieList.addAll(searchMovies);
        }
        for (int i = 0; i < movieList.size(); i++) {
            Movie m = movieList.get(i);
            m.printMovie();

        }

    }

    /**
     * A function to check if this movie is in the session
     *
     * @param movieID ID of Movie to search for
     * @return boolean           return boolean true if the movie is valid
     */
    public boolean validMovie(int movieID) {
        if (readByID(movieID) != null) {
            return true;
        }
        return false;
    }

    /**
     * A function to check whether if it is valid to review a movie
     * Customer should not be able to make a review for movies that have not been released
     *
     * @param movieID ID of Movie to search for
     * @return boolean           return boolean true if the movie is valid for moving status
     */
    public boolean validReviewMovie(int movieID) {
        Movie m = readByID(movieID);
        if (m.getShowingStatus() != ShowingStatus.COMING_SOON) {
            return true; // Returns true (valid) if the moving status is not released
        }
        return false;
    }

    /**
     * A function to list top 5 by rating
     */
    public void listTopByRating() {
        ArrayList<Movie> movieList = read();
        try {
            sortRating(movieList);
            int top = 1;
            for (Movie movie : movieList) {
                if (movie.getRatingTimes() > 1) {
                    println("Name: " + movie.getTitle() + "\n" + "Rating: " + movie.printStars());
                } else {
                    println("Name: " + movie.getTitle() + "\n" + "Rating: NA");
                }
                if (top++ == 5) {
                    break;
                }
            }
        } catch (NullPointerException e) {
            print(e.getMessage());
        }
    }

    /**
     * A function to list top 5 by sales
     */
    public void listTopBySales() {
        ArrayList<Movie> movieList = read();
        try {
            sortTicketSales(movieList);
            int top = 1;
            for (Movie movie : movieList) {
                println("Name: " + movie.getTitle() + "\n" + "Sales: " + movie.getTicketSales());
                if (top++ == 5) {
                    break;
                }
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Replace existing file to a new file
     *
     * @param filename File name of the file that it going to be replace
     * @param data     Data is the new data to be updated
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


    /*Tester function*/
    public void createMovie(Movie movie) {
        ArrayList<Movie> allData = new ArrayList<Movie>();
        File tempFile = new File(Constant.MOVIEFILE);
        // If it exists then read() the existing data
        if (tempFile.exists())
            allData = read();
        try {
            // Write the data to the movie
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(Constant.MOVIEFILE));
            allData.add(movie);
            out.writeObject(allData);
            out.flush();
            out.close();
        } catch (IOException e) {
            // ignore error
        }

    }

    /**
     * A function to read the movie Title and return ArrayList of Movie that is searched by user
     *
     * @param valueToSearch Object that is used
     * @return ArrayList           return arraylist of movies
     */
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

    /**
     * A function to check whether if it is valid to review a showing status
     *
     * @param m Movie object
     * @return boolean           return boolean true if the movie is valid for showing status
     */
    public boolean validateShowingStatus(Movie m) {
        if (m.getShowingStatus() == ShowingStatus.PREVIEW || m.getShowingStatus() == ShowingStatus.NOW_SHOWING) {
            return true;
        }
        return false;
    }

    /**
     * A function to sort ticket sales
     *
     * @param movies An array list of movies to sort
     */
    public void sortTicketSales(ArrayList<Movie> movies) {
        Collections.sort(movies, (m1, m2) -> (m1.getTicketSales() - m2.getTicketSales()));
        Collections.reverse(movies);
    }

    /**
     * A function to sort rating
     *
     * @param movies An array list of movies to sort
     */
    public void sortRating(ArrayList<Movie> movies) {
        Collections.sort(movies, new Comparator<Movie>() {
            public int compare(Movie m1, Movie m2) {
                return Double.compare(m2.getRating(), m1.getRating());
            }
        });
    }


}
