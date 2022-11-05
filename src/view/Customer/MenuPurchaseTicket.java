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
    private BookingController bookingController;

    public MenuPurchaseTicket(MenuBase previousMenu, Movie movie) {
        super(previousMenu);
        this.movie = movie;
        this.cineplexController = new CineplexController();
        this.movieController  = new MovieController();
        this.priceManager = new PriceManager();
        this.customerController = new CustomerController();
        this.sessionController = new SessionController();
        this.cinemaController = new CinemaController();
        this.bookingController = new BookingController();
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
        ArrayList<Session> sessionList = new ArrayList<>();

        System.out.println("Menu for Purchasing Ticket:");
        System.out.println();

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
                sessionList = showAvailableSessions(cineplexes.get(choice-1).getLocation(), movie);
                if (sessionList.size() == 0)
                    System.out.println("No available sessions for this cineplex! Please choose another.");
            }
        }
        int c = readIntInput("Please Choose a session");
        Session session = sessionList.get(c - 1);

        //get all the available seats for the selected session
        ArrayList<ArrayList<Seat>> seats = session.getCinema().getSeats();
        int col = seats.get(0).size(), row=seats.size();

        //find seat & select seat
        ArrayList<Seat> selected = new ArrayList<Seat>();

        while(confirm("Continue to select seats?"))
        {
            displaySeats(seats,row,col);
            selected.add(chooseSeats(seats,row,col));
        }
        /*
            Model for buying tickets:
            When selecting a few seats, they have to belong to 1 ticket type (Senior/Student/Normal)
         */

        if (selected.size() != 0) {
            ArrayList<Ticket> tickets = new ArrayList<Ticket>();
            //Function to determine the day n time
            //need to check with victor or bryan
            Enums.Day day = session.getDay();
            Enums.MovieType movieType = movie.getType();
            Enums.ClassCinema cinemaClass = session.getCinema().getClassCinema();
            double ticketPrice;

            if (confirm("Are you eligible for student discount?")) { //student price
                Enums.AgeType age = Enums.AgeType.STUDENT;
                ticketPrice = priceManager.calculateTicketPrice(age, movieType, cinemaClass, day);
                //for every seat, generate 1 ticket according to the ticket type
                for (Seat seat : selected) {
                    Ticket ticket = new Ticket(ticketPrice, movieType, cinemaClass, age, day, seat);
                    tickets.add(ticket);
                }
            }
            else if (confirm("Are you eligible for senior discount?")) { //senior price
                Enums.AgeType age = Enums.AgeType.SENIOR;
                ticketPrice = priceManager.calculateTicketPrice(age, movieType, cinemaClass, day);
                //for every seat, generate 1 ticket according to the ticket type
                for (Seat seat : selected) {
                    Ticket ticket = new Ticket(ticketPrice, movieType, cinemaClass, age, day, seat);
                    tickets.add(ticket);
                }
            }
            else { //neither student nor senior
                Enums.AgeType age = Enums.AgeType.NORMAL;
                ticketPrice = priceManager.calculateTicketPrice(age, movieType, cinemaClass, day);
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


            //Customer customer = customerController.readByUsername(username);
            String username = read("Enter username: ");
            String password = read("Enter password: ");

            Customer customer = new Customer(username, password);
            //customerController.createCustomer(customer);
            //Create the booking transaction
            Booking booking = new Booking(session.getCinema().getCinemaNo(), tid,
                    customer.getUsername(),customer.getPassword(), movie, tickets, session, totalPrice);
            bookingController.create(booking);
            System.out.println("Total price is S$" + booking.getTotalPrice() + " (Inclusive of GST).");
            if (confirm("Confirm booking? ")) {
                for (Seat seat : selected) {
                    seat.setSelected(false);
                    seat.setTaken(true);
                }
                customer.addBookings(booking);
                println("Booking successful, tid=" + tid);
                movie.addTicketSales(tickets.size());
                customerController.CustomerUpdate(username, booking);
            }
            else {
                for (Seat seat : selected)
                    seat.setSelected(false);
            }
        }while (readIntInput("Press 0 to return to previous menu: ") != 0) ;

        return this.getPreviousMenu();
    }
    /**
     * Print out the layout of the seats in the current slots,
     * including seats avaliable, seats occupied and seats chosen
     * With corridor printed out in the middle of the layout
     */
    private void displaySeats(ArrayList<ArrayList<Seat>> seats, int row, int col)
    {
        Seat seat;
        printHeader("Select Seats");
        for (int i = 0; i < (1 + col) * 3 / 2 - 8; i++)
            print(" ");
        println("|      Screen       |");
        for (int i = 0; i < (1 + col) * 3 / 2 - 8; i++) {
            print(" ");
        }
        println("---------------------");

        print("    ");
        int new_row = 0;
        println(" ");
        boolean flag = false;
        for(int i =0; i<row; i++)
        {
            new_row = 0;
            System.out.print(String.valueOf(i + 1) + " ");
            for(int j=0; j<col; j++)
            {
                if (new_row != col / 2 - 1) {
                    seat = seats.get(i).get(j);
                    if (seat.isTaken()) {
                        System.out.print("[X]");
                    }
                    else if (seat.isSelected()) {
                        System.out.print("[#]");
                    }
                    else
                        System.out.print("[ ]");
                } else {
                    System.out.print("   ");
                    j--;
                }
                new_row++;
            }
            print(" ");
        }

        println("");
        for (int i = 0; i < (1 + col) * 3 / 2 - 5; i++)
            print(" ");
        println("----------");
//        for (int i = 0; i < (1 + col) * 3 / 2 - 5; i++)
//            print(" ");
        println("|Entrance|\n");
        println("([ ] Available  [#] Seat Selected  [X] Sold)");
    }

    /**
     * Method to ask user to select a row and column,
     * check whether the seat is avaliable, and update the information in the data base
     */
    private Seat chooseSeats(ArrayList<ArrayList<Seat>> seats, int row, int col) {
        println("Please choose your seat(s).");
        int i,j;
        do {
            i = readSeatInput("Please input row number",1,row);
            j = readSeatInput("Please input col number",1,col);
            i--;j--;
            if (seats.get(i).get(j).isTaken() || seats.get(i).get(j).isSelected())
                println("Already been taken/selected please choose another seats.");
            else break;
        } while (true);

        seats.get(i).get(j).setSelected(true);
        println("Selected Seat: Row: " + (i+1) + " Col: " + (j+1));

        return seats.get(i).get(j);
    }

    public ArrayList<Session> showAvailableSessions(String cineplexName, Movie movie) {
        Session tempSession;
        Cinema tempCinema;
        ArrayList<Session> sessionList = new ArrayList<>();
        int count = 1;
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
                System.out.println(count + ".	Date: " + tempSession.getDateTime());
                System.out.println();
                printSeparator = true;
                count++;
                sessionList.add(tempSession);
            }
            if(printSeparator){
                System.out.println("------------------------------------------------------");
                printSeparator = false;
            }
        }
        return sessionList;
    }
}

