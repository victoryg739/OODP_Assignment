package controller;

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


public class MovieController {
    public final static String FILENAME = "data/movies.txt";
    SessionController sc = new SessionController();

    public MovieController() {

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

    /* Returns a movie object based on an ID */
    public Movie readByID(int valueToSearch) {
        ArrayList<Movie> allData = read();
        for (int i = 0; i < allData.size(); i++) {
            Movie m = allData.get(i);
            if (m.getId() == valueToSearch)
                return m;
        }
        return null;
    }

    /* Create Movie by giving its attributes */
    public void createMovie(String title, MovieType type, MovieRating rating, ShowingStatus ss, String synopsis, int runtime, Date DateStart, Date DateEnd, ArrayList<String> cast, String director) {
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

    /* Update Movie by updating based on choice, movieID and newValue */
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

        replaceExistingFile(FILENAME, updateList);
    }

    /* Remove Movie by updating the current showing status to end_showing */
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
        replaceExistingFile(FILENAME, allData);
        return true;
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

    /* A function to check if this movie is in the session */
    public boolean validMovie(int movieID) {
        if (readByID(movieID) != null) {
            return true;
        }
        return false;
    }

    /* A function to check whether if it is valid to review a movie */
    /* Customer should not be able to make a review for movies that have not been released */
    public boolean validReviewMovie(int movieID) {
        Movie m = readByID(movieID);
        if (m.getShowingStatus() != ShowingStatus.COMING_SOON) {
            return true; // Returns true (valid) if the moving status is not released
        }
        return false;
    }


    public void listTopSalesByRating() {
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

    public void listTopSalesBySales() {
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


    public void listALLMoviesSettings() {
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


    public void createMovie(Movie movie) {
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

    public boolean validateShowingStatus(Movie m) {
        if (m.getShowingStatus() == ShowingStatus.PREVIEW || m.getShowingStatus() == ShowingStatus.NOW_SHOWING) {
            return true;
        }
        return false;
    }

    public void sortTicketSales(ArrayList<Movie> movies) {
        Collections.sort(movies, (m1, m2) -> (m1.getTicketSales() - m2.getTicketSales()));
        Collections.reverse(movies);
    }

    public void sortRating(ArrayList<Movie> movies) {
        Collections.sort(movies, new Comparator<Movie>() {
            public int compare(Movie m1, Movie m2) {
                return Double.compare(m2.getRating(), m1.getRating());
            }
        });
    }


}
