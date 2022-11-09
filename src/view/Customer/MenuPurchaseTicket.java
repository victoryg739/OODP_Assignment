package view.Customer;

import controller.*;
import model.*;
import view.MenuBase;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    private String username;

    public MenuPurchaseTicket(MenuBase previousMenu, Movie movie, String username) {
        super(previousMenu);
        this.movie = movie;
        this.cineplexController = new CineplexController();
        this.movieController = new MovieController();
        this.priceManager = new PriceManager();
        this.customerController = new CustomerController();
        this.sessionController = new SessionController();
        this.cinemaController = new CinemaController();
        this.bookingController = new BookingController();
        this.username = username;
    }

    /**
     * List all the sessions available according to the movie inputted previously
     * Ask user to select sessions and seats.
     * Check for Holiday, PREVIEW Showing Status and PLATINUM cinemaClass
     * Ask user information whether they are student or senior
     * Provide the total ticket price to the user
     * Once booking is confirmed, send a confirmation email according to the Customer's email
     *
     * @return MenuCustomerMain
     */
    public MenuBase execute() throws IOException, AddressException, MessagingException {

        printHeader("Menu for Purchasing Ticket:");
        cineplexController.printByMovieId(movie.getId());
        Customer customer = customerController.readByUsername(username);
        String phoneNumber = customer.getPhoneNumber();
        Session session = null;
        int choice = readIntInput("Please Choose a session by SessionId (0 to return): ");
        if (choice == 0) return new MenuListMovie(this);
        else if (choice < 0) print("Invalid input! Please try again.");
        else session = sessionController.readById(choice);

        System.out.println(session.getSessionId());
        System.out.println(session.getSeats().get(0).size());
        //get all the available seats for the selected session
        ArrayList<ArrayList<Seat>> seatList = session.getSeats();
        int col = seatList.get(0).size(), row = seatList.size();

        String cineplexLocation = cineplexController.returnLocationBySessionId(session.getSessionId());

        ArrayList<Seat> selected = new ArrayList<Seat>();
        boolean flag = true;
        while (flag) {
            printHeader("Select Seats");
            sessionController.displaySeats(seatList, row, col);
            ArrayList<Seat> selectedSeat = sessionController.chooseSeats(seatList, row, col);
            for (int i = 0; i < selectedSeat.size(); i++) {
                selected.add(selectedSeat.get(i));
            }
            flag = confirm("Continue to select seats?");
        }
        println("This is your finalized seats");
        sessionController.displaySeats(seatList, row, col);
        if (selected.size() != 0) {
            ArrayList<Ticket> tickets = new ArrayList<Ticket>();
            //Function to determine the day n time
            Enums.Day day = session.getDay();
            Enums.AgeType age = Enums.AgeType.NORMAL;
            Enums.MovieType movieType = movie.getType();
            Enums.ClassCinema cinemaClass = session.getCinema().getClassCinema();
            boolean loyaltyCard = false;
            boolean holiday;
            double ticketPrice;
            //take Session datetime convert to this format
            Date sessionDateTime = session.getDateTime();
            DateFormat outputFormat = new SimpleDateFormat("dd MM yyyy");
            HolidayController h = new HolidayController();
            String formattedDate = outputFormat.format(sessionDateTime);
            Date finalDate;
            try {
                finalDate = outputFormat.parse(formattedDate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            holiday = h.isHoliday(finalDate);
            //IF sessionDate is during a holiday
            if (holiday) {
                ticketPrice = priceManager.calculateTicketPrice(age, movieType, cinemaClass, day, movie.getShowingStatus(), loyaltyCard, holiday);
            }
            //IF movie is sneak preview
            else if (movie.getShowingStatus() == Enums.ShowingStatus.PREVIEW) {
                ticketPrice = priceManager.calculateTicketPrice(age, movieType, cinemaClass, day, movie.getShowingStatus(), loyaltyCard, holiday);
            }
            //IF cinemaClass == PLATINUM
            else if (cinemaClass == Enums.ClassCinema.PLATINUM) {
                ticketPrice = priceManager.calculateTicketPrice(age, movieType, cinemaClass, day, movie.getShowingStatus(), loyaltyCard, holiday);
            }
            //Check for Student Price
            else if (confirm("Are you eligible for student discount?")) {
                age = Enums.AgeType.STUDENT;
                ticketPrice = priceManager.calculateTicketPrice(age, movieType, cinemaClass, day, movie.getShowingStatus(), loyaltyCard, holiday);
            }
            //Check for Senior Price
            else if (confirm("Are you eligible for senior discount?")) {
                age = Enums.AgeType.SENIOR;
                ticketPrice = priceManager.calculateTicketPrice(age, movieType, cinemaClass, day, movie.getShowingStatus(), loyaltyCard, holiday);
            }
            //Check for Loyalty Card
            else if (confirm("Do you have loyalty card?")) {
                loyaltyCard = true;
                ticketPrice = priceManager.calculateTicketPrice(age, movieType, cinemaClass, day, movie.getShowingStatus(), loyaltyCard, holiday);
            } else { //Normal Price
                age = Enums.AgeType.NORMAL;
                ticketPrice = priceManager.calculateTicketPrice(age, movieType, cinemaClass, day, movie.getShowingStatus(), loyaltyCard, holiday);
            }

            //Check for Blockbuster ==> +1 to ticketPrice
            ticketPrice = priceManager.isBlockbuster(movieType, ticketPrice);
            //for every seat, generate 1 ticket according to the ticket type
            for (Seat seat : selected) {
                Ticket ticket = new Ticket(ticketPrice, movieType, cinemaClass, age, day, seat);
                tickets.add(ticket);
            }
            /*
            Calculate the price of 1 ticket with the various factors: TicketType, MovieType, Platinum, Sneakpeek/FirstWeek/Blockbuster, ...
            After that, multiply by the # of seats
            */
            double totalPrice = ticketPrice * tickets.size();

            //Get the current Date/time to generate the tid according to the format XXXYYYYMMDDhhmm
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
            Date date = new Date();
            String tid = session.getCinema().getCinemaNo() + formatter.format(date);

            println("Total price is S$" + totalPrice + " (Inclusive of GST).");
            if (confirm("Confirm booking? ")) {
                //Create the booking transaction
                Booking booking = new Booking(session.getCinema().getCinemaNo(), cineplexLocation, tid,
                        username, phoneNumber, movie, tickets, session, totalPrice);
                println("Booking successful");
                booking.printBookingSummary();
                movieController.updateMovie(11, movie.getId(), tickets.size());
                bookingController.create(booking);
                for (Seat seat : selected) {
                    seat.setSelected(false);
                    seat.setTaken(true);
                }
                customer.addBookings(booking);
                customerController.CustomerUpdate(username, booking);
                sessionController.updateSeat(session.getSessionId(), selected, movie, session);
                EmailController mail = new EmailController();
                mail.setupServerProperties();
                mail.draftEmail(booking);
                mail.sendEmail();
            }
            //IF cancel booking transaction
            else {
                for (Seat seat : selected)
                    seat.setSelected(false);
            }
        }
        while (readIntInput("Press 0 to return to Main Menu: ") != 0) ;

        return new MenuCustomerMain(this);
    }
}


