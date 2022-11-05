package view.Customer;

import controller.BookingController;
import controller.CustomerController;
import modal.*;
import view.MenuBase;

import java.util.*;
import static view.utilF.*;

/* ToDO list:
    1. Present the date and time for each booking transaction
    Need to check with Victor about his datetimeFormat
*/

public class MenuBookingHistory extends MenuBase {


    public MenuBookingHistory(MenuBase initialMenu) {
        super(initialMenu);
    }

    /*
     Display user booking history menu
     Ask user for login information
     Display all user booking detail
     Return to previous menu when done
     */
    public MenuBase execute() {
        System.out.println("Booking History:");
        System.out.println("Please Login Using Username and Email");


        CustomerController customerController = new CustomerController();
        BookingController bookingController = new BookingController();
        String username = read("Enter username: ");
        String password = read("Enter password: ");
        Customer customer = customerController.readByUsername(username);
        if (!customer.getPassword().equals(password)) { //wrong password
            customer = null;
        }

        //Customer customer = null;
//
        //Customer customer = customerController.login(); //login

        if (customer != null) { //if login successful
            ArrayList<Booking> booking = bookingController.readByUsername(username);
            System.out.println("In total, "+booking.size() +" bookings found under "+customer.getUsername()+".");
            int count=1;

            //Once the user login, for each booking made, display the respective details:
            for (Booking book : booking) {
                System.out.println();
                System.out.println("Booking "+count + " :");
                count++;
                System.out.println("TID: " + book.getTID());
                System.out.println("Show Time: "  + book.getSession().getDateTime() + " " + book.getMovie().getRuntime());
                System.out.println("Total Price (GST included): S$" + book.getTotalPrice());
                System.out.println("Movie: " + book.getMovie().getTitle());
                System.out.println("Seats :");
                for(Ticket ticket: book.getTicket()){
                    System.out.println("Row: "+(ticket.getSeat().getRow()+1)+" Col: "+ (ticket.getSeat().getCol()+1));
                }
            }
        }
        while(readIntInput("Press 0 to return to previous menu: ") != 0);
        return this.getPreviousMenu();
    }

}

