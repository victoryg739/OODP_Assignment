package view;
import java.util.*;
import static view.utilF.*;

public class MovieInfo extends MenuBase {
    private final Movie movie;
    public MovieInfo(MenuBase previousMenu, Movie movie) {
        super(previousMenu);
        this.movie = movie;
    }

    /*
      Print out all the movie information
     */
    public void printMovieInformation() {
        //Movie Title
        System.out.println("Title: " + this.movie.getTitle());
        //Movie Status
        System.out.println("Showing Status: " + this.movie.getShowingStatus().toString());
        //Movie Content Rating (PG13, N16, R21, ...)
        System.out.println("Content Rating: " + this.movie.getContentRating().toString());
        //Movie Runtime
        System.out.println("Runtime: " + this.movie.getRuntime() + " minutes");
        //Movie Director
        System.out.println("Director: " + this.movie.getDirector());
        //Movie Cast
        System.out.println("Cast: ");
        StringBuilder s = new StringBuilder();
        for (String r : movie.getCasts()) { //get list of the entire cast for the movie
            s.append(r + "; ");
        }
        //print the list of cast
        System.out.println(s.toString());
        //What language the movie is in
        System.out.println("Language: " + this.movie.getLanguage());
        //Opening date of the movie
        System.out.println("Opening: " + this.movie.getFormattedDate());
        //Movie Synopsis
        System.out.println("Synopsis: ");
        System.out.println(this.movie.getSynopsis(), 16);

        //Movie Overall Rating
        if(movie.getOverAllRating() != 0 ){
            System.out.println("Overall Rating:");
            printStars(movie.getOverAllRating());
        } else {
            System.out.println("Overall Rating: N/A");
        }
        //Show the rating together with comments
        if (movie.getOverAllRating() != 0){
            for (Review r : movie.getReview()) {
                System.out.println("Review: ");
                System.out.println(r.getComment(), 16);
                System.out.println("Rating: ");
                //Individual Rating
                System.out.println(r.getRating());
            }
        }
    }

    //Show the rating in terms of *
    /*
    public void printStars(double rating) {
        String s = String.format("%.1f", rating);
        if(rating <= 1)
            println("★☆☆☆☆(" + s + ")");
        else if(rating <= 2)
            println("★★☆☆☆(" + s + ")");
        else if(rating <= 3)
            println("★★★☆☆(" + s + ")");
        else if(rating <= 4)
            println("★★★★☆(" + s + ")");
        else
            println("★★★★★(" + s + ")");
        println();
    }
    */

    public MenuBase execute() {
        //.getTitle() == get the movie title
        System.out.println(movie.getTitle());
        Scanner sc = new Scanner(System.in);

        printMovieInformation();

        int flag = 0;
        ArrayList<String> choices = new ArrayList<>();
        //If the movie is currently showing or coming soon => Allow customers to buy tickets
        if (((movie.getShowingStatus().equals(Constant.ShowingStatus.CURRENTLY_SHOWING)) || (movie.getShowingStatus().equals(Constant.ShowingStatus.COMING_SOON)))) {
            choices.add("Buy Tickets");
            flag = 1;
        }
        choices.add("Write a review");
        choices.add("Go Back");
        choices.add("Quit The Application");
        printMenu(choices, 1);
        String choice = sc.next();
        int c = readIntInput(choice);

        /* eg.
           MovieInfo (if there is an option to buy tickets)

           Option 1: Buy Ticket
           Option 2: Check movie review
           Option 3: Go Back
           Option 4 Quit The Application
         */
        MenuBase nextMenu = this;
        if (flag == 1) {
            do{
                switch (c) {
                    case 1:
                        nextMenu = new PurchaseTicketMenu(this, movie);
                        break;
                    case 2:
                        nextMenu = new ReviewsMenu(this, movie);
                        break;
                    case 3:
                        nextMenu = this.getPreviousMenu();
                        break;
                    case 4:
                        nextMenu = new Quit(null);
                        break;
                }
            } while(c >= 0);

        }

        /* eg.
           MovieInfo (if there is non option to buy tickets)

           Option 1: Check movie review
           Option 2: Go Back
           Option 3 Quit The Application
         */

        else {
            do{
                switch (c) {
                    case 1:
                        nextMenu = new ReviewsMenu(this, movie);
                        break;
                    case 2:
                        nextMenu = this.getPreviousMenu();
                        break;
                    case 3:
                        nextMenu = new Quit(null);
                        break;
                }
            }while(c >= 0);

        }

        return nextMenu;
    }

}