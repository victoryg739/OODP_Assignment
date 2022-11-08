package view.Customer;

import controller.BookingController;
import model.Booking;
import model.Ticket;
import view.MenuBase;

import java.util.ArrayList;

import static view.utilF.*;


public class MenuBookingHistory extends MenuBase {
    private String username;

    public MenuBookingHistory(MenuBase initialMenu,String username) {
        super(initialMenu);
        this.username = username;
    }
    /**
     * Display user booking history menu
     * Display all user booking detail
     * Return to previous menu when done
     * @return to previous menu
     */

    public MenuBase execute() {
        printHeader("Booking History:");
        BookingController bookingController = new BookingController();
        int count = 1;
        int seatCount = 1;

        ArrayList<Booking> booking = bookingController.readbyUsername(username);
        println("In total, " + booking.size() + " bookings found under " + username + ".");

        //for each booking made, display the respective details:
        for (Booking book : booking) {
            print(" ");
            print("Booking " + count + " : \n" +
                    "TID: " + book.getTID() + "\n" +
                    "Show Time: " + book.getSession().getDateTime() + " " + book.getMovie().getRuntime() + "\n" +
                    "Total Price (GST included): S$" + book.getTotalPrice() + "\n" +
                    "Movie: " + book.getMovie().getTitle() + "\n" +
                    "Seats : ");
            count++;
            for (Ticket ticket : book.getTickets()) {
                System.out.print(seatCount + ") Row: " + (ticket.getSeat().getRow() + 1) + " Col: " + (ticket.getSeat().getCol() + 1));
                seatCount++;
                print(" ");
            }
            seatCount = 1;
        }
        while (readIntInput("Press 0 to return to Customer Main Menu: ") != 0) ;
        return new MenuCustomerMain(this);
    }
}

