package view.Customer;

import controller.*;
import modal.Customer;
import modal.*;
import view.MenuBase;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import static view.utilF.*;

public class MenuPurchaseTicket extends MenuBase {
    private String cinemaNo;
    private LocalDateTime currentDateTime;
    private Cinema cinema;
    private Session session;
    private Movie movie;
    private Seat seat;
    private int numOfTickets;
    private CineplexController cineplexController;
    private PriceManager priceManager;
    private MovieController movieController;
    private CustomerController customerController;

    private SessionController sessionController;

    private CinemaController cinemaController;

    public MenuPurchaseTicket(MenuBase previousMenu, Movie movie) {
        super(previousMenu);
        this.movie = movie;
        this.cineplexController = new CineplexController();
        this.movieController  = new MovieController();
        this.priceManager = new PriceManager();
        this.customerController = new CustomerController();
        this.sessionController = new SessionController();
        this.cinemaController = new CinemaController();
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
        ArrayList<String> choices = new ArrayList<>();
        ArrayList<Cinema> cinemaList = new ArrayList<Cinema>();

        System.out.println("Menu for Purchasing Ticket:");
        System.out.println();

        //Print the list of Cineplexes with the selected movies
        //readBYAttribute(movie)
        ArrayList<Cineplex> cineplexes = cc.read();
        for(int i = 0; i < cineplexes.size(); i++) {
            System.out.println((i+1) + ". " + cineplexes.get(i).getLocation());
        }

        int choice;
        while (cinemaList.size() == 0) {
            choice = readIntInput("Choose cineplex (0 to return): ");
            if (choice == 0) {
                System.out.println("Returning...");
                return this.getPreviousMenu();
            }
            else if (choice < 0 || choice > cineplexes.size())
                System.out.println("Invalid input! Please try again.");
            else { //valid choice
                cinemaList = showAvailableSessions(cineplexes.get(choice-1).getLocation(), movie);
//                if (cinemaList.size() == 0)
//                    System.out.println("No available sessions for this cineplex! Please choose another.");
            }
        }
//        //Get the input for cinema
//        int cinemaChoice = readIntInput("Enter the cinema of choice:");
//        ArrayList<Session> availableSession = sessionController.read();
//        //SessionController.read() to get the ArrayList<Session>
//        //.Sessions() is to check for the available sessions for that movie
//        if(cinema.getSessions().isEmpty())
//        {
//            System.out.println("Sorry, there is no available session currently.");
//            return this.getPreviousMenu();
//        }
//
//        //For all the available movie sessions with seats
//        for(Session timeslot:cinema.getSessions())
//        {
//            choices.add(timeslot.getCinema() + " -- " + timeslot.getDatetimeFormat());
//        }
//
//
//        printMenu(choices, 1);
//        int c = readIntInput("Please Choose a session");
//        Session session = cinema.getSessions().get(c);
//
//        //get all the available seats for the selected session
//        ArrayList<Seat> seats = session.getSeat();
//        int col = seats.get(seats.size() - 1).getCol(), row= seats.get(seats.size() - 1).getRow();
//
//        //find seat & select seat
//        ArrayList<Seat>selected = new ArrayList<Seat>();
//
//        while(confirm("Continue to select seats?"))
//        {
//            displaySeats(seats,row,col);
//            selected.add(chooseSeats(seats,row,col));
//        }
//        /*
//            Model for buying tickets:
//            When selecting a few seats, they have to belong to 1 ticket type (Senior/Student/Normal)
//         */
//
//        if (selected.size() != 0) {
//            ArrayList<Ticket> tickets = new ArrayList<Ticket>();
//            //Function to determine the day n time
//            //need to check with victor or bryan
//            Enums.Day day = session.getDay();
//            Enums.MovieType movieType = movie.getType();
//            Enums.ClassCinema cinemaClass = cinema.getClassCinema();
//            double ticketPrice;
//
//
//
//            if (confirm("Are you eligible for student discount?")) { //student price
//                Enums.AgeType age = Enums.AgeType.STUDENT;
//                ticketPrice = priceManager.calculateTicketPrice(age, movieType, cinemaClass, day);
//                //for every seat, generate 1 ticket according to the ticket type
//                for (Seat seat : selected) {
//                    Ticket ticket = new Ticket(ticketPrice, movieType, cinemaClass, age, day, seat);
//                    tickets.add(ticket);
//                }
//            }
//            else if (confirm("Are you eligible for senior discount?")) { //senior price
//                Enums.AgeType age = Enums.AgeType.SENIOR;
//                ticketPrice = priceManager.calculateTicketPrice(age, movieType, cinemaClass, day);
//                //for every seat, generate 1 ticket according to the ticket type
//                for (Seat seat : selected) {
//                    Ticket ticket = new Ticket(ticketPrice, movieType, cinemaClass, age, day, seat);
//                    tickets.add(ticket);
//                }
//            }
//            else { //neither student nor senior
//                Enums.AgeType age = Enums.AgeType.NORMAL;
//                ticketPrice = priceManager.calculateTicketPrice(age, movieType, cinemaClass, day);
//                //for every seat, generate 1 ticket according to the ticket type
//                for (Seat seat : selected) {
//                    Ticket ticket = new Ticket(ticketPrice, movieType, cinemaClass, age, day, seat);
//                    tickets.add(ticket);
//                }
//            }
//            /*
//            Calculate the price of 1 ticket with the various factors: TicketType, MovieType, Platinum, Sneakpeek/FirstWeek/Blockbuster, ...
//            After that, multiply by the # of seats
//            */
//            double totalPrice = ticketPrice * tickets.size();
//
//            //Get the current Date/time to generate the tid according to the format XXXYYYYMMDDhhmm
//            Date currentTime = Calendar.getInstance().getTime();
//            String timeStamp = new SimpleDateFormat("YYYYMMDDhhmm").format(currentTime);
//            String tid = cinema.getCinemaNo() + timeStamp;
//
//
//
//                //Log into their account
//                Customer customer; //Need to create User class
//                if (confirm("Do you have an account")) {
//                    customer = login();
//                }
//                else {
//                    String email = read("Email: ");
//                    String password = read("Password: ");
//                    String password1 = read("Confirm your password again: ");
//
//                    customer = new Customer(email, password);
//
//                    //Need to add the customer detail into the database
//                    CustomerController.createCustomer(customer);
//            }
//            //Create the booking transaction
//            Booking booking = new Booking(cinema.getCinemaNo(), tid,
//                    customer.getUsername(),customer.getPassword(), movie, tickets, session, totalPrice);
//            TicketController tc = new TicketController();
//            tc.create(booking);
//            System.out.println("Total price is S$" + booking.getTotalPrice() + " (Inclusive of GST).");
//            if (confirm("Confirm booking? ")) {
//                seat.setTaken(true);
//            }
//            customer.addBookings(booking);
//            //Create a CustomerController to accept the customer object
//
//            //Need this function to update the total sale for that particular movie
//            //movie.addTicketSales(tickets.size());
//            System.out.println("Booking successful, tid=" + booking.getTID());
//
//        }while (readIntInput("Press 0 to return to previous menu: ") != 0) ;

        return this.getPreviousMenu();
    }
    /*
     Print out the layout of the seats in the current slots,
     including seats available, seats occupied and seats chosen
     With corridor printed out in the middle of the layout
     */

    private void displaySeats(ArrayList<Seat> seats, int row, int col)
    {
        Seat seat;
        int seatNumber;
        System.out.println(" Select Seats");
        for (int i = 0; i < (1 + col) * 3 / 2 - 8; i++)
            System.out.println(" ");
        System.out.println("|      Screen       |");
        for (int i = 0; i < (1 + col) * 3 / 2 - 8; i++) {
            System.out.println(" ");
        }
        System.out.println("---------------------");

        System.out.println("    ");
        int new_row = 0;
        for(int i=0; i<col; i++){
            if (new_row != col / 2 - 1) {
                System.out.println(i + 1);
            } else {
                System.out.println("  ");
                i--;
            }
            new_row++;
        }

        System.out.println("  ");
        boolean flag = false;
        for(int i =0; i<row; i++)
        {
            new_row = 0;
            System.out.println(i + 1);
            for(int j=0; j<col; j++)
            {
                if (new_row != col / 2 - 1) {
                    seatNumber = (i - 1) * 5 + j;
                    seat = seats.get(seatNumber);
                    //Seat not available
                    if (seat.isTaken()) {
                        System.out.println("[X]");
                    }
                    //Seats available
                    else
                        System.out.println("[ ]");
                }
                else {
                    System.out.println("   ");
                    j--;
                }
                new_row++;
            }
            System.out.println(" ");
            System.out.println(i + 1);
            System.out.println(" ");
        }

        System.out.println(" ");
        for (int i = 0; i < (1 + col) * 3 / 2 - 5; i++)
            System.out.println(" ");
        System.out.println("----------");
        for (int i = 0; i < (1 + col) * 3 / 2 - 5; i++)
            System.out.println(" ");
        System.out.println("|Entrance|\n");
        System.out.println("([ ] Available  [X] Sold)");
    }



// Method to ask user to select a row and column,check whether the seat is available, and update the information in the data base

    private Seat chooseSeats(ArrayList<Seat> seats, int row, int col) {
        System.out.println("Please choose your seat(s).");
        int i, j;
        int seatNumber;
        do {
            i = readSeatInput("Please input row number", 1, row);
            j = readSeatInput("Please input col number", 1, col);
            i--;
            j--;
            seatNumber = (i - 1) * 5 + j;
            if (seats.get(seatNumber).isTaken())
                System.out.println("Already been taken/selected please choose another seats.");
            else break;
        } while (true);

        seats.get(seatNumber).setTaken(true);
        System.out.println("Selected Seat: Row: " + (i + 1) + " Col: " + (j + 1));

        return seats.get(seatNumber);
    }

    public ArrayList<Cinema> showAvailableSessions(String cineplexName, Movie movie) {
        Session tempSession;
        Cinema tempCinema;
        boolean printSeparator = false;
        ArrayList<Cinema> tempCinemaList = new ArrayList<Cinema>();
        ArrayList<Cinema> cinemaList = cinemaController.readByCineplexName(cineplexName);
        System.out.println("------------------------------------------------------");
        for (int i = 0; i < cinemaList.size() ; i++) {
            tempCinema = cinemaList.get(i);
            System.out.println((i+1) + " Cinema code: " + tempCinema.getCinemaNo() + "		Cinema type: " + tempCinema.getClassCinema());
            System.out.println();
        }
        int cinemaChoice = readIntInput("Enter cinema of choice: ");
        tempCinema = cinemaList.get(cinemaChoice - 1);

        System.out.println("Available screening times of " + movie.getTitle() + " in this cinema:");
        System.out.println();

        for(int j = 0; j < tempCinema.getSessions().size() ; j++) {
            tempSession = tempCinema.getSessions().get(j);
            if (tempSession.getMovie().getTitle().equals(movie.getTitle())) {
                System.out.println("	Date: " + tempSession.getDateTime());
                System.out.println();
                tempCinemaList.add(tempCinema);
                printSeparator = true;
            }
            if(printSeparator){
                System.out.println("------------------------------------------------------");
                printSeparator = false;
            }
        }

        return tempCinemaList;
    }

}

