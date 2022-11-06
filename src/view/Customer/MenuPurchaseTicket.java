package view.Customer;

import controller.*;
import modal.Customer;
import modal.*;
import view.MenuBase;
import view.admin.MenuStaffLogin;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import static view.utilF.*;

public class MenuPurchaseTicket extends MenuBase {
    private Movie movie;
    private CineplexController cineplexController;
    private PriceManager priceManager;
    private MovieController movieController;
    private CustomerController customerController;

    private SessionController sessionController;

    private CinemaController cinemaController;
    private BookingController bookingController;

    private int tempId;
    private String username;

    public MenuPurchaseTicket(MenuBase previousMenu, Movie movie, int tempId, String username) {
        super(previousMenu);
        this.movie = movie;
        this.cineplexController = new CineplexController();
        this.movieController  = new MovieController();
        this.priceManager = new PriceManager();
        this.customerController = new CustomerController();
        this.sessionController = new SessionController();
        this.cinemaController = new CinemaController();
        this.bookingController = new BookingController();
        this.tempId = tempId;
        this.username = username;
    }


    /*
     List the cineplex with the movie
     List all the slots available according to the movie information
     Ask user to select slots, seats.
     Ask user information whether they are student or senior
     Get user's information about name, email and phone number
     Provide the total ticket price to the user
     */
    public MenuBase execute() {

        CineplexController cc = new CineplexController();
        ArrayList<Session> sessionList = new ArrayList<>();


        System.out.println("Menu for Purchasing Ticket:");
        System.out.println();
        //cinemaController.printAllCinema();

        //Print the list of Cineplexes with the selected movies
        ArrayList<Cineplex> cineplexes = cc.read();
        for(int i = 0; i < cineplexes.size(); i++) {
            System.out.println((i+1) + ". " + cineplexes.get(i).getLocation());
        }

        int choice;
        while (sessionList.size() == 0) {
            choice = readIntInput("Choose cineplex (0 to return): ");
            if (choice == 0) {
                System.out.println("Returning...");
                return this.getPreviousMenu();
            }
            else if (choice < 0 || choice > cineplexes.size())
                System.out.println("Invalid input! Please try again.");
            else { //valid choice
                sessionList = cinemaController.showAvailableSessions(cineplexes.get(choice-1).getLocation(), movie);
                if (sessionList == null)
                    System.out.println("No available sessions for this cineplex! Please choose another.");
            }
        }
//        if (sessionList == null) {
//            println(" ");
//            return new MenuCustomerMain(this);
//        }
        int c = readIntInput("Please Choose a session");
        Session session = sessionList.get(c - 1);

        //get all the available seats for the selected session
        String tempCinemeNo = session.getCinema().getCinemaNo();
        Cinema  cinema = cinemaController.readByCinemaNo(tempCinemeNo);
        ArrayList<ArrayList<Seat>> seatList = cinema.getSeats();
        int col = seatList.get(0).size(), row=seatList.size();

        //find seat & select seat
        ArrayList<Seat> selected = new ArrayList<Seat>();

        while(confirm("Continue to select seats?"))
        {
            printHeader("Select Seats");
            cinemaController.displaySeats(seatList, row, col);
            Seat selectedSeat = cinemaController.chooseSeats(seatList,row,col);
            selected.add(selectedSeat);
        }
        println("This is your finalized seats");
        cinemaController.displaySeats(seatList, row, col);
        /*
            Model for buying tickets:
            When selecting a few seats, they have to belong to 1 ticket type (Senior/Student/Normal)
         */

        //See if can make this into a helper function
        if (selected.size() != 0) {
            ArrayList<Ticket> tickets = new ArrayList<Ticket>();
            //Function to determine the day n time
            Enums.Day day = session.getDay();
            Enums.MovieType movieType = movie.getType();
            Enums.ClassCinema cinemaClass = session.getCinema().getClassCinema();
            boolean loyaltyCard = false;
            double ticketPrice;

            if (confirm("Are you eligible for student discount?")) { //student price
                Enums.AgeType age = Enums.AgeType.STUDENT;
                ticketPrice = priceManager.calculateTicketPrice(age, movieType, cinemaClass, day, movie.getShowingStatus(), loyaltyCard);
                //for every seat, generate 1 ticket according to the ticket type
                for (Seat seat : selected) {
                    Ticket ticket = new Ticket(ticketPrice, movieType, cinemaClass, age, day, seat);
                    tickets.add(ticket);
                }
            }
            else if (confirm("Are you eligible for senior discount?")) { //senior price
                Enums.AgeType age = Enums.AgeType.SENIOR;
                ticketPrice = priceManager.calculateTicketPrice(age, movieType, cinemaClass, day, movie.getShowingStatus(), loyaltyCard);
                //for every seat, generate 1 ticket according to the ticket type
                for (Seat seat : selected) {
                    Ticket ticket = new Ticket(ticketPrice, movieType, cinemaClass, age, day, seat);
                    tickets.add(ticket);
                }
            }
            else { //neither student nor senior
                Enums.AgeType age = Enums.AgeType.NORMAL;
                ticketPrice = priceManager.calculateTicketPrice(age, movieType, cinemaClass, day, movie.getShowingStatus(), loyaltyCard);
                //for every seat, generate 1 ticket according to the ticket type
                for (Seat seat : selected) {
                    Ticket ticket = new Ticket(ticketPrice, movieType, cinemaClass, age, day, seat);
                    tickets.add(ticket);
                }
            }
            /*
            Calculate the price of 1 ticket with the various factors: TicketType, MovieType, Platinum, Sneakpeek/FirstWeek/Blockbuster, ...
            After that, multiply by the # of seats
            */
            double totalPrice = ticketPrice * tickets.size();

            //Get the current Date/time to generate the tid according to the format XXXYYYYMMDDhhmm
            Date currentTime = Calendar.getInstance().getTime();
            String timeStamp = new SimpleDateFormat("YYYYMMDDhhmm").format(currentTime);
            String tid = session.getCinema().getCinemaNo() + timeStamp;

            println("Total price is S$" + totalPrice + " (Inclusive of GST).");
            if (confirm("Confirm booking? ")) {
                for (Seat seat : selected) {
                    seat.setSelected(false);
                    seat.setTaken(true);
                }
                //Create the booking transaction
                Booking booking = new Booking(session.getCinema().getCinemaNo(), tid,
                        username ,movie, tickets, session, totalPrice);
                println("Booking successful");
                movieController.updateMovie(11, movie.getId(), tickets.size());
                bookingController.create(booking);
                cinemaController.updateSeat(session.getCinema().getCinemaNo(), selected, movie, session);
            }
            else {
                for (Seat seat : selected)
                    seat.setSelected(false);
            }
        }while (readIntInput("Press 0 to return to Main Menu: ") != 0) ;

        return new MenuCustomerMain(this);
    }
}

