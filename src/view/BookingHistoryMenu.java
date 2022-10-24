package view;
import Modal.Customer;
import modal.Customer;

import java.util.*;
import static view.utilF.*;

public class BookingHistoryMenu extends MenuBase {


    public BookingHistoryMenu(MenuBase initialMenu) {
        super(initialMenu);
    }

    /**
     Display user booking history menu
     Ask user for login information
     Display all user booking detail
     Return to previous menu when done
     */

    /*
    public MenuBase execute() {
        System.out.println("History");
        System.out.println("Please Login Using Username and Email");

        Customer customer;

        customer = login(); //login

        if (customer != null) { //if login successful
            ArrayList<Booking> booking = customer.getBookings();
            System.out.println("In total "+booking.size() +" bookings found under "+customer.getName()+".");
            int count=1;

            //Once the user login, for each booking made, display the respective details:
            for (Booking book : booking) {
                System.out.println();
                System.out.println("Booking "+count + " :");
                count++;
                System.out.println("TID: " + book.getTID());
                System.out.println("Booking Time: " + book.getTimestamp());
                System.out.println("Show TIme: " + book.getDate() + " " + book.getTime());
                System.out.println("Total Price (GST included): S$" + book.getTotalPrice());
                System.out.println("Cineplex: " + book.getCinema().getCineplex().toString());
                System.out.println("Cinema: " + book.getCinema().getName());
                System.out.println("Movie: " + book.getMovie().getTitle());
                System.out.println("Seats :");
                for(Ticket ticket: book.getTickets()){
                    System.out.println("Row: "+(ticket.getSeat().getRow()+1)+" Col: "+ (ticket.getSeat().getCol()+1));
                }
            }
        }
        while(readIntInput("Press 0 to return to previous menu: ") != 0);
        return this.getPreviousMenu();
    }

}

     */