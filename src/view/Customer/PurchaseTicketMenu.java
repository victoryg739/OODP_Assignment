package view.Customer;

import controller.CineplexController;
import controller.MovieController;
import controller.PriceManager;
import modal.Customer;
import modal.*;
import view.MenuBase;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import static view.utilF.*;

public class PurchaseTicketMenu extends MenuBase {
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

    public PurchaseTicketMenu(MenuBase previousMenu, Movie movie) {
        super(previousMenu);
        this.movie = movie;
        this.cineplexController = new CineplexController();
        this.movieController  = new MovieController();
        this.priceManager = new PriceManager();
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
//        Scanner sc = new Scanner(System.in);
//        ArrayList<String> choices = new ArrayList<>();
//        ArrayList<Cinema> cinemaList = new ArrayList<Cinema>();
//
//        System.out.println("Menu for Purchasing Ticket:");
//        System.out.println("Choose cineplex (0 to return): ");
//        System.out.println();
//
//        //Print the list of Cineplexes
//        ArrayList<Cineplex> cineplexes = CineplexController.read();
//        for(int i = 0; i < cineplexes.size(); i++) {
//            System.out.println((i+1) + ". " + cineplexes.get(i).getLocation());
//        }
//
//        while (cinemaList.size() == 0) {
//            int choice = sc.nextInt();
//            if (choice == 0) {
//                System.out.println("Returning...");
//                return this.getPreviousMenu();
//            }
//            else if (choice < 0 || choice > cineplexes.size())
//                System.out.println("Invalid input! Please try again.");
//            else {
//                cinemaList = showAvailableSessions(cineplexes.get(choice-1).getLocation());
//                if (cinemaList.size() == 0)
//                    System.out.println("No available sessions for this cineplex! Please choose another.");
//            }
//        }
//
//        //.Sessions() is to check for the available sessions for that movie
//        if(movie.getSessions().isEmpty())
//        {
//            System.out.println("Sorry, there is no available session currently.");
//            return this.getPreviousMenu();
//        }
//
//        //For all the available movie sessions with seats
//        for(Session timeslot:movie.getSessions())
//        {
//            choices.add(cinema.getName() + " -- " + timeslot.getFormattedDate() + " " + timeslot.getFormattedTime());
//        }
//
//
//        printMenu(choices, 0);
//        System.out.println("Please Choose a session");
//        int c = sc.nextInt();
//        Session session = movie.getSessions().get(c);
//
//        //get all the available seats for the selected session
//        ArrayList<ArrayList<Seat>> seats = session.getSeats();
//        int col = seats.get(0).size(), row=seats.size();
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
//
//            if (confirm("Are you eligible for student discount?")) { //student price
//                //getTicketType(slot, isstudent, issenior) => determine the type of ticket
//                Constant.TicketType ticketType = priceManager.getTicketType(slot, true, false);
//                //for every seat, generate 1 ticket according to the ticket type
//                for (Seat seat : selected) {
//                    Ticket ticket = new Ticket(seat, slot.getMovieType(), ticketType);
//                    tickets.add(ticket);
//                }
//            }
//            else if (confirm("Are you eligible for senior discount?")) { //senior price
//                //getTicketType(slot, isstudent, issenior) => determine the type of ticket
//                Constant.TicketType ticketType = priceManager.getTicketType(slot, false, true);
//                //for every seat, generate 1 ticket according to the ticket type
//                for (Seat seat : selected) {
//                    Ticket ticket = new Ticket(seat, slot.getMovieType(), ticketType);
//                    tickets.add(ticket);
//                }
//            }
//            else { //neither student nor senior
//                Constant.TicketType ticketType = priceManager.getTicketType(slot, false, false);
//                //for every seat, generate 1 ticket according to the ticket type
//                for (Seat seat : selected) {
//                    Ticket ticket = new Ticket(seat, slot.getMovieType(), ticketType);
//                    tickets.add(ticket);
//                }
//            }
//
//
//
//            //Get the first ticket to calculate the totalprice
//            Ticket ticket = tickets.get(0);
//            /*
//            Calculate the price of 1 ticket with the various factors: TicketType, MovieType, Platinum, Sneakpeek/FirstWeek/Blockbuster, ...
//            After that, multiply by the # of seats
//            */
//            double totalprice = priceManager.getPrice();
//
//            //Get the current Date/time to generate the tid according to the format XXXYYYYMMDDhhmm
//            Date currentTime = Calendar.getInstance().getTime();
//            String timeStamp = new SimpleDateFormat("YYYYMMDDhhmm").format(currentTime);
//            String tid = cinema.getCinemaNo() + timeStamp;
//            //Generate the Booking (String cinemaNo, String customerId, String email, String phoneNumber, Movie movie,
//            //                    ArrayList<Ticket> ticket, Session session, double totalPrice)
//
//
//            //Log into the account if have
//            Customer customer; //Need to create User class
//            if (confirm("Do you have an account")) {
//                customer = login();
//            }
//            else {
//                String name = read("New Name: ");
//                String email = read("New Email: ");
//                String phoneNumber = read("New phone number: ");
//
//                customer = new Customer(name, email, phoneNumber);
//                //manager.add(Constant.Tables.USER, customer);
//            }
//            //Create the booking transaction
//            Booking booking = new Booking(cinema.getCinemaNo(), customer.getId(), customer.getEmail(),
//                    customer.getPhoneNumber(), movie, tickets, session, totalprice);
//
//            System.out.println("Total price is S$" + booking.getTotalPrice() + " (Inclusive of GST).");
//            if (confirm("Confirm booking? ")) {
//                for (Seat seat : selected) {
//                    seat.setSelected(false);
//                }
//                seat.setTaken(true);
//            }
//            customer.addBookings(booking);
//            booking.setTID(tid);
//            //Add the booking into the database
//            //manager.add(Constant.Tables.BOOKING, booking);
//
//            //movie.addTicketSales(tickets.size());
//            //for (Ticket ticket1 : tickets) manager.add(Constant.Tables.TICKET, ticket1);
//            System.out.println("Booking successful, tid=" + booking.getTID());
//
//        }
//        else {
//            for (Seat seat : selected)
//                seat.setSelected(false);
//        }
//        while (readIntInput("Press 0 to return to previous menu: ") != 0) ;
//
        return this.getPreviousMenu();
    }
//    /*
//     Print out the layout of the seats in the current slots,
//     including seats available, seats occupied and seats chosen
//     With corridor printed out in the middle of the layout
//     */
//
//    private void displaySeats(ArrayList<ArrayList<Seat>> seats, int row, int col)
//    {
//        Seat seat;
//        System.out.println(" Select Seats");
//        for (int i = 0; i < (1 + col) * 3 / 2 - 8; i++)
//            System.out.println(" ");
//        System.out.println("|      Screen       |");
//        for (int i = 0; i < (1 + col) * 3 / 2 - 8; i++) {
//            System.out.println(" ");
//        }
//        System.out.println("---------------------");
//
//        System.out.println("    ");
//        int new_row = 0;
//        for(int i=0; i<col; i++){
//            if (new_row != col / 2 - 1) {
//                System.out.println(i + 1);
//            } else {
//                System.out.println("  ");
//                i--;
//            }
//            new_row++;
//        }
//
//        System.out.println("  ");
//        boolean flag = false;
//        for(int i =0; i<row; i++)
//        {
//            new_row = 0;
//            System.out.println(i + 1);
//            for(int j=0; j<col; j++)
//            {
//                if (new_row != col / 2 - 1) {
//                    seat = seats.get(i).get(j);
//                    //Seat not available
//                    if (seat.isTaken()) {
//                        System.out.println("[X]");
//                    }
//                    //Seat selected
//                    else if (seat.isSelected()) {
//                        System.out.println("[#]");
//                    }
//                    //Seats available
//                    else
//                        System.out.println("[ ]");
//                }
//                else {
//                    System.out.println("   ");
//                    j--;
//                }
//                new_row++;
//            }
//            System.out.println(" ");
//            System.out.println(i + 1);
//            System.out.println(" ");
//        }
//
//        System.out.println(" ");
//        for (int i = 0; i < (1 + col) * 3 / 2 - 5; i++)
//            System.out.println(" ");
//        System.out.println("----------");
//        for (int i = 0; i < (1 + col) * 3 / 2 - 5; i++)
//            System.out.println(" ");
//        System.out.println("|Entrance|\n");
//        System.out.println("([ ] Available  [#] Seat Selected  [X] Sold)");
//    }
//
//
//
//// Method to ask user to select a row and column,check whether the seat is available, and update the information in the data base
//
//    private Seat chooseSeats(ArrayList<ArrayList<Seat>> seats, int row, int col) {
//        System.out.println("Please choose your seat(s).");
//        int i,j;
//        do {
//            i = readSeatInput("Please input row number",1,row);
//            j = readSeatInput("Please input col number",1,col);
//            i--;j--;
//            if (seats.get(i).get(j).isTaken())
//                System.out.println("Already been taken/selected please choose another seats.");
//            else break;
//        } while (true);
//
//        seats.get(i).get(j).setTaken(true);
//        System.out.println("Selected Seat: Row: " + (i+1) + " Col: " + (j+1));
//
//        return seats.get(i).get(j);
//    }
//
//    public ArrayList<Cinema> showAvailableSessions(String cineplexName) {
//        Session tempSession;
//        Cinema tempCinema;
//        boolean printedCinemaCode =  false;
//        boolean printSeparator = false;
//        ArrayList<Cinema> tempCinemaList = new ArrayList<Cinema>();
//        ArrayList<Cinema> cinemaList = cineplexController.readByAttribute(cineplexName);
//        System.out.println("------------------------------------------------------");
//        for (int i = 0; i < cinemaList.size(); i++) {
//            printedCinemaCode = false;
//            tempCinema = cinemaList.get(i);
//            for(int j = 0; j < tempCinema.getSessions().size(); j++) {
//                tempSession = tempCinema.getSessions().get(j);
//                if (tempSession.getMovie().getTitle().equals(movie)) {
//                    if (!printedCinemaCode) {
//                        System.out.println("Cinema code: " + tempCinema.getCinemaNo() + "		Cinema type: " + tempCinema.getClassCinema());
//                        System.out.println();
//                        System.out.println("Available screening times of " + movie + " in this cinema:");
//                        System.out.println();
//                    }
//                    printedCinemaCode = true;
//                    System.out.println("	Date: " + tempSession.getSessionDateTimeToString());
//                    System.out.println();
//                    tempCinemaList.add(tempCinema);
//                    printSeparator = true;
//                }
//            }
//            if(printSeparator){
//                System.out.println("------------------------------------------------------");
//                printSeparator = false;
//            }
//        }
//        return tempCinemaList;
//    }

}
